/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import p2p.simulator.utils.AppNode;
import p2p.simulator.utils.Cluster;

/**
 *
 * @author gp
 */
@XmlRootElement(name = "configuration")
@XmlType(name = "ConfigurationType")
public class Configuration {

    public Cluster cluster;
    public Distribution distribution;
    public Misc misc;
    
    public Configuration () {
        
    }
    
    public void setDefault() {
        ArrayList appNodes = new ArrayList<AppNode>();
        appNodes.add(new AppNode(1, 50, "192.168.1.100"));
        appNodes.add(new AppNode(2, 50, "192.168.1.101"));
            
        cluster = new Cluster(1, appNodes);
        misc = new Misc(4, "SEVERE", "simulation.cfg", "d-p2p-sim.log", 
                            "/home/gp/NetBeansProjects/p2p.simulator.plugin/d-p2p-sim/protocols/",
                            "/home/gp/NetBeansProjects/p2p.simulator.plugin/d-p2p-sim/reports/");
        distribution = new Distribution(new Random(1), new Beta(1.0, 1.0), new PowerLaw(1.0, 1.0));
    }    
    
    public static Configuration load(String configFile) {
        
        Configuration conf = null;
        
        try {           
            JAXBContext jc = JAXBContext.newInstance(Configuration.class);
            Unmarshaller u = jc.createUnmarshaller();
            conf = (Configuration) u.unmarshal(new FileInputStream(configFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conf;
    }
    
    public static void store(Configuration conf, String configFile) {
        
        try {           
            JAXBContext jc = JAXBContext.newInstance(Configuration.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(conf, new FileOutputStream(configFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
