package com.matcha.leetcode.stock;

/**
 * 309. Best Time to Buy and Sell Stock with Cooldown
 * <p>
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * Find the maximum profit you can achieve. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * <p>
 * Example 1:
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell], profit = (2-1) + (2-0) = 3.
 * <p>
 * Example 2:
 * Input: prices = [1]
 * Output: 0
 */
public class LC309_BestTimeToBuyAndSellStockWithCooldown {

    /**
     * State machine DP: rest (can buy), hold, sold (cooldown applies via rest update).
     * Time O(n), space O(1).
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int rest = 0;
        int hold = -prices[0];
        int sold = 0;
        for (int i = 1; i < prices.length; i++) {
            int prevSold = sold;
            sold = hold + prices[i];
            hold = Math.max(hold, rest - prices[i]);
            rest = Math.max(rest, prevSold);
        }
        return Math.max(rest, sold);
    }
}
