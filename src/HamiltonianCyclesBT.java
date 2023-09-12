import java.util.Scanner;
import java.util.LinkedList;

class HGraph {
    boolean adjacenyMatrix[][];
    int n;
    boolean visited[];

    HGraph(int n) {
        this.n = n;
        adjacenyMatrix = new boolean[n][n];
        visited = new boolean[n];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter adjacency matrix:");
        for (int v = 0; v < n; v++) {
            for (int u = 0; u < n; u++) {
                int edge = sc.nextInt();
                adjacenyMatrix[v][u] = (edge == 1);
            }
        }
        sc.close();
    }
}

public class HamiltonianCyclesBT {

    static LinkedList<Integer> x;// global solution list

    static void hamiltonianCycles(HGraph g, int v) {
        g.visited[v] = true;
        x.push(v);
        // check if cycle has completed
        boolean cycleComplete = g.adjacenyMatrix[v][0];
        for (int i = 0; i < g.n; i++) {
            if (!g.visited[i])
                cycleComplete = false;
        }
        if (cycleComplete) {
            x.push(0);
            System.out.println(x);
            x.pop();
        } else {
            for (int i = 0; i < g.n; i++) {
                if (!g.visited[i] && g.adjacenyMatrix[v][i]) {
                    hamiltonianCycles(g, i);
                }
            }
        }
        // back tracking code
        g.visited[v] = false;
        x.pop();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter number of verticies:");
        int n = s.nextInt();
        HGraph g = new HGraph(n);
        x = new LinkedList<>();
        hamiltonianCycles(g, 0);
        s.close();
    }
}
/*
 0 1 1 1 0 0
 1 0 1 0 0 1
 1 1 0 1 1 0
 1 0 1 0 1 0
 0 0 1 1 0 1
 0 1 0 0 1 0
 */