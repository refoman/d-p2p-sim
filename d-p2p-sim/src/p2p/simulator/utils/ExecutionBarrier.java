/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.utils;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author gp
 */
public interface ExecutionBarrier extends Remote {

    public void enable() throws RemoteException;
    
    public void disable() throws RemoteException;
    
    public boolean isEnabled() throws RemoteException;
    
}
