package everyday;

/**
 * 3096. 得到更多分数的最少关卡数目
 * @author 15304
 */
public class MinLevelsWithMorePoints {

    public int minimumLevels(int[] possible) {
        if (possible.length < 2){
            return -1;
        }
        // count[] 用于保存困难关和简单关的个数
        int[] count = new int[2];
        for (int i : possible){
            count[i % 2]++;
        }
        // Alice 和 Bob 至少需要完成 1 个关卡
        int AliceScore = 0;
        for (int i = 0; i < possible.length - 1; i++){
            if (possible[i] == 1){
                AliceScore++;
                count[1]--;
            }else{
                AliceScore--;
                count[0]--;
            }
            if (AliceScore > count[1] - count[0]){
                return i + 1;
            }
        }
        return -1;
    }

    public int minimumLevels2(int[] possible) {
        int n = possible.length;
        int s = 0;
        for (int x : possible) {
            // 这里计算的 s 是 1 的个数， 0 的个数就是 n - s
            s += x;
        }
        // 实际分数为 s - (n - s) = 2 * s - n
        s = 2 * s - n;
        // pre 表示 Alice 的得分，则 s - pre 表示 Bob 的得分 故 pre > s - pre === 2 * pre > s
        int pre = 0;
        for (int i = 0; i < n - 1; i++) {
            // 这里计算分数时用的是 2/-2，就不需要 2 * pre了
            pre += possible[i] == 1 ? 2 : -2;
            if (pre > s) {
                return i + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] possible = {0, 0};
        System.out.println(new MinLevelsWithMorePoints().minimumLevels(possible));
        System.out.println(new MinLevelsWithMorePoints().minimumLevels2(possible));
    }
}
