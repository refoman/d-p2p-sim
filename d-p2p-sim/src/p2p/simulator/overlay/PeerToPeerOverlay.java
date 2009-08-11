/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.overlay;

import java.util.logging.Level;
import p2p.simulator.network.Network;
import p2p.simulator.protocol.Peer;
import java.util.Vector;
import java.util.logging.Logger;
import p2p.simulator.utils.AppNode;
import p2p.simulator.utils.Cluster;

/**
 *
 * @author gp
 */

public class PeerToPeerOverlay implements Overlay {

    private Vector<Peer> Peers;
    private Cluster clusterMgr;
    private long N;
    private long currentPeers;
    private long K;
    private long minPeerId;
    private long maxPeerId;
    private long nPeers;
    private Network Net;
    private Logger logger;
    
    public PeerToPeerOverlay(Network Net, long nofPeers, long nofKeys) {
        
        this.N      = nofPeers;
        this.K      = nofKeys;
        Peers       = new Vector<Peer>((int)nofPeers);
        this.Net    = Net;
        this.currentPeers = 1;
    }
    
    public PeerToPeerOverlay(Network Net, long nofPeers, long nofKeys, Cluster clusterMgr) {
        
        int appNodeId;
        AppNode appNode;
        
        this.clusterMgr = clusterMgr;
        appNodeId       = clusterMgr.getAppNodeId();
        appNode         = clusterMgr.getAppNode(appNodeId);
        this.nPeers     = appNode.getRange();
        this.minPeerId  = appNode.getMinPeerId();
        this.maxPeerId  = appNode.getMaxPeerId();
        this.N          = nofPeers;
        this.K          = nofKeys;
        Peers           = new Vector<Peer>((int)nPeers);
        this.Net        = Net;
        this.currentPeers = 1;
    }
    
    public void lookup(long peerId, long key) {
        Peer p;
        int index;
        
        index = (int)(peerId - minPeerId);
        p = Peers.elementAt(index);
        p.lookup(key);
        logger.log(Level.FINER, "PeerId "+peerId+" is requesting key "+key);
    }

    public void insert(long peerId, long key) {
        Peer p;
        int index;
        
        index = (int)(peerId - minPeerId);
        p = Peers.elementAt(index);
        p.insert(key);
        logger.log(Level.FINER, "PeerId "+peerId+" is inserting key "+key);
    }

    public void delete(long peerId, long key) {
        Peer p;
        int index;
        
        index = (int)(peerId - minPeerId);
        p = Peers.elementAt(index);
        p.delete(key);
    }

    public void joinPeer(long peerId) {
        Peer p;
        int index;
        
        index = (int)(peerId - minPeerId);
        p = Peers.elementAt(index);
        p.joinPeer();
    }

    public void leavePeer(long peerId) {
        Peer p;
        int index;
        
        index = (int)(peerId - minPeerId);
        p = Peers.elementAt(index);
        p.leavePeer();
    }

    public void create(Peer peerPrototype) {
        
        int id, appNodeId;
        Peer p = null;
        //ConsoleProgressBar cpb = new ConsoleProgressBar();
         
        // Barrier
        appNodeId = clusterMgr.getAppNodeId();
        for (id = 0; id < nPeers; id++) {

            try {
                p = (Peer) peerPrototype.clone();
            } catch (CloneNotSupportedException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
            p.init(id + minPeerId, N, K, Net);
            p.registerLogger(logger);
            Peers.add(p);
            if (id != 0 || appNodeId != 1) {
                p.joinPeer();
                waitingPeer(p);
                currentPeers++;
            }
            //cpb.setProgress(id, (int)N);
        }
        
        // Stabilization phase?
    }

    private void waitingPeer(Peer p) {
            
        synchronized(this) {
            while (!p.isOnline())
                try {
                    //System.out.println(".-");
                    wait(5);
                    synchronized(Net) {
                        Net.notifyAll();
                    }
                } catch (InterruptedException e) {

                }
        }
    }
    
    public void reset() {
        
        int i;
        Peer p;
        
        for (i = 0; i < nPeers; i++) {
            p = Peers.elementAt(i);
            p = null;
        }
        Peers.clear();
        Net.getNetQueue().clear();
        
        this.N = 0;
        this.K = 0;
        this.currentPeers = 0;
    }

    public Vector<Peer> getOverlayPeers() {
        return this.Peers;
    }

    public void registerLogger(Logger logger) {
        this.logger = logger;
    }
    
    public long getNofPeers() {
        return this.currentPeers;
    }
    
    public long getNofKeys() {
        return this.K;
    }
}
