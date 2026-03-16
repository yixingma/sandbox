package com.matcha.leetcode;

/**
 * 200. Number of Islands
 * <p>
 * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. You may assume all four edges of the grid are
 * all surrounded by water.
 * <p>
 * Example 1:
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * <p>
 * Example 2:
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 */
public class LC200_NumberOfIslands {

    /**
     * Count the number of islands using DFS (Depth-First Search).
     * Time complexity: O(m * n)
     * Space complexity: O(m * n) in worst case (recursion stack)
     */
    public int numberOfIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        return count;
    }
}
// public int numIslands(char[][] grid) {
//     if (grid == null || grid.length == 0 || grid[0].length == 0) {
//         return 0;
//     }
//     int m = grid.length;
//     int n = grid[0].length;
//     int count = 0;
//     for (int i = 0; i < m; i++) {
//         for (int j = 0; j < n; j++) {
//             if (grid[i][j] == '1') {
//                 dfs(grid, i, j, m, n);
//                 count++;
//             }
//         }
//     }
//     return count;
// }

// /**
//  * DFS helper to mark all connected land cells as visited (set to '0').
//  */
// private void dfs(char[][] grid, int i, int j, int m, int n) {
//     if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') {
//         return;
//     }
//     grid[i][j] = '0';
//     dfs(grid, i + 1, j, m, n);
//     dfs(grid, i - 1, j, m, n);
//     dfs(grid, i, j + 1, m, n);
//     dfs(grid, i, j - 1, m, n);
// }