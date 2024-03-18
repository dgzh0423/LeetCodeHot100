package main.heap;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author 15304
 */
public class KthLargest {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }

    private final static Random RANDOM = new Random();

    public int  findKthLargestByQuickSelect(int[] nums, int k){
        int len = nums.length, left = 0, right = len - 1;
        //「快速选择」：设 N 为数组长度。根据快速排序原理，如果某次哨兵划分后，pivot的索引正好是 N−k ，则意味着它就是第 k 大的数字。
        int targetIndex = len - k;

        while (true) {
            int pivotIndex = partition(nums, left, right);
            if (pivotIndex == targetIndex) {
                return nums[pivotIndex];
            } else if (pivotIndex < targetIndex) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }

    }

    private int partition(int[] nums, int left, int right) {
        // 随机交换第 1 个元素与它后面的任意 1 个元素的位置, 然后再作为 pivot
        int randomIndex = left + RANDOM.nextInt(right - left + 1);
        swap(nums, left, randomIndex);
        int pivot = nums[left];
        int le = left + 1, ge = right;
        // 使 nums[left + 1..le) <= pivot;  nums(ge..right] >= pivot;
        while (true) {
            while (le <= ge && nums[le] < pivot) {
                le++;
            }
            while (le <= ge && nums[ge] > pivot) {
                ge--;
            }
            if (le >= ge) {
                break;
            }
            swap (nums, le, ge);
            le++;
            ge--;
        }
        //将pivot放到正确的位置上
        swap(nums, left, ge);
        return ge;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void main(String[] args) {
        KthLargest kth = new KthLargest();
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(kth.findKthLargestByQuickSelect(nums, k));
        System.out.println(kth.findKthLargest(nums, k));
    }
}
