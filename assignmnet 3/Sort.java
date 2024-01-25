import java.util.Random;
import java.util.Arrays;

public class Sort{
    static Random randomise = new Random();
    public static void main(String[] args) {
        int size = 1000000;
          int upperbound = 1000000;
       // System.out.println(Arrays.toString(array));
        long min= 9999999;
        long sum = 0;
        for(int i= 0; i <100000; i++){
            System.out.println(i);
            //pick type of array
            //int[] array1 = randomArray(size, upperbound);
            int[] array1 = sortedArray(size);
            //int[] array2 = sortedArray(size);
            int randomKey= array1[randomise.nextInt(size)];
            long t1 = System.nanoTime();
            boolean ans = search_sorted(array1, randomKey);
            //boolean ans = search_duplicate(array1, array2);
            long t2 = System.nanoTime();
            sum += t2-t1;
            if(t2-t1< min){min = t2-t1;}
        }
        System.out.println("min "+ min);
        System.out.println("avg "+ sum/100000);
    }

    public static int[] randomArray(int size, int upperbound){
        int[] rarray =  new int[size];
        
        for(int i=0; i<size; i++){
           rarray[i] = randomise.nextInt(upperbound);
        }
        
        return  rarray;

        }        
    
    public static int[] sortedArray(int n) {
            Random rnd = new Random();
            int[] array = new int[n];
            int nxt = 0;
            for (int i = 0; i < n ; i++) {
            nxt += rnd.nextInt(10) + 1;
            array[i] = nxt;
            }
            return array;
            }    
    
    
    public static boolean search_sorted(int[] array, int key){
        for (int index = 0; index < array.length; index++) {
            if (array[index]> key){
                break;
            }
            if (array[index] == key) {
            return true;
            }
            
        }
        return false;
        
    }
    
    public static boolean search_unsorted(int[] array, int key) {
        for (int index = 0; index < array.length ; index++) {
            if (array[index] == key) {
            return true;
            }
        }
        return false;
    }

    public static boolean search_binary(int[] array, int key) {
        int first = 0;
        int last = array.length-1;
        while (true) {
            int index = (first +last)/2;
        if (array[index] == key) {
        return true;
        }
        if (array[index] < key && index < last) {
            first = index +1;
            continue;
        // The index position holds something that is less than
        // what we're looking for, what is the first possible page
        }
        if (array[index] > key && index > first) {
        // The index position holds something that is larger than
        last = index -1;
        continue;
        }
        // The Item is not in the arry
        break;
        }
        return false;
        }

    public static boolean search_duplicate(int[] array1, int[] array2){
       int index1 = 0;
       int index2 = 0; 
        if(index1 == array1.length-1 ||index2 == array2.length-1){
              return false;
        }
       
        while(true){
        if (array1[index1] == array2[index2]){
        return true;}

        if (array1[index1]< array2[index2]){
           while (array1[index1]< array2[index2]){
            if(index1 == array1.length-1 ||index2 ==array2.length-1){
              return false;
        }
                boolean val = search_binary(array2, array1[index1]);
                if(val == true){
                    return true;
                
                }
                index1 ++;
           }
        }
         if (array1[index1] > array2[index2]){
           while (array1[index1] > array2[index2]){
            if(index1 == array1.length-1 ||index2 == array2.length-1){
              return false;
        }
                boolean val = search_binary(array1, array2[index2]);
                if(val ==true){
                    return true;
                }
                index2 ++;
           }
        }
       



  
    }
  }
}