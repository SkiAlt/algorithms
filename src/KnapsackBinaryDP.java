import java.util.Scanner;

public class KnapsackBinaryDP {

    static void binaryKnapsack(int v[], int w[], int numberOfItems, int capacity) {
        int F[][] = new int[numberOfItems + 1][capacity + 1]; // solution table
        for (int i = 1; i <= numberOfItems; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j - w[i] >= 0) {
                    F[i][j] = Math.max(F[i - 1][j], v[i] + F[i - 1][j - w[i]]);
                }
                if (j - w[i] < 0) {
                    F[i][j] = F[i - 1][j];
                }
            }
        }
        System.out.println("Knapsack Table:\n");
        for (int i = 0; i <= numberOfItems; i++) {
            for (int j = 0; j <= capacity; j++) {
                System.out.printf("%5d", F[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Maximum Value: " + F[numberOfItems][capacity]);
        System.out.println("Items list:");
        int i = numberOfItems, j = capacity;
        while (i > 0 && j > 0) {
            if (F[i][j] > F[i - 1][j]) {
                System.out.println(i);
                j -= w[i];
            }
            i--;
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Number of items avaiable: ");
        int numberOfItems = s.nextInt();
        System.out.println("Enter Max capacity of Knapsack:  ");
        int capacity = s.nextInt();
        System.out.println("Enter profit and weight of element: ");
        int v[] = new int[numberOfItems + 1];
        int w[] = new int[numberOfItems + 1];
        for (int i = 1; i <= numberOfItems; i++) {
            System.out.printf("P%d W%d\n", i, i);
            v[i] = s.nextInt();
            w[i] = s.nextInt();
        }
        binaryKnapsack(v, w, numberOfItems, capacity);
        s.close();
    }
}
/* 4 5 12 2 10 1 20 3 15 2 */