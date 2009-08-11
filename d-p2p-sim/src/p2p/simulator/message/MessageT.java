/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.message;

import java.io.Serializable;


/**
 * This class declares the types of messages that are used by the simulator to
 * extract performance statistics regarding the protocols under simulation. The 
 * class that extends this one should start numbering from 1000, since the first
 * 1,000 numbers are reserved.
 * 
 */
public class MessageT implements Serializable {

    public final static int LOOKUP_REQ = 0;
    public final static int LOOKUP_RES = 1;
    public final static int INSERT_REQ = 2;
    public final static int INSERT_RES = 3;        
    public final static int DELETE_REQ = 4;
    public final static int DELETE_RES = 5;
    
    public static String toString(int mType) {
        
        String str = "";
        
        switch(mType) {
            case LOOKUP_REQ:
                str = "LOOKUP_REQ";
                break;
            case LOOKUP_RES:
                str = "LOOKUP_RES";
                break;
            case INSERT_REQ:
                str = "INSERT_REQ";
                break;
            case INSERT_RES:
                str = "INSERT_RES";
                break;
            case DELETE_REQ:
                str = "DELETE_REQ";
                break;
            case DELETE_RES:
                str = "DELETE_RES";
                break;
        }
        return str;
    }
}
