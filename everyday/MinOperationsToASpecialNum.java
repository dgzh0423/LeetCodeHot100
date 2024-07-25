package everyday;

/**
 * 2844. 生成特殊字符的最少操作
 * @author 15304
 */
public class MinOperationsToASpecialNum {

    // 能被 25 整除的整数称为特殊数字: 5种情况：数字0、或者以00、25、50、75为结尾的数
    public int minimumOperations(String num) {
        int n = num.length();
        boolean found0 = false, found5 = false;
        for (int i = n - 1; i >= 0; i--){
            char c = num.charAt(i);
            if (found0 && (c == '0' || c == '5') || found5 && (c == '2' || c == '7')){
                return n - i - 2;
            }
            if (c == '0'){
                found0 = true;
            } else if (c == '5'){
                found5 = true;
            }
        }
        return found0 ? n - 1 : n;
    }

    public static void main(String[] args) {
        MinOperationsToASpecialNum minOperationsToASpecialNum = new MinOperationsToASpecialNum();
        System.out.println(minOperationsToASpecialNum.minimumOperations("2245047"));
    }
}
