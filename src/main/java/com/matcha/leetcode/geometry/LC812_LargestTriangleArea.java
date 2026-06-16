package com.matcha.leetcode.geometry;

/**
 * 812. Largest Triangle Area
 * <p>
 * Given an array of points on the X-Y plane points where points[i] = [xi, yi],
 * return the area of the largest triangle that can be formed by any three different points.
 * Answers within 10^-5 of the actual answer will be accepted.
 * <p>
 * Example 1:
 * Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * Output: 2.00000
 * <p>
 * Example 2:
 * Input: points = [[1,0],[0,0],[0,1]]
 * Output: 0.50000
 */
public class LC812_LargestTriangleArea {

    /**
     * Brute-force approach examining all triplets.
     * Time Complexity: O(n^3) where n is the number of points.
     * Space Complexity: O(1).
     */
    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        double maxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int[] x = points[i];
                    int[] y = points[j];
                    int[] z = points[k];
                    double area = 0.5 * Math.abs(
                        x[0] * (y[1] - z[1]) +
                        y[0] * (z[1] - x[1]) +
                        z[0] * (x[1] - y[1])
                    );
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }
}
