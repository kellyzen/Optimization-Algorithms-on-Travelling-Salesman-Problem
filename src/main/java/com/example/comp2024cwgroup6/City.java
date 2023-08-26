package com.example.comp2024cwgroup6;

public class City {
    private int id;
    private int x;
    private int y;

    public City(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    public double distanceTo(City other) {
        int dx = this.x - other.x;
        int dy = this.y - other.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}
