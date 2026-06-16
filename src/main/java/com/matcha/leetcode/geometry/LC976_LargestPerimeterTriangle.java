package com.matcha.leetcode.geometry;

import java.util.Arrays;

/**
 * 976. Largest Perimeter Triangle
 * <p>
 * Given an integer array nums, return the largest perimeter of a triangle with a non-zero area,
 * formed from three of these lengths. If it is impossible to form any triangle of a non-zero area,
 * return 0.
 * <p>
 * Example 1:
 * Input: nums = [2,1,2]
 * Output: 5
 * <p>
 * Example 2:
 * Input: nums = [1,2,1]
 * Output: 0
 */
public class LC976_LargestPerimeterTriangle {

    /**
     * Solution using Sorting and Greedy approach.
     * Time Complexity: O(n log n) due to sorting.
     * Space Complexity: O(1) or O(log n) depending on the sorting implementation.
     */
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            int a = nums[i - 2];
            int b = nums[i - 1];
            int c = nums[i];
            if (a + b > c) {
                return a + b + c;
            }
        }
        return 0;
    }
}
