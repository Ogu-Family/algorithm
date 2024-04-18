package src.week19.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/18513
 * 메모리: 166904 KB
 * 시간: 1460 ms
 * 시간 복잡도: O(N + M)
 * 공간 복잡도: O(N + M)
 */

/**
 * BFS
 *
 * 샘터 설치하고 1씩 움직이면서 집을 설치. 그 거리를 전부 더해서 반환.
 */

import java.util.*;

public class p3_ZZAMBAs{
    static int N, K;
    static long res;
    static Map<Integer, Integer> coor;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); K = sc.nextInt() + N;
        coor = new HashMap<>();
        for (int i = 0; i < N; i++)
            coor.put(sc.nextInt(), 1);

        bfs();

        System.out.println(res);
    }

    static void bfs() {
        int[] d = {-1, 1};
        int nowDepth = 0;
        Queue<Integer> q = new ArrayDeque<>(coor.keySet());

        while (!q.isEmpty() && K != 0) {
            int qSize = q.size();
            for (int i = 0; i < qSize && K != 0; i++) {
                int curLoc = q.poll();
                K--;
                res += nowDepth;

                Arrays.stream(d).forEach(v -> {
                    int nextLoc = curLoc + v;
                    if (!coor.containsKey(nextLoc)){
                        q.add(nextLoc);
                        coor.put(nextLoc, 1);
                    }
                });
            }
            nowDepth++;
        }
    }
}
