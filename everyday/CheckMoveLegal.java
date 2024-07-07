package everyday;

/**
 * 1958.检查操作是否合法
 * @author 15304
 */
public class CheckMoveLegal {

    /**
     * @param board 8 x 8 网格
     * @param rMove 起点横坐标
     * @param cMove 起点纵坐标
     * @param color 棋子颜色
     * @return 以(rMove, cMove)为端点，color为端点颜色，能否在8个方向上找到至少一个好线段（长度 ≥ 3 && 两个端点之间的颜色与端点颜色相反，不能包含空格）
     */
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        // 从 x 轴正方向开始逆时针枚举 8 个方向
        // 行改变量
        int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
        // 列改变量
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
        for (int k = 0; k < 8; k++) {
            if (check(board, rMove, cMove, color, dx[k], dy[k])) {
                return true;
            }
        }
        return false;
    }

    // 判断每个方向是否存在以操作位置为起点的好线段
    public boolean check(char[][] board, int rMove, int cMove, char color, int dx, int dy) {
        int x = rMove + dx;
        int y = cMove + dy;
        // 当前遍历到的节点序号，step至少为 2，才能满足长度至少为 3
        int step = 1;
        // 横纵坐标不越界
        while (x >= 0 && x < 8 && y >= 0 && y < 8) {
            if (step == 1) {
                // 第一个点必须为相反颜色
                if (board[x][y] == '.' || board[x][y] == color) {
                    return false;
                }
            } else {
                // 好线段中不应存在空格子
                if (board[x][y] == '.') {
                    return false;
                }
                // 遍历到好线段的终点，返回 true
                if (board[x][y] == color) {
                    return true;
                }
            }
            ++step;
            x += dx;
            y += dy;
        }
        // 不存在符合要求的好线段
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'.', '.', '.', 'B', '.', '.', '.', '.'},
                {'.', '.', '.', 'W', '.', '.', '.', '.'},
                {'.', '.', '.', 'W', '.', '.', '.', '.'},
                {'.', '.', '.', 'W', '.', '.', '.', '.'},
                {'W', 'B', 'B', '.', 'W', 'W', 'W', 'B'},
                {'.', '.', '.', 'B', '.', '.', '.', '.'},
                {'.', '.', '.', 'B', '.', '.', '.', '.'},
                {'.', '.', '.', 'W', '.', '.', '.', '.'}
        };
        System.out.println(new CheckMoveLegal().checkMove(board, 4, 3, 'B'));
    }
}

