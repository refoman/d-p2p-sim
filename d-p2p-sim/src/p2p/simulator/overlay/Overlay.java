/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.overlay;

import p2p.simulator.protocol.Peer;
import java.util.Vector;

/**
 *
 * @author gp
 */
public interface Overlay {
    
    //public void Overlay(Network Net, long nofPeers, long nofKeys, DistributionT dist);
    
    public void lookup(long peerId, long key);
    
    public void insert(long peerId, long key);
    
    public void delete(long peerId, long key);
    
    public void joinPeer(long peerId);
    
    public void leavePeer(long peerId);
    
    public void create(Peer peerPrototype);
    
    public void reset();
    
    public Vector<Peer> getOverlayPeers();
    
    public long getNofPeers();
    
    public long getNofKeys();
}
