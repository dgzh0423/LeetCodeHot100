package main.twopoints;

public class TrapRainWater {
    public static int trap(int[] height) {
        int res = 0;

        /*
           思路：按列求--当前列的高度，左边最高列的高度，右边最高列的高度
           时间复杂度 O(n^2)  空间复杂度 O(1）

           优化：max_left[i] = Max(max_left [i-1],height[i-1])
                max_right[i] = Max(max_right[i+1],height[i+1])
           时间复杂度为 O(n)  空间复杂度 O(n）
         */

        for (int i = 1; i < height.length - 1; i++) {//两端的列不会存水
            int leftMax = 0;
            for (int j = i-1; j>=0; j--){
                if (height[j] > leftMax){//这里≥或者＞无所谓，因为我们只需要左边最高列的高度
                    leftMax = height[j];
                }
            }

            int rightMax = 0;
            for (int j = i+1; j < height.length; j++){
                if (height[j] > rightMax){//同理
                    rightMax = height[j];
                }
            }
            int min = Math.min(leftMax, rightMax);
            if (height[i] < min) res += (min-height[i]);//只有当前列高度 < min{左边最高列的高度，右边最高列的高度} 该列才可存水，存水量为：min{左边最高列，右边最高列} - 当前列高度
        }

        return res;
    }

    /*
        假设两柱子分别为 i，j，那么就有 iLeftMax,iRightMax,jLeftMax,jRightMax 这四个变量。
        当 iLeftMax < jRightMax 的话，那么我们就只需要判断 iLeftMax 是否比 i 高
        当 iLeftMax >= jRightMax 的话，同理，只需要判断 jRightMax 是否比 j 高
        时间复杂度为 O(n)  空间复杂度 O(1）
     */
    public static int trapWithTwoPoints(int[] height){
        int len = height.length;
        int i = 1, j = len - 2;
        int iLeftMax = 0, jRightMax = 0;
        int res = 0;

        for (int k = 1; k < len - 1 ; k++) {
            if (height[i-1] < height[j+1]){ //看柱子i
                iLeftMax = Math.max(iLeftMax , height[i-1]);
                if (iLeftMax > height[i]) {
                    res += iLeftMax - height[i];
                }
                i++;
            }else {//看柱子j
                jRightMax = Math.max(jRightMax,height[j+1]);
                if (jRightMax > height[j]){
                    res += jRightMax - height[j];
                }
                j--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
        System.out.println(trapWithTwoPoints(height));

    }
}
