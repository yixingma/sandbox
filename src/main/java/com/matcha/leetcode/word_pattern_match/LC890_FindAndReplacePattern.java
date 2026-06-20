package com.matcha.leetcode.word_pattern_match;

import java.util.ArrayList;
import java.util.List;

/**
 * 890. Find and Replace Pattern
 * <p>
 * Given a list of strings words and a string pattern, return a list of words[i] that match pattern.
 * You may return the answer in any order.
 * <p>
 * A word matches the pattern if there exists a permutation of letters p so that after replacing
 * every letter x in the pattern with p(x), we get the word.
 * <p>
 * Example 1:
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["mee","aqq"]
 * <p>
 * Example 2:
 * Input: words = ["a","b","c"], pattern = "a"
 * Output: ["a","b","c"]
 */
public class LC890_FindAndReplacePattern {

    /**
     * Finds all words that match the given pattern.
     * Time Complexity: O(N * L) where N is the number of words and L is the length of pattern/word.
     * Space Complexity: O(L) to store the result, with O(1) auxiliary space (size 26 arrays).
     */
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (matches(word, pattern)) {
                result.add(word);
            }
        }
        return result;
    }

    private boolean matches(String word, String pattern) {
        if (word.length() != pattern.length()) {
            return false;
        }
        int[] wToP = new int[26];
        int[] pToW = new int[26];
        for (int i = 0; i < word.length(); i++) {
            int w = word.charAt(i) - 'a';
            int p = pattern.charAt(i) - 'a';
            if (wToP[w] == 0 && pToW[p] == 0) {
                wToP[w] = p + 1;
                pToW[p] = w + 1;
            } else if (wToP[w] != p + 1 || pToW[p] != w + 1) {
                return false;
            }
        }
        return true;
    }
}
