public class BubbleSort extends SortAlgorithm {

    public BubbleSort(int input_array[]) {
        super(input_array);
    }

    @Override
    public void sort() {
        boolean swapped; // I add this boolean variable to check the swapped.
        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                comparison_counter++;
                if (arr[j] > arr[j + 1]) { // I check if the left side number is greater than the rightside number.
                    // I change the locations of the numbers.
                    swap(j, j + 1);
                    swapped = true;
                }
            }
            // with this boolean variable it will minimize the number of comparison.
            // Otherwise it will always show 28 as the comparison number.
            if (!swapped) {
                break;
            }
        }
    }

    @Override
    public void print() {
        System.out.print("Bubble Sort\t=>\t");
        super.print();
    }
}
