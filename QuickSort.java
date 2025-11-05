public class QuickSort {
    public static class Result {
        public int[] sortedArray;
        public int steps;
        public long timeTaken;

        public Result(int[] sortedArray, int steps, long timeTaken) {
            this.sortedArray = sortedArray;
            this.steps = steps;
            this.timeTaken = timeTaken;
        }
    }

    public static Result sort(int[] original) {
        int[] arr = original.clone();
        long start = System.nanoTime();
        int[] stepCount = {0};
        sortHelper(arr, 0, arr.length - 1, stepCount);
        long end = System.nanoTime();
        return new Result(arr, stepCount[0], end - start);
    }

    private static void sortHelper(int[] arr, int low, int high, int[] stepCount) {
        if (low < high) {
            if (high - low < 10) {
                insertionSort(arr, low, high, stepCount);
                return;
            }
            int pi = partition(arr, low, high, stepCount);
            sortHelper(arr, low, pi - 1, stepCount);
            sortHelper(arr, pi + 1, high, stepCount);
        }
    }

    private static void insertionSort(int[] arr, int low, int high, int[] stepCount) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key) {
                stepCount[0]++; // comparison
                arr[j + 1] = arr[j];
                stepCount[0]++; // shift
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static int partition(int[] arr, int low, int high, int[] stepCount) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            stepCount[0]++; // comparison
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
                stepCount[0]++; // swap
            }
        }
        swap(arr, i + 1, high);
        stepCount[0]++; // final swap
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}