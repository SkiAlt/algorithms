/*Topological Sorting using Source Removal*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*DAGraph Implimentaion for Topological Sorting*/


class DAGraph {
    boolean adjacencyMatrix[][];
    boolean visited[];
    int inDegree[];
    int noOfVerticies;

    DAGraph(int noOfVerticies) {
        this.noOfVerticies = noOfVerticies;
        adjacencyMatrix = new boolean[noOfVerticies][noOfVerticies];
        visited = new boolean[noOfVerticies];
        inDegree = new int[noOfVerticies];
    }

    void initAdjacencyMatrix() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input 1 if edge exists. any other number if edge doesnt exist:");
        for (int i = 0; i < noOfVerticies; i++) {
            for (int j = 0; j < noOfVerticies; j++) {
                if (i == j)
                    continue;
                if (adjacencyMatrix[j][i]) // assuming DAG
                    continue;
                System.out.print(i + "-->" + j + "? : ");
                if (sc.nextInt() == 1)
                    adjacencyMatrix[i][j] = true;
            }
        }
        sc.close();
        for (int i = 0; i < noOfVerticies; i++) {
            for (int j = 0; j < noOfVerticies; j++) {
                if (adjacencyMatrix[j][i])
                    inDegree[i]++;
            }
        }
    }
}
public class TopologicalSort {
    static LinkedList<Integer> topologicalSort(DAGraph g) {
        Queue<Integer> queue = new LinkedList<>();
        LinkedList<Integer> order = new LinkedList<>();
        for (int i = 0; i < g.noOfVerticies; i++) {
            if (g.inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int v = queue.remove();
            order.add(v);
            for (int i = 0; i < g.noOfVerticies; i++) {
                if (g.adjacencyMatrix[v][i]) {
                    g.inDegree[i]--;
                    if (g.inDegree[i] == 0)
                        queue.add(i);
                }
            }
        }
        return order;
    }

    public static void main(String[] args) {
        int noOfVerticies = 6;
        DAGraph g = new DAGraph(noOfVerticies);
        g.initAdjacencyMatrix();
        System.out.println(topologicalSort(g).toString());
    }
}
