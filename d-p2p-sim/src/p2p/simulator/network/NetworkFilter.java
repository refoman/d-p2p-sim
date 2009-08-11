/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.network;

import p2p.simulator.message.DeleteResponse;
import p2p.simulator.message.InsertResponse;
import p2p.simulator.message.LookupResponse;
import p2p.simulator.message.MessageT;
import p2p.simulator.message.Message;
import p2p.simulator.overlay.OverlayMonitor;

/**
 *
 * @author viennas
 */
public class NetworkFilter {

    private static String logMsg = new String();
    private static boolean loggerEnabled = false;

    static void filter(Message msg, OverlayMonitor ovMonitor) {

        LookupResponse data;
        int mType = msg.getType();
        
        // Keep statistics for the lookup path length
        if (mType == MessageT.LOOKUP_RES) {
            data = (LookupResponse)msg.getData();
            ovMonitor.addLookupHops(data.getHops());
            if (loggerEnabled) {
                logMsg += msg.toString()+"\n";
                logMsg += data.toString()+"\n";
            }
        }
        else if (mType == MessageT.INSERT_RES) {
            data = (InsertResponse)msg.getData();
            ovMonitor.addInsertHops(data.getHops());
            if (loggerEnabled) {
                logMsg += msg.toString()+"\n";
                logMsg += data.toString()+"\n";
            }
        }
        else if (mType == MessageT.DELETE_RES) {
            data = (DeleteResponse)msg.getData();
            ovMonitor.addDeleteHops(data.getHops());
            if (loggerEnabled) {
                logMsg += msg.toString()+"\n";
                logMsg += data.toString()+"\n";
            }
        }
        // Keep statistics for the number of messages that
        // are been delivered to each peer.
        else if (mType == MessageT.LOOKUP_REQ || mType == MessageT.INSERT_REQ || mType == MessageT.DELETE_REQ) {
            ovMonitor.addMsgToPeer(msg.getDestinationId());
            if (loggerEnabled)
                logMsg += msg.toString()+"\n";
        }
    }

    static void handler(Message msg) {

    }
    
    //used by gui
    public static void setLogMsg(String str) {
        logMsg = str;
    }
    
    //used by gui
    public static String getLogMsg() {
        return logMsg;
    }
    
    //used by gui
    public static void enableLogger(boolean b) {
        loggerEnabled = b;
    }
    
}
