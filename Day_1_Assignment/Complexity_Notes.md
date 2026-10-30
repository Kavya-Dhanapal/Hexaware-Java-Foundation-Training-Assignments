# Complexity Notes – Online Retail DSA Assignment

## 1. Searching

- **Linear Search – O(n):** Checks each order ID one by one. It works even if the data is not sorted.
- **Binary Search – O(log n):** Checks the middle item and removes half of the remaining search area each time. The IDs must be sorted first. Sorting takes O(n log n).

## 2. Sorting by amount (descending)

- **Bubble Sort – O(n²):** Compares neighbouring amounts and swaps them when they are in the wrong order. It is easy to understand but slow for many records.
- **Merge Sort – O(n log n):** Divides the array into smaller parts, sorts them and merges them. It is faster for large data, but uses extra memory O(n).
- **Built-in sort:** Java's `Arrays.sort()` is optimized and is usually a good choice in real applications. The program sorts the amounts and reverses them to get descending order.

## 3. Duplicate detection

A `HashSet` stores a key containing customer ID, amount and the date/time up to the minute. If the key is already present, the order is a duplicate.

- Average time: O(n) for checking all orders.
- Extra space: O(n) for stored keys.

## 4. Stack and Queue

- **Stack:** Last In, First Out (LIFO). The latest edit is removed first, so it is suitable for undo. Push and pop are O(1).
- **Queue:** First In, First Out (FIFO). The earliest order is dispatched first. Enqueue and dequeue are O(1) in the custom example.
- Java's `ArrayDeque` can be used as a built-in Stack and Queue.

## 5. Greedy and Dynamic Programming

- **Greedy:** Selects the largest denomination possible at each step. It is simple and fast, but it may not give the minimum number of coins for every denomination set.
- **Dynamic Programming:** Checks smaller amounts and stores the best answer. Time: O(A × k), space: O(A), where A is the refund amount and k is the number of denominations.

**Example:** Amount = 6, denominations = 1, 3, 4.
- Greedy: 4 + 1 + 1 = 3 coins.
- Dynamic Programming: 3 + 3 = 2 coins.

Therefore, Dynamic Programming gives the minimum number of coins for this example.
