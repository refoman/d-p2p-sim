D-P2P-Sim+ is a distributed P2P simulator. It is an evolution of D-P2P-Sim enhanced to deliver multi-million node simulation support, failure-recovery models simulation capabilities and more statistics. Please find at downloads section _all related executables_ and _source code samples_.

The key aim is to provide the appropriate integrated set of tools in a single software solution to evaluate the performance of various Peer-To-Peer protocols.

We implemented a simulation system in Java to evaluate the performance of various protocols. The basic architecture of the P2P simulator is based on a distributed, multi-threading, asynchronous, message passing environment. The peers exchange messages to build the overlay network and carry out search, insert/delete as well as join/leave operations. In our simulator, each peer is a thread executable task that can run independently. Every time a message is sent, a thread is assigned to the message's destination peer to serve that particular message. In order to avoid the thread creation overhead, thread competition, locking problems and etc, a thread pool with tunable number of threads is maintained. Furthermore, the simulator's ability to run over a grid adds even more realism to our experimental environment and overcomes the memory limitations.

The simulator has an unbiased and protocol independent mechanism to collect performance data. This mechanism is based on the observation and the filtering of special fields in the message. These fields are controlled by the simulator and are totally unaccessible from the protocol's processes. This alternative way to collect performance data, in conjunction with the realistic execution environment yields to more accurate results.

The key-features of this new simulator are:
  * Realism
  * Distributed
  * Unbiased
  * Pluggable
  * Extensible
  * Efficient
  * Practical
  * Portable

D-P2P-Sim+ Sample executable with BatonStar to show failure-recovery models simulation [D-P2P-Sim+ Sample](http://code.google.com/p/d-p2p-sim/downloads/detail?name=d-p2p-sim%2B%20-%20Sample%20executable%20with%20BatonStar%20model%20-%20.rar)

Simulator demonstration can be found at: [video](http://d-p2p-sim.googlecode.com/files/standaloned-p2p-sim1_0001.wmv)!

CIKM 2009 Poster: [Poster d-p2p-sim](http://mmlab.ceid.upatras.gr/people/sakkopoulos/CIKM%202009%20d-p2p-sim%20poster_small.jpg)

_Send your questions at {papalukg,sakkopul,ansaltou}@gmail.com_


---


**For the simulator Please reference:**

**Sioutas, S., Papaloukopoulos, G., Sakkopoulos, E., Tsichlas, K., Manolopoulos, Y.,
["A novel Distributed P2P Simulator Architecture: D-P2P-Sim"](http://delab.csd.auth.gr/papers/CIKM09spstm.pdf), in CIKM 2009, the 18th ACM Conference on Information and Knowledge Management) as demonstration, Hong Kong, November 2-6, 2009**

