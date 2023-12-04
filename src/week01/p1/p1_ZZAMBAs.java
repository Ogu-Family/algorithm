package src.week01.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2606
 * 메모리: 17936 KB
 * 시간: 244 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2)
 */

/**
 * 1번 기준 DFS를 돌리는 문제.
 * 중요한 부분
 * - 한 컴퓨터가 웜 바이러스에 걸리면 그 컴퓨터와 네트워크 상에서 연결되어 있는 모든 컴퓨터는 웜 바이러스에 걸리게 된다.
 * - 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 출력
 */

import java.util.Scanner;
import java.util.ArrayList;

public class p1_ZZAMBAs{
    public static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    public static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(), N = sc.nextInt();

        for(int i = 0; i <= T; i++)
            adj.add(new ArrayList<>());

        visited = new boolean[T + 1];

        while(N-- > 0) { // O(N^2)
            int a = sc.nextInt(), b = sc.nextInt();
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        System.out.println(dfs(1) - 1); // O(N^2)
    }

    public static int dfs(int idx) {
        visited[idx] = true;
        int ret = 0;

        for(Integer adv : adj.get(idx)) {
            if (!visited[adv]) {
                ret += dfs(adv);
            }
        }

        return ret + 1;
    }
}
