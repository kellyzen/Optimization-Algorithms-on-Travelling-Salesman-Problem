package com.example.comp2024cwgroup6.SA;

import com.example.comp2024cwgroup6.City;
import java.util.ArrayList;

public class Route {
    ArrayList<City> cities;
    double distance;

    public Route(ArrayList<City> cities) {
        this.cities = cities;
        this.distance = calculateDistance();
    }

    private double calculateDistance() {
        double total = 0;
        for (int i = 0; i < cities.size() - 1; i++) {
            total += cities.get(i).distanceTo(cities.get(i + 1));
        }
        total += cities.get(cities.size() - 1).distanceTo(cities.get(0));
        return total;
    }

    public void swapCities(int i, int j) {
        City temp = cities.get(i);
        cities.set(i, cities.get(j));
        cities.set(j, temp);
        distance = calculateDistance();
    }
}
