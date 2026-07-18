package com.matcha.leetcode.word_pattern_match;

import com.matcha.leetcode.word_pattern_match.back_tracking.LC79_WordSearch;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LC79_WordSearchTest {

    private final LC79_WordSearch solver = new LC79_WordSearch();

    @Test
    public void testWordSearchBasic() {
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        assertTrue(solver.exist(board, "ABCCED"));
        assertTrue(solver.exist(board, "SEE"));
        assertFalse(solver.exist(board, "ABCB"));
    }

    @Test
    public void testWordSearchSingleCell() {
        char[][] board = {{'A'}};
        assertTrue(solver.exist(board, "A"));
        assertFalse(solver.exist(board, "B"));
    }

    @Test
    public void testWordSearchEmptyOrNull() {
        char[][] emptyBoard = {};
        assertFalse(solver.exist(emptyBoard, "A"));
        assertFalse(solver.exist(null, "A"));
        assertFalse(solver.exist(new char[][]{{'A'}}, ""));
        assertFalse(solver.exist(new char[][]{{'A'}}, null));
    }
}
