package com.matcha.leetcode.graph;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LC1557_MinVerticesToReachAllNodesTest {

    private final LC1557_MinVerticesToReachAllNodes solver = new LC1557_MinVerticesToReachAllNodes();

    @Test
    public void testFindSmallestSetOfVerticesBasic() {
        // Example 1
        List<List<Integer>> edges1 = new ArrayList<>();
        edges1.add(List.of(0, 1));
        edges1.add(List.of(0, 2));
        edges1.add(List.of(2, 5));
        edges1.add(List.of(3, 4));
        edges1.add(List.of(4, 2));
        List<Integer> res1 = solver.findSmallestSetOfVertices(6, edges1);
        assertEquals(2, res1.size());
        assertTrue(res1.contains(0));
        assertTrue(res1.contains(3));

        // Example 2
        List<List<Integer>> edges2 = new ArrayList<>();
        edges2.add(List.of(0, 1));
        edges2.add(List.of(2, 1));
        edges2.add(List.of(3, 1));
        edges2.add(List.of(1, 4));
        edges2.add(List.of(2, 4));
        List<Integer> res2 = solver.findSmallestSetOfVertices(5, edges2);
        assertEquals(3, res2.size());
        assertTrue(res2.contains(0));
        assertTrue(res2.contains(2));
        assertTrue(res2.contains(3));
    }
}
