/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.overlay;

import p2p.simulator.protocol.Peer;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import p2p.simulator.utils.AppNode;
import p2p.simulator.utils.Cluster;

/**
 *
 * @author gp
 */
public class OverlayMonitor {

    private Vector<Peer> Peers;
    private int[] PeerMessages;
    private HashMap HMLookup;
    private HashMap HMInsert;
    private HashMap HMDelete;
    private HashMap  HMLoad;
    private HashMap HMRouting;
    private Overlay Over;
    private PeerExecutor threadPool;
    private Logger logger;
    
    private long minPeerId;
    
    private int threads;
    
    public OverlayMonitor(int threadPoolSize, Overlay Over, Cluster clusterMgr) {
        int nofPeers, appNodeId;
        AppNode appNode;
        
        this.threads     = threadPoolSize;
        this.Over        = Over;
        this.HMLookup    = new HashMap(20, 0.8f);
        this.HMInsert    = new HashMap(20, 0.8f);
        this.HMDelete    = new HashMap(20, 0.8f);
        this.HMLoad      = new HashMap(50, 0.8f);
        this.HMRouting   = new HashMap(10, 0.8f);
        this.Peers       = Over.getOverlayPeers();
        nofPeers         = Peers.capacity();
        PeerMessages     = new int[nofPeers];
                
        appNodeId        = clusterMgr.getAppNodeId();
        appNode          = clusterMgr.getAppNode(appNodeId);
        this.minPeerId   = appNode.getMinPeerId();
        
        
        long keepAlive = 0;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue();
        ThreadFactory threadFactory = new PeerThreadFactory();
        this.threadPool = new PeerExecutor(threadPoolSize, threadPoolSize, 0, TimeUnit.MILLISECONDS, workQueue, threadFactory);
    }
    
    public boolean notifyPeer(long id) {
        Peer p = null;
        int index;
        
        //if (logger.isLoggable(Level.FINER))
        //    logger.entering(this.getClass().getName(), "notifyPeer", new Long(id));
        
        index = (int)(id - minPeerId);
        try {
            p = Peers.elementAt(index);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Peers capacity "+Peers.capacity()+" index "+index+" id "+id);
        }
        p.lock();
        if (p.getState() == Thread.State.RUNNABLE) {
            p.unlock();
            return false;
        }
        else {
            p.setState(Thread.State.RUNNABLE);
            p.unlock();
        }
            
        
        logger.logp(Level.FINER, this.getClass().getName(), "notifyPeer", "Waiking up peer "+p.getPeerId());
        
        threadPool.execute(p);
        
        return true;
    }
    
    public void addMsgToPeer(long peerId) {
        
        int p;
        
        p = (int)(peerId - minPeerId);
        // Increase the number of messages for the specific peer by one
        PeerMessages[p] +=  1;
    }

    public void addLookupHops(int hops) {
        hashValueTo(hops, HMLookup);
    }
    
    public HashMap getLookupFt() {
        return HMLookup;
    }
   
    public void addInsertHops(int hops) {
        hashValueTo(hops, HMInsert);
    }
    
    public HashMap getInsertFt() {
        return HMInsert;
    }
    
    public void addDeleteHops(int hops) {
        hashValueTo(hops, HMDelete);
    }
    
    public HashMap getDeleteFt() {
        return HMDelete;
    }
    
    public HashMap getLoadFt() {
        
        for (int i = 0; i < PeerMessages.length; i++)
            hashNumberOfMsgs(PeerMessages[i]);
        
        return HMLoad;
    }
    
    public HashMap getRoutingFt() {
        
        Peer p;
        int rtSize;
        
        for (int i = 0; i < Peers.size(); i++) {
            p = Peers.elementAt(i);
            rtSize = p.getRTSize();
            hashValueTo(rtSize, HMRouting);
        }
        
        return HMRouting;
    }
        
    public void hashNumberOfMsgs(int nofMessages) {
        hashValueTo(nofMessages,  HMLoad);
    }
    
    
    private void hashValueTo(int value, HashMap hm) {
        
        Integer n;
        
        n = (Integer)hm.get(""+value);
        if (n == null) 
            hm.put(""+value, 1);
        else
            hm.put(""+value, ++n);
    }

    public int[] getNumOfMessages() {
        return PeerMessages;
    }
    
    public void registerLogger(Logger logger) {
        this.logger = logger;
    }
    
    public Overlay getOverlay() {       
        return this.Over;
    }
    
    public void stop() {
        this.threadPool.shutdownNow();
    }
    
    public int getCurrentPeers() {
        return Peers.size();
    }
    
    public int getNumOfThreads() {
        return this.threads;
    }
}
