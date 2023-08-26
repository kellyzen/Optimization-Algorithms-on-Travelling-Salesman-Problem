# Optimization Algorithms on Travelling Salesman-Problem

This Java project provides a solution to the Travelling Salesman Problem (TSP) using three different Artificial Intelligence (AI) algorithms: Genetic Algorithm (GA), Simulated Annealing (SA), and Ant Colony Optimization (ACO). The project aims to find a near-optimal solution for a TSP instance with 107 cities.

## Table of Contents

- [Introduction](#introduction)
- [Algorithms](#algorithms)
- [Getting Started](#getting-started)

## Introduction

The Travelling Salesman Problem (TSP) is a classic combinatorial optimization problem where the goal is to find the shortest possible route for a salesman to visit a set of cities exactly once and return to the starting city. This problem is known to be NP-hard, making it challenging to solve optimally for large instances.

This project provides a comprehensive review of three AI algorithms for solving the TSP: Genetic Algorithm (GA), Simulated Annealing (SA), and Ant Colony Optimization (ACO). These algorithms are implemented in Java to find approximate solutions to the TSP with 107 cities.

## Algorithms

### Genetic Algorithm (GA)

Genetic Algorithms are population-based optimization techniques inspired by the process of natural selection. In this project, a genetic algorithm is used to evolve a population of potential solutions (tours) over several generations. The algorithm employs selection, crossover, mutation, and elitism to iteratively improve the quality of solutions.

### Simulated Annealing (SA)

Simulated Annealing is a probabilistic optimization technique inspired by the annealing process in metallurgy. It starts with an initial solution and iteratively explores nearby solutions. The algorithm probabilistically accepts worse solutions early on and gradually reduces the probability of accepting worse solutions as it progresses.

### Ant Colony Optimization (ACO)

Ant Colony Optimization is inspired by the foraging behavior of ants. In this algorithm, artificial ants construct solutions by iteratively selecting cities to visit based on pheromone levels and heuristics. Over time, the pheromone levels are updated to bias future ants toward better solutions.

## Getting Started

Follow these steps to get the project up and running on your local machine:

1. Clone the repository to your local machine.
2. Open the project in your preferred Java IDE (e.g., Eclipse, IntelliJ).
4. Build the project to compile the code.
5. Configure the TSP instance:
   - You can specify the number of cities and their coordinates ``src\main\resources\TSP_107.txt``
   - You may also change the parameters of each algorithm in ``AntColonyOptimization.java``, ``GeneticAlgorithm.java``, or ``SimulatedAnnealing.java``.
7. Run ``Main.java`` to view results.

**Note**: For a detailed analysis of the project, please read the conference paper in [COMP2024-CW-Group6.pdf](COMP2024-CW-Group6.pdf).
