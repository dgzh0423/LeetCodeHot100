package everyday;

/**
 * 2972.统计移除递增子数组的数量
 * @author 15304
 */
public class NumberOfIncremovableSubarrays {

    /**
     * 可以将nums分成三部分：严格递增的前缀 nums[0,i]，移除的子数组 nums[i+1,j-1]，严格递增的后缀 nums[j,n-1]
     */
    public long incremovableSubarrayCount(int[] a) {
        int n = a.length;
        // 找到最长严格递增前缀的边界i，保证nums[0,i]严格递增
        int i = 0;
        while (i < n - 1 && a[i] < a[i + 1]) {
            i++;
        }
        // 特例：如果数组全递增，则所有的非空子数组都是移除递增子数组
        if (i == n - 1) {
            return (long) n * (n + 1) / 2;
        }
        // 不保留后缀，则删除的移除递增子数组为 nums[x,n-1]，x∈[0,i+1]，共计 i+2 个
        long ans = i + 2;
        // 保留后缀 nums[j,n-1]
        for (int j = n - 1; j == n - 1 || a[j] < a[j + 1]; j--) {
            while (i >= 0 && a[i] >= a[j]) {
                i--;
            }
            // 此时，可以删除的移除递增子数组为 nums[x,j-1]，x∈[0,i+1]，共计 i+2 个
            ans += i + 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        NumberOfIncremovableSubarrays numberOfIncremovableSubarrays = new NumberOfIncremovableSubarrays();
        System.out.println(numberOfIncremovableSubarrays.incremovableSubarrayCount(a));

    }
}
