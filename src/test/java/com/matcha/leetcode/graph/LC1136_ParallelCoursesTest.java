package com.matcha.leetcode.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LC1136_ParallelCoursesTest {

    private final LC1136_ParallelCourses solver = new LC1136_ParallelCourses();

    @Test
    public void testMinimumSemestersBasic() {
        assertEquals(2, solver.minimumSemesters(3, new int[][]{{1, 3}, {2, 3}}));
        assertEquals(-1, solver.minimumSemesters(3, new int[][]{{1, 2}, {2, 3}, {3, 1}}));
    }

    @Test
    public void testMinimumSemestersLinear() {
        assertEquals(3, solver.minimumSemesters(3, new int[][]{{1, 2}, {2, 3}}));
    }

    @Test
    public void testMinimumSemestersNoDependencies() {
        assertEquals(1, solver.minimumSemesters(4, new int[][]{}));
    }
}
