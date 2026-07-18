package com.matcha.leetcode.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LC997_FindTownJudgeTest {

    private final LC997_FindTownJudge solver = new LC997_FindTownJudge();

    @Test
    public void testFindJudgeBasic() {
        assertEquals(2, solver.findJudge(2, new int[][]{{1, 2}}));
        assertEquals(3, solver.findJudge(3, new int[][]{{1, 3}, {2, 3}}));
        assertEquals(-1, solver.findJudge(3, new int[][]{{1, 3}, {2, 3}, {3, 1}}));
    }

    @Test
    public void testFindJudgeNoTrustAndSinglePerson() {
        // N = 1, trust = [] -> judge is 1
        assertEquals(1, solver.findJudge(1, new int[][]{}));
        // N = 3, trust = [] -> no judge (since everyone except judge must trust the judge, i.e. 2 trusts needed, but 0 present)
        assertEquals(-1, solver.findJudge(3, new int[][]{}));
    }
}
