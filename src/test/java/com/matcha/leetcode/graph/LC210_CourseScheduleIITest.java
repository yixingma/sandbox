package com.matcha.leetcode.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LC210_CourseScheduleIITest {

    private final LC210_CourseScheduleII solver = new LC210_CourseScheduleII();

    @Test
    public void testFindOrderBasic() {
        int[] order1 = solver.findOrder(2, new int[][]{{1, 0}});
        assertArrayEquals(new int[]{0, 1}, order1);

        int[] order2 = solver.findOrder(2, new int[][]{{1, 0}, {0, 1}});
        assertArrayEquals(new int[0], order2);
    }

    @Test
    public void testFindOrderComplex() {
        int[] order = solver.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        assertEquals(4, order.length);
        assertEquals(0, order[0]);
        assertTrue((order[1] == 1 && order[2] == 2) || (order[1] == 2 && order[2] == 1));
        assertEquals(3, order[3]);
    }

    @Test
    public void testFindOrderSingleAndNoPrerequisites() {
        assertArrayEquals(new int[]{0}, solver.findOrder(1, new int[][]{}));

        int[] order = solver.findOrder(3, new int[][]{});
        assertEquals(3, order.length);
        assertArrayEquals(new int[]{0, 1, 2}, order);
    }
}
