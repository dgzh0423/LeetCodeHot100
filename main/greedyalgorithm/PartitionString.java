package main.greedyalgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 15304
 */
public class PartitionString {
    public List<Integer> partitionLabels(String s) {
        // s仅由小写英文字母组成
        int[] last = new int[26];
        int length = s.length();
        // 遍历字符串，得到每个字母最后一次出现的下标位置
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        List<Integer> res = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            //对于每个访问到的字母，得到当前字母的最后一次出现的下标位置 last[s.charAt(i) - 'a'], 则当前片段的结束下标end一定不会小于该值
            end = Math.max(end, last[s.charAt(i) - 'a']);
            // 当访问到下标 end 时，当前片段访问结束
            if (i == end) {
                // 将当前的片段长度保存到res中
                res.add(end - start + 1);
                // 更新下一个片段的起始位置
                start = end + 1;
            }
        }

        return res;
    }
    public static void main(String[] args) {
        PartitionString p =  new PartitionString();
        String s = "ababcbacadefegdehijhklij";
        System.out.println(p.partitionLabels(s));
    }
}
