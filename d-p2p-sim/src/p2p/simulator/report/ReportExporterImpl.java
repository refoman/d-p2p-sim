/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.report;

import java.rmi.RemoteException;
import java.util.HashMap;
import p2p.simulator.overlay.OverlayMonitor;

/**
 *
 * @author gp
 */
public class ReportExporterImpl implements ReportExporter {

    private OverlayMonitor ovMonitor;
    
    public ReportExporterImpl(OverlayMonitor ovMonitor) {
        this.ovMonitor = ovMonitor;
    }
    
    public HashMap getLoadFt() throws RemoteException {
        return ovMonitor.getLoadFt();
    }

    public HashMap getLookupPathFt() throws RemoteException {
        return ovMonitor.getLookupFt();
    }

    public HashMap getInsertPathFt() throws RemoteException {
        return ovMonitor.getInsertFt();
    }
    
    public HashMap getDeletePathFt() throws RemoteException {
        return ovMonitor.getDeleteFt();
    }
    
    public HashMap getRoutingFt() throws RemoteException {
        return ovMonitor.getRoutingFt();
    }

}
