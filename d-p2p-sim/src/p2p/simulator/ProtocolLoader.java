package p2p.simulator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import p2p.simulator.protocol.Peer;

public class ProtocolLoader {

    private static final Class[] parameters = new Class[]{URL.class};

    public static void addFile(String s) throws IOException {
        File f = new File(s);
        try {    
            addFile(f);
        } catch (IOException e) {
            System.out.println("IOException "+s);
        }
    }//end method

    public static void addFile(File f) throws IOException {
        addURL(f.toURI().toURL());
    }//end method

    public static void addURL(URL u) {

        URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class sysclass = URLClassLoader.class;

        try {
            Method method = sysclass.getDeclaredMethod("addURL", parameters);
            method.setAccessible(true);
            method.invoke(sysloader, new Object[]{u});
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("IOException: Error, could not add URL to system classloader");
        }

    }//end method

    static Peer loadProtocol(String name) {
        // We need to create a new classloader, and the URLClassLoader is very convinient.
        //URLClassLoader classloader = new URLClassLoader(locations, ClassLoader.getSystemClassLoader());
        URLClassLoader classloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        // Load the class that was specified
        Class<?> cls;
        Peer p = null;
        
        try {
            cls = classloader.loadClass(name);
            if (!Peer.class.isAssignableFrom(cls)) {
            // Lets just throw some exception since this class doesn't implement the Peer interface
                throw new RuntimeException("Invalid class");
            }
            p = (Peer) cls.newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProtocolLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex ) {
            Logger.getLogger(ProtocolLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex ) {
            Logger.getLogger(ProtocolLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Create a new instance of the new class and return it
        // We can be sure this wont throw a class cast exception since we checked for that earlier
        return p;
    }
}
