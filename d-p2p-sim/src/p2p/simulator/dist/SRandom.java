/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.dist;

import java.util.Random;

/**
 *
 * @author viennas
 */
public class SRandom extends Random {

    public static int seed;

    SRandom() {
        
    }
    public SRandom(long s){
        super(seed + s);
    }
}
