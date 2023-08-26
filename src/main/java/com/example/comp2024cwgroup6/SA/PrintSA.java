package com.example.comp2024cwgroup6.SA;

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

public class PrintSA {
    static ArrayList<City> output = new ArrayList<>();
    static ArrayList<City> bestOutput;
    int repetition = 30;
    public void print(ArrayList<City> cities) {
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();

        // Apply the simulated annealing heuristic
        SimulatedAnnealing sa = new SimulatedAnnealing(cities);

        // Determine the start time of the algorithm
        long startTime = System.currentTimeMillis();

        // First repetition
        output = sa.applyHeuristic();
        double min = SimulatedAnnealing.getDistance();
        bestOutput = output;

        // Determine the end time of the algorithm
        long endTime = System.currentTimeMillis();

        // Calculate the time taken of the algorithm
        double bestTime = (endTime - startTime) / 1000.0;

        System.out.println("Simulated Annealing");
        // Repeat 30 times
        for (int i = 0; i < repetition; i++) {
            startTime = System.currentTimeMillis();
            output = sa.applyHeuristic();
            endTime = System.currentTimeMillis();
            double timeTaken = (endTime - startTime) / 1000.0;
            System.out.println("Repetition " + i + ": " + SimulatedAnnealing.getDistance() + " : " + timeTaken + "seconds");

            if(SimulatedAnnealing.getDistance() < min) {
                bestOutput = output;
                min = SimulatedAnnealing.getDistance();
                bestTime = timeTaken;
            }
        }

        // Print all cities in the final route
        System.out.println();
        System.out.println("Final route:");
        for (City city : bestOutput) {
            x.add(city.getX());
            y.add(city.getY());
            System.out.println(city.getX() + "," + city.getY());
        }
        System.out.println("Final route distance: " + min);
        System.out.println("Time taken: " + bestTime + " seconds");

        // Plot Graph
        Pane root = new Pane();
        int maxY = Collections.max(y);
        int yOffset = 1500;
        int xOffset = -5000;
        final double scale = 0.05;

        // Add header
        Text header = new Text("Traveling Salesman Problem- Simulated Annealing");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        header.setX(120);
        header.setY(50);
        root.getChildren().addAll(header);

        // Add final route distance
        Text distance = new Text("Final route distance: " + min);
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
        stage.setTitle("Simulated Annealing Graph");
        stage.setScene(scene);
        stage.show();
    }
}
