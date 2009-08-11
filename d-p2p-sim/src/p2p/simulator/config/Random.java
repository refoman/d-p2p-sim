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
@XmlRootElement(name="random")
public class Random {
    
    @XmlElement(name="seed")
    public int seed;
    
    public Random() {
        
    }
    
    public Random(int s) {
        seed = s;
    }
}
