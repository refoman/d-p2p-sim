/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.utils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gp
 */
@XmlRootElement(name = "AppNode")
public class AppNode {

    @XmlElement private int id;
    @XmlElement private int percent;
    @XmlTransient private long minPeerId;
    @XmlTransient private long maxPeerId;
    @XmlElement private String ip;
    
    public AppNode() {
        
    }
    
    public AppNode(int id, int percent, String ip) {
        
        this.id = id;
        this.percent = percent;
        this.ip = ip;
    }
    
    public int getAppNodeId() {
        return id;
    }
    
    public int getPercent() {
        return percent;
    }
    
    public long getMinPeerId() {
        return minPeerId;
    }
    
    public void setMinPeerId(long minPeerId) {
        this.minPeerId = minPeerId;
    }
    
    public long getMaxPeerId() {
        return maxPeerId;
    }
    
    public void setMaxPeerId(long maxPeerId) {
        this.maxPeerId = maxPeerId;
    }
    
    public String getIP() {
        return ip;
    }
    
    public boolean owns(long peerId) {
        
        if (peerId >= minPeerId && peerId <= maxPeerId)
            return true;
        
        return false;
    }
    
    public long getRange() {
        
        return (maxPeerId - minPeerId + 1);
    }
}
