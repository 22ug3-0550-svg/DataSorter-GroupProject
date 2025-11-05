import java.util.Random;
import java.util.Scanner;

public class DataManager {
    private static int[] data = null;
    private static Scanner scanner = new Scanner(System.in);

    // Option 1: Enter numbers manually
    public static void enterManually() {
        System.out.print("Enter numbers (space-separated): ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("No input! Try again.");
            return;
        }
        String[] parts = input.split("\\s+");
        data = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            try {
                data[i] = Integer.parseInt(parts[i]);
            } catch (NumberFormatException e) {
                System.out.println("Error: '" + parts[i] + "' is not a number!");
                data = null;
                return;
            }
        }
        System.out.println("Your data: " + java.util.Arrays.toString(data));
    }

    // Option 2: Generate random numbers
    public static void generateRandom() {
        System.out.print("How many numbers? (1-100): ");
        String input = scanner.nextLine();
        int size;
        try {
            size = Integer.parseInt(input);
            if (size < 1 || size > 100) {
                System.out.println("Please enter a number between 1 and 100.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Must be a number.");
            return;
        }
        Random rand = new Random();
        data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = rand.nextInt(1000); // 0 to 999
        }
        System.out.println("Generated: " + java.util.Arrays.toString(data));
    }

    // Get current data
    public static int[] getData() {
        return data;
    }

    // Check if data exists
    public static boolean hasData() {
        return data != null && data.length > 0;
    }
}