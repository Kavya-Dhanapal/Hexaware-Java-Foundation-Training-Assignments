# Test Cases

The Java program includes simple built-in tests. Run the program to execute them.

| No. | Task | Input / Situation | Expected result |
|---:|---|---|---|
| 1 | BST | Search an empty tree | `false` |
| 2 | BST | Insert `20, 10, 30, 20` | Duplicate `20` is ignored |
| 3 | BST | Inorder traversal after test 2 | `[10, 20, 30]` |
| 4 | Hashing | Empty claim list | `false` (no duplicate) |
| 5 | Hashing | `POL 123` and `pol123`, same date | Duplicate detected |
| 6 | Hashing | Same policy, different incident dates | No duplicate |
| 7 | BFS | Registration to Settlement | `[Registration, Assessment, Surveyor, Settlement]` |
| 8 | DFS | Normal approval flow | No cycle |
| 9 | DFS | Add Settlement to Assessment | Cycle detected |
| 10 | BFS | Search in an empty graph | Empty path |
| 11 | Spike days | Empty daily count list | `0` |
| 12 | Spike days | `[90, 110, 120, 80, 130, 140, 150]` | `3` |
| 13 | Spike days | `[50, 70, 100]` | `0` |
| 14 | Merge | Two empty arrays | `[]` |
| 15 | Merge | `[1, 3, 5]` and `[2, 4, 6]` | `[1, 2, 3, 4, 5, 6]` |
| 16 | Merge | `[1, 2, 2]` and `[2, 3]` | `[1, 2, 2, 2, 3]` |

## Notes

- For the spike-days task, `100` is not considered a spike because the requirement says **exceeded 100**.
- For the merge task, the input arrays must be sorted already.
- The duplicate-claim task compares normalized policy number and incident date.
