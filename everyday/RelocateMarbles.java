package everyday;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 2766. 重新放置石块
 * @author 15304
 */
public class RelocateMarbles {

    // 时间复杂度：O(nlogn + m)，其中 n 是 nums 的长度，m 是 moveFrom 的长度。空间复杂度：O(n)
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        // 先将石头的初始位置记录到set中
        Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums){
            set.add(num);
        }
        // 按题目要求，将位于 moveFrom[i] 的石块移到 moveTo[i]
        for (int i = 0; i < moveFrom.length; i++){
            set.remove(moveFrom[i]);
            set.add(moveTo[i]);
        }
        // 升序返回有石块的位置
        //List<Integer> ans = new ArrayList<>(set);
        //Collections.sort(ans);
        //return ans;
        return set.stream().sorted().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] nums = {1,6,7,8};
        int[] moveFrom = {1,7,2};
        int[] moveTo = {2,9,5};
        System.out.println(new RelocateMarbles().relocateMarbles(nums, moveFrom, moveTo));
    }
}
