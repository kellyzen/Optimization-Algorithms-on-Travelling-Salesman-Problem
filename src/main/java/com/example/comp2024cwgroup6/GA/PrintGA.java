package com.example.comp2024cwgroup6.GA;

import com.example.comp2024cwgroup6.City;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;

public class PrintGA {
    public void print(ArrayList<City> cities) {
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        ArrayList<City> fittestCityList;

        // add cities to the list
        int populationSize = 107;
        double mutationRate = 0.001;
        int tournamentSize = 5;
        int elitismCount = 10;
        int maxGenerations = 100;
        int repetition = 30;

        // Create an instance of the GeneticAlgorithm class
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(cities, populationSize, mutationRate, tournamentSize, elitismCount, maxGenerations);

        // Determine the start time of the algorithm
        long startTime = System.currentTimeMillis();

        // First repetition
        fittestCityList = geneticAlgorithm.applyHeuristic();
        Route route = new Route(fittestCityList);
        double min = route.getDistance();
        Route fittestRoute = route;

        // Determine the end time of the algorithm
        long endTime = System.currentTimeMillis();

        // Calculate the time taken of the algorithm
        double bestTime = (endTime - startTime) / 1000.0;

        System.out.println("Genetic Algorithm");
        // Repeat 30 times
        for (int i = 0; i < repetition; i++) {
            startTime = System.currentTimeMillis();
            fittestCityList = geneticAlgorithm.applyHeuristic();
            endTime = System.currentTimeMillis();
            // Create a Route object from the fittest city list
            route = new Route(fittestCityList);
            double timeTaken = (endTime - startTime) / 1000.0;
            System.out.println("Repetition " + i + ": " + route.getDistance()  + " : " + timeTaken + "seconds");
            if(route.getDistance() < min) {
                fittestRoute = route;
                min = route.getDistance();
                bestTime = timeTaken;
            }
        }

        // print all cities in the fittest route
        System.out.println();
        System.out.println("Final route: ");
        for (int i = 0; i < fittestRoute.getCityCount(); i++) {
            x.add(fittestRoute.getCity(i).getX());
            y.add(fittestRoute.getCity(i).getY());
            System.out.println(fittestRoute.getCity(i));
        }

        // print final route distance
        System.out.println("Final route distance: " + fittestRoute.getDistance());
        System.out.println("Time taken: " + bestTime + " seconds");

        // Plot Graph
        Pane root = new Pane();
        int maxY = Collections.max(y);
        int yOffset = 1500;
        int xOffset = -5000;
        final double scale = 0.05;

        // Add header
        Text header = new Text("Traveling Salesman Problem- Genetic Algorithm");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        header.setX(140);
        header.setY(50);
        root.getChildren().addAll(header);

        // Add final route distance
        Text distance = new Text("Final route distance: " + fittestRoute.getDistance());
        distance.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        distance.setX(200);
        distance.setY(500);
        root.getChildren().addAll(distance);

        // Add time taken
        Text time = new Text("Time taken: " + bestTime + " seconds");
        time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        time.setX(240);
        time.setY(520);
        root.getChildren().addAll(time);

        // Add circles and coordinate text for each city
        for (int i = 0; i < x.size(); i++) {
            Circle circle = new Circle((x.get(i) + xOffset) * scale, (maxY - y.get(i) + yOffset) * scale, 2);
            circle.setFill(Color.RED);
            root.getChildren().addAll(circle);
        }

        // Add lines connecting the cities
        for (int i = 0; i < x.size(); i++) {
            Line line;
            if (i == x.size() - 1) {
                line = new Line((x.get(i) + xOffset) * scale, (maxY - y.get(i) + yOffset) * scale, (x.get(0) + xOffset) * scale, (maxY - y.get(0) + yOffset) * scale);
            } else {
                line = new Line((x.get(i) + xOffset) * scale, (maxY - y.get(i) + yOffset) * scale, (x.get(i + 1) + xOffset) * scale, (maxY - y.get(i + 1) + yOffset) * scale);
            }
            root.getChildren().addAll(line);
        }

        Scene scene = new Scene(root, 800, (maxY + yOffset) * scale);
        Stage stage = new Stage();
        stage.setTitle("Genetic Algorithm Graph");
        stage.setScene(scene);
        stage.show();
    }
}
