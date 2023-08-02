import java.util.Arrays;
import java.util.Scanner;

class DGraph {
    int costMatrix[][];
    boolean processed[];
    int numberOfVerticies;

    DGraph(int numberOfVerticies) {
        this.numberOfVerticies = numberOfVerticies;
        costMatrix = new int[numberOfVerticies][numberOfVerticies];
        processed = new boolean[numberOfVerticies];
        // initilizing cost matrix:
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter cost matrix:");
        for (int v = 0; v < numberOfVerticies; v++) {
            for (int u = 0; u < numberOfVerticies; u++) {
                int edgeWeight = sc.nextInt();
                if (edgeWeight == 0)
                    costMatrix[v][u] = 99999;
                else
                    costMatrix[v][u] = edgeWeight;
            }
        }
        sc.close();
    }
}

class DijkstrasAlgorithmDemo {
    static int[] dijkstra(DGraph g, int source) {
        int distance[] = new int[g.numberOfVerticies];
        int w = 0;
        for (int i = 0; i < g.numberOfVerticies; i++) {
            // initally assume shortest distance from
            // source to be same as cost matrix row
            distance[i] = g.costMatrix[source][i];
        }
        distance[source] = 0;
        g.processed[source] = true;
        for (int i = 0; i < g.numberOfVerticies - 1; i++) {
            int tempWeight = 99999;
            for (int u = 0; u < g.numberOfVerticies; u++) {
                if (distance[u] < tempWeight && !g.processed[u]) {
                    tempWeight = distance[u];
                    w = u;
                }
            } // now we have found the smallest edge
              // to an unprocessed vertex

            // update all distances from source considering
            // w to be processed
            g.processed[w] = true;
            for (int v = 0; v < g.numberOfVerticies; v++) {
				if(!g.processed[v])
                	distance[v] = Math.min(distance[v], distance[w] + g.costMatrix[w][v]);
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter no of verticies:  ");
        int numberOfVerticies = in.nextInt();
        System.out.println("Enter the source vertex:  ");
        int source = in.nextInt();
        DGraph g = new DGraph(numberOfVerticies);
        int distance[] = dijkstra(g, source);
        System.out.println(Arrays.toString(distance));
        in.close();
    }
}