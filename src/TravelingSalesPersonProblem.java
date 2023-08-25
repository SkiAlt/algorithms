import java.util.Scanner;
import java.util.LinkedList;

class TGraph {
    int c[][];
    int n;

    TGraph(int n) {
        this.n = n;
        c = new int[n][n];
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
        sc.close();
    }
}

class Sequence {
    int cost = 9999;
    LinkedList<Integer> J = new LinkedList<>();
}

public class TravelingSalesPersonProblem {

    static int ogSource;

    static Sequence travelingSalesPerson(TGraph g, int source, LinkedList<Integer> subset) {

        Sequence s = new Sequence();
        Sequence subSequence[] = new Sequence[g.n];
        if (subset.isEmpty()) {
            s.J.add(ogSource);
            s.cost = g.c[source][ogSource];
            return s;
        }
        int intermediateCosts[] = new int[g.n];
        for (int vertex = 0; vertex < g.n; vertex++)
            intermediateCosts[vertex] = 9999;

        for (int vertex : subset) {
            LinkedList<Integer> newSubset = new LinkedList<>(subset);
            newSubset.removeFirstOccurrence(vertex);
            subSequence[vertex] = travelingSalesPerson(g, vertex, newSubset);
            intermediateCosts[vertex] = g.c[source][vertex] + subSequence[vertex].cost;
        }

        int tempweight = 9999;
        int minPathVertex = -1;
        for (int v = 0; v < g.n; v++) {
            if (intermediateCosts[v] < tempweight) {
                tempweight = intermediateCosts[v];
                minPathVertex = v;
            }
        }
        s.J = new LinkedList<>(subSequence[minPathVertex].J);
        s.J.add(minPathVertex);
        s.cost = intermediateCosts[minPathVertex];
        return s;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of verticies:  ");
        int n = sc.nextInt();
        System.out.println("Enter the source vertex: ");
        ogSource = sc.nextInt();
        TGraph g = new TGraph(n);
        LinkedList<Integer> subset = new LinkedList<>();
        for (int vertex = 0; vertex < n; vertex++) {
            if (vertex != ogSource)
                subset.add(vertex);
        }

        Sequence tour = travelingSalesPerson(g, ogSource, subset);

        //displaying result
        int totalCost = tour.cost;
        System.out.println("Total tour cost: " + totalCost);
        sc.close();
        System.out.printf("%d", ogSource + 1);
        while(!tour.J.isEmpty()){
            System.out.printf("-->%d",(tour.J.removeLast() + 1));
        }
        System.out.println();
        sc.close();
    }
}
/*
 * sample input
 * 0 10 15 20 50 0 9 10 6 13 0 12 8 8 9 0
 */