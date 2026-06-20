package com.matcha.leetcode.word_pattern_match;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LC890_FindAndReplacePatternTest {

    private final LC890_FindAndReplacePattern solver = new LC890_FindAndReplacePattern();

    @Test
    public void testFindAndReplacePatternBasic() {
        String[] words1 = {"abc", "deq", "mee", "aqq", "dkd", "ccc"};
        List<String> res1 = solver.findAndReplacePattern(words1, "abb");
        assertEquals(2, res1.size());
        assertTrue(res1.contains("mee"));
        assertTrue(res1.contains("aqq"));

        String[] words2 = {"a", "b", "c"};
        List<String> res2 = solver.findAndReplacePattern(words2, "a");
        assertEquals(3, res2.size());
        assertTrue(res2.contains("a"));
        assertTrue(res2.contains("b"));
        assertTrue(res2.contains("c"));
    }

    @Test
    public void testFindAndReplacePatternMismatchedLength() {
        // Words of different length than pattern should not match
        String[] words = {"ab", "abcd", "abc"};
        List<String> res = solver.findAndReplacePattern(words, "abb");
        assertEquals(0, res.size());
    }
}
