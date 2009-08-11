package p2p.simulator.protocol;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;
import p2p.simulator.network.Network;

/**
 * The class Peer should be implemented by all user defined protocols. This class defines
 * the basic peer-to-peer operations and the basic management methods.
 * 
 */
public abstract class Peer implements Runnable, Cloneable {
    
    private Thread.State state; 
    private ReentrantLock threadLock = new ReentrantLock();
    
    /**
     * Initializes a peer.
     * 
     * @param id The id of the peer.
     * @param N The total number of overlay peers.
     * @param K The key space of the overlay.
     * @param Net A Network instance.
     */
    public abstract void init(long id, long N, long K, Network Net);

    /**
     * Overides the method run of the Runnable interface.
     */
    public abstract void run();
    
    /**
     * Returns the peer's identifier.
     * 
     * @return The peer's id.
     */
    public abstract long getPeerId();
    
    /**
     * Returns the number of keys stored in the peer.
     * 
     * @return The current number of keys.
     */
    public abstract int getNumOfKeys();
    
    /**
     * Returns true if the peer is ready to service any request, false otherwise.
     * 
     * @return True if the peer is ready, false otherwise.
     */
    public abstract boolean isOnline();
    
    /**
     * Sets the peer's state regarding its thread state. The simulator before invoced
     * the peer's run method, sets the state to RUNNABLE. The implementation of the 
     * peer's <code>run</code> method should set its state to TERMINATED before exit.
     * 
     * @param state The peer's state.
     */
    public void setState(Thread.State state) {
        this.state = state;
    }
    
    /**
     * Returns the peer's state regarding its thread state.
     * 
     * @return The peer's state.
     */
    public Thread.State getState() {
        return this.state;
    }
    
    /**
     * 
     */
    public void lock() {
        this.threadLock.lock();
    }
    
    /**
     * 
     */
    public void unlock() {
        this.threadLock.unlock();
    }
    
    /**
     * This method is called by the simulator in order to connect the peer to the overlay. 
     */
    public abstract void joinPeer();
    
    /**
     * This method is called by the simulator in order to remove the peer from the overlay.
     */
    public abstract void leavePeer();
    
    /**
     * This method is called by the simulator in order the peer to perform a key lookup.
     * 
     * @param key The key is being searched.
     */
    public abstract void lookup(long key);
    
    /**
     * This method is called by the simulator in order the peer to perform a key insertion.
     * 
     * @param key The key is being inserted.
     */
    public abstract void insert(long key);
    
    /**
     * This method is called by the simulator in order the peer to perform a key deletion.
     * 
     * @param key The key is being deleted.
     */
    public abstract void delete(long key);

    /**
     * Attaches the simulator's logger.
     *  
     * @param logger The simulator's logger.
     */
    public abstract void registerLogger(Logger logger);
    
    /**
     * Returns the size of the peer's routing table.
     * 
     * @return The size of the routing table.
     */
    public abstract int getRTSize();
    
    /**
     * Returns the number of pending queries.
     * 
     * @return
     */
    public abstract int getPendingQueries();

    /**
     * Creates and returns an instance of class peer.
     * 
     * @return An instance of class Peer.
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {

        Peer p = (Peer)super.clone();
        
        return p;
    }
}
