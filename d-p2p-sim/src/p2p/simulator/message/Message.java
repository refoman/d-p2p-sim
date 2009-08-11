/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.message;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import p2p.simulator.network.Network;

/**
 * The class Message creates and manages the messages that are being delivered among peers.
 * 
 */
public class Message implements Serializable {

    static final long serialVersionUID = 892420644258946184L;
    
    private long sourceId;
    private long destinationId;
    private MessageBody data;
    
    private static long msgCounter = 0;
    private long msgId;
    private int hops;
    
    public int method;
    
    /**
     * Creates a new message.
     * 
     * @param src The peer's id that sends the message.
     * @param dest The peer's id of the message's destination. 
     * @param data The message's data.
     */
    public Message(long src, long dest, MessageBody data){
        this.sourceId       = src;
        this.destinationId  = dest;
        this.data           = data;
//        try {
//            this.data = (MessageBody) data.clone();
//        } catch (CloneNotSupportedException ex) {
//            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        this.msgId  = 0;
        this.hops   = 0;
        
        this.method = Network.POINT_TO_POINT;
        this.msgId  = msgCounter;
        msgCounter++;
    }
    
    /**
     * Returns the peer id of the transmitter.
     * 
     * @return The peer's id.
     */
    public long getSourceId() {
        return this.sourceId;
    }
    
    /**
     * Sets the peer id of the transmitter.
     * 
     * @param id The peer's id.
     */
    public void setSourceId(long id) {
        this.sourceId = id;
    }
    /**
     * Sets the peer id of the receiver.
     * @param id
     */
    public void setDestinationId(long id) {
        this.destinationId = id;
    }
    
    /**
     * Returns the peer id of the receiver.
     * 
     * @return The peer id.
     */
    public long getDestinationId() {
        return this.destinationId;
    }
    
    /**
     * Returns the message's data type. The data types declared at MessageT class.
     * 
     * @return The message's data type.
     */
    public int getType() {
        return data.getType();
    }
    /**
     * Returns the unique message's identifier.
     *  
     * @return The message's id.
     */
    public long getMsgId() {
        return this.msgId;
    }
    
    /**
     * Returns the number of times this message has been forwarded.
     * 
     * @return The number of hops.
     */
    public int getHops() {
        return this.hops;
    }
    /**
     * Increases the number of hops by one. This method is used exclusively by the 
     * simulator and should not be invoced by the user.
     */
    public void incHops() {
        this.hops++;
    }
    
    /**
     * Sets the message's transmittion method. The supported methods are point to point, 
     * multicast and broadcast.
     * 
     * @param meth The transmittion method.
     */
    public void setMethod(int meth) {
        this.method = meth;
    }
    /**
     * Returns the transmittion method of the message.
     * 
     * @return The transmittion method.
     */
    public int getMethod() {
        return this.method;
    }
    /**
     * Returns the message's data. The data object implements the MessageBody interface.
     * 
     * @return The message's data.
     */
    public MessageBody getData() {
        return this.data;
    }
    
    /**
     * Sets the message data. The data object implements the MessageBody interface.
     * 
     * @param data The messages data.
     */
    public void setData(MessageBody data) {
        this.data = data;
    }
    /**
     * Creates a string with the following message's fields: transmitter's id, receiver's id, type of message,
     * number of hops and their respective values.
     *  
     * @return The message's string represantation. 
     */
    @Override
    public String toString() {
        return new String("msg "+msgId+": from: "+sourceId+" to: "+destinationId+" type: "+MessageT.toString(getType())+" hops: "+hops);
    }
}
