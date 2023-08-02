import java.util.Scanner;

class PGraph {
    int costMatrix[][];
    boolean visited[];
    int numberOfVerticies;

    PGraph(int numberOfVerticies) {
        this.numberOfVerticies = numberOfVerticies;
        costMatrix = new int[numberOfVerticies][numberOfVerticies];
        visited = new boolean[numberOfVerticies];
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

class PrimsAlgorithmDemo {
    static int totalCost = 0;
    static void prim(PGraph g) {
        int v, u, v1, u1;
        v = u = v1 = u1 = 0;
        System.out.println("The edges are:");
        // starting with arbitrary vertex 0
        g.visited[0] = true;
        for (int i = 0; i < g.numberOfVerticies - 1; i++) {
            // finding minimum edge ST e = (v1, u1) among
            // all edges (v,u) where v is visted and u unvisited.
            int minWeight = 99999;
            for (v = 0; v < g.numberOfVerticies; v++) {
                if(!g.visited[v])
                    continue;
                // skiping v that aren't visited yet
                for (u = 0; u < g.numberOfVerticies; u++) {
                    if(g.visited[u])
                        continue;
                    // skiping u that is already visited
                    if(g.costMatrix[v][u] < minWeight) {
                        minWeight = g.costMatrix[v][u];
                        v1 = v;
                        u1 = u;
                    }
                }
            }
            g.visited[u1] = true;
            System.out.printf("(%d,%d)\n", v1, u1);
            totalCost+=minWeight;
        }
        System.out.println("Total cost: " +  totalCost);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of verticies:  ");
        int numberOfVerticies = sc.nextInt();
        PGraph g = new PGraph(numberOfVerticies);
        prim(g);
        sc.close();
    }
}
