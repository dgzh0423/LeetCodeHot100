package main.backtrack;

/**
 * 参考岛屿数量， 网格DFS的遍历框架
 * @author 15304
 */
public class SearchWord {
    boolean [][] visited;
    public boolean exist(char[][] board, String word) {
        int row = board.length, column = board[0].length;
        visited = new boolean[row][column];
        boolean res = false;

        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                if (word.charAt(0) == board[i][j]){
                    res = res || dfs(i, j, board, word,0);
                }
            }
        }

        return res;
    }

    private boolean dfs(int x, int y, char[][] board, String word, int index) {
        // 找到完整的word
        if (index == word.length()){
            return true;
        }

        // 超出网格范围 或 当前遍历到的单词字符与网格内的字符不同
        if (inArea(board, x, y) || board[x][y] != word.charAt(index)){
            return false;
        }

        visited[x][y] = true;
        boolean found = dfs(x + 1, y, board, word, index + 1) ||
                dfs(x - 1, y, board, word, index + 1) ||
                dfs(x, y + 1, board, word, index + 1) ||
                dfs(x, y -1, board, word, index + 1);
        // 回溯，重置网格内字符已访问的状态
        visited[x][y] = false;

        return found;
    }

    private boolean inArea(char[][] board, int x, int y){
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCB";
        SearchWord sw = new SearchWord();
        System.out.println(sw.exist(board, word));
    }
}
