/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p.simulator.network;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import p2p.simulator.overlay.OverlayMonitor;
import p2p.simulator.message.Message;
import java.util.Vector;
import p2p.simulator.utils.Cluster;

/**
 *
 * @author gp
 */
public class NetworkMonitor extends Thread {

    private Network Net;
    private OverlayMonitor ovMonitor;
    private Cluster clusterMgr;
    private Vector<Message> netQueue;
    private boolean stopMonitoring;
    private boolean isDistributed;
    private HashMap hashTable;
    private int appNodes;

    public NetworkMonitor(Network net, OverlayMonitor ovMonitor, Cluster clusterMgr) {
        
        this.Net = net;
        this.ovMonitor = ovMonitor;
        this.clusterMgr = clusterMgr;
        this.netQueue = Net.getNetQueue();
        this.setName("NetworkMonitor");
        this.stopMonitoring = false;
        this.isDistributed = false;
        this.appNodes = clusterMgr.getNofAppNodes();
        this.setDaemon(true);
        if (appNodes > 1) {
            isDistributed = true;
            hashTable = new HashMap(appNodes - 1, 0.8f);
        }
    }
    
    @Override
    public void run() {

        int i = 0, j = 0;
        long dest = 0;
        Message msg, rmsg = null;

        while (!stopMonitoring) {
//            System.out.println("NetworkMonitor is running...");
            synchronized (Net) {

                //System.out.println(netQueue.size());
                //for (i = 0; i < netQueue.size(); i++) {
                //    msg = netQueue.elementAt(i);
                //if (netQueue.size() > 0) {
                for (i = 0; i < netQueue.size() && i < ovMonitor.getNumOfThreads(); i++) {
                    msg = netQueue.firstElement();
                    dest = msg.getDestinationId();

                    if (isDistributed && isOutOfBounds(msg)) { 
                        // Remove the message from the local queue
                        rmsg = netQueue.remove(i);                         
                        break;
                    }
                    else if (ovMonitor.notifyPeer(dest)) {
                        NetworkFilter.filter(msg, ovMonitor);
                        //j = i+1;
                        //break;
                    }
                }
            }
            
            // Not synchronized
            // Message rmsg should be set to null after the rmi call,
            // otherwise it will keep sent it for ever...
            if (rmsg != null) {
                sendRemoteMsg(rmsg);
                rmsg = null;
            }
            
            synchronized (Net) {    
                if (netQueue.isEmpty()) {
                    try {
                        //Thread.sleep(50);
//                        System.out.println("NetworkMonitor is waitting...");
                        Net.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        //System.out.println("Network Monitor exited");
    }

    public void stopMonitoring() {
        stopMonitoring = true;
        synchronized (Net) {
            Net.notifyAll();
        }
    }

    private boolean isOutOfBounds(Message msg) {

        long dest;
        int appNodeId; 
        
        dest = msg.getDestinationId();
        // If the destination peer of the message is stored in 
        // the current application node returns.
        appNodeId = clusterMgr.getAppNodeId(dest);
        if (appNodeId == clusterMgr.getAppNodeId())
            return false;
        
        return true;
    }
    
    private void sendRemoteMsg(Message msg) {
        
        long dest;
        int appNodeId;
        NetworkPipe netPipe;
                
        dest = msg.getDestinationId();
        appNodeId = clusterMgr.getAppNodeId(dest);
        
        // Remote method invocation
        netPipe = getNetworkPipe(appNodeId);
        //System.out.println("[DEBUG] appNodeId="+appNodeId+" "+msg+" netPipe="+netPipe);
        try {
            //msg = (Message)ObjectSerializer.serializeAndDeserialize(msg);
            netPipe.pushMsg(msg);
            //System.out.println("PASS to "+appNodeId+" "+msg);
        } catch (RemoteException ex) {
            Logger.getLogger(NetworkMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private NetworkPipe getNetworkPipe(int appNodeId) {
        
        return (NetworkPipe)hashTable.get(appNodeId);
    }
    
    public void initNetworkPipes(int myAppNodeId) {
        
        String appNodeIP;
        Registry registry;
        NetworkPipe netPipe = null;
                
        for (int appNodeId = 1; appNodeId <= appNodes; appNodeId++) {
            
            if (appNodeId == myAppNodeId)
                continue;
//            System.out.println("[DEBUG] appNodeId="+appNodeId+" appNodes="+appNodes);
            appNodeIP = clusterMgr.getAppNodeIP(appNodeId);
            try {
                registry = LocateRegistry.getRegistry(appNodeIP);
                netPipe = (NetworkPipe) registry.lookup("NetworkPipe"+appNodeId);
            } catch (NotBoundException ex) {
                Logger.getLogger(NetworkMonitor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(NetworkMonitor.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            }
            
            hashTable.put(appNodeId, netPipe);
        }
    }
    
}
