package com.example.comp2024cwgroup6.SA;

import com.example.comp2024cwgroup6.City;
import com.example.comp2024cwgroup6.FileReader;
import java.util.ArrayList;

public class SimulatedAnnealing {
    ArrayList<City> cities;
    private static double distance;

    public static double getDistance() {
        return distance;
    }

    public SimulatedAnnealing(ArrayList<City> cities) {
        this.cities = cities;
    }

    public ArrayList<City> applyHeuristic() {
        Route current = new Route(FileReader.getCities());

        double temperature = 1000000;
        double coolingRate = 0.0000005;

        while (temperature > 1) {
            int i = (int)(Math.random() * cities.size());
            int j = (int)(Math.random() * cities.size());
            Route next = new Route((ArrayList<City>) current.cities.clone());
            next.swapCities(i, j);
            double delta = next.distance - current.distance;
            if (delta < 0 || Math.exp(-delta/temperature) > Math.random()) {
                current = next;
            }
            temperature *= 1 - coolingRate;
        }

        SimulatedAnnealing.distance = current.distance;
        return current.cities;
    }

}
