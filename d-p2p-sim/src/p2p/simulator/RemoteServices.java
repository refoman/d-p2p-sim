/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import p2p.simulator.config.Configuration;
import p2p.simulator.network.Network;
import p2p.simulator.network.NetworkPipe;
import p2p.simulator.network.NetworkPipeImpl;
import p2p.simulator.overlay.OverlayMonitor;
import p2p.simulator.report.ReportExporter;
import p2p.simulator.report.ReportExporterImpl;
import p2p.simulator.utils.AppNode;
import p2p.simulator.utils.Cluster;
import p2p.simulator.utils.ExecutionBarrier;
import p2p.simulator.utils.ExecutionBarrierImpl;

/**
 *
 * @author gp
 */
public class RemoteServices {

    private Network net;
    private NetworkPipeImpl netPipe;
    private OverlayMonitor ovMonitor;
    private Cluster cluster;
    private ExecutionBarrierImpl threadBarrier;
    private int appNodeId;
    private long peerRange;
    private AppNode appNode;
    private int appNodes;
    private Logger logger = Logger.getLogger("p2pitec.NbdtStarOverlay");
    private Configuration config;
    private Registry registry;
    
    RemoteServices(Configuration config, Network net, OverlayMonitor ovMonitor) {
                
        this.config = config;
        this.net = net;
        this.ovMonitor = ovMonitor;
        this.cluster = config.cluster;
        this.appNodes = cluster.getNofAppNodes();
        this.appNodeId = cluster.getAppNodeId();
        this.appNode = cluster.getAppNode(appNodeId);
        this.peerRange = appNode.getRange(); 
        //System.out.print("Starting RMI registry...");
        if (appNodes > 1) 
            try {
                registry = LocateRegistry.createRegistry(1099);
            } catch (RemoteException ex) {
                Logger.getLogger(RemoteServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        //System.out.println("done");
        
    }
    
    void networkPipeService(boolean start) {

        String nameService;
        NetworkPipe stub;
        //Registry registry;

        if (appNodes > 1) {
            nameService = "NetworkPipe" + appNodeId;
            try {
                //registry = LocateRegistry.getRegistry();
                if (start) {
                    netPipe = new NetworkPipeImpl(net);
                    stub = (NetworkPipe) UnicastRemoteObject.exportObject(netPipe, 0);
                    registry.rebind(nameService, stub);
                } else {
                    registry.unbind(nameService);
                }
            } catch (NotBoundException ex) {
                logger.log(Level.SEVERE, nameService, ex);
            } catch (RemoteException ex) {
                logger.log(Level.SEVERE, nameService, ex);
            }
        }
    }

    void simulatorSyncService(boolean start) {

        String nameService;
        ExecutionBarrier stub;
        //Registry registry;

        if (appNodes > 1) {
            nameService = "SimulatorBarrier" + appNodeId;
            try {
                //registry = LocateRegistry.getRegistry();
                if (start) {
                    threadBarrier = new ExecutionBarrierImpl();
                    stub = (ExecutionBarrier) UnicastRemoteObject.exportObject(threadBarrier, 0);
                    registry.rebind(nameService, stub);
                } else {
                    registry.unbind(nameService);
                }
            } catch (NotBoundException ex) {
                logger.log(Level.SEVERE, nameService, ex);
            } catch (RemoteException ex) {
                logger.log(Level.SEVERE, nameService, ex);
            }
        }
    }

    void simulatorReportService(boolean start) {

        String nameService;
        ReportExporter stub;
        ReportExporterImpl reportExporter;
        //Registry registry;

        if (appNodes > 1) {
            nameService = "ReportExporter" + appNodeId;
            try {
                //registry = LocateRegistry.getRegistry();
                if (start) {
                    reportExporter = new ReportExporterImpl(ovMonitor);
                    stub = (ReportExporter) UnicastRemoteObject.exportObject(reportExporter, 0);
                    registry.rebind(nameService, stub);
                } else {
                    registry.unbind(nameService);
                }
            } catch (NotBoundException ex) {
                logger.log(Level.SEVERE, nameService, ex);
            } catch (RemoteException ex) {
                logger.log(Level.SEVERE, nameService, ex);
            }
        }
    }

    void preInit() {

        if (appNodes > 1 && appNodeId != 1) {

            synchronized (threadBarrier) {
                try {
                    threadBarrier.enable();
                } catch (RemoteException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    void postInit() {

        ExecutionBarrier remoteSimBarrier;

        if (appNodes == 1) {
            return;
        }
        if (appNodeId == appNodes) {
            for (int i = 1; i < appNodes; i++) {
                try {
                    (getSimulatorBarrier(i)).disable();
                } catch (RemoteException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        } else {
            remoteSimBarrier = getSimulatorBarrier(appNodeId + 1);

            try {
                remoteSimBarrier.disable();
            } catch (RemoteException ex) {
                logger.log(Level.SEVERE, null, ex);
            }

            synchronized (threadBarrier) {
                try {
                    threadBarrier.enable();
                } catch (RemoteException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    void postRun() {

        if (appNodes == 1) {
            return;
        }

        if (appNodeId != 1) {

            synchronized (threadBarrier) {
                try {
                    threadBarrier.enable();
                } catch (RemoteException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        } else {
            for (int i = 2; i <= appNodes; i++) {
                try {
                    if (getSimulatorBarrier(i).isEnabled()) {
                    } else {
                        //System.out.println("Waitting Last Barrier(" + i + ")..." + netMonitor.getState());
                        synchronized (this) {
                            wait(500);
                        }
                        i--;
                    }
                } catch (RemoteException ex) {
                    logger.log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
            //System.out.println("Exiting Last Barrier....");
            // Wake up all application Nodes
            for (int i = 2; i <= appNodes; i++) {
                try {
                    getSimulatorBarrier(i).disable();
                } catch (RemoteException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    void preExit() {

        if (appNodes == 1) {
            return;
        }

        if (appNodeId != 1) {

            synchronized (threadBarrier) {
                try {
                    threadBarrier.enable();
                } catch (RemoteException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        } else {
            for (int i = 2; i <= appNodes; i++) {
                try {
                    getSimulatorBarrier(i).disable();
                } catch (RemoteException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private ExecutionBarrier getSimulatorBarrier(int appNodeId) {

        String appNodeIP;
        Registry registry;
        ExecutionBarrier simBarrier = null;
        boolean success = false;
        appNodeIP = cluster.getAppNodeIP(appNodeId);

        try {
            registry = LocateRegistry.getRegistry(appNodeIP);
            simBarrier = (ExecutionBarrier) registry.lookup("SimulatorBarrier" + appNodeId);
            success = true;
        } catch (NotBoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return simBarrier;
    }
    
    public void destroyRegistry() {
       
        if (appNodes > 1) { 
            try {
                UnicastRemoteObject.unexportObject(registry, true);
            } catch (NoSuchObjectException ex) {
                Logger.getLogger(RemoteServices.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(RemoteServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
