/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author gp
 */
@XmlRootElement
@XmlType
public class Simulation {

    @XmlElement public String protocolPackage;
    @XmlElement public int overlayPeers;
    @XmlElement public int overlayKeys;
    @XmlElement public int lookupQueries;
    @XmlElement public String lookupDistribution;
    @XmlElement public int insertQueries;
    @XmlElement public String insertDistribution;
    @XmlElement public int deleteQueries;
    @XmlElement public String deleteDistribution;
    @XmlElement public String reportName;
    
    public Simulation() {
        
    }
    
    public Simulation (
                        String protocolPackage,
                        int overlayPeers,
                        int overlayKeys,
                        int lookupQueries,
                        String lookupDistribution,
                        int insertQueries,
                        String insertDistribution,
                        int deleteQueries,
                        String deleteDistribution,
                        String reportName) {
         
        this.protocolPackage = protocolPackage; 
        this.overlayPeers = overlayPeers; 
        this.overlayKeys = overlayKeys;
        this.lookupQueries = lookupQueries;
        this.lookupDistribution = lookupDistribution;
        this.insertQueries = insertQueries;
        this.insertDistribution = insertDistribution;
        this.deleteQueries = deleteQueries;
        this.deleteDistribution = deleteDistribution;
        this.reportName = reportName;
        
    }
}
