package com.matcha.leetcode.graph;

/**
 * 997. Find the Town Judge
 * <p>
 * In a town, there are n people labeled from 1 to n. There is a rumor that one
 * of these people
 * is secretly the town judge.
 * <p>
 * If the town judge exists, then:
 * 1. The town judge trusts nobody.
 * 2. Everybody (except for the town judge) trusts the town judge.
 * 3. There is exactly one person that satisfies properties 1 and 2.
 * <p>
 * Return the label of the town judge if the town judge exists and can be
 * identified, or return -1 otherwise.
 * <p>
 * Example 1:
 * Input: n = 2, trust = [[1,2]]
 * Output: 2
 * <p>
 * Example 2:
 * Input: n = 3, trust = [[1,3],[2,3]]
 * Output: 3
 * <p>
 * Example 3:
 * Input: n = 3, trust = [[1,3],[2,3],[3,1]]
 * Output: -1
 */
public class LC997_FindTownJudge {

    /**
     * Finds the town judge by tracking net trust score (indegree - outdegree) for
     * each person.
     * Time Complexity: O(T + N) where T is the length of trust array and N is
     * number of people.
     * Space Complexity: O(N) to store trust scores.
     */
    public int findJudge(int n, int[][] trust) {
        int[] trustScores = new int[n + 1];
        for (int[] relation : trust) {
            trustScores[relation[0]]--;
            trustScores[relation[1]]++;
        }
        for (int i = 1; i <= n; i++) {
            if (trustScores[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
}
