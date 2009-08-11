/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gp
 */
@XmlRootElement(name="miscellaneous")
public class Misc {

    @XmlElement(name="inputFile")
    public String inputFile;
    
    @XmlElement(name="threadPoolSize")
    public int threadPoolSize;
    
    @XmlElement(name="debugLevel")
    public String debugLevel;
    
    @XmlElement(name="logFile")
    public String logFile;
    
    @XmlElement(name="protocolsDir")
    public String protocolsDir;
    
    @XmlElement(name="reportsDir")
    public String reportsDir;
    
    public Misc() {
        
    }
    
    public Misc(int nthreads, String dlevel, String infile, String logfile, String protocolsDir, String reportsDir) {
        this.threadPoolSize = nthreads;
        this.debugLevel = dlevel;
        this.inputFile = infile;
        this.logFile = logfile;
        this.protocolsDir = protocolsDir;
        this.reportsDir = reportsDir;
    }
}
