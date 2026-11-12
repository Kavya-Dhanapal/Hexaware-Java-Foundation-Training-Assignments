# Time and Space Complexity

Let:
- `n` = number of claim IDs in the BST.
- `c` = number of submitted claims checked for duplicates.
- `V` = number of approval stages (vertices).
- `E` = number of approval connections (edges).
- `d` = number of days in the daily claim-count array.
- `n` and `m` = sizes of the two regional claim arrays.

| Task | Operation | Time complexity | Extra space | Explanation |
|---|---|---:|---:|---|
| 1 | BST insert | Average `O(log n)`, worst `O(n)` | `O(h)` recursive call stack | Depends on the height `h` of the tree. |
| 1 | BST search | Average `O(log n)`, worst `O(n)` | `O(1)` | Follows one branch of the tree. |
| 1 | Each traversal | `O(n)` | `O(h)` | Visits every node once. |
| 2 | Duplicate detection | Average `O(c)` | `O(c)` | HashSet insert and lookup are average `O(1)` per claim. |
| 3 | BFS shortest path | `O(V + E)` | `O(V)` | Visits vertices and edges. |
| 3 | DFS cycle detection | `O(V + E)` | `O(V)` | Uses state tracking and a recursion stack. |
| 4 | Longest spike run | `O(d)` | `O(1)` | Checks each day once. |
| 5 | Merge sorted lists | `O(n + m)` | `O(n + m)` for result | Each input value is copied once. |

## Important Complexity Notes

- `h` is the height of the BST. A balanced BST has height around `log n`; a highly unbalanced BST can have height `n`.
- HashSet operations are average `O(1)`, but collisions can affect performance. Java uses hash values and equality checks to distinguish keys.
- BFS gives the shortest path by number of edges when every graph connection has equal cost.
- The merge operation does not sort the inputs again. It only walks through the two already-sorted arrays.
