package com.matcha.leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1136. Parallel Courses
 * <p>
 * You are given an integer n, which indicates that there are n courses labeled from 1 to n.
 * You are also given an array relations where relations[i] = [prevCoursei, nextCoursei],
 * representing a prerequisite relationship between course prevCoursei and course nextCoursei.
 * <p>
 * In one semester, you can take any number of courses as long as you have taken all the
 * prerequisites in the previous semesters for the courses you are taking.
 * <p>
 * Return the minimum number of semesters needed to take all courses. If there is no way to take
 * all the courses, return -1.
 * <p>
 * Example 1:
 * Input: n = 3, relations = [[1,3],[2,3]]
 * Output: 2
 * <p>
 * Example 2:
 * Input: n = 3, relations = [[1,2],[2,3],[3,1]]
 * Output: -1
 */
public class LC1136_ParallelCourses {

    /**
     * Finds the minimum semesters needed to take all courses using Kahn's algorithm (BFS topological sort)
     * keeping track of the BFS depth level.
     * Time Complexity: O(V + E) where V is n and E is relations length.
     * Space Complexity: O(V + E) for adjacency list and indegree array.
     */
    public int minimumSemesters(int n, int[][] relations) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        int[] inDegree = new int[n + 1];
        for (int[] relation : relations) {
            int prev = relation[0];
            int next = relation[1];
            adj.get(prev).add(next);
            inDegree[next]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int semesters = 0;
        int takenCourses = 0;
        while (!queue.isEmpty()) {
            semesters++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                takenCourses++;
                for (int next : adj.get(curr)) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
        }

        return takenCourses == n ? semesters : -1;
    }
}
