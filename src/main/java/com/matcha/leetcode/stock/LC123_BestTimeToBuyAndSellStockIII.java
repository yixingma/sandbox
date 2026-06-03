package com.matcha.leetcode.stock;

/**
 * 123. Best Time to Buy and Sell Stock III
 * <p>
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * <p>
 * Example 1:
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3. Total = 6.
 * <p>
 * Example 2:
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * <p>
 * Example 3:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 */
public class LC123_BestTimeToBuyAndSellStockIII {

    /**
     * State machine with up to two transactions:
     * buy1/sell1 for the first transaction, buy2/sell2 for the second.
     * Time O(n), space O(1).
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int buy1 = -prices[0];
        int sell1 = 0;
        int buy2 = -prices[0];
        int sell2 = 0;
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, buy1 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
        }
        return sell2;
    }
}

