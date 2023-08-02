import java.util.Scanner;

class KnapsackFractionalGreedy {
    static void GreedyKnapsack(float profit[], float weight[], int numberOfItems, float capacity) {
        float profitPerWeight[] = new float[numberOfItems];
        for (int i = 0; i < numberOfItems; i++) {
            profitPerWeight[i] = profit[i] / weight[i];
        }
        float totalProfit = 0;
        System.out.format("\n\n%-6s%-10s%-10s\n", "Item", "Fraction", "Profit");
        while (capacity > 0) {
            int item = 0;
            float maxProfitRatio = 0;
            for (int i = 0; i < numberOfItems; i++) {
                if (profitPerWeight[i] > maxProfitRatio) {
                    maxProfitRatio = profitPerWeight[i];
                    item = i;
                }
            }
            profitPerWeight[item] = 0;

            float fraction = 1;
            if (capacity < weight[item])
                fraction = capacity / weight[item];
            System.out.printf("%-6d%-10.2f%-10.2f\n", item + 1, fraction, fraction * profit[item]);
            totalProfit += fraction * profit[item];
            capacity -= weight[item];
        }
        System.out.println("Total Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Number of items avaiable: ");
        int numberOfItems = s.nextInt();
        System.out.println("Enter Max capacity of Knapsack:  ");
        float capacity = s.nextInt();
        System.out.println("Enter profit and weight of element: ");
        float profit[] = new float[numberOfItems];
        float weight[] = new float[numberOfItems];
        for (int i = 0; i < numberOfItems; i++) {
            System.out.printf("P%d W%d\n", i + 1, i + 1);
            profit[i] = s.nextFloat();
            weight[i] = s.nextFloat();
        }
        GreedyKnapsack(profit, weight, numberOfItems, capacity);
        s.close();
    }
}
