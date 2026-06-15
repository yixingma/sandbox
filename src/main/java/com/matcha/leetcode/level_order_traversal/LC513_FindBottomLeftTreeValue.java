package com.matcha.leetcode.levelordertraversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 513. Find Bottom Left Tree Value
 * <p>
 * Given the root of a binary tree, return the leftmost value in the last row of the tree.
 * <p>
 * Example 1:
 * Input: root = [2,1,3]
 * Output: 1
 * <p>
 * Example 2:
 * Input: root = [1,2,3,4,null,5,6,null,null,7]
 * Output: 7
 */
public class LC513_FindBottomLeftTreeValue {

    /**
     * Return the leftmost value in the bottom level using BFS.
     * Track the first node of each level; after the last level, that is the answer.
     * Time O(n), space O(w) where w is max level width.
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int leftmost = root.val;
        while (!queue.isEmpty()) {
            int size = queue.size();
            leftmost = queue.peek().val;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return leftmost;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
