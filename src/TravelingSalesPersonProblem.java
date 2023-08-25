import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;

class TGraph {
    int c[][];
    int n;
    // boolean visited[];

    TGraph(int n) {
        this.n = n;
        c = new int[n][n];
        // visited = new boolean[n];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter cost matrix:");
        for (int v = 0; v < n; v++) {
            for (int u = 0; u < n; u++) {
                int edgeWeight = sc.nextInt();
                if (edgeWeight == 0)
                    c[v][u] = 99999;
                else
                    c[v][u] = edgeWeight;
            }
        }
    }
}

public class TravelingSalesPersonProblem {

    static int ogSource;
    static int J[];

    static int travelingSalesPerson(TGraph g, int source, LinkedList<Integer> subset) {
        if (subset.isEmpty()) {
            //J[source] = ogSource;
            return g.c[source][ogSource];
        }
        int intermediateCosts[] = new int[g.n];
        for (int vertex = 0; vertex < g.n; vertex++)
            intermediateCosts[vertex] = 9999;

        for (int vertex : subset) {
            LinkedList<Integer> newSubset = new LinkedList<>(subset);
            newSubset.removeFirstOccurrence(vertex);
            intermediateCosts[vertex] = g.c[source][vertex] + travelingSalesPerson(g, vertex, newSubset);
        }

        int tempweight = 9999;
        int minPathVertex = -1;
        for (int v = 0; v < g.n; v++) {
            if (intermediateCosts[v] < tempweight) {
                tempweight = intermediateCosts[v];
                minPathVertex = v;
            }
        }
        // I still havent figured out how to print visit sequence
        J[source] = minPathVertex;
        return intermediateCosts[minPathVertex];
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter no of verticies:  ");
        int n = s.nextInt();
        TGraph g = new TGraph(n);

        J = new int[n];
        for (int i = 0; i < n; i++)
                J[i] = 9999;

        System.out.println("Enter the source vertex: ");
        ogSource = s.nextInt();
        LinkedList<Integer> subset = new LinkedList<>();
        for (int vertex = 0; vertex < n; vertex++) {
            if (vertex != ogSource)
                subset.add(vertex);
        }

        int totalCost = travelingSalesPerson(g, ogSource, subset);

        System.out.println("Total tour cost: " + totalCost);
        s.close();
        System.out.println(Arrays.toString(J));

    }
}
/*
 * sample input
 * 0 10 15 20 5 0 9 10 6 13 0 12 8 8 9 0
 */