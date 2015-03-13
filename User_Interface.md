# Introduction #

The GUI is organized in such a way that can guide some one easily through the simulation process with no need for any prior knowledge about the simulator. One may select the protocol of the overlay to be constructed and enter the number of peers that constitute the overlay network and its key capacity. Perform step-by-step execution of the lookup and update operations-algorithms, and verify the algorithm’s correctness and proper functionality by observing the messages that are being displayed at real time. Moreover, the user may design, in batch mode, multiple experiment scenarios to evaluate the protocol’s efficiency. For each experiment it should be supplied the number of queries that will be performed and the distribution according to the keys will be picked up for the experiments. After the termination of the experiments the user has available
statistical charts which are automatically generated. Additional performance statistics such as the average path length of the lookup, insert and delete algorithms, as the average size of the peers’ routing table and the total number of operations that have been conducted at that time, are displayed.

# Graphical User Interface #

Figure 1 shows the first tab of the GUI. In the _SetUp_ section, one may select the protocol of the overlay to be constructed and enters the number of peers that constitute the overlay network and its key capacity. During the initialization phase a progress bar at the bottom of the current tab shows the overall process. Also, the user can reset the simulator's state and begin a new cycle of experiments by clicking on the reset button. This action destroys the overlay network and discards all the statistics and plots that may have been collected.

Figure 2 shows the second tab of the GUI. In the _Operations_ section, the user may perform step-by-step execution of the lookup and update operations-algorithms. While one of these operations is being executed, appropriate messages are being displayed real time to assist the verification of the algorithm’s correctness and proper functionality.

<table cellspacing='25px'>
<tr>
<td>
<img src='http://wiki.d-p2p-sim.googlecode.com/hg/screenshots/tab_setup.png' />
<br />
<br />
<b>Figure 1</b>: SetUp tab.<br>
</td>
<td>
<img src='http://wiki.d-p2p-sim.googlecode.com/hg/screenshots/tab_operations.png' />
<br />
<br />
<b>Figure 2</b>: Operations tab.<br>
</td>
</tr>
</table>

Figure 3 shows the third tab of the GUI. In the _Experiments_ section of the D-P2P-simulator, the user may design, in batch mode, multiple experiments to evaluate the protocol’s efficiency. For each experiment it should be supplied the number of queries that will be performed and the distribution according to the keys will be picked up for the experiments. One may choose one of the lookup, insert or delete operations to fire up the corresponding experiment. After the termination of the experiments the user has available statistical charts which are automatically generated.

Figure 4 shows the fourth tab of the GUI. In the _Statistics_ section, general information and performance statistics of the overlay network is presented such as the size and the key capacity of the overlay network as the average size of the peers’ routing table and the total number of operations that have been conducted at that time. Furthermore, is displayed the average path length of the lookup, insert and delete algorithms. In this tab the user may also see the load distribution of the overlay

<table cellspacing='25px'>
<tr>
<td>
<img src='http://wiki.d-p2p-sim.googlecode.com/hg/screenshots/tab_experiments.png' />
<br />
<br />
<b>Figure 3</b>: Experiments tab.<br>
</td>
<td>
<img src='http://wiki.d-p2p-sim.googlecode.com/hg/screenshots/tab_statistics.png' />
<br />
<br />
<b>Figure 4</b>: Statistics tab.<br>
</td>
</tr>
</table>

Figure 5 shows an auto-generated plot depicting the performance of the lookup algorithm. The X axis indicates the number of lookup queries that have been resolved performing a certain number of hops (Y axis). With green color it is marked the average path length. That kind of plots are generated at the _Experiments_ tab.

Figure 6 shows the performance of the load balancing algorithm. In this plot, the X axis  indicates the number of peers have processed a certain number of lookup and/or update messages (Y axis). With green color it is marked the average load distribution. This plot is generated at the _Statistics_ tab.

<table cellspacing='25px'>
<tr>
<td>
<img src='http://wiki.d-p2p-sim.googlecode.com/hg/screenshots/plot_lookupperformance.png' />
<br />
<br />
<b>Figure 5</b>: Plot of the lookup's algorithm performance.<br>
</td>
<td>
<img src='http://wiki.d-p2p-sim.googlecode.com/hg/screenshots/plot_loaddistribution.png' />
<br />
<br />
<b>Figure 6</b>: Plot of the load balancing algorithm performance.<br>
</td>
</tr>
</table>

# Command Line Arguments #

The executable takes two optional parameters, as it is shown below. The first parameter enables the graphical user interface while the second parameter provides an alternative configuration file than the default. When the simulator is acting on a distributed environment the `--gui` option is silently ignored. It is also recommended to use the `-Xmx` and `-Xms` java's run time parameters to reserve as much as possible physical memory. Informations and details about the configuration and simulation files available at the [configuration](dP2PsimConfiguration.md) section.

<blockquote>
<pre>
usage: d-p2p-sim [--gui] [config-file]<br>
<br>
--gui        Enables the graphical user interface (enabled only on standalone environment)<br>
config-file	 The simulator's configuration file (default config.xml)<br>
<br>
Example 1: java -jar d-p2p-sim.jar<br>
<br>
Example 2: java -jar d-p2p-sim.jar myConfig.xml<br>
<br>
Example 3: java -jar d-p2p-sim.jar --gui<br>
<br>
Example 4: java -jar d-p2p-sim.jar --gui myConfig.xml<br>
</pre>
</blockquote>