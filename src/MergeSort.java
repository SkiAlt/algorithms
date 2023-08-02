import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class MergeSort {
	static int basicOpCount = 0;

	static void merge(int b[], int c[], int a[]) {
		int i = 0, j = 0, k = 0;
		while (i < b.length && j < c.length) {
			basicOpCount++;
			if (b[i] < c[j])
				a[k++] = b[i++];
			else
				a[k++] = c[j++];
		}
		while (i < b.length) {
			a[k++] = b[i++];
		}
		while (j < c.length) {
			a[k++] = c[j++];
		}
	}

	static void sort(int a[]) {
		if (a.length > 1) {
			int b[] = Arrays.copyOfRange(a, 0, a.length / 2);
			int c[] = Arrays.copyOfRange(a, a.length / 2, a.length);
			sort(b);
			sort(c);
			merge(b, c, a);
		}
	}

	public static void main(String[] args) throws IOException {
		int size = 10000;
		int a[] = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            a[i] = rand.nextInt(size) + 1;
        }
		sort(a);
		System.out.println("1. Merge Sort running time only depends upon size of input.");
		System.out.println("   Hence single case analysis.");
		System.out.println("2. Basic Operation: comparison");
		System.out.println("3. No of times Basic Operation was executed C(n) = " + basicOpCount);
	}
}
