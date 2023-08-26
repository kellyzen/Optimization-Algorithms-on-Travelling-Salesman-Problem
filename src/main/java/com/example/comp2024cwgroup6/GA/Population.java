package com.example.comp2024cwgroup6.GA;

import com.example.comp2024cwgroup6.City;
import java.util.ArrayList;
import java.util.Collections;

public class Population {
    private ArrayList<Route> routes;

    public Population() {
        routes = new ArrayList<>();
    }

    public Population(ArrayList<City> cities, int populationSize) {
        routes = new ArrayList<>(populationSize);
        for (int i = 0; i < populationSize; i++) {
            Route route = new Route(cities);
            route.shuffle();
            routes.add(route);
        }
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public Route getRoute(int index) {
        return routes.get(index);
    }

    public void evaluateFitness() {
        for (Route route : routes) {
            route.evaluateFitness();
        }
        Collections.sort(routes);
    }

    public Route getFittest() {
        return routes.get(0);
    }

    public int size() {
        return routes.size();
    }

    public Route tournamentSelection(int tournamentSize) {
        Population tournament = new Population();
        for (int i = 0; i < tournamentSize; i++) {
            int randomIndex = (int) (Math.random() * size());
            tournament.addRoute(getRoute(randomIndex));
        }
        tournament.evaluateFitness();
        return tournament.getFittest();
    }

}
