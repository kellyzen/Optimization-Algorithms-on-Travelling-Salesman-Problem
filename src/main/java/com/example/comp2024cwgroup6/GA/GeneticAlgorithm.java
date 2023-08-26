package com.example.comp2024cwgroup6.GA;

import com.example.comp2024cwgroup6.City;
import java.util.ArrayList;

public class GeneticAlgorithm {
    private ArrayList<City> cities;
    private int populationSize;
    private double mutationRate;
    private int tournamentSize;
    private int elitismCount;
    private int maxGenerations;

    public GeneticAlgorithm(ArrayList<City> cities, int populationSize, double mutationRate, int tournamentSize,
                            int elitismCount, int maxGenerations) {
        this.cities = cities;
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.tournamentSize = tournamentSize;
        this.elitismCount = elitismCount;
        this.maxGenerations = maxGenerations;
    }

    public ArrayList<City> applyHeuristic() {
        // Initialize population
        Population population = new Population(cities, populationSize);

        int generationCount = 0;
        while (generationCount < maxGenerations) {
            // Evaluate population fitness
            population.evaluateFitness();

            // Select parents
            Population parents = new Population();
            for (int i = 0; i < elitismCount; i++) {
                parents.addRoute(population.getFittest());
            }
            for (int i = 0; i < populationSize - elitismCount; i++) {
                Route parent1 = population.tournamentSelection(tournamentSize);
                Route parent2 = population.tournamentSelection(tournamentSize);
                parents.addRoute(crossover(parent1, parent2));
            }

            // Mutate offspring
            for (int i = elitismCount; i < populationSize; i++) {
                mutate(parents.getRoute(i));
            }

            // Replace population with offspring
            population = parents;

            generationCount++;
        }

        // Return fittest route as an ArrayList of cities
        population.evaluateFitness();
        Route fittestRoute = population.getFittest();
        ArrayList<City> fittestCityList = fittestRoute.getCities();
        return fittestCityList;
    }



    private Route crossover(Route parent1, Route parent2) {
        Route offspring = new Route(cities.size());

        // Get random start and end indices for parent1
        int start = (int) (Math.random() * parent1.getCityCount());
        int end = (int) (Math.random() * parent1.getCityCount());
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        // Add cities from parent1 to offspring
        for (int i = start; i <= end; i++) {
            offspring.setCity(i, parent1.getCity(i));
        }

        // Add remaining cities from parent2 to offspring
        int offspringIndex = (end + 1) % parent1.getCityCount();
        for (int i = 0; i < parent2.getCityCount(); i++) {
            City city = parent2.getCity(i);
            if (!offspring.containsCity(city)) {
                offspring.setCity(offspringIndex, city);
                offspringIndex = (offspringIndex + 1) % parent1.getCityCount();
            }
        }

        return offspring;
    }

    private void mutate(Route route) {
        for (int i = 0; i < route.getCityCount(); i++) {
            if (Math.random() < mutationRate) {
                int j = (int) (Math.random() * route.getCityCount());
                route.swapCities(i, j);
            }
        }
    }
}
