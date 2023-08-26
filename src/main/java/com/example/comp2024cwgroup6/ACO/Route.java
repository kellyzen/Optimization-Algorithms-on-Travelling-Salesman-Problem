package com.example.comp2024cwgroup6.ACO;

import java.util.ArrayList;

public class Route {

    private ArrayList<Integer> tourX, tourY;
    private double length;

    public Route(ArrayList<Integer> tourX, ArrayList<Integer> tourY, double length) {
        this.tourX = tourX;
        this.tourY = tourY;
        this.length = length;
    }

    //Return the x-coordinates of cities
    public ArrayList<Integer> getTourX() {
        return tourX;
    }

    //Return the y-coordinates of cities
    public ArrayList<Integer> getTourY() {
        return tourY;
    }

    //Return the length of the route
    public double getLength() {
        return length;
    }
}