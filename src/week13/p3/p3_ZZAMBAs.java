package src.week13.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/23835
 * 메모리: 32756 KB
 * 시간: 448 ms
 * 시간 복잡도: O(N^2) = O(QN)
 * 공간 복잡도: O(N)
 */

/**
 * DFS
 * 트리임에 따라 그냥 DFS를 사용해도 괜찮다고 판단하였다.
 */


import java.util.ArrayList;
import java.util.Scanner;

public class p3_ZZAMBAs {
    static int[] milk;
    static ArrayList<ArrayList<Integer>> adv;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        adv = new ArrayList<>();
        milk = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            adv.add(new ArrayList<>());
        }

        while (N-- - 1 > 0) {
            int a = sc.nextInt(), b = sc.nextInt();
            adv.get(a).add(b);
            adv.get(b).add(a);
        }

        int Q = sc.nextInt();

        while (Q-- > 0) {
            int cmd = sc.nextInt();
            if (cmd == 1) {
                int start = sc.nextInt(), end = sc.nextInt();
                dfs(start, end, 0);
            }else {
                int searchNum = sc.nextInt();
                System.out.println(milk[searchNum]);
            }
        }

    }

    public static boolean dfs(int cur, int end, int depth) {
        if (cur == end) {
            milk[cur] += depth;

            return true;
        }

        visited[cur] = true;

        for (Integer adj : adv.get(cur)) {
            if (!visited[adj] && dfs(adj, end, depth + 1)) {
                milk[cur] += depth;

                visited[cur] = false;
                return true;
            }
        }

        visited[cur] = false;
        return false;
    }
}
