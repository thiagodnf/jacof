package tsplib;

/**
 * Created by wojci on 31.10.2017.
 */
public enum AlgorithmName {

    DBEA, //Implementation of the Improved Decomposition-Based Evolutionary Algorithm (I-DBEA). This implementation is based on the Matlab version published by the original authors.
    DifferentialEvolution, //Single-objective differential evolution (DE) algorithm.
    eMOEA, //Implementation of the ε-MOEA algorithm. The ε-MOEA is a steady-state algorithm, meaning only one individual in the population is evolved per step, and uses an ε-dominance archive to maintain a well-spread set of Pareto-optimal solutions.
    GDE3, //Implementation of the Generalized Differential Evolution (GDE3) algorithm.
    GeneticAlgorithm, //Single-objective genetic algorithm (GA) implementation with elitism. A single elite individual is retained in each generation.
    IBEA, //Implementation of the Indicator-Based Evolutionary Algorithm (IBEA). Instead of using Pareto dominance to evaluate the quality of solutions, IBEA uses an indicator function (typically hypervolume but other indicator functions can be specified).
    MSOPS, //Implementation of the Multiple Single Objective Pareto Sampling (MSOPS) algorithm. This implementation only supports differential evolution.
    NSGAII, //Implementation of NSGA-II, with the ability to attach an optional ε-dominance archive.
    PAES, //Implementation of the (1+1) Pareto Archived Evolution Strategy (PAES). PAES uses an adaptive grid archive to maintain a diverse set of solutions.
    PESA2, //Implementation of the Pareto Envelope-based Selection Algorithm (PESA2).
    RVEA, // Implementation of the Reference Vector Guided Evolutionary Algorithm (RVEA). This version does not include the reference vector regeneration method proposed by the authors.     RVEA is similar in concept to NSGA-III, but replaces NSGA-III's dominance-based selection with an angle-penalized distance function. Additionally, whereas NSGA-III renormalizes the objectives every iteration, RVEA periodically scales the reference vectors, potentially reducing algorithm overhead.
    SMSEMOA, //Implementation of the S-metric Selection MOEA (SMS-MOEA). The S metric is also known as the hypervolume indicator.
    SPEA2, //Implementation of the strength-based evolutionary algorithm (SPEA2). SPEA2 uses a novel strength-based measure of fitness for handling multiple objectives.
    VEGA; //Implementation of the Vector Evaluated Genetic Algorithm (VEGA). VEGA should be avoided in practice, since many modern algorithms outperform it and exhibit better convergence properties, but is included due to its historical significance. VEGA is considered the earliest MOEA. It supports M objectives during the selection phase by selecting M different subgroups, each selected based on the i-th objective value, for i=1,...,M.

}
