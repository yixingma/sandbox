package com.matcha.leetcode.geometry;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LC1344_AngleBetweenHandsOfClockTest {

    private final LC1344_AngleBetweenHandsOfClock solver = new LC1344_AngleBetweenHandsOfClock();

    @Test
    public void testAngleClockBasic() {
        assertEquals(165.0, solver.angleClock(12, 30), 1e-5);
        assertEquals(75.0, solver.angleClock(3, 30), 1e-5);
        assertEquals(7.5, solver.angleClock(3, 15), 1e-5);
    }

    @Test
    public void testAngleClockEdgeCases() {
        // When hands overlap/nearly overlap or form maximum angle
        assertEquals(0.0, solver.angleClock(12, 0), 1e-5);
        assertEquals(180.0, solver.angleClock(6, 0), 1e-5);
    }
}
