package com.example.comp2024cwgroup6.GA;

import com.example.comp2024cwgroup6.City;
import java.util.ArrayList;
import java.util.Collections;

public class Route implements Comparable<Route> {
    private ArrayList<City> cities;
    private double fitness;
    private double distance;

    public Route(int cityCount) {
        cities = new ArrayList<>(cityCount);
        for (int i = 0; i < cityCount; i++) {
            cities.add(null);
        }
        fitness = 0.0;
        distance = 0.0;
    }

    public Route(ArrayList<City> cities) {
        this.cities = new ArrayList<>(cities.size());
        this.cities.addAll(cities);
        Collections.shuffle(this.cities);
        fitness = 0.0;
        distance = 0.0;
    }

    public City getCity(int index) {
        return cities.get(index);
    }

    public void setCity(int index, City city) {
        cities.set(index, city);
        fitness = 0.0;
        distance = 0.0;
    }

    public int getCityCount() {
        return cities.size();
    }

    public double getFitness() {
        if (fitness == 0.0) {
            fitness = 1 / getDistance();
        }
        return fitness;
    }

    public double getDistance() {
        if (distance == 0.0) {
            for (int i = 0; i < cities.size(); i++) {
                City fromCity = getCity(i);
                City toCity;
                if (i + 1 < cities.size()) {
                    toCity = getCity(i + 1);
                } else {
                    toCity = getCity(0);
                }
                distance += fromCity.distanceTo(toCity);
            }
        }
        return distance;
    }

    public ArrayList<City> getCities() {
        ArrayList<City> cityList = new ArrayList<>();
        for (int i = 0; i < cities.size(); i++) {
            cityList.add(cities.get(i));
        }
        return cityList;
    }


    public void evaluateFitness() {
        fitness = 1 / getDistance();
    }

    public boolean containsCity(City city) {
        return cities.contains(city);
    }

    public void swapCities(int index1, int index2) {
        City temp = getCity(index1);
        setCity(index1, getCity(index2));
        setCity(index2, temp);
    }

    public void shuffle() {
        Collections.shuffle(cities);
    }

    @Override
    public int compareTo(Route other) {
        return Double.compare(other.getFitness(), getFitness());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cities.size(); i++) {
            sb.append(getCity(i)).append(" -> ");
        }
        sb.append(getCity(0));
        return sb.toString();
    }
}
