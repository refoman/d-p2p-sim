package p2p.simulator.dist;

/**
 *
 * @author gp
 */

public class Distribution {
    
    public enum DistributionT { UNIF, BETA, POWL };
    
 
    public static DistributionT parse(String type) {
        
        if (type.matches("UNIF"))
            return DistributionT.UNIF;
        else if (type.matches("BETA"))
            return DistributionT.BETA;
        else if (type.matches("POWL"))
            return DistributionT.POWL;
        else
            return null;
    }
}
    
