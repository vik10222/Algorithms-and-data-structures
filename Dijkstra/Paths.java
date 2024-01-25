// import java.io.*;
// import java.util.*;

// public class Paths {
//     City[] path;
//     int sp;

//     public Paths() {
//         path = new City[54];
//         sp = 0;
//     }

//     public class Pair {
//         public final Integer distance;
//         public final Integer citiesReviewed;
    
//         public Pair(Integer distance, Integer citiesReviewed) {
//             this.distance = distance;
//             this.citiesReviewed = citiesReviewed;
//         }
//     }A

//     // public static void main(String[] args) {
//     //     MyMap map = new MyMap("trains.csv");
//     //     Paths paths = new Paths();
//     //     paths.benchmark(map);
//     // }
    
//     //all ndoes from stockholm
//     public static void main(String[] args) {
        
//         MyMap map = new MyMap("europe.csv");
//         Paths paths = new Paths();
//         long t1 = System.nanoTime();
//         paths.benchmarkAllFromMalmö(map);
//         long t2 = System.nanoTime();
//         System.out.println(t2-t1);
//     }
    

//    // Benchmarking method for specific routes
//    public void benchmark(MyMap map, String[] route) {
//     long startTime = System.nanoTime();

//     Pair result = dijkstra(map, map.lookup(route[0]), map.lookup(route[1]));
//     Integer distance = result.distance;
//     int citiesReviewedForThisRoute = result.citiesReviewed;

//     long endTime = System.nanoTime();
//     long time = endTime - startTime;

//     System.out.println(route[0] + "\t\t" + route[1] + "\t\t" + distance + "\t\t" + time + " ns" + "\t\t" + citiesReviewedForThisRoute + " cities");
// }

//     private Integer shortest(MyMap map, int fromId, int toId, Integer max) {
     
//     City from = map.lookupById(fromId);
//     City to = map.lookupById(toId);
    
//     if (max < 0) return null;
//     if (fromId == toId) return 0;

//     for (int i = 0; i < sp; i++) {
//         if (path[i].id == fromId) return null;
//     }

//     path[sp++] = from;

//     Integer shrt = null;
//     for (Connection conn : from.connections) {
//         int remaining = max - conn.minutes;
//         Integer dist = shortest(map, conn.destination.id, toId, remaining);
//         if (dist != null) {
//             int totalDist = conn.minutes + dist;
//             if (shrt == null || totalDist < shrt) {
//                 shrt = totalDist;
//                 max = totalDist;  //
//             }
//         }
//     }

//     path[sp--] = null;
//     return shrt;
//     }



//     // private Integer shortest(City from, City to, Integer max) {
//     //     if (max < 0) return null;
//     //     if (from == to) return 0;
    
//     //     for (int i = 0; i < sp; i++) {
//     //         if (path[i] == from) return null;
//     //     }
    
//     //     path[sp++] = from;
    
//     //     Integer shrt = null;
//     //     for (Connection conn : from.connections) {
//     //         int remaining = max - conn.minutes;
//     //         Integer dist = shortest(conn.destination, to, remaining);
//     //         if (dist != null) {
//     //             int totalDist = conn.minutes + dist;
//     //             if (shrt == null || totalDist < shrt) {
//     //                 shrt = totalDist;
//     //                 max = totalDist;  // Update the max value dynamically
//     //             }
//     //         }
//     //     }
    
//     //     path[sp--] = null;
//     //     return shrt;
//     // }



//     private Integer shortestFlawed(City from, City to, Integer max, int depth) {
//         if (max < 0 || depth > 1000) return null;  // Stop recursion after 1000 calls
//         if (from == to) return 0;
    
//         Integer shrt = null;
//         for (Connection conn : from.connections) {
//             int remaining = max - conn.minutes;
//             Integer dist = shortestFlawed(conn.destination, to, remaining, depth + 1);
//             if (dist != null) {
//                 int totalDist = conn.minutes + dist;
//                 if (shrt == null || totalDist < shrt) {
//                     shrt = totalDist;
//                     max = totalDist;  // Update the max value dynamically
//                 }
//             }
//         }
    
//         return shrt;
//     }
    

//     public Pair dijkstra(MyMap map, City source, City destination) {
//         PriorityQueue<City> queue = new PriorityQueue<>(Comparator.comparingInt(map::getShortestDistance));
//         Map<City, Integer> shortestDistances = new HashMap<>();
//         Map<City, City> predecessors = new HashMap<>();
//         int citiesReviewed = 0;

//         for (City city : map.getCities()) {
//             shortestDistances.put(city, Integer.MAX_VALUE);
//         }
//         shortestDistances.put(source, 0);

//         queue.add(source);

//         while (!queue.isEmpty()) {
//             City currentCity = queue.poll();
//             citiesReviewed++;

//             for (City neighbor : map.getNeighbors(currentCity)) {
//                 int alt = shortestDistances.get(currentCity) + map.getDistance(currentCity, neighbor);
//                 if (alt < shortestDistances.get(neighbor)) {
//                     shortestDistances.put(neighbor, alt);
//                     predecessors.put(neighbor, currentCity);
//                     queue.add(neighbor);
//                 }
//             }
//         }

//         return new Pair(shortestDistances.get(destination), citiesReviewed);
//     }
    
    
//     public void benchmarkAllFromMalmö(MyMap map) {
//         City malmö = map.lookup("Malmö");

//         for (City city : map.getCities()) {
//             if (city.equals(malmö)) continue;

//             long startTime = System.nanoTime();

//             Pair result = dijkstra(map, malmö, city);
//             Integer distance = result.distance;
//             int citiesReviewedForThisCity = result.citiesReviewed;

//             long endTime = System.nanoTime();
//             long time = endTime - startTime;

//             System.out.println("Malmö" + "\t\t" + city.name + "\t\t" + distance + "\t\t" + time + " ns" + "\t\t" + citiesReviewedForThisCity + " cities");
//         }
//     }
    
    
// }
import java.io.*;
import java.util.*;

public class Paths {
    City[] path;
    int sp;

    public class Pair {
        public final Integer distance;
        public final List<City> path;
        public final Integer citiesReviewed;

        public Pair(Integer distance, List<City> path, Integer citiesReviewed) {
            this.distance = distance;
            this.path = path;
            this.citiesReviewed = citiesReviewed;
        }
    }

    class CityComparator implements Comparator<City> {
        private Map<City, Integer> distances;

        public CityComparator(Map<City, Integer> distances) {
            this.distances = distances;
        }

        @Override
        public int compare(City city1, City city2) {
            return distances.getOrDefault(city1, Integer.MAX_VALUE).compareTo(distances.getOrDefault(city2, Integer.MAX_VALUE));
        }
    }

    public static void main(String[] args) {
        MyMap map = new MyMap("europe.csv");
        Paths paths = new Paths();
        long t1 = System.nanoTime();
        paths.benchmarkAllFromMalmö(map);
        long t2 = System.nanoTime();
        System.out.println(t2-t1);
    }

    public void benchmark(MyMap map, String[] route) {
        long startTime = System.nanoTime();

        Pair result = dijkstra(map, map.lookup(route[0]), map.lookup(route[1]));
        Integer distance = result.distance;
        int citiesReviewedForThisRoute = result.citiesReviewed;

        long endTime = System.nanoTime();
        long time = endTime - startTime;

        System.out.println(route[0] + "\t\t" + route[1] + "\t\t" + distance + "\t\t" + time + " ns" + "\t\t" + citiesReviewedForThisRoute + " cities");
    }

    public Pair dijkstra(MyMap map, City source, City destination) {
        Map<City, Integer> shortestDistances = new HashMap<>();
        Map<City, City> predecessors = new HashMap<>();
        int citiesReviewed = 0;

        for (City city : map.cities) {
            if (city != null) {
                shortestDistances.put(city, Integer.MAX_VALUE);
            }
        }
        shortestDistances.put(source, 0);

        PriorityQueue<City> queue = new PriorityQueue<>(new CityComparator(shortestDistances));
        queue.add(source);

        while (!queue.isEmpty()) {
            City currentCity = queue.poll();
            citiesReviewed++;

            for (Connection conn : currentCity.connections) {
                City neighbor = conn.destination;
                int alt = shortestDistances.get(currentCity) + conn.minutes;
                if (alt < shortestDistances.get(neighbor)) {
                    shortestDistances.put(neighbor, alt);
                    predecessors.put(neighbor, currentCity);
                    queue.add(neighbor);
                }
            }
        }

        List<City> shortestPath = new ArrayList<>();
        City currentCity = destination;
        while (currentCity != null) {
            shortestPath.add(currentCity);
            currentCity = predecessors.get(currentCity);
        }
        Collections.reverse(shortestPath); // Reverse to get the path from source to destination

        return new Pair(shortestDistances.getOrDefault(destination, Integer.MAX_VALUE), shortestPath, citiesReviewed);
    }

    public void benchmarkAllFromMalmö(MyMap map) {
        City malmö = map.lookup("Malmö");

        for (City city : map.cities) {
            if (city != null && !city.equals(malmö)) {
                long startTime = System.nanoTime();

                Pair result = dijkstra(map, malmö, city);
                Integer distance = result.distance;
                int citiesInPath = result.path.size(); // This gives the number of cities in the path
                int citiesReviewedForThisCity = result.citiesReviewed;

                long endTime = System.nanoTime();
                long time = endTime - startTime;

                System.out.println("Malmö" + "\t\t" + city.name + "\t\t" + distance + "\t\t" + time + " ns" + "\t\t" + citiesInPath + " cities in path" + "\t\t" + citiesReviewedForThisCity + " cities reviewed");
            }
        }
    }
}
