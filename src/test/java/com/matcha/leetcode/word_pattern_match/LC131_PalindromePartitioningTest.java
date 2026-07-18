package com.matcha.leetcode.word_pattern_match;

import com.matcha.leetcode.word_pattern_match.back_tracking.LC131_PalindromePartitioning;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LC131_PalindromePartitioningTest {

    private final LC131_PalindromePartitioning solver = new LC131_PalindromePartitioning();

    @Test
    public void testPartitionBasic() {
        List<List<String>> res1 = solver.partition("aab");
        assertEquals(2, res1.size());
        assertTrue(res1.contains(List.of("a", "a", "b")));
        assertTrue(res1.contains(List.of("aa", "b")));

        List<List<String>> res2 = solver.partition("a");
        assertEquals(1, res2.size());
        assertTrue(res2.contains(List.of("a")));
    }

    @Test
    public void testPartitionNoPalindromeLongerThanOne() {
        List<List<String>> res = solver.partition("abc");
        assertEquals(1, res.size());
        assertTrue(res.contains(List.of("a", "b", "c")));
    }

    @Test
    public void testPartitionEmpty() {
        List<List<String>> res = solver.partition("");
        assertEquals(1, res.size());
        assertTrue(res.get(0).isEmpty());
    }
}
