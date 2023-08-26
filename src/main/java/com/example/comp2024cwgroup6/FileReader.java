package com.example.comp2024cwgroup6;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FileReader {
    private static ArrayList<City> cities;
    public static ArrayList<City> getCities() {
        return cities;
    }
    public static void readFile(ArrayList<City> cities) throws URISyntaxException {
        // Read the dataset file
        URL resource = Main.class.getClassLoader().getResource("TSP_107.txt");
        File file = new File(resource.toURI());

        try {
            Scanner scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //Check if the new line is EOF
                if (!Objects.equals(line, "EOF")) {
                    // Split each line into its components
                    String[] components = line.split("\\s+");
                    // Create a new City object using the components
                    int id = Integer.parseInt(components[0]);
                    int x = Integer.parseInt(components[1]);
                    int y = Integer.parseInt(components[2]);
                    City city = new City(id, x, y);
                    // Add the City object to the array
                    cities.add(city);
                }

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileReader.cities = cities;

    }
}
