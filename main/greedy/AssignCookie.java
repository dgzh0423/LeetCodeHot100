package main.greedy;

import java.util.Arrays;

/**
 * 455. 分发饼干
 * @author 15304
 */
public class AssignCookie {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gIndex = 0, sIndex = 0;
        int res = 0;
        while (gIndex < g.length && sIndex < s.length) {
            if (g[gIndex] <= s[sIndex]) {
                res++;
                gIndex++;
            }
            sIndex++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] g = {1, 2};
        int[] s = {1, 2, 3};
        System.out.println(new AssignCookie().findContentChildren(g, s));
    }
}
