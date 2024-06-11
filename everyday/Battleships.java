package everyday;

/**
 * @author 15304
 */
public class Battleships {

    /**
     * 419. 甲板上的战舰 时间复杂度O(m*n) 空间复杂度O(1)
     * @param board 战舰只能是竖直的或水平的
     * @return 战舰的数量
     */
    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        int ans = 0;
        // 为了避免遍历时重复对战舰计数，可以只对战舰头节点进行计数
        // 战舰头结点：战舰的上方和左方没有战舰
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && board[i - 1][j] == 'X'){
                    continue;
                }
                if (j > 0 && board[i][j - 1] == 'X'){
                    continue;
                }
                if (board[i][j] == 'X'){
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'X','.','.','X'},
                {'.','.','.','X'},
                {'.','.','.','X'}
        };
        System.out.println(new Battleships().countBattleships(board));
    }
}
