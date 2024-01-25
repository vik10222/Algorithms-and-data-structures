import java.io.*;
import java.util.*;

public class Naive {
    public static void main(String[] args) {
        System.out.println("Hi");
        MyMap map = new MyMap("trains.csv");
        benchmark(map);
        System.out.println("end");
    }
    

    public static void benchmark(MyMap map) {
        String[][] routes = {
            {"Malmö", "Göteborg"},
            {"Göteborg", "Stockholm"},
            {"Malmö", "Stockholm"},
            // {"Stockholm", "Sundsvall"},
            // {"Stockholm", "Umeå"},
            // {"Göteborg", "Sundsvall"},
            // {"Sundsvall", "Umeå"},
            {"Umeå", "Göteborg"},
            {"Göteborg", "Umeå"}
        };
    
        System.out.println("From\t\tTo\t\tDistance\tTime Taken (ms)");
        System.out.println("---------------------------------------------------");
    
        for (String[] route : routes) {
            long t0 = System.nanoTime();
            Integer dist = shortest(map.lookup(route[0]), map.lookup(route[1]), 1000000000); // Assuming a large enough max value
            long time = (System.nanoTime() - t0) / 1_000_000; // Convert to milliseconds
    
            String distance = (dist != null) ? dist + " min" : "n/a";
            System.out.println(route[0] + "\t\t" + route[1] + "\t\t" + distance + "\t\t" + time + " ms");
        }
    }
    
    

    private static Integer shortest(City from, City to, Integer max) {
        System.out.println("Exploring from " + from.name + " to " + to.name + " with max time: " + max); // Debugging line
    
        if (max < 0) return null;
        if (from == to) return 0;
        Integer shrt = null;
        for (Connection conn : from.connections) {
            int remaining = max - conn.minutes;
            Integer dist = shortest(conn.destination, to, remaining);
            if (dist != null) {
                int totalDist = conn.minutes + dist;
                if (shrt == null || totalDist < shrt) {
                    shrt = totalDist;
                }
            }
        }
        return shrt;
    }
    
}
