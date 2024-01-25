import java.util.*;
import java.io.*;

public class City {
    String name;
    List<Connection> connections;

    public City(String name) {
        this.name = name;
        this.connections = new ArrayList<>();
    }

    public void addConnection(City destination, int minutes) {
        this.connections.add(new Connection(destination, minutes));
    }
}

