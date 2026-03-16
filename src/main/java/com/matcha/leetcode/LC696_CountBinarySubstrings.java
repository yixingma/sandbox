package com.matcha.leetcode;

/**
 * 696. Count Binary Substrings
 * <p>
 * Given a binary string s, return the number of non-empty substrings that have
 * the same number
 * of 0's and 1's, and all the 0's and all the 1's in these substrings are
 * grouped consecutively.
 * Substrings that occur multiple times are counted the number of times they
 * occur.
 * <p>
 * Example: "00110011" -> 6 ("01", "0011", "10", "1100", "01", "0011")
 */
public class LC696_CountBinarySubstrings {

    /**
     * Count valid substrings by grouping consecutive same digits, then for each
     * adjacent
     * pair of groups add min(count1, count2).
     */
    public int countBinarySubstrings(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int count = 1;
        int prevCount = 0;
        int ans = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                ans += Math.min(count, prevCount);
                prevCount = count;
                count = 1;
            }
        }
        return ans + Math.min(count, prevCount);
    }
}

// public static int countBinarySubstrings(String s) {
// if (s == null || s.length() < 2) {
// return 0;
// }
// int n = s.length();
// int[] groups = new int[n];
// int g = 0;
// groups[0] = 1;
// for (int i = 1; i < n; i++) {
// if (s.charAt(i) != s.charAt(i - 1)) {
// groups[++g] = 1;
// } else {
// groups[g]++;
// }
// }
// int count = 0;
// for (int i = 0; i < g; i++) {
// count += Math.min(groups[i], groups[i + 1]);
// }
// return count;
// }