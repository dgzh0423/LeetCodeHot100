package main.heap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 15304
 */
public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }
        // 统计每个数字出现的次数 key 是 num， value 是 num出现的次数
        Map<Integer, Integer> counter = IntStream.of(nums).boxed().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        // 定义小根堆，根据数字频率自小到大排序
        // new PriorityQueue<>((v1, v2) -> counter.get(v1) - counter.get(v2));
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(counter::get));
        // 遍历数组，维护一个大小为 k 的小根堆：
        // 不足 k 个直接将当前数字加入到堆中；否则判断堆中的最小次数是否小于当前数字的出现次数，若是，则删掉堆中出现次数最少的一个数字，将当前数字加入堆中。
        counter.forEach((num, cnt) -> {
            if (queue.size() < k) {
                queue.offer(num);
            } else if (counter.get(queue.peek()) < cnt) {
                queue.poll();
                queue.offer(num);
            }
        });
        // 构造返回结果 queue to int[]
        int[] res = new int[k];
        int i = 0;
        for (int num: queue) {
            res[i++] = num;
        }
        return res;
    }


    public int[] topKFrequentByBucketSort(int[] nums, int k) {
        // 统计每个数字出现的次数
        Map<Integer, Integer> counterMap = IntStream.of(nums).boxed().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        // 一个数字最多出现 nums.length 次，因此定义一个长度为 nums.length + 1 的数组
        // 若 k 很小，且 nums 中元素的 freq 很大
        List<List<Integer>> freqList = new ArrayList<>(nums.length + 1);
        // 若 nums 中不同元素的 freq 相等或近似，就会创建很多无用的空List
        for (int i = 0; i < nums.length + 1; i++) {
            freqList.add(i, new ArrayList<>());
        }
        counterMap.forEach((num, freq) -> freqList.get(freq).add(num));
        // 按照出现频次，从大到小遍历频次数组，构造返回结果
        int[] res = new int[k];
        int i = 0;
        for (int j = freqList.size() - 1; j > 0; j--) {
            if (freqList.get(j).isEmpty()) {
                continue;
            }
            for (int num: freqList.get(j)) {
                res[i++] = num;
                if (i == k) {
                    return res;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TopKFrequent f = new TopKFrequent();
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        System.out.println(Arrays.toString(f.topKFrequent(nums, k)));
        System.out.println(Arrays.toString(f.topKFrequentByBucketSort(nums, k)));
    }
}
