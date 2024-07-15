package everyday;

import java.util.*;

/**
 * 721.账户合并
 * @author 15304
 */
public class MergeAccounts {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // key 为邮箱，value 为用户下标，用于寻找同一个用户
        Map<String, List<Integer>> userHolder = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++){
                userHolder.computeIfAbsent(accounts.get(i).get(j), k -> new ArrayList<>()).add(i);
            }
        }

        List<List<String>> res = new ArrayList<>();
        boolean[] visited = new boolean[accounts.size()];
        // 保存当前用户所有的邮箱
        Set<String> emailSet = new HashSet<>();
        for (int i = 0; i < accounts.size(); i++) {
            if (visited[i]){
                continue;
            }
            // 清除前一个用户的邮箱
            emailSet.clear();
            // dfs查找当前用户所有的邮箱，并保存到 emailSet
            dfs(i, accounts, visited, emailSet, userHolder);
            List<String> account = new ArrayList<>(emailSet);
            // 每个用户的邮箱按字符 ASCII 顺序排序
            Collections.sort(account);
            account.add(0, accounts.get(i).get(0));
            res.add(account);
        }
        return res;
    }

    private void dfs(int i, List<List<String>> accounts, boolean[] visited, Set<String> emailSet, Map<String, List<Integer>> userHolder) {
        // 标记当前用户的邮箱都访问过了
        visited[i] = true;
        // 遍历当前用户的每个邮箱 email
        for (int j = 1; j < accounts.get(i).size(); j++){
            String email = accounts.get(i).get(j);
            if (emailSet.contains(email)){
                continue;
            }
            // 将email添加到emailSet
            emailSet.add(email);
            // 检查此email是否有"共同用户"(即同一个用户)，将该用户的所有邮箱添加到emailSet
            for (int index : userHolder.get(email)){
                if (!visited[index]){
                    dfs(index, accounts, visited, emailSet, userHolder);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        MergeAccounts mergeAccounts = new MergeAccounts();
        List<List<String>> res = mergeAccounts.accountsMerge(accounts);
        for (List<String> account : res) {
            System.out.println(account);
        }
    }
}
