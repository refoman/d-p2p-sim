/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gp
 */
@XmlRootElement(name = "Cluster")
public class Cluster {

    @XmlElement private int appNodeId;
    @XmlElement private List<AppNode> appNode;
        
    public Cluster() {
        
    }
    
    public Cluster(int id, ArrayList nodes) {
        appNode = nodes;
        appNodeId = id;
    }
    
    public void addAppNode(AppNode node) {
        appNode.add(node);
    }
    
    public AppNode getAppNode(int appNodeId) {
        
        Iterator it;
        AppNode node;
        
        it = this.appNode.iterator();
        
        while (it.hasNext()) {
            node = (AppNode)it.next();
                        
            if (node.getAppNodeId() == appNodeId)
                return node;
        }
        
        return null;
    }
    
    public int getAppNodeId(long peerId) {
        
        Iterator it;
        AppNode node;
        
        it = this.appNode.iterator();
        
        while (it.hasNext()) {
            node = (AppNode)it.next();
                        
            if (node.owns(peerId))
                return node.getAppNodeId();
        }
        
        return -1;
    }
    
    public String getAppNodeIP(int appNodeId) {
        
        Iterator it;
        AppNode node;
        
        it = this.appNode.iterator();
        
        while (it.hasNext()) {
            node = (AppNode)it.next();
                        
            if (node.getAppNodeId() == appNodeId)
                return node.getIP();
        }
        
        return null;
    }
         
    public int getAppNodeId() {
        return appNodeId;
    }
    
    public int getNofAppNodes() {
        return appNode.size();
    }
}
