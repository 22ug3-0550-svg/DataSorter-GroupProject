public class MergeSort {
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
        int[] stepCount = {0};
        int[] sorted = sortHelper(arr, stepCount);
        return new Result(sorted, stepCount[0]);
    }

    private static int[] sortHelper(int[] arr, int[] stepCount) {
        if (arr.length <= 1) return arr;
        int mid = arr.length / 2;
        int[] left = sortHelper(copy(arr, 0, mid), stepCount);
        int[] right = sortHelper(copy(arr, mid, arr.length), stepCount);
        return merge(left, right, stepCount);
    }

    private static int[] copy(int[] arr, int start, int end) {
        int[] result = new int[end - start];
        for (int i = 0; i < result.length; i++) {
            result[i] = arr[start + i];
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right, int[] stepCount) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            stepCount[0]++; // comparison
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
        return result;
    }
}