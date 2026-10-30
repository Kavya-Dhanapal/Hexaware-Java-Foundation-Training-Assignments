# Online Retail Orders – Java DSA Assignment

A beginner-friendly Java 17 project that demonstrates searching, sorting, duplicate detection, Stack, Queue, Greedy and Dynamic Programming.

## Project files

```text
Retail_DSA_GitHub_Ready/
├── data/
│   └── orders.csv
├── src/
│   └── Main.java
├── Complexity_Notes.md
├── console_output.txt
├── .gitignore
└── README.md
```

## Dataset

`data/orders.csv` contains 50,000 sample orders with these columns:

- `orderId`
- `customerId`
- `amount`
- `createdAt`

The first 10 rows match the simple sample table in the assignment. The file contains 50,000 data rows in total. Order IDs are numeric strings (1001 to 51000), matching `Main.java`.

Some repeated submissions are included to test duplicate detection.

## Requirements

- Java JDK 17 or later
- VS Code (optional) or any Java IDE

## How to run

Open a terminal in the project folder.

**Windows:**

```bash
javac -d out src/Main.java
java -cp out Main data/orders.csv
```

**Mac/Linux:**

```bash
javac -d out src/Main.java
java -cp out Main data/orders.csv
```

The program prints the results for all five tasks.

## Concepts used

1. Linear Search and Binary Search
2. Bubble Sort, Merge Sort and Java's built-in `Arrays.sort()`
3. `HashSet` for duplicate detection
4. Custom Stack and Queue, compared with Java `ArrayDeque`
5. Greedy and Dynamic Programming for coin change


