import java.util.Random;

public class TreeVsArray {

    private static final int[] ns = {64, 128, 256, 512, 1024};
    private static final Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("n\tArray Add Time\tTree Add Time\tArray Remove Time\tTree Remove Time");
        int reps= 10000;
        for (int n : ns) {
            ArrayHeap arrayHeap = new ArrayHeap(n * 2);  // Assuming max capacity is 2n for simplicity
            Heap treeHeap = new Heap();

            // Benchmark ArrayHeap add

            long avgArrAdd = 0;
            long avgArrrem = 0;
            for(int s= 0; s<reps; s++){
                long t1 = System.nanoTime();
                for (int i = 0; i < n; i++) {
                    arrayHeap.add(rand.nextInt(10001));
                }
                long t2 = System.nanoTime();
                avgArrAdd += t2-t1;
                
                // Benchmark ArrayHeap remove
                long t3 = System.nanoTime();
                for (int i = 0; i < n -1; i++) {  
                    arrayHeap.poll();
                }
                long t4 = System.nanoTime();
                avgArrrem +=t4-t3;
            }
            
            long arrayAddTime = avgArrAdd/reps;
            long arrayRemoveTime = avgArrrem/reps;

            long avgTreeAdd = 0;
            long avgTreerem = 0;
            for(int s= 0; s<reps; s++){
                long t1 = System.nanoTime();
                  for (int i = 0; i < n; i++) {
                treeHeap.add(rand.nextInt(10001));
                 }
                long t2 = System.nanoTime();
                avgTreeAdd += t2-t1;
                
                // Benchmark ArrayHeap remove
                long t3 = System.nanoTime();
                 for (int i = 0; i < n -1; i++) {
                treeHeap.remove();
                }
                long t4 = System.nanoTime();
                avgTreerem +=t4-t3;
            }
            
            long treeAddTime = avgTreeAdd/reps;
            long treeRemoveTime = avgTreerem/reps;

            // Print results for this value of n
            System.out.println(n + "\t" + arrayAddTime + "\t \t" + treeAddTime + "\t \t" + arrayRemoveTime + "\t \t \t" + treeRemoveTime);
        }
    }
}

