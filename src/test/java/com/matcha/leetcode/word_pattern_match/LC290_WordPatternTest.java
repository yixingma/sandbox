package com.matcha.leetcode.word_pattern_match;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LC290_WordPatternTest {

    private final LC290_WordPattern solver = new LC290_WordPattern();

    @Test
    public void testWordPatternBasic() {
        assertTrue(solver.wordPattern("abba", "dog cat cat dog"));
        assertFalse(solver.wordPattern("abba", "dog cat cat fish"));
        assertFalse(solver.wordPattern("aaaa", "dog cat cat dog"));
        assertFalse(solver.wordPattern("abba", "dog dog dog dog"));
    }

    @Test
    public void testWordPatternEdgeCases() {
        // Different lengths
        assertFalse(solver.wordPattern("ab", "dog"));
        assertFalse(solver.wordPattern("a", "dog cat"));

        // Single letter pattern and single word
        assertTrue(solver.wordPattern("a", "dog"));
    }
}
