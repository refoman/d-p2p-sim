package p2p.simulator.dist;

/**
 *
 * @author viennas
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class Uniform {

    public static int seed;

    public static List<Integer> getUniform(int n, int range) {

        int i, d;
        Random random;
        Set collection;
        List<Integer> list;

        collection = new HashSet();
        list = new ArrayList(n);
        random = new Random(seed);

        if (n <= range) {
            while (collection.size() < n) {
                d = random.nextInt(range);
                if (collection.add(d))
                    list.add((int)d);
            }
        }
        else {
            for (i = 0; i < n; i++) {
                d = random.nextInt(range);
                list.add((int)d);
            }
        }

        return list;
    }

    public static List<Integer> getUniformOrdered(int n, int range) {

        List<Integer> list;

        list = getUniform(n, range);
        Collections.sort(list);

        return list;
    }

}
