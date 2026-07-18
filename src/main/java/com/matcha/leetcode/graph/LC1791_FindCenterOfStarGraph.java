package com.matcha.leetcode.graph;

/**
 * 1791. Find Center of Star Graph
 * <p>
 * There is an undirected star graph consisting of n nodes labeled from 1 to n. A star graph is a
 * graph where there is one center node and exactly n - 1 edges that connect the center node with
 * every other node.
 * <p>
 * Given a 2D integer array edges representing the star graph, return the center of the graph.
 * <p>
 * Example 1:
 * Input: edges = [[1,2],[2,3],[4,2]]
 * Output: 2
 * <p>
 * Example 2:
 * Input: edges = [[1,2],[5,1],[1,3],[1,4]]
 * Output: 1
 */
public class LC1791_FindCenterOfStarGraph {

    /**
     * Finds the center of the star graph. Since the center node must connect to all other nodes,
     * it must appear in all edges. Thus, we only need to compare the first two edges.
     * Time Complexity: O(1).
     * Space Complexity: O(1).
     */
    public int findCenter(int[][] edges) {
        if (edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1]) {
            return edges[0][0];
        }
        return edges[0][1];
    }
}
