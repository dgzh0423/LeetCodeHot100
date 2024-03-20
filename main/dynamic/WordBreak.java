package main.dynamic;

import java.util.HashMap;
import java.util.List;

/**
 * @author 15304
 */
public class WordBreak {
    HashMap<String, Boolean> map = new HashMap<>();
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        //方便check，构建一个哈希表
        for(String word : wordDict){
            map.put(word, true);
        }
        dp[0] = true;
        // dp[j] = dp[i] && check(s[i+1, j]);
        for(int j = 1; j <= s.length(); j++){
            for(int i = j-1; i >= 0; i--){
                // substring [ )
                dp[j] = dp[i] && check(s.substring(i, j));
                if(dp[j]) {
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean check(String s){
        return map.getOrDefault(s, false);
    }

    public static void main(String[] args) {

    }
}


