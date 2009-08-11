package p2p.simulator.dist;

/**
 *
 * @author viennas
 */

import cern.jet.random.AbstractDistribution;
import cern.jet.random.engine.MersenneTwister;
import cern.jet.random.engine.RandomEngine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Beta {

    public static int seed;
    public static double alpha;
    public static double beta;
    
    public static List<Integer> getBeta(int n, int range) {

        int i = 0;
        double d;
        RandomEngine engine;
        AbstractDistribution dist;
        Set collection;
        List<Integer> list;

        collection = new HashSet();
        list = new ArrayList<Integer>(n);
        engine = new MersenneTwister(seed);
        dist = new cern.jet.random.Beta(alpha, beta, engine);

        if (n <= range) {
            while (collection.size() < n) {
                d = Math.round(dist.nextDouble() * (range - 1));
                if (collection.add(d))
                    list.add((int)d);
            }
        }
        else {
            for (i = 0; i < n; i++) {
                d = Math.round(dist.nextDouble() * (range - 1));
                list.add((int)d);
            }
        }

        return list;
    }

    public static List<Integer> getBetaOrdered(int n, int range) {

        List<Integer> list;

        list = getBeta(n, range);
        Collections.sort(list);

        return list;
    }

}
