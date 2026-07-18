package com.matcha.leetcode.hashmap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LC1_TwoSumTest {

    private final LC1_TwoSum solver = new LC1_TwoSum();

    @Test
    public void testTwoSumBasic() {
        assertArrayEquals(new int[]{0, 1}, solver.twoSum(new int[]{2, 7, 11, 15}, 9));
        assertArrayEquals(new int[]{1, 2}, solver.twoSum(new int[]{3, 2, 4}, 6));
        assertArrayEquals(new int[]{0, 1}, solver.twoSum(new int[]{3, 3}, 6));
    }

    @Test
    public void testTwoSumWithNegatives() {
        assertArrayEquals(new int[]{0, 2}, solver.twoSum(new int[]{-3, 4, 3, 90}, 0));
    }
}
