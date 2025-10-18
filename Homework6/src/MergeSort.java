public class MergeSort extends SortAlgorithm {

    public MergeSort(int input_array[]) {
        super(input_array);
    }

    private void merge(int left, int mid,  int right){
        // firstly I calculate the size of the arrays to use them later.
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int L[] = new int[n1];
        int R[] = new int[n2];

        // I copy the data to temporary arrays L[] and R[]
        // I use temporary arrays to make it easy the sort them
        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];
        int i = 0, j = 0;

        // I merge the two subarrays by comparing elements and increase the comparison number.
        int k = left;
        while (i < n1 && j < n2) {
            comparison_counter++;
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // I copy the remaining elements of L[], if any element exists
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // I copy the remaining elements of R[], if any element exists
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private void sort(int left, int right){
        if (left < right) { // firstly I checked the elements if they are not sorted it'll sort
            int mid = (left + right) / 2; // I divide two parts and decide the middle of the array.
            sort(left, mid); // I sort the left part of the array.
            sort(mid + 1, right); // I sort the right part of the array

            // after sorting the two parts I merge them.
            merge(left, mid, right);
        }
    }

    @Override
    public void sort() {
        sort(0, arr.length-1);
    }

    @Override
    public void print() {
        System.out.print("Merge Sort\t=>\t");
        super.print();
    }
}
