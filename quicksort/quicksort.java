import java.util.Random;
class quicksort{
    


public static int partition(int[] input, int min, int max) {
    int mid = input[min];
    int x = min;
    int y = max;
    while (x< y){
    while (input[x] <= mid && x < max) {
    x++;
    }
    while (input[y] > mid && y> min) {
    y--;
    }
    if (x < y) {
    int tmp = input[x];
    input[x] = input[y];
    input[y] = tmp;
    }
    }
    input[min] = input[y];
    input[y] = mid;
    return y;
}

public static void sort(int[] arr, int min, int max) {
    if (min<max) {
    int mid = partition(arr, min, max);
    sort(arr, min , mid- 1);
    sort(arr, mid + 1, max);
    }
    }


        public static int[] generateRandomArray(int n) {
            Random rand = new Random();
            int[] arr = new int[n];
            
            for (int i = 0; i < n; i++) {
                arr[i] = rand.nextInt(100);  // Generates random numbers between 0 (inclusive) and 100(exclusive)
            }
            
            return arr;
        }
    


    public static void main(String[] args) {

        for(int power = 6; power< 12 ; power++){
        System.out.println("n: " + Math.pow(2, power));
        int[] arr = generateRandomArray((int)Math.pow(2, power));

        // System.out.println("Original Array:");
        // printArray(arr);
        long sum = 0;
        for(int i = 0; i<10000; i++){
        long t1 = System.nanoTime();
        sort(arr, 0, arr.length - 1);
        long t2 = System.nanoTime();
        sum += t2-t1;
        }
        System.out.println(sum/10000);

        

        // System.out.println("\nSorted Array:");
        // printArray(arr);
    }
    }
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
    









