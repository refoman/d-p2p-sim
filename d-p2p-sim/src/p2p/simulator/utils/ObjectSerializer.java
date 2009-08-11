/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p.simulator.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gp
 */
public class ObjectSerializer {

    public static Object serializeAndDeserialize(Serializable obj) {
        
        byte[] bytes;
                
        try {
            bytes = ObjectSerializer.serialize(obj);
            
        } catch (IOException ex) {
            throw new RuntimeException("Serialization failed: " + ex.getClass().getName() + " - " + ex.getMessage());
        }
        
        try {
            return ObjectSerializer.deserialize(bytes);
        } catch (IOException ex) {
            Logger.getLogger(ObjectSerializer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObjectSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    protected static byte[] serialize(Serializable obj) throws IOException {
        
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ObjectOutput serialStream = new ObjectOutputStream(stream);
        serialStream.writeObject(obj);
        
        return stream.toByteArray();
    }

    protected static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        
        InputStream stream = new ByteArrayInputStream(bytes);
        ObjectInput serialStream = new ObjectInputStream(stream);
        
        return serialStream.readObject();
    }
}
