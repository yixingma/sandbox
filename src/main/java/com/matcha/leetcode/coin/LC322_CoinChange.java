package com.matcha.leetcode.coin;

import java.util.Arrays;

/**
 * 322. Coin Change
 * <p>
 * You are given an integer array coins representing coins of different denominations and an integer
 * amount representing a total amount of money. Return the fewest number of coins that you need to
 * make up that amount. If that amount of money cannot be made up by any combination of the coins,
 * return -1. You may assume that you have an infinite number of each kind of coin.
 * <p>
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * <p>
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 * <p>
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 */
public class LC322_CoinChange {

    /**
     * Bottom-up DP: dp[i] is the minimum coins needed to make amount i.
     * Time O(amount * coins.length), space O(amount).
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
