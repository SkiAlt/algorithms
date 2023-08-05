import java.util.Scanner;

class Heaps {

    int[] heap;

    Heaps(int size) {
        heap = new int[size + 1];
        heap[0] = -1;
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i < heap.length; i++) {
            heap[i] = sc.nextInt();
        }
        sc.close();
    }

    void sort() {
        int heapSize = heap.length - 1;
        for (int i = 0; i < heapSize - 1; i++) {
            // applying the root deletion operation
            // n - 1 times.
            int lastKeyIndex = heapSize - i;
            int temp = heap[1];
            heap[1] = heap[lastKeyIndex];
            heap[lastKeyIndex] = temp;
            heapify(lastKeyIndex - 1);
        }
    }

    // implimenting a max heap
    void heapify(int length) {
        for (int i = length / 2; i >= 1; i--) {
            int parentIndex = i;
            int parent = heap[parentIndex];
            boolean isHeap = false;
            while (!isHeap && 2 * parentIndex <= length) {
                int childIndex = 2 * parentIndex;
                if (childIndex < length - 1) {
                    // there are two children
                    if (heap[childIndex] < heap[childIndex + 1])
                        childIndex += 1;
                    // chosing the larger child
                    // to swap later
                }
                if (parent >= heap[childIndex]) {
                    isHeap = true;
                } else {
                    heap[parentIndex] = heap[childIndex];
                    parentIndex = childIndex; // ?
                }
            }
            heap[parentIndex] = parent;
        }
    }

    void printHeap() {
        System.out.print("< ");
        for (int i = 1; i < heap.length; i++)
            System.out.print(heap[i] + " ");
        System.out.println(">");
    }

    // below is demoDriverCode
    // comment if using as an ADT
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Heap size: ");
        int size = s.nextInt();
        System.out.println("Enter elements of heap:");
        Heaps H = new Heaps(size);
        H.heapify(H.heap.length);
        System.out.println("\nHeapified:");
        H.printHeap();
        H.sort();
        System.out.println("\nSorted:");
        H.printHeap();
        s.close();
    }
}