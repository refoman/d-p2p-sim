# Introduction #

Currently, the following P2P protocols have been implemented for the simulator while others are in progress and evolving. The jar packages can be downloaded from [here](http://code.google.com/p/d-p2p-sim/downloads/list)

# Protocols #

  * BATON`*` :: A multi-way tree structure derived from BATON structure `[1]`.
  * NBDT :: Nested Balanced Distributed Tree `[2]`.
  * NBDT`*` :: A finger-like lookup algorithm for the NBDT structure.
  * R-NBDT`*` :: A better load distribution algorithm for the NBDT structure.
  * Dummy :: A sample dummy implementation demonstrating the basic design of a P2P protocol on the D-P2P-Sim simulator. (source code available at downloads)
  * Chord :: The known Chord protocol as sample (source code available at downloads)

## Experiments with Baton`*` ##

| **Nof Peers** | 1000 | 2000 | 3000 | 4000 | 5000 | 6000 | 7000 | 8000 | 9000 |  10000 | 100000 |
|:--------------|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-------|:-------|
| **Nof Keys** | 1000000 | 2000000 | 3000000 | 4000000 | 5000000 | 30000000 | 35000000 | 37000000 | 38000000 | 80000000 |  100000000 |

## Experiments with NBDT ##

| **Nof Peers** | 1000 | 2000 | 3000 | 4000 | 5000 | 6000 | 7000 | 8000 | 9000 |  10000 | 100000 |
|:--------------|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|:-------|:-------|
| **Nof Keys** | 140000 | 322000 | 519000 | 728000 | 945000 | 1170000 | 1400000 | 1640000 | 1881000 | 2120000 |  28900000 |

<img src='http://wiki.d-p2p-sim.googlecode.com/hg/uniform.jpeg' width='242' height='212' /> <img src='http://wiki.d-p2p-sim.googlecode.com/hg/lb2.jpeg' width='242' height='212' /> <img src='http://wiki.d-p2p-sim.googlecode.com/hg/lb.jpeg' width='288' height='243' />



---


# References #

`[1]` Jagadish, H. V., Ooi, B. C., Tan, K., Vu, Q. H., and Zhang, R. 2006. Speeding up search in peer-to-peer networks with a multi-way tree structure. In Proceedings of the 2006 ACM SIGMOD international Conference on Management of Data (Chicago, IL, USA, June 27 - 29, 2006). SIGMOD '06. ACM, New York, NY, 1-12. http://portal.acm.org/citation.cfm?id=1142473.1142475

`[2]` Sioutas, S. A. 2008. NBDT: an efficient P2P indexing scheme for web service discovery. Int. J. Web Eng. Technol. 4, 1 (Dec. 2008), 95-113 http://portal.acm.org/citation.cfm?id=1358549