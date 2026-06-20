package com.matcha.leetcode.word_pattern_match;

/**
 * 205. Isomorphic Strings
 * <p>
 * Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 * <p>
 * All occurrences of a character must be replaced with another character while preserving the order
 * of characters. No two characters may map to the same character, but a character may map to itself.
 * <p>
 * Example 1:
 * Input: s = "egg", t = "add"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "foo", t = "bar"
 * Output: false
 * <p>
 * Example 3:
 * Input: s = "paper", t = "title"
 * Output: true
 */
public class LC205_IsomorphicStrings {

    /**
     * Determines if two strings are isomorphic using index mapping.
     * Time Complexity: O(n) where n is the length of s (and t).
     * Space Complexity: O(1) auxiliary space as the map arrays have a constant size of 256.
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] mapS = new int[256];
        int[] mapT = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            if (mapS[charS] != mapT[charT]) {
                return false;
            }
            mapS[charS] = i + 1;
            mapT[charT] = i + 1;
        }
        return true;
    }
}
