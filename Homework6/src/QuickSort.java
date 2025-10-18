public class QuickSort extends SortAlgorithm {

    public QuickSort(int input_array[]) {
        super(input_array);
    }

    private int partition(int min, int max){
        int pivot = arr[max]; // I assign the pivot as the last Index of the array
        int i = min - 1;
        for(int j=min; j<max; j++){
            comparison_counter++;
            if(arr[j] < pivot){ // In every step the numbers sorted according to pivot.
                // when the leftside is less than the pivot I increase the i because I'll swap them after this if statement.
                i++;
                swap(i,j);
            }
        }
        i++;
        swap(i, max);
        // After all of the steps, numbers sorted if they are less than pivot or greater than pivot.
        // Number may not be sorted with each other, I'll do it with the private sort method.
        return i; // I return the index of the pivot to use it in the private sort method.
    }

    private void sort(int min, int max){
        if(max <= min) {return;}
            int pivot = partition(min, max); // I use the method partition to use pivot to sort them.
            sort(min, pivot - 1); // I sort the numbers less than the pivot.
            sort(pivot + 1, max); // I sort the numbers are greater than the pivot.
    }

    @Override
    public void sort() {
        sort(0,arr.length-1);
    }

    @Override
    public void print() {
        System.out.print("Quick Sort\t=>\t");
        super.print();
    }
}
