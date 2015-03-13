# d-p2p-sim+ Introduction #

d-p2p-sim+ Interface and extensions are shortly presented in this page.


# d-p2p-sim+ Details #

The new D-P2P-Sim+ simulator that is able to deliver and to provide two (2) additional key features (a) support for multiple millions of nodes simulation, (b) and implementation of node failure and node departure recovery strategies for best simulation of real life conditions. All these new features are integrated in the same integrated user friendly environment that provides extensive statistical results.

Node failures and departures are important in order to achieve real life conditions during simulation. The simulator supports new operations and services so that it provides services for node failure and network recovery and for node departures and substitutions. Simulators up to now tend to limit themselves to support (a) import of nodes for the creation of overlay network and (b) searching, (c) import and deletion of keys in nodes.

For this reason, we present the operations of a new simulator with all the necessary services to implement strategies for
  * self-willing retirement of peer nodes (node departure)
  * failure or their sudden retirement for any reason (network failure, application failure etc)
  * monitoring whether the overlay network is parted after succesive node failures or departures
  * statistics and management in order to monitor all different strategies natively in the simulator.