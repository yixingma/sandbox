package com.matcha.leetcode.hashmap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LC242_ValidAnagramTest {

    private final LC242_ValidAnagram solver = new LC242_ValidAnagram();

    @Test
    public void testIsAnagramBasic() {
        assertTrue(solver.isAnagram("anagram", "nagaram"));
        assertFalse(solver.isAnagram("rat", "car"));
    }

    @Test
    public void testIsAnagramEdgeCases() {
        // Different lengths
        assertFalse(solver.isAnagram("abc", "ab"));
        // Empty strings
        assertTrue(solver.isAnagram("", ""));
    }

    @Test
    public void testIsAnagramUnicodeBasic() {
        assertTrue(solver.isAnagramUnicode("anagram", "nagaram"));
        assertFalse(solver.isAnagramUnicode("rat", "car"));
    }

    @Test
    public void testIsAnagramUnicodeEdgeCases() {
        // Different lengths
        assertFalse(solver.isAnagramUnicode("abc", "ab"));
        // Empty strings
        assertTrue(solver.isAnagramUnicode("", ""));
        // Unicode characters (accented characters, emojis, etc.)
        assertTrue(solver.isAnagramUnicode("café", "éfac"));
        assertFalse(solver.isAnagramUnicode("café", "cafe"));
        assertTrue(solver.isAnagramUnicode("😊🚀", "🚀😊"));
        assertFalse(solver.isAnagramUnicode("😊🚀", "😊🛸"));
    }
}
