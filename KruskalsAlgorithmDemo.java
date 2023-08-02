import java.util.Scanner;

class KGraph {
    int costMatrix[][];
    int numberOfVerticies;
    int parent[];

    KGraph(int numberOfVerticies) {
        this.numberOfVerticies = numberOfVerticies;
        costMatrix = new int[numberOfVerticies][numberOfVerticies];
        parent = new int[numberOfVerticies];
        for (int i = 0; i < numberOfVerticies; i++) {
            // intially each vertex is
            // the representative of it's own set.
            parent[i] = i;
        }
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

class KruskalsAlgorithmDemo {
    static int totalCost = 0;

    static int find(int vertex, KGraph g) {
        while (g.parent[vertex] != vertex)
            vertex = g.parent[vertex];
        return vertex;
    }

    static void union(int v1, int u1, KGraph g) {
        // we can interchange u1 and v1 here
        // that will also work just fine.
        g.parent[v1] = u1;
    }

    static void kruskal(KGraph g) {
        int edgeCount = 0, u1 = 0, v1 = 0;
        System.out.println("\nThe edges are:");
        while (edgeCount < g.numberOfVerticies - 1) {
            // finding minimum edge
            int minWeight = 99999;
            for (int v = 0; v < g.numberOfVerticies; v++) {
                for (int u = 0; u < g.numberOfVerticies; u++) {
                    if (g.costMatrix[v][u] < minWeight) {
                        minWeight = g.costMatrix[v][u];
                        v1 = v;
                        u1 = u;
                    }
                }
            }
            // found min edge (v1, u1) of weight minWeight
            int parentOfv = find(v1, g);
            int parentOfu = find(u1, g);
            if (parentOfu != parentOfv) {
                // u1 and v1 belong to disjoint sets. ie:
                // after adding edge (v1, u1)
                // spanning tree remains acyclic
                System.out.printf("(%d,%d)\n", v1, u1);
                union(v1, u1, g);
                totalCost += minWeight;
                g.costMatrix[v1][u1] = g.costMatrix[u1][v1] = 99999;
                edgeCount++;
            }
        }
        System.out.println("\nTotal cost: " + totalCost);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of verticies:  ");
        int numberOfVerticies = sc.nextInt();
        KGraph g = new KGraph(numberOfVerticies);
        kruskal(g);
        sc.close();
    }
}
