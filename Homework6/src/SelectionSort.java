public class SelectionSort extends SortAlgorithm {

    public SelectionSort(int input_array[]) {
        super(input_array);
    }

    @Override
    public void sort() {
        for(int i=0; i < arr.length - 1; i++){
            int min =  i; // every loop the min number will be the index i.
            for(int j= i + 1; j < arr.length; j++){
                comparison_counter++;
                if(arr[min] > arr[j]){
                    // I change the indexes if the index min is greater than the index j.
                    min = j;
                }
            }
            swap(i,min); // Finally I swap the last number that doesn't sorted.
        }
// So basically in this method, I assign a number into the array as the min and compare it with the other numbers inside of the array.
// then compare them according to the if statement I swap the numbers.
    }

    @Override
    public void print() {
        System.out.print("Selection Sort\t=>\t");
        super.print();
    }
}

