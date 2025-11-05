import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> DataManager.enterManually();
                case "2" -> DataManager.generateRandom();
                case "3" -> runSingleSort("Bubble Sort", Main::runBubble);
                case "4" -> runSingleSort("Merge Sort", Main::runMerge);
                case "5" -> runSingleSort("Quick Sort", Main::runQuick);
                case "6" -> compareAll();
                case "7" -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Data Sorter: Sorting Algorithm Comparison Tool ---");
        System.out.println("1. Enter numbers manually");
        System.out.println("2. Generate random numbers");
        System.out.println("3. Perform Bubble Sort");
        System.out.println("4. Perform Merge Sort");
        System.out.println("5. Perform Quick Sort");
        System.out.println("6. Compare all algorithms (show performance table)");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    // Run one sort and show result
    private static void runSingleSort(String name, SortRunner runner) {
        if (!DataManager.hasData()) {
            System.out.println("No data! Please enter or generate numbers first.");
            return;
        }
        SortResult result = runner.run();
        System.out.println(name + " Result:");
        System.out.println("   Sorted: " + java.util.Arrays.toString(result.sortedArray));
        System.out.println("   Steps: " + result.steps);
        System.out.println("   Time: " + result.timeTaken + " ns");
    }

    // Helper to run Bubble
    private static SortResult runBubble() {
        BubbleSort.Result r = BubbleSort.sort(DataManager.getData());
        return new SortResult("Bubble Sort", r.sortedArray, r.steps, 0);
    }

    // Helper to run Merge
    private static SortResult runMerge() {
        MergeSort.Result r = MergeSort.sort(DataManager.getData());
        return new SortResult("Merge Sort", r.sortedArray, r.steps, r.timeTaken);
    }

    // Helper to run Quick
    private static SortResult runQuick() {
        QuickSort.Result r = QuickSort.sort(DataManager.getData());
        return new SortResult("Quick Sort", r.sortedArray, r.steps, r.timeTaken);
    }

    // Compare all three
    private static void compareAll() {
        if (!DataManager.hasData()) {
            System.out.println("No data! Please enter or generate first.");
            return;
        }

        int[] data = DataManager.getData();

        // Run all sorts
        BubbleSort.Result bubble = BubbleSort.sort(data);
        MergeSort.Result merge = MergeSort.sort(data);
        QuickSort.Result quick = QuickSort.sort(data);

        // Build results
        SortResult b = new SortResult("Bubble Sort", bubble.sortedArray, bubble.steps, 0);
        SortResult m = new SortResult("Merge Sort", merge.sortedArray, merge.steps, merge.timeTaken);
        SortResult q = new SortResult("Quick Sort", quick.sortedArray, quick.steps, quick.timeTaken);

        // Print table
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        PERFORMANCE COMPARISON TABLE");
        System.out.println("=".repeat(50));
        System.out.printf("%-15s %-12s %-15s%n", "Algorithm", "Steps", "Time (ns)");
        System.out.println("-".repeat(50));
        System.out.printf("%-15s %-12d %-15d%n", b.algorithmName, b.steps, measureTime(() -> BubbleSort.sort(data)));
        System.out.printf("%-15s %-12d %-15d%n", m.algorithmName, m.steps, m.timeTaken);
        System.out.printf("%-15s %-12d %-15d%n", q.algorithmName, q.steps, q.timeTaken);
        System.out.println("=".repeat(50));
    }

    // Measure time for any sort
    private static long measureTime(SortTask task) {
        long start = System.nanoTime();
        task.run();
        return System.nanoTime() - start;
    }
}

// Helper interfaces
interface SortRunner {
    SortResult run();
}

interface SortTask {
    void run();
}