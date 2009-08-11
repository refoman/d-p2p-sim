/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gp
 */
@XmlRootElement
public class BatchSimulation {

    @XmlElement(name = "simulation")
    public List<Simulation> simulations;
    
    public BatchSimulation () {
        
    }
    
    public BatchSimulation(List sims) {
        this.simulations = sims;
    }
    
    public static BatchSimulation load(String simFile) {
        
        BatchSimulation bsim = null;
        
        try {           
            JAXBContext jc = JAXBContext.newInstance(BatchSimulation.class);
            Unmarshaller u = jc.createUnmarshaller();
            bsim = (BatchSimulation) u.unmarshal(new FileInputStream(simFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bsim;
    }
    
    public static void store(BatchSimulation bsim, String simFile) {
        
        try {           
            JAXBContext jc = JAXBContext.newInstance(BatchSimulation.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(bsim, new FileOutputStream(simFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
