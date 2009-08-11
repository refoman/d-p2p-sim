/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import p2p.simulator.overlay.OverlayMonitor;
import p2p.simulator.utils.AppNode;
import p2p.simulator.utils.Cluster;

/**
 *
 * @author gp
 */
public class Report {

    private HashMap HMLookup;
    private HashMap HMInsert;
    private HashMap HMDelete;
    private HashMap HMLoad;
    private HashMap HMRouting;
    private Cluster clusterMgr;
    
    public Report(Cluster clusterMgr, OverlayMonitor ovMonitor) {
        int appNodes;
        
        this.clusterMgr = clusterMgr;
        appNodes = clusterMgr.getNofAppNodes();
                
        this.HMLookup = ovMonitor.getLookupFt();
        this.HMInsert = ovMonitor.getInsertFt();
        this.HMDelete = ovMonitor.getDeleteFt();
        this.HMLoad = ovMonitor.getLoadFt();
        this.HMRouting = ovMonitor.getRoutingFt();        
        
        if (appNodes > 1) {
            for (int appNodeId = 2; appNodeId <= appNodes; appNodeId++) {
                getSubResultsFrom(appNodeId);
            }
        }   
    }
    
    private void getSubResultsFrom(int appNodeId) {
        
        AppNode appNode;
        String appNodeIP;
        Registry registry;
        ReportExporter reportExporter = null;
        
        appNode = clusterMgr.getAppNode(appNodeId);
        appNodeIP = appNode.getIP();
        
        try {
            registry = LocateRegistry.getRegistry(appNodeIP);
            reportExporter = (ReportExporter) registry.lookup("ReportExporter"+appNodeId);
            
            concatenateFt(HMLoad, reportExporter.getLoadFt());
            concatenateFt(HMLookup, reportExporter.getLookupPathFt());
            concatenateFt(HMInsert, reportExporter.getInsertPathFt());
            concatenateFt(HMDelete, reportExporter.getDeletePathFt());
            concatenateFt(HMRouting, reportExporter.getRoutingFt());
        } catch (NotBoundException ex) {
            
        } catch (RemoteException ex) {
            
        }
        
    }
    
    private void concatenateFt(HashMap ft1, HashMap ft2) {
        
        Integer frequency1, frequency2;
        String key;
        Set keys;
        Iterator it;
        
        keys = ft2.keySet();
        it = keys.iterator();
        
        while (it.hasNext()) {
            key = (String) it.next();
            frequency1 = (Integer)ft1.get(key);
            frequency2 = (Integer)ft2.get(key);
            
            if (frequency1 == null) 
                ft1.put(key, frequency2);
            else
                ft1.put(key, frequency1+frequency2);
        }
    }
    
    public void publish(String path) {
        BufferedWriter bufWr;
        String str;

        try {
            bufWr = new BufferedWriter(new FileWriter(new File(path)));
            bufWr.write(getLookupHops());
            bufWr.write(getInsertionHops());
            bufWr.write(getDeletionHops());
            bufWr.write(getMsgLoadBalance());
            bufWr.write(getRoutingTableSize());
            bufWr.close();
        } catch (IOException e) {
            System.out.println("IOEexception: " + e);
        }
    }
    
    
    public String getLookupHops() {
        
        String str;
        
        str = "Lookup Path | Frequency \n";
        str += getFrequencyTable(HMLookup);
        str +="\n";
        
        return str;
    }
    
    public String getInsertionHops() {
        
        String str;
        
        str = "Insertion Path | Frequency \n";
        str += getFrequencyTable(HMInsert);
        str +="\n";
        
        return str;
    }
    
    public String getDeletionHops() {
        
        String str;
        
        str = "Deletion Path | Frequency \n";
        str += getFrequencyTable(HMDelete);
        str +="\n";
        
        return str;
    }
    
    public String getMsgLoadBalance() {
        
        String str;
                
        str = "Messages/Peer | Frequency \n";
        str += getFrequencyTable(HMLoad);
        str +="\n";
        
        return str;
    }
    
    public String getRoutingTableSize() {
        
        String str;
                                
        str = "RT size/Peer | Frequency \n";
        str += getFrequencyTable(HMRouting);
        str +="\n";
        
        return str;
    }
    
    public String getFrequencyTable(HashMap hm) {
        
        int n, freq, min, max;
        double avrg, sum, total;
        boolean firstIteration;
        Iterator keyIterator;
        String key, frequency;
        Set keys;
        String str;
        
        keys = hm.keySet();
        keyIterator = keys.iterator();
        avrg = total = sum = min = max = n = 0;
        firstIteration = true;
        
        str = "";
        
        while (keyIterator.hasNext()) {
            key = (String)keyIterator.next();
            frequency = hm.get(key).toString();
            str += "   "+key+"           "+frequency+"\n";
            try {
                n = Integer.parseInt(key);
                freq = Integer.parseInt(frequency);
                sum += n*freq;
                total += freq;
            }
            catch(NumberFormatException e) {
                e.printStackTrace();
            }
            
            // Find the minimum and maximum path length
            if (firstIteration) {
                firstIteration = false;
                min = max = n;
            } 
            else {
                if (n < min)
                    min = n;
                if (n > max)
                    max = n;
            }
        }
        
        // Calculate the average path length
        avrg = sum / total;
        
        str += "Average "+avrg+"\n";
        str += "Minimum "+min+"\n";
        str += "Maximum "+max+"\n";
        
        return str;
    }
    
    private double getAvgMetric(HashMap hm) {
    
        int n, freq, min, max;
        double avrg, sum, total;
        boolean firstIteration;
        Iterator keyIterator;
        String key, frequency;
        Set keys;
        String str;
        
        keys = hm.keySet();
        keyIterator = keys.iterator();
        avrg = total = sum = min = max = n = 0;
        firstIteration = true;
        
        str = "";
        
        while (keyIterator.hasNext()) {
            key = (String)keyIterator.next();
            frequency = hm.get(key).toString();
            str += "   "+key+"           "+frequency+"\n";
            try {
                n = Integer.parseInt(key);
                freq = Integer.parseInt(frequency);
                sum += n*freq;
                total += freq;
            }
            catch(NumberFormatException e) {
                e.printStackTrace();
            }
            
            // Find the minimum and maximum path length
            if (firstIteration) {
                firstIteration = false;
                min = max = n;
            } 
            else {
                if (n < min)
                    min = n;
                if (n > max)
                    max = n;
            }
        }
        
        // Calculate the average path length
        avrg = sum / total;
        
        return avrg;
    }
    
    public double getAvgLookupPath() {
        return getAvgMetric(HMLookup);
    }
    
    public double getAvgInsertionPath() {
        return getAvgMetric(HMInsert);
    }
    
    public double getAvgDeletionPath() {
        return getAvgMetric(HMDelete);
    }
    
    public double getAvgLoad() {
        return getAvgMetric(HMLoad);
    }
}
