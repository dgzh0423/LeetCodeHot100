package main.twopoints;

/**
 * @author 15304
 */
public class TrapRainWater {
    public static int trap(int[] height) {
        int res = 0;

        /*
           思路：按列求--当前列的高度，左边最高列的高度，右边最高列的高度
           时间复杂度 O(n^2)  空间复杂度 O(1）

           优化：max_left[i] = Max(max_left[i-1], height[i-1]) 从左往右遍历
                max_right[i] = Max(max_right[i+1], height[i+1]) 从右往左遍历
           时间复杂度为 O(n)  空间复杂度 O(n）
         */

        //两端的列不会存水
        for (int i = 1; i < height.length - 1; i++) {
            // 找到 min{柱子i左边最高列的高度，柱子i右边最高列的高度}
            int min = getMin(height, i);
            // 只有当前列高度 < min{左边最高列的高度，右边最高列的高度} 该列才可存水，存水量为：min{左边最高列，右边最高列} - 当前列高度
            if (height[i] < min) {
                res += (min-height[i]);
            }
        }
        return res;
    }

    private static int getMin(int[] height, int i) {
        int leftMax = 0;
        for (int j = i -1; j>=0; j--){
            //这里≥或者＞无所谓，因为我们只需要左边最高列的高度
            if (height[j] > leftMax){
                leftMax = height[j];
            }
        }

        int rightMax = 0;
        for (int j = i +1; j < height.length; j++){
            // 找到右边最高列的高度
            if (height[j] > rightMax){
                rightMax = height[j];
            }
        }
        return Math.min(leftMax, rightMax);
    }

    /*
        假设两柱子分别为 i，j，那么就有 iLeftMax,iRightMax,jLeftMax,jRightMax 这四个变量
        由于 i < j ，故 iLeftMax ≤ jLeftMax，iRightMax ≥ jRightMax.
        当 height[i-1] < height[j+1]，可推得 iLeftMax < jRightMax，必有 iLeftMax < iRightMax 只需要判断 iLeftMax 是否比 i 高
        反之，当 iLeftMax ≥ jRightMax，必有 jRightMax ≤ jLeftMax 只需要判断 jRightMax 是否比 j 高
        时间复杂度为 O(n)  空间复杂度 O(1）
     */
    public static int trapWithTwoPoints(int[] height){
        int len = height.length;
        int i = 1, j = len - 2;
        int iLeftMax = 0, jRightMax = 0;
        int res = 0;

        for (int k = 1; k < len - 1 ; k++) {
            //看柱子i
            if (height[i-1] < height[j+1]){
                iLeftMax = Math.max(iLeftMax, height[i-1]);
                if (iLeftMax > height[i]) {
                    res += iLeftMax - height[i];
                }
                i++;
            }
            //看柱子j
            else {
                jRightMax = Math.max(jRightMax, height[j+1]);
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
