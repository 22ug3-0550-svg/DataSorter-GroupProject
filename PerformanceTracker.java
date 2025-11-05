public class PerformanceTracker {
    // This will help time any sorting function
    public static SortResult timeSort(String name, SortFunction sorter, int[] data) {
        int[] copy = data.clone();
        long start = System.nanoTime();
        int[] sorted = sorter.sort(copy);
        long end = System.nanoTime();
        long time = end - start;
        return new SortResult(name, sorted, 0, time); // steps filled later
    }
}

// Helper interface so we can pass any sort function
interface SortFunction {
    int[] sort(int[] arr);
}