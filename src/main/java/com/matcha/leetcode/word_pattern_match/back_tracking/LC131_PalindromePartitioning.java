package com.matcha.leetcode.word_pattern_match.back_tracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. Palindrome Partitioning
 * <p>
 * Given a string s, partition s such that every substring of the partition is a
 * palindrome.
 * Return all possible palindrome partitioning of s.
 * <p>
 * Example 1:
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * <p>
 * Example 2:
 * Input: s = "a"
 * Output: [["a"]]
 */
public class LC131_PalindromePartitioning {

    /**
     * Finds all possible palindrome partitionings of s using backtracking.
     * Time Complexity: O(N * 2^N) where N is the length of s.
     * Space Complexity: O(N) for recursion stack and path storage.
     */
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<>();
        dfs(s, 0, new ArrayList<>(), ret);
        return ret;
    }

    private void dfs(String s, int start, List<String> lst, List<List<String>> ret) {
        if (start == s.length()) {
            ret.add(new ArrayList<>(lst));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String str = s.substring(start, i + 1);
            if (isPalindrome(str)) {
                lst.add(str);
                dfs(s, i + 1, lst, ret);
                lst.remove(lst.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
