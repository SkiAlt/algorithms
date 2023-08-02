import java.io.IOException;
import java.util.Random;

public class SelectionSort {
    static int basicOpCount = 0;

    static void sort(int a[]) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                basicOpCount++;
                if (a[j] < a[minIndex])
                    minIndex = j;
            }
            if (minIndex != i) {
                int temp = a[minIndex];
                a[minIndex] = a[i];
                a[i] = temp;
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
        sort(a);
        System.out.println("1. Selection Sort running time only depends upon size of input.");
        System.out.println("   Hence single case analysis.");
        System.out.println("2. Basic Operation: comparison");
        System.out.println("3. No of times Basic Operation was executed C(n) = " + basicOpCount);
    }
}
