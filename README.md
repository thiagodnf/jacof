Java Ant Colony Optimization Framework
=========
**jacof** is an object-oriented Java-based framework for ant colony optimization (ACO). It implements the most used ACO's implementations.

What is the ACO?
-----

Ant Colony Optimization (ACO) studies artificial systems that take inspiration from the behavior of real ant colonies and which are used to solve discrete optimization problems. In 1999, the Ant Colony Optimization metaheuristic was defined by Dorigo, Di Caro and Gambardella.

The ACOs Developed
-------

This framework implements the following ACO's variations:

| Algorithm                  | Authors | Year |
|---------------------------|----------------------|----------------------|
Ant System | Dorigo, Maniezzo and Colomi | 1992 |
Elitist Ant System | Dorigo | 1992 |
Ant Colony System | Dorigo and Gambardella | 1997 |
Rank-based Ant System | Bullnheimer, Hartl and Strauss | 1997 |
MaxMin Ant System | Stützle and Hoos | 2000 |

The Problems Developed
-------

This framework addresses the following problems:

* Next Release Problem
* Traveling Salesperson Problem


Ant System

jacof -p NRP -in src/main/resources/problems/nrp/delsagrado20.nrp -aco AS -n 10 -it 100 -a 2 -b 3 -rho 0.1

Ant Colony System

jacof -p NRP -in src/main/resources/problems/nrp/delsagrado20.nrp -aco ACS -n 10 -it 100 -a 2 -b 3 -rho 0.1 -q0 0.9 -o 0.2

Elitist Ant System

jacof -p NRP -in src/main/resources/problems/nrp/delsagrado20.nrp -aco EAS -n 10 -it 100 -a 2 -b 3 -rho 0.1 -w 6

Rank-based Ant System

jacof -p NRP -in src/main/resources/problems/nrp/delsagrado20.nrp -aco ASRANK -n 10 -it 100 -a 2 -b 3 -rho 0.1 -w 6

Max-Min Ant System

jacof -p NRP -in src/main/resources/problems/nrp/delsagrado20.nrp -aco MMAS -n 10 -it 100 -a 2 -b 3 -rho 0.1

