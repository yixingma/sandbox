package com.matcha.leetcode.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LC207_CourseScheduleTest {

    private final LC207_CourseSchedule solver = new LC207_CourseSchedule();

    @Test
    public void testCanFinishBasic() {
        assertTrue(solver.canFinish(2, new int[][]{{1, 0}}));
        assertFalse(solver.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }

    @Test
    public void testCanFinishComplex() {
        // Directed acyclic graph (DAG)
        assertTrue(solver.canFinish(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
        // Graph with cycle
        assertFalse(solver.canFinish(4, new int[][]{{1, 0}, {2, 1}, {3, 2}, {1, 3}}));
    }

    @Test
    public void testCanFinishNoPrerequisites() {
        assertTrue(solver.canFinish(3, new int[][]{}));
    }
}
