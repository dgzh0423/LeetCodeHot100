package everyday;

/**
 * 724.寻找数组的中心下标
 * @author 15304
 */
public class FindPivotIndex {

    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int num : nums){
            sum += num;
        }
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++){
            if (leftSum == sum - leftSum - nums[i]){
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        //int[] nums = {1,7,3,6,5,6};
        int[] nums = {1,-1,0,0,0};
        System.out.println(new FindPivotIndex().pivotIndex(nums));
    }
}
