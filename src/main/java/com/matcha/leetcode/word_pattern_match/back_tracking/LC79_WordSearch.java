package com.matcha.leetcode.word_pattern_match.back_tracking;

/**
 * 79. Word Search
 * <p>
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells
 * are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * Example 1:
 * Input: board = [
 *   ["A","B","C","E"],
 *   ["S","F","C","S"],
 *   ["A","D","E","E"]
 * ], word = "ABCCED"
 * Output: true
 * <p>
 * Example 2:
 * Input: board = [
 *   ["A","B","C","E"],
 *   ["S","F","C","S"],
 *   ["A","D","E","E"]
 * ], word = "SEE"
 * Output: true
 * <p>
 * Example 3:
 * Input: board = [
 *   ["A","B","C","E"],
 *   ["S","F","C","S"],
 *   ["A","D","E","E"]
 * ], word = "ABCB"
 * Output: false
 */
public class LC79_WordSearch {

    /**
     * Determines if the word exists in the grid using DFS and backtracking.
     * Time Complexity: O(m * n * 4^L) where m and n are board dimensions and L is word length.
     * Space Complexity: O(L) for recursion stack.
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, 0, i, j, m, n)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int index, int r, int c, int m, int n) {
        if (index == word.length()) {
            return true;
        }
        if (r < 0 || r >= m || c < 0 || c >= n || board[r][c] != word.charAt(index)) {
            return false;
        }

        char temp = board[r][c];
        board[r][c] = '#'; // Mark as visited

        boolean found = dfs(board, word, index + 1, r + 1, c, m, n)
                || dfs(board, word, index + 1, r - 1, c, m, n)
                || dfs(board, word, index + 1, r, c + 1, m, n)
                || dfs(board, word, index + 1, r, c - 1, m, n);

        board[r][c] = temp; // Restore (backtrack)
        return found;
    }
}
