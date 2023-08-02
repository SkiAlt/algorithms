import java.io.IOException;
import java.util.Random;

public class InsertionSort {
    static int basicOpCount = 0;

    static void sort(int a[]) {
        for (int i = 1; i < a.length; i++) {
            int ele = a[i];
            int j = i - 1;
            basicOpCount++;
            while (j >= 0 && a[j] > ele) {
                basicOpCount++;
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = ele;
        }
    }

    public static void main(String[] args) throws IOException {
        int size = 100000;
        int a[] = new int[size];
        // worst case
        int ele = size;
        for (int i = 0; i < a.length; i++)
            a[i] = ele--;
        System.out.println("C(n) <worst case>:\n" + basicOpCount);
        sort(a);
        // average case
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            a[i] = rand.nextInt(size) + 1;
        }
        basicOpCount = 0;
        sort(a);
        System.out.println("C(n) <avg case>:\n" + basicOpCount);
        //best case
        basicOpCount = 0;
        sort(a);
        System.out.println("C(n) <best case>:\n" + basicOpCount);
    }
}
