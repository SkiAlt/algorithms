import java.util.Arrays;
import java.util.Scanner;

public class SumOfSubsetsBT {

    static int x[]; // global solution array

    static int arraySum(int arr[]) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++)
            sum += arr[i];
        return sum;
    }

    static void sumOfSubsets(int a[], int d, int k) {
        // backtracking conditions
        if (arraySum(x) == d) {
            // soln found
            System.out.println(Arrays.toString(x));
            return;
        }
        if ((arraySum(x) + a[k + 1]) > d)
            return; // subset too big
        if ((arraySum(x) + arraySum(a)) < d)
            return; // subset too small

        for (int i = k; i < a.length; i++) {
            int temp = a[i];
            x[k] = a[i];
            a[i] = 0;
            sumOfSubsets(a, d, k + 1);
            //backtrack
            x[k] = 0; 
            a[i] = temp;
        }

    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter no of set items");
        int n = s.nextInt();
        int a[] = new int[n];
        x = new int[n];
        System.out.println("Enter set elements:");
        for (int i = 0; i < n; i++)
            a[i] = s.nextInt();
        Arrays.sort(a);
        System.out.println("Enter sum value 'd'");
        int d = s.nextInt();
        int k = 0;
        sumOfSubsets(a, d, k);
        s.close();
    }
}
