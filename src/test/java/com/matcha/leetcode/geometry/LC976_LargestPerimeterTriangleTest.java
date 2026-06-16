package com.matcha.leetcode.geometry;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LC976_LargestPerimeterTriangleTest {

    private final LC976_LargestPerimeterTriangle solver = new LC976_LargestPerimeterTriangle();

    @Test
    public void testLargestPerimeterBasic() {
        assertEquals(5, solver.largestPerimeter(new int[]{2, 1, 2}));
        assertEquals(0, solver.largestPerimeter(new int[]{1, 2, 1}));
    }

    @Test
    public void testLargestPerimeterMultipleTriplets() {
        assertEquals(10, solver.largestPerimeter(new int[]{3, 2, 3, 4}));
        assertEquals(8, solver.largestPerimeter(new int[]{3, 6, 2, 3}));
    }
}
