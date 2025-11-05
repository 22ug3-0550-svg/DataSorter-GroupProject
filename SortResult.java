public class SortResult {
    public String algorithmName;
    public int[] sortedArray;
    public int steps;
    public long timeTaken;

    public SortResult(String algorithmName, int[] sortedArray, int steps, long timeTaken) {
        this.algorithmName = algorithmName;
        this.sortedArray = sortedArray;
        this.steps = steps;
        this.timeTaken = timeTaken;
    }
}