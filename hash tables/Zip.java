import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Zip {
    private Node[] data;  // For direct lookup
    private Bucket[] bucketData;  // For bucket-based lookup
    private Node[] improvedBucketData;  // For improved bucket-based lookup without indirection

    private class Node {
        Integer code;
        String name;
        int pop;

        Node(Integer code, String name, int pop) {
            this.code = code;
            this.name = name;
            this.pop = pop;
        }
    }

    private class Bucket {
        List<Node> nodes;

        Bucket() {
            nodes = new ArrayList<>();
        }

        void add(Node node) {
            nodes.add(node);
        }

        LookupResult get(Integer code) {
            int depth = 0;
            for (Node node : nodes) {
                depth++;
                if (node.code.equals(code)) {
                    return new LookupResult(node, depth);
                }
            }
            return new LookupResult(null, depth);
        }
    }

    private class LookupResult {
        Node node;
        int depth;

        LookupResult(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public Zip(String file, int m) {
        data = new Node[100000];  // For direct lookup
        bucketData = new Bucket[m];  // For bucket-based lookup
        improvedBucketData = new Node[2 * m];  // Twice the size for improved bucket

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                data[code] = new Node(code, row[1], Integer.valueOf(row[2]));  // Direct indexing
                
                int index = hashFunction(code, m);
                if (bucketData[index] == null) {
                    bucketData[index] = new Bucket();
                }
                bucketData[index].add(new Node(code, row[1], Integer.valueOf(row[2])));

                // Improved bucket insertion
                int improvedIndex = hashFunction(code, improvedBucketData.length);
                while (improvedBucketData[improvedIndex] != null) {
                    improvedIndex = (improvedIndex + 1) % improvedBucketData.length;  // Wrap around if needed
                }
                improvedBucketData[improvedIndex] = new Node(code, row[1], Integer.valueOf(row[2]));
            }
        } catch (Exception e) {
            System.out.println("Error reading file " + file + ": " + e.getMessage());
        }
    }

    public int hashFunction(Integer code, int m) {
        return code % m;
    }

    public LookupResult directLookup(Integer code) {
        if (code >= 0 && code < data.length && data[code] != null) {
            return new LookupResult(data[code], 1);  // Direct lookup always has depth 1
        }
        return new LookupResult(null, 0);
    }

    public LookupResult bucketLookup(Integer code) {
        int index = hashFunction(code, bucketData.length);
        if (bucketData[index] != null) {
            return bucketData[index].get(code);
        }
        return new LookupResult(null, 0);
    }

    public LookupResult improvedBucketLookup(Integer code) {
        int index = hashFunction(code, improvedBucketData.length);
        int depth = 0;
        while (improvedBucketData[index] != null) {
            depth++;
            if (improvedBucketData[index].code.equals(code)) {
                return new LookupResult(improvedBucketData[index], depth);
            }
            index = (index + 1) % improvedBucketData.length;  // Wrap around if needed
        }
        return new LookupResult(null, depth);
    }

    public static void main(String[] args) {
        int m = 20000;  // Example value for m
        Zip zip = new Zip("C:\\\\Users\\\\vikto\\\\Downloads\\\\kth\\\\programming\\\\java\\\\Algorithms and datastructures\\\\hash tables\\\\postnummer.csv", m);
        int reps = 100000000;
        int[] codes = {11115, 43792, 98499};
        for (int code : codes) {
            // Benchmark for direct lookup
            long avgDirect = 0;
            for (int i = 0; i < reps; i++) {
                long t1 = System.nanoTime();
                Node result = zip.directLookup(code).node;  // Example zip code
                long t2 = System.nanoTime();
                avgDirect += t2 - t1;
            }
            System.out.println("Direct Lookup average time: " + avgDirect / reps);

            // Benchmark for bucket lookup
            long avgBucket = 0;
            for (int i = 0; i < reps; i++) {
                long t1 = System.nanoTime();
                Node result = zip.bucketLookup(code).node;  // Example zip code
                long t2 = System.nanoTime();
                avgBucket += t2 - t1;
            }
            System.out.println("Bucket Lookup average time: " + avgBucket / reps);

            // Benchmark for improved bucket lookup
            long avgImprovedBucket = 0;
            for (int i = 0; i < reps; i++) {
                long t1 = System.nanoTime();
                Node result = zip.improvedBucketLookup(code).node;  // Example zip code
                long t2 = System.nanoTime();
                avgImprovedBucket += t2 - t1;
            }
            System.out.println("Improved Bucket Lookup average time: " + avgImprovedBucket / reps);

            // Depth statistics
            LookupResult resultDirect = zip.directLookup(code);
            LookupResult resultBucket = zip.bucketLookup(code);
            LookupResult resultImprovedBucket = zip.improvedBucketLookup(code);

            System.out.println("Zip code: " + code);
            System.out.println("Direct Lookup depth: " + resultDirect.depth);
            System.out.println("Bucket Lookup depth: " + resultBucket.depth);
            System.out.println("Improved Bucket Lookup depth: " + resultImprovedBucket.depth);
            System.out.println("----------------------------");
        }
    }
}
