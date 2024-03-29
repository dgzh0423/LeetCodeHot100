package main.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 15304
 */
public class NQueens {
    public List<List<String>> solveQueens(int n) {
        char[][] chess = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chess[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        solve(res, chess, 0);
        return res;
    }

    private void solve(List<List<String>> res, char[][] chess, int row) {
        if (row == chess.length) {
            res.add(construct(chess));
            return;
        }
        for (int col = 0; col < chess.length; col++) {
            if (valid(chess, row, col)) {
                chess[row][col] = 'Q';
                solve(res, chess, row + 1);
                chess[row][col] = '.';
            }
        }
    }

    //row表示第几行，col表示第几列
    private boolean valid(char[][] chess, int row, int col) {
        // 判断当前列有没有皇后, 看当前坐标位置的上面有没有皇后
        for (int i = 0; i < row; i++) {
            if (chess[i][col] == 'Q') {
                return false;
            }
        }
        // 判断当前坐标的右上角有没有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < chess[i].length; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        // 判断当前坐标的左上角有没有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    //把char数组转为list
    private List<String> construct(char[][] chess) {
        List<String> path = new ArrayList<>();
        for (char[] chars : chess) {
            path.add(new String(chars));
        }
        return path;
    }

    public static void main(String[] args) {
        NQueens n = new NQueens();
        int N = 8;
        List<List<String>> lists = n.solveQueens(N);
        System.out.println( N + " 皇后有 " + lists.size() + " 种解法");
        for (List<String> list : lists) {
            System.out.println(list);
        }

    }
}
