import java.util.Scanner;
import java.util.LinkedList;

class TGraph {
    int costMatrix[][];
    int n;
    // boolean visited[];

    TGraph(int n) {
        this.n = n;
        costMatrix = new int[n][n];
        // visited = new boolean[n];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter cost matrix:");
        for (int v = 0; v < n; v++) {
            for (int u = 0; u < n; u++) {
                int edgeWeight = sc.nextInt();
                if (edgeWeight == 0)
                    costMatrix[v][u] = 99999;
                else
                    costMatrix[v][u] = edgeWeight;
            }
        }
    }
}

public class TravelingSalesPersonProblem {

    static int graphSoln[][], ogSource;

    static int travelingSalesPerson(TGraph g, int source, LinkedList<Integer> subset) {
        if (subset.isEmpty()) {
            return g.costMatrix[source][ogSource];
        }
        int intermediateCosts[] = new int[g.n];
        for (int vertex = 0; vertex < g.n; vertex++)
            intermediateCosts[vertex] = 9999; // from source to vertex
        LinkedList<Integer> newSubset = new LinkedList<>(subset);
        newSubset.removeFirstOccurrence(source); 
        for (int vertex : subset) {
            intermediateCosts[vertex] = travelingSalesPerson(g, vertex, newSubset);
        }
        int tempweight = 9999;
        int minPathVertex = -1;
        for (int v = 0; v < g.n; v++) {
            if (intermediateCosts[v] < tempweight) {
                tempweight = intermediateCosts[v];
                minPathVertex = v;
            }
        }
        graphSoln[source][minPathVertex] = g.costMatrix[source][minPathVertex] + intermediateCosts[minPathVertex];
        System.out.println(source + " --> " + minPathVertex);
        return graphSoln[source][minPathVertex];
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter no of verticies:  ");
        int n = s.nextInt();
        TGraph g = new TGraph(n);
        graphSoln = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                graphSoln[i][j] = 9999;
        System.out.println("Enter the source vertex: ");
        ogSource = s.nextInt();
        LinkedList<Integer> subset = new LinkedList<>();
        for (int vertex = 0; vertex < n; vertex++) {
            subset.add(vertex);
        }
        travelingSalesPerson(g, ogSource, subset);

        s.close();
    }
}
