package com.matcha.leetcode.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 1557. Minimum Number of Vertices to Reach All Nodes
 * <p>
 * Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges
 * where edges[i] = [fromi, toi] represents a directed edge from fromi to toi.
 * <p>
 * Find the smallest set of vertices from which all nodes in the graph are reachable.
 * <p>
 * Example 1:
 * Input: n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]]
 * Output: [0,3]
 * <p>
 * Example 2:
 * Input: n = 5, edges = [[0,1],[2,1],[3,1],[1,4],[2,4]]
 * Output: [0,2,3]
 */
public class LC1557_MinVerticesToReachAllNodes {

    /**
     * Finds the smallest set of vertices to reach all nodes. Since it's a DAG, this set is
     * exactly the nodes with an in-degree of 0.
     * Time Complexity: O(N + E) where N is the number of vertices and E is the number of edges.
     * Space Complexity: O(N) to store in-degree information.
     */
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        boolean[] hasIncoming = new boolean[n];
        for (List<Integer> edge : edges) {
            hasIncoming[edge.get(1)] = true;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!hasIncoming[i]) {
                result.add(i);
            }
        }
        return result;
    }
}
