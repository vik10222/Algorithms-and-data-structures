import java.io.*;
import java.util.*;

public class Paths {
    City[] path;
    int sp;

    public Paths() {
        path = new City[54];
        sp = 0;
    }

    public static void main(String[] args) {
        MyMap map = new MyMap("trains.csv");
        Paths paths = new Paths();
        paths.benchmark(map);
    }

    public void benchmark(MyMap map) {
        String[][] routes = {
            {"Malmö", "Göteborg"},
            //{"Göteborg", "Stockholm"},
            {"Malmö", "Stockholm"},
            {"Malmö", "Sundsvall"},
            {"Malmö", "Umeå"},
            // {"Stockholm", "Sundsvall"},
            // {"Stockholm", "Umeå"},
            // {"Göteborg", "Sundsvall"},
            // {"Sundsvall", "Umeå"},
            // {"Umeå", "Göteborg"},
            // {"Göteborg", "Umeå"},
            {"Malmö", "Kiruna"}
        };
    

        System.out.println("From\t\tTo\t\tDistance\tTime Taken (ns)");
        System.out.println("---------------------------------------------------");

        for (String[] route : routes) {
            long t0 = System.nanoTime();
            Integer dist = shortest(map.lookup(route[0]), map.lookup(route[1]), Integer.MAX_VALUE);

            //Integer dist = shortestFlawed(map.lookup(route[0]), map.lookup(route[1]), Integer.valueOf(Integer.MAX_VALUE), 0);


            long time = (System.nanoTime() - t0);

            String distance = (dist != null) ? dist + " min" : "n/a";
            System.out.println(route[0] + "\t\t" + route[1] + "\t\t" + distance + "\t\t" + time + " ns");
        }
    }

    private Integer shortest(City from, City to, Integer max) {
        if (max < 0) return null;
        if (from == to) return 0;
    
        for (int i = 0; i < sp; i++) {
            if (path[i] == from) return null;
        }
    
        path[sp++] = from;
    
        Integer shrt = null;
        for (Connection conn : from.connections) {
            int remaining = max - conn.minutes;
            Integer dist = shortest(conn.destination, to, remaining);
            if (dist != null) {
                int totalDist = conn.minutes + dist;
                if (shrt == null || totalDist < shrt) {
                    shrt = totalDist;
                    max = totalDist;  // Update the max value dynamically
                }
            }
        }
    
        path[sp--] = null;
        return shrt;
    }



    private Integer shortestFlawed(City from, City to, Integer max, int depth) {
        if (max < 0 || depth > 1000) return null;  // Stop recursion after 1000 calls
        if (from == to) return 0;
    
        Integer shrt = null;
        for (Connection conn : from.connections) {
            int remaining = max - conn.minutes;
            Integer dist = shortestFlawed(conn.destination, to, remaining, depth + 1);
            if (dist != null) {
                int totalDist = conn.minutes + dist;
                if (shrt == null || totalDist < shrt) {
                    shrt = totalDist;
                    max = totalDist;  // Update the max value dynamically
                }
            }
        }
    
        return shrt;
    }
    
    
    
}
