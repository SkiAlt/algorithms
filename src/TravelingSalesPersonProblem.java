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

    static int ogSource;
    static LinkedList<Integer> visitSequence = new LinkedList<>();

    // graphSoln is useless as you are never really tabulating the results

    static int travelingSalesPerson(TGraph g, int source, LinkedList<Integer> subset) {
        if (subset.isEmpty()) {
            return g.costMatrix[source][ogSource];
        }

        int intermediateCosts[] = new int[g.n];
        for (int vertex = 0; vertex < g.n; vertex++)
            intermediateCosts[vertex] = 9999;

        for (int vertex : subset) {
            LinkedList<Integer> newSubset = new LinkedList<>(subset);
            newSubset.removeFirstOccurrence(vertex);
            System.out.println("newSubset:" + newSubset); // debug line
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

        //System.out.printf("\n\n\tDEBUG\tsource: %d, minPathVertex: %d ", source, minPathVertex);
        visitSequence.push(minPathVertex + 1);
        return g.costMatrix[source][minPathVertex] + intermediateCosts[minPathVertex];
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter no of verticies:  ");
        int n = s.nextInt();
        TGraph g = new TGraph(n);
        System.out.println("Enter the source vertex: ");
        ogSource = s.nextInt();
        LinkedList<Integer> subset = new LinkedList<>();
        for (int vertex = 0; vertex < n; vertex++) {
            if (vertex != ogSource)
                subset.add(vertex);
        }
        visitSequence.push(ogSource + 1);
        int totalCost = travelingSalesPerson(g, ogSource, subset);
        visitSequence.push(ogSource + 1);

        System.out.println("\n\nVisiting sequence:");
        System.out.println(visitSequence);
        System.out.println("Total tour cost: " + totalCost);
        s.close();
    }
}
/*
 * sample input
  0 10 15 20
  5 0 9 10
  6 13 0 12
  8 8 9 0
 */