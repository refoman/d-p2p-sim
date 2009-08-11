/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.network;

import p2p.simulator.message.Message;
import java.util.Vector;

/**
 * This class simulates the underlying network. A vector is used to store the messages that
 * are sent using the sendMsg method. A peer can retrive a message, if there is any, using
 * the recvMsg and its Id as parameter.
 */
public class Network {

    public static final int POINT_TO_POINT = 0;
    public static final int BROADCAST = 1;
    public static final int MULTICAST = 2;
    
    private Vector<Message> netQueue;
    
    /**
     * Instatiates and initializes the uderlying network.
     */
    public Network() {
        this.netQueue = new Vector<Message>();
    }
    
    /**
     * Sends a message to peer.
     * 
     * @param msg The message is beeing sent.
     */
    public void sendMsg(Message msg) {
        
        synchronized(this) {
            //System.out.println("lock acquiered..."+msg);
            this.netQueue.add(msg);
            msg.incHops();
            notify();
            //System.out.println("lock released..."+msg);
        }
    }
    /**
     * Sends a message to peer.
     * 
     * @param msg The message is beeing sent.
     */
    void sendRemoteMsg(Message msg) {
        
        synchronized(this) {
            //System.out.println("lock acquiered..."+msg);
            this.netQueue.add(msg);
            notify();
            //System.out.println("lock released..."+msg);
        }
    }
    
    /**
     * Receives a message destinate to peer with the specific id.
     * 
     * @param id The peer's id that calls the method. 
     * @return A message if there is a message for the peer id or null otherwise.
     */
    public Message recvMsg(long id) {
        
        int i = 0, index = -1;
        Message msg = null;
        
        synchronized(this) {
            for (i = 0; i < this.netQueue.size(); i++) {
                msg = this.netQueue.elementAt(i);
                if (msg.getDestinationId() == id) {
                    index = i;
                    break;
                }
            }

            if (this.netQueue.isEmpty() || index < 0)
                return null;

            msg = this.netQueue.remove(index);
            notify();
        }
        
        return msg;
    }
    
    /**
     * Should not be used.
     * 
     * @param msg
     */
    public void broadcastMsg(Message msg) {
        msg.setMethod(Network.BROADCAST);
        this.sendMsg(msg);
    }
    
    /**
     * Sends a message to a range of peers.
     * 
     * @param msg The message that is beeing sent.
     * @param begin The first peer id of the range.
     * @param end The last peer id of the range.
     */
    public void multicastMsg(Message msg, long begin, long end) {
        
        long dest = 0;
        Message m = null;
        
        for (dest = begin; dest <= end; dest++) {
            m = new Message(msg.getSourceId(), dest, msg.getData());
            this.sendMsg(m);
        }
    }
    
    /**
     * Returns the queue that stores the messages.
     * 
     * @return The message queue.
     */
    public Vector<Message> getNetQueue() {
        return this.netQueue;
    }
    
}
