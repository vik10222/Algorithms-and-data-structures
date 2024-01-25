import java.io.*;
import java.util.*;

public class MyMap {
    private City[] cities;
    private final int mod = 541;

    public MyMap(String file) {
        cities = new City[mod];
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                City city1 = lookup(parts[0]);
                City city2 = lookup(parts[1]);
                int minutes = Integer.parseInt(parts[2]);
                city1.addConnection(city2, minutes);
                city2.addConnection(city1, minutes); // Since it's bidirectional
            }
        } catch (Exception e) {
            System.out.println("File " + file + " not found or corrupt");
        }
    }
    
    

    private int hash(String name) {
        int hash = 7;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 % mod) + name.charAt(i);
        }
        return hash % mod;
    }

    public City lookup(String name) {
        int index = hash(name);
        while (cities[index] != null && !cities[index].name.equals(name)) {
            index = (index + 1) % mod; // Linear probing
        }
        if (cities[index] == null) {
            cities[index] = new City(name);
        }
        //System.out.println("Looking up city: " + name + ". Found at index: " + index);

        return cities[index];
    }
    
}
