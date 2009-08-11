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
public class InsertResponse extends LookupResponse {
   
    /**
     * Creates the body message of an insert response message.
     *  
     * @param key The key of the lookup request.
     * @param exist If the key is found is true, otherwise false.
     * @param msg The initial lookup request message.
     */
    public InsertResponse(long key, boolean exist, Message msg) {
        super(key, exist, msg);
    }
    
    /**
     * Returns the message's type.
     * 
     * @return The type of the message
     */
    @Override
    public int getType() {
        return MessageT.INSERT_RES;
    }
         
    public String toString() {
        String str;
        
        str = "Key: "+key+" inserted: "+keyExist+" hops: "+hops;
        
        return str;
            
    }
}
