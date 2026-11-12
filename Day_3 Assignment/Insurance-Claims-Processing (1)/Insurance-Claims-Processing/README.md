# Insurance Claims Processing System

A beginner-friendly Java Data Structures and Algorithms assignment based on an insurance claims business scenario.

## Business Scenario

An insurance company needs to:
- Store and search claim IDs.
- Identify duplicate claim submissions.
- Move claims through approval stages.
- Monitor days when claim numbers increase above 100.
- Combine sorted claim lists from two regional offices.

## DSA Concepts Used

| Task | Concept | What it does |
|---|---|---|
| 1 | Binary Search Tree (BST) | Inserts and searches claim IDs; demonstrates inorder, preorder and postorder traversals. |
| 2 | Hashing | Finds duplicate claims using policy number and incident date. |
| 3 | Graph, BFS and DFS | Finds the shortest approval path and detects workflow loops. |
| 4 | Sliding Window / Two Pointers | Finds the longest consecutive run of days with more than 100 claims. |
| 5 | Two Pointers | Merges two already-sorted regional claim lists. |

## Requirements

- Java JDK 8 or later
- A terminal or Java IDE such as IntelliJ IDEA or VS Code

## How to Run

Open a terminal in the project folder.

Compile:

```bash
javac src/InsuranceClaimsProcessing.java
```

Run:

```bash
java -cp src InsuranceClaimsProcessing
```

The program prints sample results and runs built-in test cases.

## Task Explanations

### 1. Binary Search Tree

A BST stores smaller claim IDs on the left and larger claim IDs on the right.

- **Inorder:** Left, Root, Right. This prints claim IDs in sorted ascending order.
- **Preorder:** Root, Left, Right.
- **Postorder:** Left, Right, Root.

The example ignores an ID if it is already present.

### 2. Duplicate Claim Detection Using Hashing

The program creates a standard key from the policy number and incident date.

For example:
- `POL 123` becomes `POL123`
- `pol123` becomes `POL123`

If both the cleaned policy number and incident date match, the claim is treated as a duplicate.

A Java `HashSet` stores the keys. Hashing collisions are handled by Java's hash collection implementation: it checks key equality as well as hash values, so different keys that share a hash are not automatically treated as duplicates.

This is a simple exact-match check. It does not detect claims that are only similar in amount, name, or description.

### 3. Approval Graph

The stages are connected in this order:

`Registration -> Assessment -> Surveyor -> Settlement`

- **BFS** explores the graph level by level and finds a path with the fewest edges in an unweighted graph.
- **DFS** explores connected stages and detects a cycle (loop).

### 4. Claim Spike Days

The input is the number of claims received on each day, in date order.

A day is a spike day only when its count is **greater than 100**. The program counts consecutive spike days and keeps the longest run.

Example: `[80, 120, 135, 110, 90, 125, 140]` has a longest run of `3` days.

This is a one-pass sliding-window/two-pointer approach. The window grows while days meet the condition and resets when a day does not.

### 5. Merge Regional Lists

Both input arrays must already be sorted in ascending order. Two pointers compare the current values and copy the smaller one into the result. Any remaining values are copied at the end.

No additional sorting step is needed.

## Test Cases

See [test-cases/TestCases.md](test-cases/TestCases.md).

## Complexity Analysis

See [docs/ComplexityAnalysis.md](docs/ComplexityAnalysis.md).

## Limitations and Assumptions

- Claim IDs are integers and duplicate IDs are ignored in the BST.
- Duplicate claims mean the same normalized policy number and the same incident date.
- The approval graph is unweighted; BFS finds the path with the fewest stage connections.
- Daily claim counts are supplied in chronological order.
- Both regional claim arrays are sorted before merging.
