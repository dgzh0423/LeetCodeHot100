package main.greedy;

/**
 * @author 15304
 */
public class MaxStockProfit {

    // 121. 买卖股票的最佳时机  操作次数 k = 1
    public int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for (int price : prices) {
            // 第i天卖出的最高利润，一定是在第i天前以最低价格价格买入
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }

    // 122. 买卖股票的最佳时机 II
    public int maxProfitII(int[] prices) {
        int profit = 0;
        // 只要两天之间有利润，就可以进行买卖操作，累加利润
        for (int i = 1; i < prices.length; i++) {
            profit += Math.max(prices[i] - prices[i - 1], 0);
        }
        return profit;
    }

    public static void main(String[] args) {
        MaxStockProfit max = new MaxStockProfit();
        int[] prices = {7,1,5,3,6,4};
        System.out.println(max.maxProfit(prices));
        System.out.println(max.maxProfitII(prices));
    }
}
