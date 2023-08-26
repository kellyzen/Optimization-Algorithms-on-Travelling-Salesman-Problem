package com.example.comp2024cwgroup6;

import com.example.comp2024cwgroup6.ACO.PrintACO;
import com.example.comp2024cwgroup6.GA.PrintGA;
import com.example.comp2024cwgroup6.SA.PrintSA;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {

    private static ArrayList<City> cities = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Get the cities from the file
        FileReader.readFile(cities);

        // Print output for Genetic Algorithm
        new PrintGA().print(cities);
        System.out.println();

        // Print output for Simulated Annealing
        new PrintSA().print(cities);
        System.out.println();

        // Print output for Ant Colony Algorithm
        new PrintACO().print(cities);
    }
}
