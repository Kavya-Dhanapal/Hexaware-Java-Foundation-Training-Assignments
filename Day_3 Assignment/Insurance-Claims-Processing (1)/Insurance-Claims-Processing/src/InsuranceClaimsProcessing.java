import java.time.LocalDate;
import java.util.*;

/**
 * Beginner-friendly DSA assignment:
 * Insurance Claims Processing System
 *
 * Compile: javac src/InsuranceClaimsProcessing.java
 * Run:     java -cp src InsuranceClaimsProcessing
 */
public class InsuranceClaimsProcessing {

    // ------------------------------------------------------------
    // TASK 1: Binary Search Tree (BST) for claim IDs
    // ------------------------------------------------------------

    static class TreeNode {
        int claimId;
        TreeNode left;
        TreeNode right;

        TreeNode(int claimId) {
            this.claimId = claimId;
        }
    }

    static class ClaimBST {
        TreeNode root;

        void insert(int claimId) {
            root = insertNode(root, claimId);
        }

        private TreeNode insertNode(TreeNode node, int claimId) {
            if (node == null) {
                return new TreeNode(claimId);
            }

            if (claimId < node.claimId) {
                node.left = insertNode(node.left, claimId);
            } else if (claimId > node.claimId) {
                node.right = insertNode(node.right, claimId);
            }
            // If the ID already exists, do not insert it again.
            return node;
        }

        boolean search(int claimId) {
            TreeNode current = root;

            while (current != null) {
                if (claimId == current.claimId) {
                    return true;
                } else if (claimId < current.claimId) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
            return false;
        }

        void inorder(TreeNode node, List<Integer> result) {
            if (node == null) return;
            inorder(node.left, result);
            result.add(node.claimId);
            inorder(node.right, result);
        }

        void preorder(TreeNode node, List<Integer> result) {
            if (node == null) return;
            result.add(node.claimId);
            preorder(node.left, result);
            preorder(node.right, result);
        }

        void postorder(TreeNode node, List<Integer> result) {
            if (node == null) return;
            postorder(node.left, result);
            postorder(node.right, result);
            result.add(node.claimId);
        }
    }

    // ------------------------------------------------------------
    // TASK 2: Hashing to detect duplicate claims
    // ------------------------------------------------------------

    static class Claim {
        String policyNumber;
        LocalDate incidentDate;

        Claim(String policyNumber, LocalDate incidentDate) {
            this.policyNumber = policyNumber;
            this.incidentDate = incidentDate;
        }
    }

    static String createClaimKey(Claim claim) {
        // Ignore letter case and all spaces in the policy number.
        String cleanPolicyNumber =
                claim.policyNumber.replaceAll("\\s+", "").toUpperCase();

        return cleanPolicyNumber + "|" + claim.incidentDate;
    }

    static boolean hasDuplicateClaims(List<Claim> claims) {
        Set<String> seenClaims = new HashSet<>();

        for (Claim claim : claims) {
            String key = createClaimKey(claim);

            if (seenClaims.contains(key)) {
                return true;
            }

            seenClaims.add(key);
        }
        return false;
    }

    // ------------------------------------------------------------
    // TASK 3: Directed graph, BFS shortest path and DFS cycle check
    // ------------------------------------------------------------

    static class ApprovalGraph {
        Map<String, List<String>> graph = new HashMap<>();

        void addStage(String stage) {
            graph.putIfAbsent(stage, new ArrayList<>());
        }

        void addConnection(String from, String to) {
            addStage(from);
            addStage(to);
            graph.get(from).add(to);
        }

        // BFS finds the shortest path in an unweighted graph.
        List<String> shortestPath(String start, String destination) {
            if (!graph.containsKey(start) || !graph.containsKey(destination)) {
                return Collections.emptyList();
            }

            Queue<String> queue = new LinkedList<>();
            Map<String, String> parent = new HashMap<>();
            Set<String> visited = new HashSet<>();

            queue.add(start);
            visited.add(start);
            parent.put(start, null);

            while (!queue.isEmpty()) {
                String current = queue.poll();

                if (current.equals(destination)) {
                    break;
                }

                for (String next : graph.get(current)) {
                    if (!visited.contains(next)) {
                        visited.add(next);
                        parent.put(next, current);
                        queue.add(next);
                    }
                }
            }

            if (!visited.contains(destination)) {
                return Collections.emptyList();
            }

            List<String> path = new ArrayList<>();
            String current = destination;

            while (current != null) {
                path.add(current);
                current = parent.get(current);
            }

            Collections.reverse(path);
            return path;
        }

        // DFS cycle detection using three states:
        // 0 = not visited, 1 = currently visiting, 2 = finished.
        boolean hasCycle() {
            Map<String, Integer> state = new HashMap<>();

            for (String stage : graph.keySet()) {
                if (state.getOrDefault(stage, 0) == 0) {
                    if (dfsHasCycle(stage, state)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfsHasCycle(String stage, Map<String, Integer> state) {
            state.put(stage, 1);

            for (String next : graph.get(stage)) {
                int nextState = state.getOrDefault(next, 0);

                if (nextState == 1) {
                    return true; // A back edge means a cycle exists.
                }

                if (nextState == 0 && dfsHasCycle(next, state)) {
                    return true;
                }
            }

            state.put(stage, 2);
            return false;
        }
    }

    // ------------------------------------------------------------
    // TASK 4: Longest consecutive run of days with more than 100 claims
    // ------------------------------------------------------------

    static int longestClaimSpikeRun(int[] dailyClaimCounts) {
        int longestRun = 0;
        int currentRun = 0;

        // Expand the current run when the daily count is above 100.
        // Reset it when the count is 100 or below.
        for (int count : dailyClaimCounts) {
            if (count > 100) {
                currentRun++;
                longestRun = Math.max(longestRun, currentRun);
            } else {
                currentRun = 0;
            }
        }

        return longestRun;
    }

    // ------------------------------------------------------------
    // TASK 5: Merge two sorted regional claim lists using two pointers
    // ------------------------------------------------------------

    static int[] mergeClaimLists(int[] regionOne, int[] regionTwo) {
        int[] merged = new int[regionOne.length + regionTwo.length];

        int first = 0;
        int second = 0;
        int index = 0;

        while (first < regionOne.length && second < regionTwo.length) {
            if (regionOne[first] <= regionTwo[second]) {
                merged[index] = regionOne[first];
                first++;
            } else {
                merged[index] = regionTwo[second];
                second++;
            }
            index++;
        }

        // Copy any remaining values from the first list.
        while (first < regionOne.length) {
            merged[index++] = regionOne[first++];
        }

        // Copy any remaining values from the second list.
        while (second < regionTwo.length) {
            merged[index++] = regionTwo[second++];
        }

        return merged;
    }

    // ------------------------------------------------------------
    // Simple tests, including empty input and duplicates
    // ------------------------------------------------------------

    static void check(boolean condition, String testName) {
        if (!condition) {
            throw new AssertionError("FAILED: " + testName);
        }
        System.out.println("PASSED: " + testName);
    }

    static void runTests() {
        System.out.println("Running test cases...");

        // BST tests
        ClaimBST tree = new ClaimBST();
        check(!tree.search(10), "BST search in empty tree");
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(20); // Duplicate should be ignored.
        check(tree.search(10), "BST finds an existing claim");
        check(!tree.search(99), "BST does not find a missing claim");

        List<Integer> sortedIds = new ArrayList<>();
        tree.inorder(tree.root, sortedIds);
        check(sortedIds.equals(Arrays.asList(10, 20, 30)),
                "BST ignores duplicate IDs and inorder is sorted");

        // Hashing tests
        check(!hasDuplicateClaims(new ArrayList<>()),
                "Duplicate check with empty input");

        List<Claim> duplicateClaims = Arrays.asList(
                new Claim("POL 123", LocalDate.of(2026, 1, 10)),
                new Claim("pol123", LocalDate.of(2026, 1, 10))
        );
        check(hasDuplicateClaims(duplicateClaims),
                "Hashing detects case and spacing differences");

        List<Claim> differentClaims = Arrays.asList(
                new Claim("POL123", LocalDate.of(2026, 1, 10)),
                new Claim("POL123", LocalDate.of(2026, 1, 11))
        );
        check(!hasDuplicateClaims(differentClaims),
                "Different incident dates are not duplicates");

        // Graph tests
        ApprovalGraph graph = new ApprovalGraph();
        graph.addConnection("Registration", "Assessment");
        graph.addConnection("Assessment", "Surveyor");
        graph.addConnection("Surveyor", "Settlement");

        check(graph.shortestPath("Registration", "Settlement").equals(
                Arrays.asList("Registration", "Assessment", "Surveyor", "Settlement")),
                "BFS finds the approval path");
        check(!graph.hasCycle(), "DFS finds no cycle in normal workflow");

        graph.addConnection("Settlement", "Assessment");
        check(graph.hasCycle(), "DFS detects a cycle");

        ApprovalGraph emptyGraph = new ApprovalGraph();
        check(emptyGraph.shortestPath("A", "B").isEmpty(),
                "BFS on an empty graph");

        // Sliding window / consecutive run tests
        check(longestClaimSpikeRun(new int[]{}) == 0,
                "Spike check with empty input");
        check(longestClaimSpikeRun(new int[]{90, 110, 120, 80, 130, 140, 150}) == 3,
                "Finds the longest consecutive spike run");
        check(longestClaimSpikeRun(new int[]{50, 70, 100}) == 0,
                "Exactly 100 is not above 100");

        // Two-pointer merge tests
        check(Arrays.equals(mergeClaimLists(new int[]{}, new int[]{}), new int[]{}),
                "Merge two empty lists");
        check(Arrays.equals(mergeClaimLists(new int[]{1, 3, 5}, new int[]{2, 4, 6}),
                new int[]{1, 2, 3, 4, 5, 6}), "Merge two sorted lists");
        check(Arrays.equals(mergeClaimLists(new int[]{1, 2, 2}, new int[]{2, 3}),
                new int[]{1, 2, 2, 2, 3}), "Merge lists containing duplicates");

        System.out.println("All tests completed.");
    }

    // ------------------------------------------------------------
    // Main method: sample demonstration
    // ------------------------------------------------------------

    public static void main(String[] args) {
        System.out.println("INSURANCE CLAIMS PROCESSING SYSTEM");
        System.out.println("----------------------------------");

        // Task 1 demonstration
        ClaimBST tree = new ClaimBST();
        int[] claimIds = {50, 30, 70, 20, 40, 60, 80};

        for (int id : claimIds) {
            tree.insert(id);
        }

        List<Integer> inorder = new ArrayList<>();
        List<Integer> preorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();

        tree.inorder(tree.root, inorder);
        tree.preorder(tree.root, preorder);
        tree.postorder(tree.root, postorder);

        System.out.println("\n1. BINARY SEARCH TREE");
        System.out.println("Inorder:   " + inorder);
        System.out.println("Preorder:  " + preorder);
        System.out.println("Postorder: " + postorder);
        System.out.println("Search claim 60: " + tree.search(60));
        System.out.println("Inorder gives claim IDs in sorted order.");

        // Task 2 demonstration
        List<Claim> claims = Arrays.asList(
                new Claim("POL 123", LocalDate.of(2026, 1, 10)),
                new Claim("pol123", LocalDate.of(2026, 1, 10)),
                new Claim("POL 456", LocalDate.of(2026, 1, 12))
        );

        System.out.println("\n2. DUPLICATE CLAIM DETECTION");
        System.out.println("Duplicate claim found: " + hasDuplicateClaims(claims));

        // Task 3 demonstration
        ApprovalGraph approval = new ApprovalGraph();
        approval.addConnection("Registration", "Assessment");
        approval.addConnection("Assessment", "Surveyor");
        approval.addConnection("Surveyor", "Settlement");

        System.out.println("\n3. CLAIM APPROVAL GRAPH");
        System.out.println("Shortest path: " +
                approval.shortestPath("Registration", "Settlement"));
        System.out.println("Workflow contains a loop: " + approval.hasCycle());

        // Task 4 demonstration
        int[] dailyClaims = {80, 120, 135, 110, 90, 125, 140, 150, 70};
        System.out.println("\n4. CLAIM SPIKE DAYS");
        System.out.println("Longest run above 100 claims: " +
                longestClaimSpikeRun(dailyClaims) + " days");

        // Task 5 demonstration
        int[] northOffice = {101, 105, 110, 120};
        int[] southOffice = {102, 108, 115, 125};

        System.out.println("\n5. MERGED REGIONAL CLAIM LIST");
        System.out.println(Arrays.toString(mergeClaimLists(northOffice, southOffice)));

        // Run test cases
        System.out.println("\n");
        runTests();
    }
}