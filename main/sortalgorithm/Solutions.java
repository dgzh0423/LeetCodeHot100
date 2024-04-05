package main.sortalgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author 15304
 */
public class Solutions {

    /**
     * 选择排序：开启一个循环，每轮从未排序区间选择 "最小的元素"，将其放到已排序区间的末尾。
     */
    void selectionSort(int[] nums) {
        int n = nums.length;
        // 外循环：未排序区间为 [i, n-1]
        for (int i = 0; i < n - 1; i++) {
            // 内循环：找到未排序区间内的最小元素,下标为 k
            int k = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[k]) {
                    // 记录最小元素的索引
                    k = j;
                }
            }
            // 将该最小元素与未排序区间的首个元素交换
            swap(nums, i, k);
        }
    }

    /**
     * 冒泡排序：从数组最左端开始向右遍历，依次比较相邻元素大小，如果“左元素 > 右元素”就交换二者。遍历完成后，最大的元素会被移动到数组的最右端。
     */
    void bubbleSort(int[] nums) {
        // 外循环：未排序区间为 [0, i]
        for (int i = nums.length - 1; i > 0; i--) {
            // 内循环：将未排序区间 [0, i] 中的最大元素交换至该区间的最右端
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    // 交换 nums[j] 与 nums[j + 1]
                    swap(nums, j ,j + 1);
                }
            }
        }
    }

    /**
     * 插入排序：每轮选取未排序区间的首个元素为pivot，将pivot与排好序区间的元素比较，找到合适的位置，再插入。
     */
    void insertionSort(int[] nums) {
        // 外循环：初始默认nums[0]已排好序，pivot从num[1]开始
        for (int i = 1; i < nums.length; i++) {
            int pivot = nums[i], j = i - 1;
            // 内循环：将 pivot 插入到已排序部分的正确位置
            while (j >= 0 && nums[j] > pivot) {
                // 将 nums[j] 向右移动一位
                nums[j + 1] = nums[j]; 
                j--;
            }
            // 将 pivot 赋值到正确位置
            nums[j + 1] = pivot;        
        }
    }


    /**
     * 快速排序：核心是 哨兵划分 和 递归分治
     * @param nums
     * @param left
     * @param right
     */
    void quickSort(int[] nums, int left, int right) {
        // 子数组长度为 1 时终止递归
        if (left >= right) {
            return;
        }
        // 哨兵划分
        int pivot = partition(nums, left, right);
        // 递归左子数组、右子数组
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    /**
     * 哨兵划分：选择数组中的某个元素作为“基准数”，将所有小于基准数的元素移到其左侧，而大于基准数的元素移到其右侧。
     * @param nums
     * @param left
     * @param right
     * @return
     */
    int partition(int[] nums, int left, int right) {
        // 以 nums[left] 为 pivot
        int i = left, j = right;
        while (i < j) {
            // 从右向左找首个小于pivot的元素
            while (i < j && nums[j] >= nums[left]) {
                j--;
            }
            // 从左向右找首个大于pivot的元素
            while (i < j && nums[i] <= nums[left]) {
                i++;
            }
            swap(nums, i, j);
        }
        // 将pivot交换至两子数组的分界线
        swap(nums, i, left);
        // 返回pivot的索引
        return i;
    }

    /**
     * 归并排序
     * @param nums
     * @param left
     * @param right
     */
    void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        // 划分阶段：先通过递归不断地将数组从中点处分开，将长数组的排序问题转换为短数组的排序问题
        int mid = (left + right) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        // 合并阶段；当子数组长度为 1 时终止划分，开始合并，持续地将左右两个较短的有序数组合并为一个较长的有序数组，直至结束
        merge(nums, left, mid, right);
    }

    /**
     * 合并左子数组和右子数组
     */
    void merge(int[] nums, int left, int mid, int right) {
        // 左子数组区间为 [left, mid], 右子数组区间为 [mid+1, right]
        // 创建一个临时数组 tmp ，用于存放合并后的结果
        int[] tmp = new int[right - left + 1];
        // 初始化左子数组和右子数组的起始索引
        int i = left, j = mid + 1, k = 0;
        // 当左右子数组都还有元素时，进行比较并将较小的元素复制到临时数组中
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        // 将左子数组和右子数组的剩余元素复制到临时数组中
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        // 将临时数组 tmp 中的元素复制回原数组 nums 的对应区间
        for (k = 0; k < tmp.length; k++) {
            nums[left + k] = tmp[k];
        }
    }

    /**
     * 桶排序：先通过设置一些具有大小顺序的桶，每个桶对应一个数据范围，将数据平均分配到各个桶中；然后，在每个桶内部分别执行排序；最终按照桶的顺序将所有数据合并。
     * @param nums
     * @param numberOfBuckets
     */
    public void bucketSort(int[] nums, int numberOfBuckets){
        if (numberOfBuckets <= 0){
            throw new ArithmeticException();
        }
        List<List<Integer>> buckets = createBuckets(nums, numberOfBuckets);
        int i = 0;
        for (List<Integer> bucket : buckets){
            Collections.sort(bucket);
            for (int item: bucket){
                nums[i++] = item;
            }
        }

    }
    private List<List<Integer>> createBuckets(int[] nums, int numberOfBuckets){
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < numberOfBuckets; i++){
            buckets.add(new ArrayList<>());
        }
        for (int num: nums){
            // !! nums里的元素必须小于 numberOfBuckets的平方，否则就会超出buckets的下标范围[0, numberOfBuckets - 1] !!
            buckets.get(num / numberOfBuckets).add(num);
        }
        return buckets;
    }


    /**
     * 计数排序
     * @param nums
     */
    void countingSort(int[] nums) {
        // 1. 统计数组最大元素 max
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        // 2. 统计各数字的出现次数
        // counter[num] 代表 num 的出现次数
        int[] counter = new int[max + 1];
        for (int num : nums) {
            counter[num]++;
        }
        // 3. 遍历 counter ，将各元素填回原数组 nums
        int index = 0;
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] == 0) {
                continue;
            }
            for (int j = 0; j < counter[i]; j++) {
                nums[index] = i;
                index++;
            }
        }
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static void main(String[] args) {
        Solutions solutions = new Solutions();
        int[] nums = {4,1,3,1,5,2};
        solutions.quickSort(nums, 0, nums.length - 1);
        solutions.mergeSort(nums, 0, nums.length - 1);
        solutions.insertionSort(nums);
        solutions.bubbleSort(nums);
        solutions.selectionSort(nums);
        solutions.bucketSort(nums, 4);
        solutions.countingSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
