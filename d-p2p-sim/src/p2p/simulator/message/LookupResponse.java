/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.message;

/**
 * This class creates a special lookup response message body. This special message
 * is used to extract statistics regarding the protocol's lookup algorithm. 
 * 
 */
public class LookupResponse extends MessageBody {

    int hops;
    long key;
    boolean keyExist;
    
    LookupResponse() {
        
    }
    /**
     * Creates the body message of a lookup response message.
     *  
     * @param key The key of the lookup request.
     * @param exist If the key is found is true, otherwise false.
     * @param msg The initial lookup request message.
     */
    public LookupResponse(long key, boolean exist, Message msg) {
        this.key = key; 
        this.keyExist = exist;
        this.hops = msg.getHops();
    }
    
    /**
     * Returns the number of peers that the lookup request message visited.
     * 
     * @return The number of hops.
     */
    public int getHops() {
        return this.hops;
    }
    
    /**
     * Returns the requested key.
     * 
     * @return The key.
     */
    long getKey() {
        return this.key;
    }
    /**
     * Returns true if the key was found, otherwise false.
     * 
     * @return Returns true if the requested key was found, otherwise false.
     */
    boolean isKeyExist() {
        return keyExist;
    }

    /**
     * Returns the message's type.
     * 
     * @return The type of the message
     */
    @Override
    public int getType() {
        return MessageT.LOOKUP_RES;
    }
    
    public String toString() {
        String str;
        
        str = "Key: "+key+" exist: "+keyExist+" hops: "+hops;
        
        return str;
            
    }
}
