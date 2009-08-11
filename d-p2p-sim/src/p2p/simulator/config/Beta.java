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
@XmlRootElement(name="beta")
public class Beta {
    
    @XmlElement(name="alpha")
    public double alpha;
    
    @XmlElement(name="beta")
    public double beta;

    public Beta() {
        
    }
    
    public Beta(double a, double b) {
        alpha = a;
        beta = b;
    }
}
