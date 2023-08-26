package com.example.comp2024cwgroup6.ACO;

import com.example.comp2024cwgroup6.City;
import java.util.ArrayList;

public class AntColonyOptimization {

    private ArrayList<Ant> ants;
    private double[][] pheromone;
    private ArrayList<City> cities;
    private ArrayList<Integer> x = new ArrayList<>();
    private ArrayList<Integer> y = new ArrayList<>();
    private double alpha;
    private double beta;
    private double evaporationRate;
    private int numAnts;
    private int numIterations;

    public AntColonyOptimization(ArrayList<City> cities, int numAnts, int numIterations, double alpha, double beta, double evaporationRate) {
        this.cities = cities;
        this.numAnts = numAnts;
        this.numIterations = numIterations;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;

        pheromone = new double[cities.size()][cities.size()];
        for (int i = 0; i < cities.size(); i++) {
            for (int j = 0; j < cities.size(); j++) {
                pheromone[i][j] = 1.0;
            }
        }
        for (City city : cities) {
            x.add(city.getX());
            y.add(city.getY());
        }
    }

    public Route applyHeuristic() {
        // Initialize ants
        ants = new ArrayList<>();
        for (int i = 0; i < numAnts; i++) {
            ants.add(new Ant(cities.size(), alpha, beta));
        }

        Route bestRoute = null;
        double bestLength = Double.POSITIVE_INFINITY;

        // Perform ant colony optimization
        for (int i = 0; i < numIterations; i++) {
            // Reset ants and visit starting city
            for (Ant ant : ants) {
                ant.reset(cities.size());
            }

            // Have each ant construct a tour
            for (int j = 0; j < cities.size() - 1; j++) {
                for (Ant ant : ants) {
                    int nextCity = ant.selectNextCity(pheromone, cities);
                    ant.visitCity(nextCity);
                }
            }

            // Update pheromone levels
            for (Ant ant : ants) {
                ArrayList<Integer> tourX = new ArrayList<>();
                ArrayList<Integer> tourY = new ArrayList<>();
                ant.calculateTourLength(cities);
                ant.updatePheromone(pheromone, evaporationRate);
                if (ant.getTourLength() < bestLength) {
                    bestLength = ant.getTourLength();

                    ArrayList<Integer> tours = ant.getCityTour();

                    for (Integer tour :tours) {
                        tourX.add(x.get(tour));
                        tourY.add(y.get(tour));
                    }
                    bestRoute = new Route(tourX, tourY, bestLength);
                }
            }
        }

        return bestRoute;
    }

}
