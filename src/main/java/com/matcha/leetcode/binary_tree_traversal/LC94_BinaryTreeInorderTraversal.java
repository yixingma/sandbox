package com.matcha.leetcode.binary_tree_traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 94. Binary Tree Inorder Traversal
 * <p>
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 * <p>
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
 * <p>
 * Example 2:
 * Input: root = []
 * Output: []
 * <p>
 * Example 3:
 * Input: root = [1]
 * Output: [1]
 */
public class LC94_BinaryTreeInorderTraversal {

    /**
     * Inorder traversal using recursive DFS (left, root, right).
     * Time O(n), space O(h) for recursion stack.
     */
    public List<Integer> inorderTraversalDfs(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private void dfs(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        dfs(node.left, result);
        result.add(node.val);
        dfs(node.right, result);
    }

    /**
     * Inorder traversal using an iterative queue (left spine, then visit, then right).
     * Time O(n), space O(h).
     */
    public List<Integer> inorderTraversalBfs(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode current = root;
        while (current != null || !queue.isEmpty()) {
            while (current != null) {
                queue.offer(current);
                current = current.left;
            }
            current = queue.poll();
            result.add(current.val);
            current = current.right;
        }
        return result;
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
