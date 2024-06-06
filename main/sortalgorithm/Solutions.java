package main.sortalgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author 15304
 * 常见排序算法
 */
public class Solutions {

    /**
     * 选择排序：开启一个循环，每轮从未排序区间选择 "最小的元素"，将其放到已排序区间的末尾
     * 特点： 时间复杂度 O(n²) 空间复杂度 O(1) 不稳定排序
     */
    public void selectionSort(int[] nums) {
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
     * 冒泡排序：从数组最左端开始向右遍历，依次比较相邻元素大小，如果“左元素 > 右元素”就交换二者。遍历完成后，最大的元素会被移动到数组的最右端
     * 特点：时间复杂度 O(n²) 空间复杂度 O(1) 稳定排序
     */
    public void bubbleSort(int[] nums) {
        // 外循环：未排序区间为 [0, i]
        for (int i = nums.length - 1; i > 0; i--) {
            boolean flag = false;
            // 内循环：将未排序区间 [0, i] 中的最大元素交换至该区间的最右端
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    // 交换 nums[j] 与 nums[j + 1]
                    swap(nums, j ,j + 1);
                    flag = true;
                }
            }
            // 如果某轮“冒泡”中没有执行任何交换操作，说明数组已经完成排序，可直接返回结果
            if (!flag){
                break;
            }
        }
    }

    /**
     * 插入排序：每轮选取未排序区间的首个元素为pivot，将pivot与排好序区间的元素比较，找到合适的位置，再插入
     * 特点：时间复杂度 O(n²) 空间复杂度 O(1) 稳定排序，适合数据量很小的情况
     */
    public void insertionSort(int[] nums) {
        // 外循环：初始默认nums[0]已排好序，pivot从num[1]开始
        for (int i = 1; i < nums.length; i++) {
            int pivot = nums[i], j = i - 1;
            // 内循环：将 pivot 要插入的位置空出来
            while (j >= 0 && nums[j] > pivot) {
                nums[j + 1] = nums[j]; 
                j--;
            }
            // 将 pivot 赋值到正确位置
            nums[j + 1] = pivot;        
        }
    }

    /**
     * 希尔排序: 通过将相距一定步长的元素进行分组，对每个分组进行插入排序，然后逐步缩小步长直到1，最终完成整个数组的排序
     * 特点：时间复杂度 O[n(logn)²] 空间复杂度 O(1) 不稳定排序
     */
    public void shellSort(int[] nums) {
        int n = nums.length;
        // 外循环：初始步长为数组长度的一半，循环完一轮，步长减半
        for (int gap = n/2; gap > 0; gap /= 2) {
            // 内循环：按步长分组，对同组的元素进行插入排序
            for (int i = gap; i < n; i++) {
                // 记录 pivot 的值
                int temp = nums[i];
                int j = i;
                // 将 pivot 要插入的位置空出来
                while (j >= gap && nums[j - gap] > temp) {
                    // 如果前一个元素大于当前元素，则将前一个元素后移
                    nums[j] = nums[j - gap];
                    j -= gap;
                }
                // 找到合适的插入位置后，将pivot放入该位置
                nums[j] = temp;
            }
        }
    }

    /**
     * 快速排序：核心是 哨兵划分 和 递归分治
     * 特点：时间复杂度 O(nlogn) 空间复杂度 O(n) 不稳定排序
     */
    public void quickSort(int[] nums, int left, int right) {
        // 子数组长度为 1 时终止递归
        if (left >= right) {
            return;
        }
        // 哨兵划分，实质是将一个较长数组的排序问题简化为两个较短数组的排序问题
        int pivot = partition(nums, left, right);
        // 递归左子数组、右子数组
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    /**
     * 哨兵划分：选择数组中的某个元素作为“基准数”，将所有小于基准数的元素移到其左侧，而大于基准数的元素移到其右侧
     * 哨兵的选择会影响算法效率：可以在数组中选取三个候选元素（通常为数组的首、尾、中点元素），并将这三个候选元素的中位数作为基准数
     */
    public int partition(int[] nums, int left, int right) {
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
     * 归并排序：先划分，再合并
     * 特点：时间复杂度 O(nlogn) 空间复杂度 O(n) 稳定排序
     */
    public void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        // 划分阶段：先通过递归不断地将数组从中点处分开，将长数组的排序问题转换为短数组的排序问题
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        // 合并阶段；当子数组长度为 1 时终止划分，开始合并，持续地将左右两个较短的有序数组合并为一个较长的有序数组，直至结束
        merge(nums, left, mid, right);
    }

    /**
     * 合并左子数组和右子数组
     */
    public void merge(int[] nums, int left, int mid, int right) {
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
        // 将左子数组或右子数组的剩余元素复制到临时数组中
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
     * 桶排序：先通过设置 k 个具有大小顺序的桶，每个桶对应一个数据范围，将数据平均分配到各个桶中；然后，在每个桶内部分别执行排序；最后按照桶的顺序将所有数据合并
     * 特点：时间复杂度 O(n + k) 空间复杂度 O(n + k) 稳定性取决于排序桶内元素的算法是否稳定
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
            // nums里的元素必须小于 numberOfBuckets的平方，否则就会超出buckets的下标范围[0, numberOfBuckets - 1]
            buckets.get(num / numberOfBuckets).add(num);
        }
        return buckets;
    }

    /**
     * 计数排序 注意元素都是“非负整数”
     * 特点：时间复杂度 O(n + k) 空间复杂度 O(K)，k 是输入数据的范围（即数组中最大元素与最小元素的差加一） 稳定排序
     */
    public void countingSort(int[] nums) {
        // 1. 找到数组最大元素记为 max
        int max = getMax(nums);
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

    /**
     * 基数排序从最低位(个位)开始，依次对每一位进行计数排序
     */
    public void radixSort(int[] nums) {
        // 找到最大数以确定排序的次数
        int max = getMax(nums);

        // 从最低位开始，对每一位进行排序
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(nums, exp);
        }
    }

    /**
     * 按指定位数(个位，十位。。。)对数组进行计数排序
     */
    public static void countingSort(int[] nums, int exp) {
        // output存储排序后的结果
        int [] output = new int[nums.length];
        // count用于存储每个数字在当前位数上的出现次数，当前位数上的数字只能是 0~9
        int [] count = new int[10];
        Arrays.fill(count, 0);

        // 统计每个数字在当前位数上的出现次数
        int i;
        for (i = 0; i < nums.length; i++) {
            count[(nums[i] / exp) % 10]++;
        }

        // 遍历count数组，将其元素依次累加，用于计算每个数字(0~9)在排序后数组中的位置
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // 构建输出数组
        for (i = nums.length - 1; i >= 0; i--) {
            output[count[(nums[i] / exp) % 10] - 1] = nums[i];
            count[(nums[i] / exp) % 10]--;
        }

        // 将排序结果复制回原数组
        for (i = 0; i < nums.length; i++) {
            nums[i] = output[i];
        }
    }

    // 获取数组中的最大数以确定排序的轮数
    static int getMax(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        return max;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solutions solutions = new Solutions();
        int[] nums = {4,1,3,1,5,2};
        solutions.shellSort(nums);
        solutions.quickSort(nums, 0, nums.length - 1);
        solutions.mergeSort(nums, 0, nums.length - 1);
        solutions.insertionSort(nums);
        solutions.bubbleSort(nums);
        solutions.selectionSort(nums);
        solutions.bucketSort(nums, 4);
        solutions.countingSort(nums);
        System.out.println(Arrays.toString(nums));
        int[] nums2 = {170, 45, 75, 90, 802, 24, 2, 66};
        solutions.radixSort(nums2);
        System.out.println(Arrays.toString(nums2));
    }
}
