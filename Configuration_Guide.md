# Introduction #

Several options affecting the simulator’s behavior are provided for certain tunable parameters concerning three main categories: the application nodes, the distributions and the execution environment. The first set of parameters specifies the applications nodes that constitute the cluster and the percentage of overlay peers that will be managed by each one. The second set specifies the parameters of Beta’s and Power Law’s distribution functions which affect their skweness, as well as the seed of the random engines. Finally,
the third set specifies the number of threads created by the simulator, the debug level and the directories/files that are used by the simulator.

Moreover, another type of configuration file that lists simulation scenarios is handled by the simulator. Each scenario defines the protocol, the size and the key capacity of the overlay, as well as the lookup and update operations for that overlay.

# Simulator's tunable parameters #

```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<configuration>
    <cluster>
        <appNodeId>1</appNodeId>
        <appNode>
            <id>1</id>
            <percent>100</percent>
            <ip>150.140.9.53</ip>
        </appNode>        
        <!--appNode>
            <id>2</id>
            <percent>51</percent>
            <ip>150.140.142.17</ip>
        </appNode-->
    </cluster>
    <distribution>
        <random>
            <seed>1</seed>
        </random>
        <beta>
            <alpha>2.0</alpha>
            <beta>4.0</beta>
        </beta>
        <powerLaw>
            <alpha>0.5</alpha>
            <beta>1.0</beta>
        </powerLaw>
    </distribution>
    <misc>
        <inputFile>NbdtStar_QuickWayDown-simulation.xml</inputFile>
        <threadPoolSize>4</threadPoolSize>
        <debugLevel>SEVERE</debugLevel>
        <logFile>d-p2p-sim.log</logFile>
        <protocolsDir>/home/gp//d-p2p-sim/protocols/</protocolsDir>
        <reportsDir>/home/gp//d-p2p-sim/reports/</reportsDir>
    </misc>
</configuration>
```

# Simulation Scenario #

```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<batchSimulation>
    <simulation>
        <protocolName>gr.upatras.ceid.mmlab.NbdtStar</protocolName>
        <protocolPackage>NbdtStar_QuickWayDown.jar</protocolPackage>
        <overlayPeers>100</overlayPeers>
        <overlayKeys>8100</overlayKeys>
        <lookupQueries>100</lookupQueries>
        <lookupDistribution>UNIF</lookupDistribution>
        <insertQueries>100</insertQueries>
        <insertDistribution>BETA</insertDistribution>
        <deleteQueries>100</deleteQueries>
        <deleteDistribution>POWL</deleteDistribution>
        <reportName>NbdtStar_QuickWayDown-sim-1</reportName>
    </simulation>
    <simulation>
        <protocolName>gr.upatras.ceid.mmlab.NbdtStar</protocolName>
        <protocolPackage>NbdtStar_Rainbow.jar</protocolPackage>
        <overlayPeers>769</overlayPeers>
	<overlayKeys>110770</overlayKeys>
        <lookupQueries>100</lookupQueries>
        <lookupDistribution>UNIF</lookupDistribution>
        <insertQueries>100</insertQueries>
        <insertDistribution>BETA</insertDistribution>
        <deleteQueries>100</deleteQueries>
        <deleteDistribution>POWL</deleteDistribution>
        <reportName>NbdtStar_Rainbow-sim-1</reportName>
    </simulation>
</batchSimulation>
```