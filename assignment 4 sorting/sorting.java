import java.util.Random;
import java.util.Arrays;

public class sorting{
    static Random randomise = new Random();
    public static void main(String[] args) {
        int size = 1000;
        System.out.println(size);
        System.out.println(size*size);
        int upperbound = 100;
        
        long min= 9999999;
        long sum = 0;
        for(int i= 0; i <10000; i++){
            //System.out.println(i);
            int[] array1 = randomArray(size, upperbound);
            //System.out.println(Arrays.toString(array1));
            //System.out.println(Arrays.toString(insertionSort(array1)));
            //mergeSort(array1);
           //System.out.println(Arrays.toString((array1)));
            long t1 = System.nanoTime();
            optimizedMergeSort(array1);
            long t2 = System.nanoTime();
            sum += t2-t1;
            if(t2-t1< min){min = t2-t1;}
        }
        System.out.println("min "+ min);
        System.out.println("avg "+ sum/10000);
        
    }

    public static void mergeSort(int[] org) {
        int[] aux = new int[org.length]; // auxiliary array for merging
        mergeSort(org, aux, 0, org.length - 1);
    }
    
    private static void mergeSort(int[] org, int[] aux, int lo, int hi) {
        if (lo != hi) {
            int mid = lo + (hi - lo) / 2;
            mergeSort(org, aux, lo, mid);
            mergeSort(org, aux, mid + 1, hi);
            merge(org, aux, lo, mid, hi);
        }
    }
    
    private static void merge(int[] org, int[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = org[i];
        }
        int i = lo; // the index in the first part
        int j = mid + 1; // the index in the second part
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                org[k] = aux[j];
                j++;
            } else if (j > hi) {
                org[k] = aux[i];
                i++;
            } else if (aux[i] < aux[j]) {
                org[k] = aux[i];
                i++;
            } else {
                org[k] = aux[j];
                j++;
            }
        }
    }
    
    public static void optimizedMergeSort(int[] org) {
        int[] aux = new int[org.length];
        System.arraycopy(org, 0, aux, 0, org.length); // Copy org to aux
        optimizedMergeSort(aux, org, 0, org.length - 1); // Note the swapped arguments
    }
    
    private static void optimizedMergeSort(int[] src, int[] dst, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        optimizedMergeSort(dst, src, lo, mid); // Swap roles of src and dst
        optimizedMergeSort(dst, src, mid + 1, hi);
        optimizedMerge(src, dst, lo, mid, hi);
    }
    
    private static void optimizedMerge(int[] src, int[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (src[i] < src[j]) {
                dst[k] = src[i++];
            } else {
                dst[k] = src[j++];
            }
        }
    }
    

    public static int[] insertionSort(int[] array) {
        for (int index = 1; index < array.length; index++) {
            int candidate = index;
            while (candidate > 0 && array[candidate - 1] > array[candidate]) {
                swap(array, candidate - 1, candidate);
                candidate--;
            }
        }
        return array;
    }
    

    public static int[] selectionSort(int[] inputArray){
        for( int index =0; index < inputArray.length-1 ; index++){
            for (int candidate =index + 1; candidate < inputArray.length-1; candidate++){
                if(inputArray[index]> inputArray[candidate]){
                    swap(inputArray, index, candidate);
                  
                }
            }
        }
        return inputArray;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static int[] randomArray(int size, int upperbound){
        int[] rarray =  new int[size];
        
        for(int i=0; i<size; i++){
           rarray[i] = randomise.nextInt(upperbound);
        }
        
        return  rarray;

        }   

}