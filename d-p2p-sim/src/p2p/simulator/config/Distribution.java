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

@XmlRootElement(name="distribution")
public class Distribution {

    @XmlElement(name="random")
    public Random random;
    
    @XmlElement(name="beta")
    public Beta beta;
    
    @XmlElement(name="powerLaw")
    public PowerLaw powerLaw;
    
    public Distribution() {
        
    }
    
    public Distribution(Random r, Beta b, PowerLaw p) {
        random = r;
        beta = b;
        powerLaw = p;
    }
}
