package p2p.simulator.dist;

/**
 *
 * @author George Papaloukopoulos
 */
import cern.jet.random.Distributions;
import cern.jet.random.engine.MersenneTwister;
import cern.jet.random.engine.RandomEngine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;   

public class PowLaw {

    public static int seed;
    public static double alpha;
    public static double cut;
  
    public static List<Integer> getPowLaw(int n, int range) {

        int i = 0;
        double d;
        RandomEngine engine;
        Set collection;
        List<Integer> list;
        
        collection = new HashSet(n);
        list = new ArrayList<Integer>(n);
        engine = new MersenneTwister(seed);
        
        if (n <= range) {
            while (collection.size() < n) {
                d = Math.round(Distributions.nextPowLaw(alpha, cut, engine) * (range - 1));
                if (collection.add(d))
                    list.add((int)d);
            }
        }
        else {    
            for (i = 0; i < n; i++) {
                d = Math.round(Distributions.nextPowLaw(alpha, cut, engine) * (range - 1));
                list.add((int)d);
            }
        }
                
        return list;
    }        
    
    public static List<Integer> getPowLawOrdered(int n, int range) {
        
        List<Integer> list;
        
        list = getPowLaw(n, range);
        Collections.sort(list);
        
        return list;
    }
}
