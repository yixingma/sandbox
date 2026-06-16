package com.matcha.leetcode.geometry;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LC812_LargestTriangleAreaTest {

    private final LC812_LargestTriangleArea solver = new LC812_LargestTriangleArea();

    @Test
    public void testLargestTriangleAreaBasic() {
        int[][] points1 = {{0,0}, {0,1}, {1,0}, {0,2}, {2,0}};
        assertEquals(2.0, solver.largestTriangleArea(points1), 1e-5);

        int[][] points2 = {{1,0}, {0,0}, {0,1}};
        assertEquals(0.5, solver.largestTriangleArea(points2), 1e-5);
    }

    @Test
    public void testLargestTriangleAreaCollinear() {
        // Collinear points should result in 0 area
        int[][] points = {{0,0}, {1,1}, {2,2}};
        assertEquals(0.0, solver.largestTriangleArea(points), 1e-5);
    }
}
