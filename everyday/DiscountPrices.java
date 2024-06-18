package everyday;

/**
 * 2288. 价格减免
 * @author 15304
 */
public class DiscountPrices {

    // 时间复杂度：O(n)，空间复杂度：O(n)
    public String discountPrices(String sentence, int discount) {
        // 1. 将句子按空格分开为多个单词
        String[] strs = sentence.split(" ");
        double d = 1 - discount / 100.0;
        StringBuilder sb = new StringBuilder();
        // 2. 查找符合要求的价格，以‘$’开头，价格都是正整数且没有前置0
        for (int i = 0; i < strs.length; i++) {
            // [$]{1}：表示字符串必须精确包含一个美元符号($)
            if (strs[i].matches("[$][0-9]+")){
                strs[i] = String.format("$%.2f", Long.parseLong(strs[i].substring(1)) * d);
            }
            sb.append(strs[i]);
            if (i != strs.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String sentence = "there are $1 $2 and 5$ candies in the shop";
        int discount = 50;
        System.out.println(new DiscountPrices().discountPrices(sentence, discount));
    }
}
