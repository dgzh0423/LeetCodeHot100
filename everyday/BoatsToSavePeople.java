package everyday;

import java.util.Arrays;

/**
 * 881. 救生艇
 * @author 15304
 */
public class BoatsToSavePeople {

    // 时间复杂度 O(nlogn) 空间复杂度 O(n) 主要是 Arrays.sort 方法
    public int numRescueBoats(int[] people, int limit) {
        // 1.先将people按体重升序排序
        Arrays.sort(people);
        int ans = 0;
        // 2.left指向体重较轻的，right指向体重较重的
        int left = 0, right = people.length - 1;
        // 3.要使得总船数最小，优先将体重最轻的和体重最重的放在同一艘船上，放不下的则将体重最重的单独占一艘船
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] people = {3, 2, 2, 1};
        int limit = 3;
        System.out.println(new BoatsToSavePeople().numRescueBoats(people, limit));
    }
}
