package com.matcha.leetcode.dp;

/**
 * 72. Edit Distance
 * <p>
 * Given two strings word1 and word2, return the minimum number of operations required to convert
 * word1 to word2. You have the following three operations permitted on a word:
 * Insert a character, delete a character, replace a character.
 * <p>
 * Example 1:
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation: horse -> rorse (replace 'h' with 'r') -> rose (remove 'r') -> ros (remove 'e')
 * <p>
 * Example 2:
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 */
public class LC72_EditDistance {

    /**
     * 2D DP: dp[i][j] is min edits to convert word1[0..i) to word2[0..j).
     * Time O(m * n), space O(m * n).
     */
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j],
                            Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[m][n];
    }
}
