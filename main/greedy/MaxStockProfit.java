package main.greedy;

/**
 * 121. 买卖股票的最佳时机
 * @author 15304
 */
public class MaxStockProfit {
    public int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for (int price : prices) {
            // 第i天卖出的最高利润，一定是在第i天前以最低价格价格买入
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }

    public static void main(String[] args) {
        MaxStockProfit max = new MaxStockProfit();
        int[] prices = {7,1,5,3,6,4};
        System.out.println(max.maxProfit(prices));
    }
}
