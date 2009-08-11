/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.utils;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gp
 */
public class ExecutionBarrierImpl implements ExecutionBarrier {
    
    private boolean isEnabled;
    
    public ExecutionBarrierImpl() throws RemoteException {
        this.isEnabled = false;
    }
    
    public synchronized void enable() throws RemoteException {
        try {
            isEnabled = true;
            this.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(ExecutionBarrierImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void disable() throws RemoteException {
        this.isEnabled = false;
        this.notify();
    }

    public boolean isEnabled() throws RemoteException {
        return isEnabled;
    }
}
