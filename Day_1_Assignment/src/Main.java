import java.io.*;
import java.util.*;

public class Main {

    // Read the CSV file and store each column in a separate array.
    static int loadOrders(String fileName, String[] orderIds,
                          String[] customerIds, double[] amounts,
                          String[] createdAt) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine(); // Skip the heading row
        int count = 0;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            orderIds[count] = parts[0];
            customerIds[count] = parts[1];
            amounts[count] = Double.parseDouble(parts[2]);
            createdAt[count] = parts[3];
            count++;
        }

        reader.close();
        return count;
    }

    // TASK 1: Linear Search
    static int linearSearch(String[] ids, int size, String target) {
        for (int i = 0; i < size; i++) {
            if (ids[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    // TASK 1: Binary Search (array must be sorted)
    static int binarySearch(String[] ids, String target) {
        int low = 0;
        int high = ids.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (ids[mid].equals(target)) {
                return mid;
            } else if (ids[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // TASK 2: Bubble Sort - descending order
    static void bubbleSort(double[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] < a[j + 1]) {
                    double temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    // TASK 2: Merge Sort - descending order
    static void mergeSort(double[] a, int left, int right) {
        if (left >= right) {
            return;
        }

        int middle = (left + right) / 2;
        mergeSort(a, left, middle);
        mergeSort(a, middle + 1, right);

        double[] temp = new double[right - left + 1];
        int i = left;
        int j = middle + 1;
        int k = 0;

        // Merge the two parts in descending order
        while (i <= middle && j <= right) {
            if (a[i] >= a[j]) {
                temp[k] = a[i];
                i++;
            } else {
                temp[k] = a[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            temp[k] = a[i];
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = a[j];
            j++;
            k++;
        }

        for (int x = 0; x < temp.length; x++) {
            a[left + x] = temp[x];
        }
    }

    // Print first few values to check sorting.
    static void printFirstFive(double[] a) {
        for (int i = 0; i < 5 && i < a.length; i++) {
            System.out.printf("%.2f ", a[i]);
        }
        System.out.println();
    }

    // TASK 3: Find duplicate orders using HashSet.
    static int findDuplicates(String[] customerIds, double[] amounts,
                              String[] createdAt, int size) {
        HashSet<String> seen = new HashSet<>();
        int duplicateCount = 0;

        for (int i = 0; i < size; i++) {
            // First 16 characters represent yyyy-MM-ddTHH:mm
            String minute = createdAt[i].substring(0, 16);
            String key = customerIds[i] + "-" + amounts[i] + "-" + minute;

            if (seen.contains(key)) {
                duplicateCount++;
            } else {
                seen.add(key);
            }
        }
        return duplicateCount;
    }

    // TASK 4: Simple Stack using an array.
    static class MyStack {
        String[] items = new String[100];
        int top = -1;

        void push(String value) {
            top++;
            items[top] = value;
        }

        String pop() {
            if (top == -1) {
                return "Stack is empty";
            }
            String value = items[top];
            top--;
            return value;
        }
    }

    // TASK 4: Simple Queue using an array.
    static class MyQueue {
        String[] items = new String[100];
        int front = 0;
        int rear = -1;

        void enqueue(String value) {
            rear++;
            items[rear] = value;
        }

        String dequeue() {
            if (front > rear) {
                return "Queue is empty";
            }
            String value = items[front];
            front++;
            return value;
        }
    }

    // TASK 5: Greedy method
    static int greedyChange(int amount, int[] coins) {
        int count = 0;

        // This example uses denominations in descending order: 4, 3, 1.
        for (int i = coins.length - 1; i >= 0; i--) {
            count = count + amount / coins[i];
            amount = amount % coins[i];
        }
        return count;
    }

    // TASK 5: Dynamic Programming method
    static int dpChange(int amount, int[] coins) {
        int[] dp = new int[amount + 1];

        // A large value means the amount cannot yet be formed.
        for (int i = 1; i <= amount; i++) {
            dp[i] = 999999;
        }

        dp[0] = 0;

        for (int value = 1; value <= amount; value++) {
            for (int coin : coins) {
                if (coin <= value && dp[value - coin] + 1 < dp[value]) {
                    dp[value] = dp[value - coin] + 1;
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) throws Exception {

        String fileName = "data/orders.csv";
        if (args.length > 0) {
            fileName = args[0];
        }

        String[] orderIds = new String[50000];
        String[] customerIds = new String[50000];
        double[] amounts = new double[50000];
        String[] createdAt = new String[50000];

        int size = loadOrders(fileName, orderIds, customerIds, amounts, createdAt);

        System.out.println("===== ONLINE RETAIL DSA ASSIGNMENT =====");
        System.out.println("Total orders loaded: " + size);

        // ---------------- TASK 1 ----------------
        System.out.println("\nTASK 1: SEARCHING");

        String target = "51000";

        long start = System.nanoTime();
        int linearResult = linearSearch(orderIds, size, target);
        long linearTime = System.nanoTime() - start;

        String[] sortedIds = Arrays.copyOf(orderIds, size);
        Arrays.sort(sortedIds);

        start = System.nanoTime();
        int binaryResult = binarySearch(sortedIds, target);
        long binaryTime = System.nanoTime() - start;

        System.out.println("Linear Search found: " + (linearResult >= 0 ? target : "Not found"));
        System.out.println("Binary Search found: " + (binaryResult >= 0 ? target : "Not found"));
        System.out.printf("Linear Search time: %.3f ms%n", linearTime / 1000000.0);
        System.out.printf("Binary Search time: %.3f ms%n", binaryTime / 1000000.0);
        System.out.println("Note: Binary Search needs sorted IDs.");

        // ---------------- TASK 2 ----------------
        System.out.println("\nTASK 2: SORTING AMOUNTS (DESCENDING)");

        double[] small = {450, 120, 700, 300, 550, 200, 900, 100};
        double[] trace = small.clone();

        System.out.println("Bubble Sort - first three passes:");
        for (int pass = 0; pass < 3; pass++) {
            for (int j = 0; j < trace.length - 1 - pass; j++) {
                if (trace[j] < trace[j + 1]) {
                    double temp = trace[j];
                    trace[j] = trace[j + 1];
                    trace[j + 1] = temp;
                }
            }
            System.out.print("Pass " + (pass + 1) + ": ");
            for (double value : trace) {
                System.out.print((int) value + " ");
            }
            System.out.println();
        }

        System.out.println("Merge Sort - result after each merge level:");
        double[] mergeTrace = small.clone();
        for (int width = 1; width < mergeTrace.length; width *= 2) {
            double[] next = new double[mergeTrace.length];

            for (int left = 0; left < mergeTrace.length; left += 2 * width) {
                int middle = Math.min(left + width, mergeTrace.length);
                int right = Math.min(left + 2 * width, mergeTrace.length);
                int i = left, j = middle, k = left;

                while (i < middle && j < right) {
                    if (mergeTrace[i] >= mergeTrace[j]) next[k++] = mergeTrace[i++];
                    else next[k++] = mergeTrace[j++];
                }
                while (i < middle) next[k++] = mergeTrace[i++];
                while (j < right) next[k++] = mergeTrace[j++];
            }

            mergeTrace = next;
            System.out.print("Merge width " + width + ": ");
            for (double value : mergeTrace) System.out.print((int) value + " ");
            System.out.println();
        }

        // Benchmark the three sorting methods on 10,000 records.
        int n = 10000;
        double[] original = Arrays.copyOf(amounts, n);
        double[] bubble = original.clone();
        double[] merge = original.clone();
        double[] builtIn = original.clone();

        start = System.nanoTime();
        bubbleSort(bubble);
        long bubbleTime = System.nanoTime() - start;

        start = System.nanoTime();
        mergeSort(merge, 0, merge.length - 1);
        long mergeTime = System.nanoTime() - start;

        start = System.nanoTime();
        Arrays.sort(builtIn); // Java built-in sort (ascending)
        // Reverse the array to get descending order.
        for (int i = 0; i < builtIn.length / 2; i++) {
            double temp = builtIn[i];
            builtIn[i] = builtIn[builtIn.length - 1 - i];
            builtIn[builtIn.length - 1 - i] = temp;
        }
        long builtInTime = System.nanoTime() - start;

        System.out.printf("Bubble Sort: %.3f ms%n", bubbleTime / 1000000.0);
        System.out.printf("Merge Sort: %.3f ms%n", mergeTime / 1000000.0);
        System.out.printf("Built-in sort: %.3f ms%n", builtInTime / 1000000.0);
        System.out.print("Top five amounts: ");
        printFirstFive(merge);

        // ---------------- TASK 3 ----------------
        System.out.println("\nTASK 3: DUPLICATE ORDERS");
        int duplicates = findDuplicates(customerIds, amounts, createdAt, size);
        System.out.println("Duplicate orders found: " + duplicates);
        System.out.println("Rule: Same customer, same amount and same minute.");

        // ---------------- TASK 4 ----------------
        System.out.println("\nTASK 4: STACK AND QUEUE");

        MyStack stack = new MyStack();
        stack.push("Edit order O100: amount changed to 500");
        stack.push("Edit order O101: amount changed to 700");
        System.out.println("Custom Stack undo: " + stack.pop());

        ArrayDeque<String> builtInStack = new ArrayDeque<>();
        builtInStack.push("Edit order O100");
        builtInStack.push("Edit order O101");
        System.out.println("Built-in Stack undo: " + builtInStack.pop());

        MyQueue queue = new MyQueue();
        queue.enqueue("Order 1");
        queue.enqueue("Order 2");
        queue.enqueue("Order 3");
        System.out.println("Custom Queue dispatch: " +
                queue.dequeue() + ", " + queue.dequeue() + ", " + queue.dequeue());

        ArrayDeque<String> builtInQueue = new ArrayDeque<>();
        builtInQueue.add("Order 1");
        builtInQueue.add("Order 2");
        builtInQueue.add("Order 3");
        System.out.println("Built-in Queue dispatch: " +
                builtInQueue.remove() + ", " + builtInQueue.remove() + ", " + builtInQueue.remove());

        // ---------------- TASK 5 ----------------
        System.out.println("\nTASK 5: REFUND COIN CHANGE");

        int refund = 6;
        int[] coins = {1, 3, 4}; // Greedy checks largest first: 4, then 3, then 1

        int greedyCount = greedyChange(refund, coins);
        int dpCount = dpChange(refund, coins);

        System.out.println("Refund amount: " + refund);
        System.out.println("Denominations: [1, 3, 4]");
        System.out.println("Greedy minimum coin count: " + greedyCount);
        System.out.println("Dynamic Programming minimum coin count: " + dpCount);
        System.out.println("Greedy gives 4 + 1 + 1 (3 coins).");
        System.out.println("DP gives 3 + 3 (2 coins).");

        System.out.println("\n===== PROGRAM COMPLETED =====");
    }
}
