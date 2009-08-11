/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p.simulator;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import p2p.simulator.config.Simulation;
import java.rmi.RemoteException;
import p2p.simulator.network.NetworkMonitor;
import p2p.simulator.network.Network;
import p2p.simulator.overlay.OverlayMonitor;
import java.net.URL;
import java.rmi.RMISecurityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import p2p.simulator.config.BatchSimulation;
import p2p.simulator.config.Configuration;
import p2p.simulator.dist.*;
import p2p.simulator.gui.SimGui;
import p2p.simulator.network.NetworkFilter;
import p2p.simulator.overlay.PeerToPeerOverlay;
import p2p.simulator.protocol.Peer;
import p2p.simulator.report.Report;
import p2p.simulator.utils.AppNode;
import p2p.simulator.utils.Cluster;

/**
 *
 * @author gp
 */
public class Simulator {

    private Network net;
    private NetworkMonitor netMonitor;
    private PeerToPeerOverlay p2pOv;
    private OverlayMonitor ovMonitor;
    private Cluster cluster;
    private int threadPoolSize;
    private int appNodeId;
    private Logger logger;
    private Level debugLevel;
    private String logFile;
    private Configuration config;
    private RemoteServices rsvcs;
    private String protocolsDir;
    private String reportsDir;
    private String protocolName;
    private String protocolPkg;
    private long nofPeers;
    private long nofKeys;
    private AppNode appNode;
    private long peerRange;
    private int operations;
    private boolean guiEnabled;

    public Simulator(Configuration config, boolean guiEnabled) {

        this.guiEnabled = guiEnabled;
        this.config = config;
        this.cluster = config.cluster;
        this.appNodeId = config.cluster.getAppNodeId();
        this.debugLevel = Level.parse(config.misc.debugLevel);
        this.logFile = config.misc.logFile;
        this.threadPoolSize = config.misc.threadPoolSize;
        this.protocolsDir = config.misc.protocolsDir;
        this.reportsDir = config.misc.reportsDir;
        this.operations = 0;
        // Create Logger for the NbdtStarOverlay class
        this.logger = simulatorLogger();
        appNode = cluster.getAppNode(appNodeId);
        peerRange = appNode.getRange();
        setDistributionParams();
    }
    
    public void setDistributionParams() {
               
        p2p.simulator.dist.SRandom.seed = config.distribution.random.seed;
        
        p2p.simulator.dist.Uniform.seed = config.distribution.random.seed;
        
        p2p.simulator.dist.Beta.seed = config.distribution.random.seed;
        p2p.simulator.dist.Beta.alpha = config.distribution.beta.alpha;
        p2p.simulator.dist.Beta.beta = config.distribution.beta.beta;
        
        p2p.simulator.dist.Beta.seed = config.distribution.random.seed;
        p2p.simulator.dist.PowLaw.alpha = config.distribution.powerLaw.alpha;
        p2p.simulator.dist.PowLaw.cut = config.distribution.powerLaw.cut;
    }
    
    public void initSimulator(long nofPeers, long nofKeys) throws RemoteException {

        System.out.print("Initializing Simulator...");
        // If gui is enabled
        if (guiEnabled) {
            config.cluster.getAppNode(appNodeId).setMinPeerId(1);
            config.cluster.getAppNode(appNodeId).setMaxPeerId(nofPeers);
        }
        // Create the underlying pysical network
        net = new Network();
        // Create the overlay network
        p2pOv = new PeerToPeerOverlay(net, nofPeers, nofKeys, cluster);
        // Register a logger
        p2pOv.registerLogger(logger);
        // Create a monitor for the Overlay
        ovMonitor = new OverlayMonitor(threadPoolSize, p2pOv, cluster);
        // Register a logger
        ovMonitor.registerLogger(logger);
        // Create a network monitor and attach it to the underlying network
        netMonitor = new NetworkMonitor(net, ovMonitor, cluster);
        // Start monitoring the Network
        netMonitor.start();
        
        rsvcs = new RemoteServices(config, net, ovMonitor);
        // If distributed mode enabled, bind the NetworkPipe service with the RMI Registry
        rsvcs.networkPipeService(true);
        // If distributed mode enabled, initialize simulator's synchronization
        rsvcs.simulatorSyncService(true);
        // If distributed mode enabled, initialize simulator's ReportExporter
        rsvcs.simulatorReportService(true);
        
        this.nofPeers = nofPeers;
        this.nofKeys = nofKeys;
        
        System.out.println("done");
    }

    public void initOverlay(String protocolPackage) {
        
        Peer peerPrototype = null;
        String str = protocolsDir + protocolPackage;
        URL urlProtocol = null;
        
        try {
            urlProtocol = new File(str).toURI().toURL();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.print("Loading Protocol...");
        setProtocolPkg(protocolPackage);
        ProtocolLoader.addURL(urlProtocol);
        peerPrototype = ProtocolLoader.loadProtocol(protocolName);
        System.out.println("done");

        rsvcs.preInit();
        netMonitor.initNetworkPipes(appNodeId);

        System.out.print("Initializing Overlay...");
        p2pOv.create(peerPrototype);
        System.out.println("done");

        rsvcs.postInit();
    }
    
    public void publishReport(String filename) {

        Report report;
        
        rsvcs.postRun();

        if (appNodeId == 1) {
            System.out.print("Publishing Report...");
            report = new Report(cluster, ovMonitor);
            if (filename.contains(File.separator))
                report.publish(filename);
            else
                report.publish(reportsDir + filename);
            System.out.println("done");
        }
    }
    
    public void exitSimulation() {
    
        rsvcs.preExit();

        System.out.print("Stopping Simulation...");
        ovMonitor.stop();
        netMonitor.stopMonitoring();
        p2pOv.reset();
        rsvcs.networkPipeService(false);
        rsvcs.simulatorSyncService(false);
        rsvcs.simulatorReportService(false);
        rsvcs.destroyRegistry();
        this.protocolName = null;
        this.protocolPkg = null;
        this.nofKeys = 0;
        this.nofPeers = 0;
        System.out.println("done");
    }

    public void lookupTest(int qnumber, Distribution.DistributionT qDist) {

        System.out.print("Running Lookup Test...");
                
        List<Integer> sp = Uniform.getUniform(qnumber, (int) nofPeers);
        List<Integer> sk;

        switch (qDist) {
            case UNIF:
                sk = Uniform.getUniform(qnumber, (int) (nofKeys));
                break;
            case BETA:
                sk = Beta.getBeta(qnumber, (int) (nofKeys));
                break;
            case POWL:
                sk = PowLaw.getPowLaw(qnumber, (int) (nofKeys));
                break;
            default:
                sk = Uniform.getUniform(qnumber, (int) (nofKeys));
        }


        Iterator setIteratorP = sp.iterator();
        Iterator setIteratorK = sk.iterator();
        int j = 0;
//    if (appNodeId == 3)
        while (setIteratorK.hasNext()) {
            j++;
            int p = (Integer) setIteratorP.next();
            int k = (Integer) setIteratorK.next();

            if (appNode.owns(p + 1)) {
                operations++;
                p2pOv.lookup(++p, ++k);
            }
        }

        waitPendingQueries();
        System.out.println("done");
    }
    
    public void insertionTest(int qnumber, Distribution.DistributionT qDist) {

        System.out.print("Running Insertion Test...");
        
        List<Integer> sp = Uniform.getUniform(qnumber, (int) nofPeers);
        List<Integer> sk;

        switch (qDist) {
            case UNIF:
                sk = Uniform.getUniform(qnumber, (int) (nofKeys));
                break;
            case BETA:
                sk = Beta.getBeta(qnumber, (int) (nofKeys));
                break;
            case POWL:
                sk = PowLaw.getPowLaw(qnumber, (int) (nofKeys));
                break;
            default:
                sk = Uniform.getUniform(qnumber, (int) (nofKeys));
        }


        Iterator setIteratorP = sp.iterator();
        Iterator setIteratorK = sk.iterator();
        int j = 0;
//    if (appNodeId == 3)
        while (setIteratorK.hasNext()) {
            j++;
            int p = (Integer) setIteratorP.next();
            int k = (Integer) setIteratorK.next();

            if (appNode.owns(p + 1)) {
                operations++;
                p2pOv.insert(++p, ++k);
            }
        }

        waitPendingQueries();
        System.out.println("done");
    }

    public void deletionTest(int qnumber, Distribution.DistributionT qDist) {

        System.out.print("Running Deletion Test...");
        
        List<Integer> sp = Uniform.getUniform(qnumber, (int) nofPeers);
        List<Integer> sk;

        switch (qDist) {
            case UNIF:
                sk = Uniform.getUniform(qnumber, (int) (nofKeys));
                break;
            case BETA:
                sk = Beta.getBeta(qnumber, (int) (nofKeys));
                break;
            case POWL:
                sk = PowLaw.getPowLaw(qnumber, (int) (nofKeys));
                break;
            default:
                sk = Uniform.getUniform(qnumber, (int) (nofKeys));
        }


        Iterator setIteratorP = sp.iterator();
        Iterator setIteratorK = sk.iterator();
        int j = 0;
//    if (appNodeId == 3)
        while (setIteratorK.hasNext()) {
            j++;
            int p = (Integer) setIteratorP.next();
            int k = (Integer) setIteratorK.next();

            if (appNode.owns(p + 1)) {
                operations++;
                p2pOv.delete(++p, ++k);
            }
        }

        waitPendingQueries();
        System.out.println("done");
    }
    
    private void waitPendingQueries() {
        
        // Wait until all the pending queries are finished...
        for (int i = 0; i < peerRange; i++) {
            if (p2pOv.getOverlayPeers().elementAt(i).getPendingQueries() > 0) {

                //System.out.println("Pending queries for peer " + (i + minPeerId));
                try {
                    synchronized (this) {
                        wait(1000);
                    }
                    synchronized (net) {
                        net.notify();
                    }
                } catch (InterruptedException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
                i = 0;
            }
        }
    }

    //used by gui
    public void lookupKey(long peerId, long key) {
        operations++;
        p2pOv.lookup(peerId, key);
        waitPendingQueries();
    }
    
    //used by gui
    public void insertKey(long peerId, long key) {
        operations++;
        p2pOv.insert(peerId, key);
        waitPendingQueries();
    }
    
    //used by gui
    public void deleteKey(long peerId, long key) {
        operations++;
        p2pOv.delete(peerId, key);
        waitPendingQueries();
    }
    
    //used by gui
    public HashMap getLookupFt() {
        return ovMonitor.getLookupFt();
    }
    
    //used by gui
    public HashMap getInsertFt() {
        return ovMonitor.getInsertFt();
    }
    
    //used by gui
    public HashMap getDeleteFt() {
        return ovMonitor.getDeleteFt();
    }
    
    //used by gui
    public HashMap getLoadFt() {
        return ovMonitor.getLoadFt();
    }
    
    //used by gui
    public HashMap getRoutingFt() {
        return ovMonitor.getRoutingFt();
    }
    
    //Used by gui
    public long getNofPeers() {
        
        if (p2pOv != null)
            return p2pOv.getNofPeers();
        return 0;
    }
    
    //Used by gui
    public long getNofKeys() {
        
        if (p2pOv != null)
            return p2pOv.getNofKeys();
        return 0;
    }
    
    //Used by gui
    public long getOperations() {
        return this.operations;
    }

    //used by gui
    public String getProtocolName() {
        return this.protocolName;
    }
    
    //used by gui
    public String getProtocolPkg() {
        return this.protocolPkg;
    }
    
    //used by gui
//    public void setProtocolName(String protocolName) {
//        this.protocolName = protocolName;
//    }
    
    //used by gui
    public void setProtocolPkg(String protocolPkg) {
        
        this.protocolPkg = protocolPkg;
        
        try {
            JarFile jar = new JarFile(protocolsDir+protocolPkg);
            Manifest mf = jar.getManifest();
            Attributes attrs = mf.getMainAttributes();
            this.protocolName = attrs.getValue(Attributes.Name.MAIN_CLASS);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Failed to load "+protocolsDir+protocolPkg+" jar file", ex);
        }
    }
    
    //used by gui
    public String[] getProtocols() {
        
        File protoDir = new File(this.protocolsDir);
        FilenameFilter jarFilter = new FilenameFilter() {

            public boolean accept(File dir, String name) {
                return name.endsWith(".jar");
            }
        };

        return protoDir.list(jarFilter);
    } 
    
    //used by gui
    public void enableGuiLogger(boolean f) {
        NetworkFilter.enableLogger(f);
    }
    
    private Logger simulatorLogger() {

        Logger logger = Logger.getLogger("d-p2p-sim");
        logger.setUseParentHandlers(false);
        logger.setLevel(debugLevel);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(debugLevel);
        logger.addHandler(ch);
        Handler[] handlers = logger.getHandlers();
        for (int index = 0; index < handlers.length; index++) {
            handlers[index].setLevel(debugLevel);
        }

        return logger;
    }

    
    public static void main(String args[]) throws RemoteException {

        String configFile, inputFile;
        Configuration config;
        BatchSimulation batchSim;
        ArrayList<Simulation> simulation = new ArrayList<Simulation>();
        Simulation simu;
        Simulator sim;   
        boolean guiEnabled;
        long minPeerId, maxPeerId;
        int subPercent = 0, myPercent = 0, appNodeId, myAppNodeId, nofAppNodes;
        AppNode appNode;

        guiEnabled = false;
        String protocolPackage = "NbdtStar.jar";
        int overlayPeers = 100;
        int overlayKeys = 8100;
        int lookupQueries = 1000;
        String lookupDistribution = "UNIF";
        int insertQueries = 0;
        String insertDistribution = "BETA";
        int deleteQueries = 0;
        String deleteDistribution = "POWLAW";
        String reportName = "NbdtStar-sim-1";

//        simulation.add(new Simulation(protocolName, protocolPackage, overlayPeers, overlayKeys,
//                        lookupQueries, lookupDistribution, insertQueries, insertDistribution,
//                        deleteQueries, deleteDistribution, reportName) );
//        
//        batchSim = new BatchSimulation(simulation);
//        BatchSimulation.store(batchSim, inputFile);

        configFile = "config.xml";
        if (args.length == 2) {
            if (args[0].matches("--gui")) {
                guiEnabled = true;
                configFile = args[1];
            }
            else {
                System.err.println("d-p2p-sim usage:");
                System.err.println("\td-p2p-sim.jar [--gui] [configuration]");
                System.err.println();
                System.exit(1);
            }
        }
        else if (args.length == 1 && !args[0].matches("--gui")) {
            configFile = args[0];
        }
        else if (args.length == 1 && args[0].matches("--gui")) {
            guiEnabled = true;
        }
        else if (args.length != 0) {
            System.err.println("d-p2p-sim usage:");
            System.err.println("\td-p2p-sim.jar [--gui] [config file]");
            System.err.println();
            System.exit(1);
        }
        
        System.out.print("Reading Configuration...");
        config = Configuration.load(configFile);
        inputFile = config.misc.inputFile;
        
        myAppNodeId = config.cluster.getAppNodeId();
        nofAppNodes = config.cluster.getNofAppNodes();
        
        System.setSecurityManager(new RMISecurityManager());
        
        //Start a new thread for the gui
        if (guiEnabled) {
            System.out.println("done");
            
            sim = new Simulator(config, true);
            NetworkFilter.enableLogger(false);
            new Thread(new SimGui(sim)).start();
//            System.exit(0);
        }
        else {   
            batchSim = BatchSimulation.load(inputFile);
            System.out.println("done");

            for (int ii = 0; ii < batchSim.simulations.size(); ii++) {

                simu = batchSim.simulations.get(ii);

                protocolPackage     = simu.protocolPackage;
                overlayPeers        = simu.overlayPeers;
                overlayKeys         = simu.overlayKeys;
                lookupQueries       = simu.lookupQueries;
                lookupDistribution  = simu.lookupDistribution;
                insertQueries       = simu.insertQueries;
                insertDistribution  = simu.insertDistribution;
                deleteQueries       = simu.deleteQueries;
                deleteDistribution  = simu.deleteDistribution;
                reportName          = simu.reportName;

                for (appNodeId = 1; appNodeId <= nofAppNodes; appNodeId++) {
                    appNode = config.cluster.getAppNode(appNodeId);
                    myPercent = appNode.getPercent();

                    minPeerId = subPercent * overlayPeers / 100 + 1;
                    maxPeerId = (subPercent + myPercent) * overlayPeers / 100;

                    subPercent += appNode.getPercent();

                    config.cluster.getAppNode(appNodeId).setMinPeerId(minPeerId);
                    config.cluster.getAppNode(appNodeId).setMaxPeerId(maxPeerId);
                }

                System.out.println("\nCluster Inforamtion --"+myAppNodeId+"--");
                for (appNodeId = 1; appNodeId <= nofAppNodes; appNodeId++) {
                    appNode = config.cluster.getAppNode(appNodeId);
                    System.out.println("\n\tAppNode");
                    System.out.print("\t\tId        "+appNode.getAppNodeId()+
                                   "\n\t\tPercent   "+appNode.getPercent()+"%"+
                                   "\n\t\tIpAddr    "+appNode.getIP()+
                                   "\n\t\tMinPeerId "+appNode.getMinPeerId()+
                                   "\n\t\tMaxPeerId "+appNode.getMaxPeerId()
                                    );
                }

                System.out.println("\nSimulation --" + ii + "--");
                System.out.println(
                        "\n\tProtocolPackage     " + protocolPackage +
                        "\n\tOverlayPeers        " + overlayPeers +
                        "\n\tOverlayKeys         " + overlayKeys +
                        "\n\tLookupQueries       " + lookupQueries +
                        "\n\tLookupDistribution  " + lookupDistribution +
                        "\n\tInsertQueries       " + insertQueries +
                        "\n\tInsertDistribution  " + insertDistribution +
                        "\n\tDeleteQueries       " + deleteQueries +
                        "\n\tDeleteDistribution  " + deleteDistribution +
                        "\n\tReportName          " + reportName);
                System.out.println();

                sim = new Simulator(config, false);   
                sim.initSimulator(overlayPeers, overlayKeys);
                sim.initOverlay(protocolPackage);
                sim.insertionTest(insertQueries, Distribution.parse(insertDistribution));
                sim.lookupTest(lookupQueries, Distribution.parse(lookupDistribution));
                sim.deletionTest(deleteQueries, Distribution.parse(deleteDistribution));
                sim.publishReport(reportName);
                sim.exitSimulation();

            }
        }
    }
}
