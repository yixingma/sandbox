package com.matcha.leetcode.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * 242. Valid Anagram
 * <p>
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * <p>
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 */
public class LC242_ValidAnagram {

    /**
     * Solution using a fixed-size frequency array for lowercase English letters.
     * Time Complexity: O(n) where n is the length of the string.
     * Space Complexity: O(1) as the array size is constant (26).
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Follow-up solution using HashMap to handle Unicode characters.
     * Time Complexity: O(n)
     * Space Complexity: O(k) where k is the number of unique characters in the strings.
     */
    public boolean isAnagramUnicode(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            counts.put(charS, counts.getOrDefault(charS, 0) + 1);
            char charT = t.charAt(i);
            counts.put(charT, counts.getOrDefault(charT, 0) - 1);
        }
        for (int val : counts.values()) {
            if (val != 0) {
                return false;
            }
        }
        return true;
    }
}
