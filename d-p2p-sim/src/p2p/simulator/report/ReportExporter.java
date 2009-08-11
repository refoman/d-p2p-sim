/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.report;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 *
 * @author gp
 */
public interface ReportExporter extends Remote {
    
    public HashMap getLoadFt() throws RemoteException;
    
    public HashMap getLookupPathFt() throws RemoteException;
    
    public HashMap getInsertPathFt() throws RemoteException;
    
    public HashMap getDeletePathFt() throws RemoteException;
    
    public HashMap getRoutingFt() throws RemoteException;
}
