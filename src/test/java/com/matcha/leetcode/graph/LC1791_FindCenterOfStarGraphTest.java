package com.matcha.leetcode.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LC1791_FindCenterOfStarGraphTest {

    private final LC1791_FindCenterOfStarGraph solver = new LC1791_FindCenterOfStarGraph();

    @Test
    public void testFindCenterBasic() {
        assertEquals(2, solver.findCenter(new int[][]{{1, 2}, {2, 3}, {4, 2}}));
        assertEquals(1, solver.findCenter(new int[][]{{1, 2}, {5, 1}, {1, 3}, {1, 4}}));
    }
}
