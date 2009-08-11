/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p.simulator.overlay;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import p2p.simulator.protocol.Peer;

/**
 *
 * @author gp
 */
public class PeerExecutor extends ThreadPoolExecutor {

    

    public PeerExecutor(int corePoolSize, int maxPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue workQueue, ThreadFactory threadFactory) {

        super(corePoolSize, maxPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }
       
    @Override
    protected void afterExecute(Runnable r, Throwable w) {

        super.afterExecute(r, w);
        Peer p = (Peer) r;
        p.lock();
        p.setState(Thread.State.TERMINATED);
        p.unlock();
        //System.out.println("PeerExecutor: Peer's "+p.getPeerId()+" state change to TERMINATED");
    }
}

class PeerThreadFactory implements ThreadFactory {

    public Thread newThread(Runnable r) {
        
        Thread t = new Thread(r);
        t.setPriority(Thread.NORM_PRIORITY);
        //t.setDaemon(true);
        //System.out.println("Thread: "+t.getName());

        return t; 
    }
}
