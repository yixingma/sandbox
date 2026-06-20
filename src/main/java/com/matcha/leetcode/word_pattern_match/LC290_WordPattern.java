package com.matcha.leetcode.word_pattern_match;

import java.util.HashMap;
import java.util.Map;

/**
 * 290. Word Pattern
 * <p>
 * Given a pattern and a string s, find if s follows the same pattern.
 * <p>
 * Here follow means a full match, such that there is a bijection between a letter in pattern
 * and a non-empty word in s.
 * <p>
 * Example 1:
 * Input: pattern = "abba", s = "dog cat cat dog"
 * Output: true
 * <p>
 * Example 2:
 * Input: pattern = "abba", s = "dog cat cat fish"
 * Output: false
 * <p>
 * Example 3:
 * Input: pattern = "aaaa", s = "dog cat cat dog"
 * Output: false
 */
public class LC290_WordPattern {

    /**
     * Determines if string s follows the pattern using two maps for bijection.
     * Time Complexity: O(n + m) where n is pattern length and m is s length.
     * Space Complexity: O(w) where w is the number of unique words in s.
     */
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }

        Map<Character, String> charToWord = new HashMap<>();
        Map<String, Character> wordToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            if (charToWord.containsKey(c)) {
                if (!charToWord.get(c).equals(word)) {
                    return false;
                }
            } else {
                if (wordToChar.containsKey(word)) {
                    return false;
                }
                charToWord.put(c, word);
                wordToChar.put(word, c);
            }
        }

        return true;
    }
}
