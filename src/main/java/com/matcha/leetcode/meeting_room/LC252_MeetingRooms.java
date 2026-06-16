package com.matcha.leetcode.meeting_room;

import java.util.Arrays;

/**
 * 252. Meeting Rooms
 * <p>
 * Given an array of meeting time intervals where intervals[i] = [start_i, end_i],
 * determine if a person can attend all meetings.
 * <p>
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 * <p>
 * Example 2:
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 */
public class LC252_MeetingRooms {

    /**
     * Return true if no meetings overlap after sorting by start time.
     * Time O(n log n), space O(1) excluding sort.
     */
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }

}
