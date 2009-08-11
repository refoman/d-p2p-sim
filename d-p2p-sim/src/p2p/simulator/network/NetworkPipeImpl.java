/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.network;

import java.rmi.RemoteException;
import p2p.simulator.message.Message;

/**
 *
 * @author gp
 */
public class NetworkPipeImpl implements NetworkPipe {

    private Network Net;
    
    public NetworkPipeImpl(Network Net) throws RemoteException {
        this.Net = Net;
    }
    
    public void pushMsg(Message msg) throws RemoteException {
        //System.out.println("SENDING MESSAGE -- "+msg);
        Net.sendRemoteMsg(msg);
        //System.out.println("SENT MESSAGE -- "+msg);
    }
}