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

## Important benchmark note

Execution times depend on the computer and JVM. Run the program on your own computer and use the values printed there in your submission. Binary Search requires sorted IDs, so its sorting preparation is separate from the lookup time.

## GitHub upload

1. Create a new GitHub repository.
2. Give it a name such as `java-dsa-retail-assignment`.
3. Keep it Public or Private as your instructor requests.
4. Upload this project's files and folders, or use Git:

```bash
git init
git add .
git commit -m "Add Java DSA retail assignment"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/java-dsa-retail-assignment.git
git push -u origin main
```

Replace `YOUR-USERNAME` with your GitHub username.
