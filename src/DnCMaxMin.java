class MaxMin {
    int max;
    int min;
}

class DnCMaxMin {

    static void findMaxMin(int a[], MaxMin mm, int low, int high) {
        if (low == high) { 
            mm.max = mm.min = a[low]; 
        } else if (low == high - 1) {
            if (a[low] > a[high]) { 
                mm.max = a[low];
                mm.min = a[high];
            } else {
                mm.max = a[high];
                mm.min = a[low];
            }
        } else {
            MaxMin mmTemp = new MaxMin();
            int mid = (low + high) / 2;
            findMaxMin(a, mm, low, mid); 
            findMaxMin(a, mmTemp, mid, high);
            if (mm.max < mmTemp.max)
                mm.max = mmTemp.max;
            if (mm.min > mmTemp.min)
                mm.min = mmTemp.min;
        }
    }

    public static void main(String[] args) {
        int a[] = { 1, 2, 3, 4, 5 };
        MaxMin mm = new MaxMin(); 
        int low = 0, high = a.length - 1;
        findMaxMin(a, mm, low, high);
        System.out.println("Max = " + mm.max + "\nMin = " + mm.min);
    }
}
