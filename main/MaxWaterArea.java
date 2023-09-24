package main;

/**
 * 水面积计算公式：S(i,j) = min(h[i],h[j]) × (j−i)
 */
public class MaxWaterArea {
    public static int maxArea(int[] height){

        int i = 0, j = height.length - 1, max = 0;
        //面积由短板决定，故要比较 height[i] 和 height[j]哪个更短，然后套面积公式，
        //取height[i]计算之后，需要i++ ; 取height[j]计算之后，需要j--
        while (i < j){
            max = height[i] < height[j] ? Math.max(max, (j-i) * height[i++]) : Math.max(max, (j-i) * height[j--]);
        }
        return max;
    }

    public static void main(String[] args) {
//        int [] height = {1,8,6,2,5,4,8,3,7};
        int [] height = {1, 1};
        System.out.println(maxArea(height));
    }
}
