import java.util.Scanner;

class FGraph {
    int costMatrix[][];
    int numberOfVerticies;

    FGraph(int numberOfVerticies) {
        this.numberOfVerticies = numberOfVerticies;
        costMatrix = new int[numberOfVerticies][numberOfVerticies];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter cost matrix:");
        for (int v = 0; v < numberOfVerticies; v++) {
            for (int u = 0; u < numberOfVerticies; u++) {
                int edgeWeight = sc.nextInt();
                if (edgeWeight == 0 && (u != v))
                    costMatrix[v][u] = 99999;
                else
                    costMatrix[v][u] = edgeWeight;
            }
        }
        sc.close();
    }
}

class FloydsAlgorithmDemo {
    static int[][] floyd(FGraph g) {
        int D[][] = new int[g.numberOfVerticies][g.numberOfVerticies];
        for (int i = 0; i < g.numberOfVerticies; i++) {
            for (int j = 0; j < g.numberOfVerticies; j++) {
                D[i][j] = g.costMatrix[i][j];
            }
        }
        for (int k = 0; k < g.numberOfVerticies; k++) {
            for (int i = 0; i < g.numberOfVerticies; i++) {
                for (int j = 0; j < g.numberOfVerticies; j++) {
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
                }
            }
        }
        return D;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of verticies:  ");
        int numberOfVerticies = sc.nextInt();
        FGraph g = new FGraph(numberOfVerticies);
        int D[][] = floyd(g);
        System.out.println("Shortest Paths' Distance matrix: ");
        for (int i = 0; i < g.numberOfVerticies; i++) {
            for (int j = 0; j < g.numberOfVerticies; j++) {
                System.out.printf("%3d",D[i][j]);
            }
            System.out.println();
        }
        sc.close();
    }
}
