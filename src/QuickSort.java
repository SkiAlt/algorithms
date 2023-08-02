import java.io.IOException;
import java.util.Random;

class QuickSort {
    static int basicOpCount = 0;

    static void quicksort(int a[], int low, int high) {
        if (low > high)
            return;
        int j = partition(a, low, high);
        quicksort(a, low, j - 1);
        quicksort(a, j + 1, high);
    }

    static int partition(int a[], int low, int high) {
        int i = low + 1, j = high;
        int p = a[low];
        while (true) {
            while (a[i] <= p && i < high) {
                i++;
                basicOpCount++;
            }
            while (a[j] > p && j > low) {
                j--;
                basicOpCount++;
            }
            if (i < j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            } else {
                int temp = a[low];
                a[low] = a[j];
                a[j] = temp;
                return j;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int size = 10000;
         int a[] = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            a[i] = rand.nextInt(size) + 1;
        }
        quicksort(a, 0, a.length - 1);
        System.out.println("1. Worst case occurs when pivot selected as largest/smallest ele,");
        System.out.println("   resulting in unbalanced partitions.");
        System.out.println("   Hence best/average case is analysed separtely from worst case.");
        System.out.println("\n   Best/Average Case:");
        System.out.println("2. Basic Operation: comparison");
        System.out.println("3. No of times Basic Operation was executed C(n) = " + basicOpCount);
        
        System.out.println("\n   Worst Case:");
        basicOpCount = 0;
        int b[] = new int[size];
        int ele = size;
        for (int i = 0; i < size; i++) {
            b[i] = ele--;
        }
        quicksort(b, 0, b.length - 1);
        System.out.println("3. No of times Basic Operation was executed C(n) = " + basicOpCount);
    }
}
