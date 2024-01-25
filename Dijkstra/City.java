import java.util.*;
import java.io.*;

public class City {
    String name;
    int id; // New field
    List<Connection> connections;

    public City(String name, int id) { // Modified constructor
        this.name = name;
        this.id = id;
        this.connections = new ArrayList<>();
    }

    public void addConnection(City destination, int minutes) {
        this.connections.add(new Connection(destination, minutes));
    }
}
