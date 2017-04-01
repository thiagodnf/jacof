![alt tag](https://raw.githubusercontent.com/thiagodnf/jacof/master/src/main/resources/logo.png)


Java Ant Colony Optimization Framework
=========
**jacof** is an object-oriented Java-based framework for ant colony optimization (ACO). It implements the most used ACO's implementations.

### Status
[![Build Status](https://travis-ci.org/thiagodnf/jacof.svg?branch=master)](https://travis-ci.org/thiagodnf/jacof)
[![codecov](https://codecov.io/gh/thiagodnf/jacof/branch/master/graph/badge.svg)](https://codecov.io/gh/thiagodnf/jacof)

What is the ACO?
-----

Dorigo [1] introduces an ant-based algorithm called Ant Colony Optimization (ACO). The algorithm tries to reproduce the behavior of the ants in the search process of solutions from the choice of the path to be followed until the process of updating the pheromone trail.

The main concepts used in ACO are based on real ants such as an artificial pheromone trail used to communication among the ants, a sequence of local moves to find the shortest path and a stochastic decision policy

The below figure shows an example of an ant building its solution traversing the graph starting from vertex 6 and reaching
the vertex 4, obtaining as solution the path {6-2-3-4}.

![alt tag](https://raw.githubusercontent.com/thiagodnf/jacof/master/src/main/resources/path.png)


The ACOs Developed
-------

This framework implements the following ACO's variations:

| Algorithm                  | Abbreviation | Authors | Year | Reference | How to use | 
|---------------------------|----|----------------------|----------------------|----|----|
Ant System | AS | Dorigo, Maniezzo and Colomi | 1992 |  [1] | [Click here](#ant-system) |
Elitist Ant System | EAS| Dorigo | 1992 |  [1,3] | [Click here](#elitist-ant-system) |
Ant Colony System | ACS| Dorigo and Gambardella | 1997 |  [2] | [Click here](#ant-colony-system) |
Rank-based Ant System | ASRank | Bullnheimer, Hartl and Strauss | 1997 |  [4] | [Click here](#rank-based-ant-system ) |
Max-Min Ant System | MMAS | Stützle and Hoos | 2000 |  [5] | [Click here](#max-min-ant-system) |

The Problems Developed
-------

This framework addresses the following problems:

* Next Release Problem (NRP)
* Symmetric Traveling Salesman Problem (TSP)
* Knapsack Problem (KP)

Obs. 1: This framework supports the TSPLIB library (a library of sample instances for the TSP). [Click here](http://elib.zib.de/pub/mp-testdata/tsp/tsplib/tsp/index.html) to access the problem instances.

Obs. 2: For Knapsack Problem, access https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html 

How To Use
-------

To use this framework, it is necessary to define: i) the problem addressed; and ii) the ACO's variation used. After that, the algorithm should be executed. At the following it is shown some examples.

### Defining the Addressed Problem

```java
Problem nrp = new NextReleaseProblem("in/delsagrado20.nrp");
Problem tsp = new TravellingSalesmanProblem("in/oliver30.tsp");
Problem kp = new KnapsackProblem("in/p01.tsp");
```

### Defining the ACO's variation

Some parameter settings are the same in all ACO's variations such as the number of ants, the number of iterations, alpha, betha and the evaporation rate. However, other algorithms have their own parameter settings. Next it is shown how to instanciate each ACO's variation.

#### Ant System

```java
AntSystem aco = new AntSystem(problem);

aco.setNumberOfAnts(10);
aco.setNumberOfIterations(50);
aco.setAlpha(1.0);
aco.setBeta(2.0);
aco.setRho(0.1);
```

#### Ant Colony System

```java
AntColonySystem aco = new AntColonySystem(problem);

aco.setNumberOfAnts(10);
aco.setNumberOfIterations(50);
aco.setAlpha(1.0);
aco.setBeta(2.0);
aco.setRho(0.1);
aco.setOmega(0.1);
aco.setQ0(0.9);
```

#### Elitist Ant System


```java
ElitistAntSystem aco = new ElitistAntSystem(problem);

aco.setNumberOfAnts(10);
aco.setNumberOfIterations(50);
aco.setAlpha(1.0);
aco.setBeta(2.0);
aco.setRho(0.1);
aco.setWeight(6);
```

#### Rank-based Ant System

```java
RankBasedAntSystem aco = new RankBasedAntSystem(problem);

aco.setNumberOfAnts(10);
aco.setNumberOfIterations(50);
aco.setAlpha(1.0);
aco.setBeta(2.0);
aco.setRho(0.1);
aco.setWeight(6);
```

#### Max-Min Ant System

```java
MaxMinAntSystem aco = new MaxMinAntSystem(problem);

aco.setNumberOfAnts(10);
aco.setNumberOfIterations(50);
aco.setAlpha(1.0);
aco.setBeta(2.0);
aco.setRho(0.1);
aco.setStagnation(10);
```

### Running the algorithm

To run the algorithm, you can use the ExecutionStats class defined as follows:

```java
ExecutionStats es = ExecutionStats.execute(aco, problem);
es.printStats();
```
The output will be:

```sh
2017-03-31 21:01:35 [INFO ] ==================================================
2017-03-31 21:01:35 [INFO ] Execution Time: 36918.0
2017-03-31 21:01:35 [INFO ] Best Value: 423.74056313320284
2017-03-31 21:01:35 [INFO ] Best Solution: [11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 0, 1]
2017-03-31 21:01:35 [INFO ] ==================================================
```

References
-------
[1] M. Dorigo. *Optimization, Learning and Natural Algorithms*. PhD thesis, Politecnico di
Milano, Italy, 1992. [in Italian].

[2] M. Dorigo and L. M. Gambardella. Ant colony system: A cooperative learning approach
to the traveling salesman problem. *IEEE Transactions on Evolutionary Computation*,
1(1):53–66, April 1997.

[3] M. Dorigo, V. Maniezzo, and A. Colorni. Ant system: Optimization by a colony of
cooperating agents. *IEEE Transactions on Systems, Man, and Cybernetics, Part B:
Cybernetics*, 26(1):29–41, February 1996.

[4] B. Bullnheimer, R. F. Hartl, and C. Strauss. A new rank based version of the ant system - a
computational study. *Central European Journal for Operations Research and Economics*,
7:25–38, 1997.

[5] T. Stützle and H. H. Hoos. MAX-MIN ant system. *Future Generation Computer Systems*,
16(9):889–914, June 2000.
