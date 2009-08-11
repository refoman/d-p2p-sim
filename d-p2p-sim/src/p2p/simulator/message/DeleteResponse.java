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
public class DeleteResponse extends LookupResponse {

    /**
     * Creates the body message of a lookup response message.
     *  
     * @param key The key of the lookup request.
     * @param exist If the key is found is true, otherwise false.
     * @param msg The initial lookup request message.
     */
    public DeleteResponse(long key, boolean exist, Message msg) {
        super(key, exist, msg);        
    }

    /**
     * Returns the message's type.
     * 
     * @return The type of the message
     */
    @Override
    public int getType() {
        return MessageT.DELETE_RES;
    }
    
    @Override
    public String toString() {
        String str;
        
        str = "Key: "+key+" deleted: "+keyExist+" hops: "+hops;
        
        return str;
            
    }
}
