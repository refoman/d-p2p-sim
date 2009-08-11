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
@XmlRootElement(name="powerLaw")
public class PowerLaw {

    @XmlElement(name="alpha")
    public double alpha;
    
    @XmlElement(name="beta")
    public double cut;
    
    public PowerLaw() {
        
    }
    
    public PowerLaw(double a, double c) {
        alpha = a;
        cut = c;
    }
}
