/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.network;

import java.rmi.Remote;
import java.rmi.RemoteException;
import p2p.simulator.message.Message;

/**
 *
 * @author gp
 */
public interface NetworkPipe extends Remote {

    public void pushMsg(Message msg) throws RemoteException;
}
