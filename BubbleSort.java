public class BubbleSort {
    public static class Result {
        public int[] sortedArray;
        public int steps;

        public Result(int[] sortedArray, int steps) {
            this.sortedArray = sortedArray;
            this.steps = steps;
        }
    }

    public static Result sort(int[] original) {
        int[] arr = original.clone();
        int n = arr.length;
        int steps = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                steps++; // comparison
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    steps++; // swap
                }
            }
        }
        return new Result(arr, steps);
    }
}