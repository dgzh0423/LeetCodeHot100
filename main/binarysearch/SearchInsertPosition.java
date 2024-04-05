package main.binarysearch;

/**
 * @author 15304
 */
public class SearchInsertPosition {
    /* 二分查找（双闭区间）模板 */
    int binarySearch(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        // 循环，当搜索区间为空时跳出（当 i > j 时为空）
        while (i <= j) {
            // 中点位置
            int mid = i + (j - i) / 2;
            // target 在区间 [m+1, j] 中
            if (nums[mid] < target) {
                i = mid + 1;
            }
            // target 在区间 [i, m-1] 中
            else if (nums[mid] > target) {
                j = mid - 1;
            }
            // 找到目标元素，返回其索引
            else if(nums[mid] == target) {
                return mid;
            }
        }
        // 未找到目标元素，返回 -1
        return -1;
    }

    /* 查找插入点本质上是在查找最左一个 target 的索引 */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // 如果target不存在：二分结束时，left指向首个大于 target 的元素， right指向首个小于 target 的元素
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 这里找到target直接返回是因为nums元素不重复，即只有一个target
                return mid;
                // 如果nums里有多个target，想要找到最左边的target的下标，就不要立即返回，而是缩小右边界，继续往左找
                // right = mid - 1;
                // 同理，想要找到最右边的target的下标，就不要立即返回，而是缩小左边界，继续往右找，最后返回 right
                // left = mid + 1；
            }
        }
        // 未找到target，返回它将会被按顺序插入的位置
        return left;
    }

    public static void main(String[] args) {
        int[]nums = {1,3,5,6};
        int target = 2;
        SearchInsertPosition sp = new SearchInsertPosition();
        System.out.println(sp.searchInsert(nums, target));
        System.out.println(sp.binarySearch(nums, target));
    }
}
