import java.util.Random;

public class Benchmark {

    public static void main(String[] args) {
        
        Random rand = new Random();
        int reps = 10000000;
        for (int power = 1; power < 8; power++) {
            System.out.println("n: " + Math.pow(10, power));
            int n = (int) Math.pow(2, power);
            // Implementation1 impl1 = new Implementation1();
            // Implementation2 impl2 = new Implementation2();
            // for(int i= 0; i<n; i++){
            //     int value = rand.nextInt();
            //     impl1.add(value);
            //     impl2.add(value);
            // }

            // // Benchmark for Implementation1 add
            // long sumImpl1Add = 0;
            // for (int i = 0; i < reps; i++) {
            //     int value = rand.nextInt();
            //     long t1 = System.nanoTime();
            //     impl1.add(value);
            //     long t2 = System.nanoTime();
            //     impl1.remove();
            //     sumImpl1Add += t2 - t1;
            // }
            // System.out.println("Implementation1 add time: " + (sumImpl1Add / reps));

            // // Benchmark for Implementation1 remove
            // long sumImpl1Remove = 0;
            // for (int i = 0; i < reps; i++) {
            //     int value = rand.nextInt();
            //     impl1.add(value);
            //     long t1 = System.nanoTime();
            //     impl1.remove();
            //     long t2 = System.nanoTime();
            //     sumImpl1Remove += t2 - t1;
            // }
            // System.out.println("Implementation1 remove time: " + (sumImpl1Remove / reps));

            // // Benchmark for Implementation2 add
            // long sumImpl2Add = 0;
            // for (int i = 0; i < reps; i++) {
            //     int value = rand.nextInt();
            //     long t1 = System.nanoTime();
            //     impl2.add(value);
            //     long t2 = System.nanoTime();
            //     impl2.remove();
            //     sumImpl2Add += t2 - t1;
            // }
            // System.out.println("Implementation2 add time: " + (sumImpl2Add / reps));

            // // Benchmark for Implementation2 remove
            // long sumImpl2Remove = 0;
            // for (int i = 0; i < reps; i++) {
            //     int value = rand.nextInt();
            //     impl2.add(value);
            //     long t1 = System.nanoTime();
            //     impl2.remove();
            //     long t2 = System.nanoTime();
            //     sumImpl2Remove += t2 - t1;
            // }
            // System.out.println("Implementation2 remove time: " + (sumImpl2Remove / reps));
        }
    }
}
