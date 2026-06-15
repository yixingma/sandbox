package com.matcha.leetcode.coin;

/**
 * 518. Coin Change II
 * <p>
 * You are given an integer array coins representing coins of different denominations and an integer
 * amount representing a total amount of money. Return the number of combinations that make up that
 * amount. If that amount of money cannot be made up by any combination of the coins, return 0.
 * You may assume that you have an infinite number of each kind of coin.
 * The answer is guaranteed to fit into a signed 32-bit integer.
 * <p>
 * Example 1:
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: 5 = 1+1+1+1+1, 1+1+1+2, 1+2+2, 5
 * <p>
 * Example 2:
 * Input: amount = 3, coins = [2]
 * Output: 0
 * <p>
 * Example 3:
 * Input: amount = 10, coins = [10]
 * Output: 1
 */
public class LC518_CoinChangeII {

    /**
     * Unbounded knapsack DP: dp[i] is the number of ways to make amount i.
     * Iterate coins outer loop so combinations are counted, not permutations.
     * Time O(amount * coins.length), space O(amount).
     */
    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        if (coins == null || coins.length == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    /*
     * Notes: why iterate coins in the outer loop (combinations, not permutations)
     *
     * Combination vs permutation
     * - Combination: order does not matter. For amount=5, coins=[1,2,5], {1,2,2} and {2,1,2} are the SAME way.
     * - Permutation: order matters. {1,2,2}, {2,1,2}, {2,2,1} would be three different ways.
     * LC518 asks for combinations.
     *
     * DP meaning
     * - dp[i] = number of combinations that sum to i.
     * - Update: dp[i] += dp[i - coin] means "take every way to make (i - coin), then add one more coin."
     *
     * Coins outer (correct for LC518)
     * - When processing coin=2, dp[i - 2] only contains ways built from coins already processed (1 and 2).
     * - We never "go back" to add a smaller coin after a larger one in a way that double-counts reorderings.
     * - Each multiset of coins is built once, in a canonical order (e.g. 1s first, then 2s, then 5s).
     *
     * Example: amount=3, coins=[1,2]
     * - After coin=1: dp = [1,1,1,1]  (ways: {1}, {1,1}, {1,1,1})
     * - After coin=2: dp[2] += dp[0] -> {2}; dp[3] += dp[1] -> {1,2}
     * - Result: {1,1,1}, {1,2} -> 2 ways (correct)
     *
     * Amount outer (wrong for LC518 — counts permutations)
     *   for (int i = 1; i <= amount; i++)
     *       for (int coin : coins)
     *           dp[i] += dp[i - coin];
     * - For i=3: coin=1 adds {1,1,1} and {2,1}; coin=2 adds {1,2}
     * - Result: {1,1,1}, {2,1}, {1,2} -> 3 ways. {2,1} and {1,2} are the same combination but counted twice.
     *
     * One-line intuition
     * - Coins outer: extend combinations by adding this coin type -> each multiset counted once.
     * - Amount outer: for this total, try every coin -> same coins in different orders counted multiple times.
     */
}
