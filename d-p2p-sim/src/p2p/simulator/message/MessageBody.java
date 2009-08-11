/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.message;

import java.io.Serializable;

/**
 *
 * This interface should be implemented by the classes that consist the message's data.
 */
public abstract class MessageBody implements Serializable, Cloneable {

    static final long serialVersionUID = 892420644258946183L;
    /**
     * Returns the message's type.
     * 
     * @return The type of the message
     */
    public abstract int getType();
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
