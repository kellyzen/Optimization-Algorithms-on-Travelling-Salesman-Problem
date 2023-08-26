package com.example.comp2024cwgroup6.ACO;

import com.example.comp2024cwgroup6.City;
import java.util.ArrayList;

public class Ant {

    private ArrayList<Integer> cityTour;
    private double tourLength;
    private boolean[] visitedCities;
    private double alpha;
    private double beta;

    public Ant(int numCities, double alpha, double beta) {
        this.cityTour = new ArrayList<>();
        this.visitedCities = new boolean[numCities];
        this.alpha = alpha;
        this.beta = beta;
    }

    public void reset(int numCities) {
        cityTour.clear();
        for (int i = 0; i < numCities; i++) {
            visitedCities[i] = false;
        }
        int startingCity = (int) (Math.random() * numCities);
        cityTour.add(startingCity);
        visitedCities[startingCity] = true;
    }

    public void visitCity(int city) {
        cityTour.add(city);
        visitedCities[city] = true;
    }

    public int selectNextCity(double[][] pheromone, ArrayList<City> cities) {
        int currentCity = cityTour.get(cityTour.size() - 1);
        double[] probabilities = new double[cities.size()];
        double probabilitiesTotal = 0.0;
        for (int i = 0; i < cities.size(); i++) {
            if (!visitedCities[i]) {
                probabilities[i] = Math.pow(pheromone[currentCity][i], alpha) *
                        Math.pow(1.0 / cities.get(currentCity).distanceTo(cities.get(i)), beta);
                probabilitiesTotal += probabilities[i];
            }
        }
        double random = Math.random() * probabilitiesTotal;
        double sum = 0.0;
        for (int i = 0; i < cities.size(); i++) {
            if (!visitedCities[i]) {
                sum += probabilities[i];
                if (sum >= random) {
                    return i;
                }
            }
        }
        // If there are no unvisited cities, return to the starting city
        return cityTour.get(0);
    }

    public void updatePheromone(double[][] pheromone, double evaporationRate) {
        double pheromoneToAdd = 1.0 / tourLength;
        for (int i = 0; i < cityTour.size() - 1; i++) {
            int city1 = cityTour.get(i);
            int city2 = cityTour.get(i + 1);
            pheromone[city1][city2] = (1.0 - evaporationRate) * pheromone[city1][city2] + evaporationRate * pheromoneToAdd;
            pheromone[city2][city1] = (1.0 - evaporationRate) * pheromone[city2][city1] + evaporationRate * pheromoneToAdd;
        }
        // Update pheromone levels for the last city and the starting city
        int lastCity = cityTour.get(cityTour.size() - 1);
        int firstCity = cityTour.get(0);
        pheromone[lastCity][firstCity] = (1.0 - evaporationRate) * pheromone[lastCity][firstCity] + evaporationRate * pheromoneToAdd;
        pheromone[firstCity][lastCity] = (1.0 - evaporationRate) * pheromone[firstCity][lastCity] + evaporationRate * pheromoneToAdd;
    }
    public void calculateTourLength(ArrayList<City> cities) {
        double tourLength = 0.0;
        for (int i = 0; i < cityTour.size() - 1; i++) {
            int city1 = cityTour.get(i);
            int city2 = cityTour.get(i + 1);
            tourLength += cities.get(city1).distanceTo(cities.get(city2));
        }
        // Add distance from last city to starting city
        int lastCity = cityTour.get(cityTour.size() - 1);
        int firstCity = cityTour.get(0);
        tourLength += cities.get(lastCity).distanceTo(cities.get(firstCity));
        this.tourLength = tourLength;
    }

    public ArrayList<Integer> getCityTour() {
        return cityTour;
    }

    public double getTourLength() {
        return tourLength;
    }

}
