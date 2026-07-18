package com.matcha.leetcode.word_pattern_match;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. Restore IP Addresses
 * <p>
 * A valid IP address consists of exactly four integers, each between 0 and 255,
 * separated by single dots and cannot have leading zeros.
 * <p>
 * Given a string s containing only digits, return all possible valid IP
 * addresses that can be
 * formed by inserting dots into s. You are not allowed to reorder or remove any
 * digits in s.
 * You may return the answers in any order.
 * <p>
 * Example 1:
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * <p>
 * Example 2:
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * <p>
 * Example 3:
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 */
public class LC93_RestoreIpAddresses {

    /**
     * Restores all possible valid IP addresses from the digit string.
     * Time Complexity: O(1) as the recursion depth is at most 4 and the length of s
     * is at most 12.
     * Space Complexity: O(1) since the maximum number of combinations is constant.
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> ret = new ArrayList<>();
        restore(s, 0, new ArrayList<String>(), ret);
        return ret;
    }

    public void restore(String s, int start, List<String> path, List<String> ret) {
        if (path.size() == 4) {
            if (start == s.length()) {
                ret.add(String.join(".", path));
            }
            return;
        }
        for (int len = 1; len <= 3; len++) {
            if (start + len > s.length()) {
                break;
            }
            String temp = s.substring(start, start + len);
            if (!isValid(temp)) {
                continue;
            }
            path.add(temp);
            restore(s, start + len, path, ret);
            path.remove(path.size() - 1);
        }
        return;
    }

    private boolean isValid(String part) {
        if (part.length() > 1 &&
                part.charAt(0) == '0') {
            return false;
        }

        int num = Integer.parseInt(part);

        return num <= 255;
    }
}
