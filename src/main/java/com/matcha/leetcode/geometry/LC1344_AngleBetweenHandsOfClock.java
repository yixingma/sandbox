package com.matcha.leetcode.geometry;

/**
 * 1344. Angle Between Hands of a Clock
 * <p>
 * Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between
 * the hour and the minute hand.
 * Answers within 10^-5 of the actual value will be accepted.
 * <p>
 * Example 1:
 * Input: hour = 12, minutes = 30
 * Output: 165
 * <p>
 * Example 2:
 * Input: hour = 3, minutes = 30
 * Output: 75
 * <p>
 * Example 3:
 * Input: hour = 3, minutes = 15
 * Output: 7.5
 */
public class LC1344_AngleBetweenHandsOfClock {

    /**
     * Calculates the smaller angle between the hour and minute hands of a clock.
     * Time Complexity: O(1).
     * Space Complexity: O(1).
     */
    public double angleClock(int hour, int minutes) {
        // Minute hand moves 360 degrees in 60 minutes -> 6 degrees per minute.
        double minuteAngle = minutes * 6.0;

        // Hour hand moves 360 degrees in 12 hours -> 30 degrees per hour.
        // It also moves 30 degrees / 60 minutes -> 0.5 degrees per minute.
        double hourAngle = (hour % 12) * 30.0 + minutes * 0.5;

        double diff = Math.abs(hourAngle - minuteAngle);
        return Math.min(diff, 360.0 - diff);
    }
}
