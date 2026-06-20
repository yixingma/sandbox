package com.matcha.leetcode.word_pattern_match;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LC205_IsomorphicStringsTest {

    private final LC205_IsomorphicStrings solver = new LC205_IsomorphicStrings();

    @Test
    public void testIsIsomorphicBasic() {
        assertTrue(solver.isIsomorphic("egg", "add"));
        assertFalse(solver.isIsomorphic("foo", "bar"));
        assertTrue(solver.isIsomorphic("paper", "title"));
    }

    @Test
    public void testIsIsomorphicEdgeCases() {
        // Different lengths
        assertFalse(solver.isIsomorphic("ab", "a"));
        // Empty strings
        assertTrue(solver.isIsomorphic("", ""));
        // Mapping conflict
        assertFalse(solver.isIsomorphic("ab", "aa"));
        assertFalse(solver.isIsomorphic("badc", "baba"));
    }
}
