package algorithm.src.week33.p1;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/13549
 * 메모리: 18664 KB
 * 시간: 164 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(1)
 */

/**
 * BFS
 * 풀이 참고한 문제
 * 현재 위치부터 시작하여 순간이동 왼쪽 오른쪽 순으로 이동하며 이동한 시간 값을 최소 값으로 업데이트 한다.
 * 여기서 순간이동 -> 왼쪽 -> 오른쪽 순으로 방문해야 함 주의
 * 순간이동은 당연히 이득이라 제일 먼저 수행했는데 왜 왼쪽이 오른쪽 보다 먼저 수행되어야 하는지 이해가 안됐는데 아래와 같은 반례가 있더라구요!
 * 예) 4 6
 * 순간이동 -> 왼쪽 -> 오른쪽인 경우
 * 4->3->6 결과 1
 * 순간이동 -> 오른쪽 -> 왼쪽인 경우
 * 4->5->6 결과 2
 */

public class p1_JeongeunChoi {

    private static int bfs(int X, int K) {
        Queue<Integer> q = new LinkedList<>();
        int[] dp = new int[100001];
        dp[X] = 1;
        q.add(X);

        while (!q.isEmpty()) {
            int current = q.poll(), next;

            next = 2 * current;
            if (next != X && next <= 100000 && (dp[next] == 0 || dp[next] > dp[current])) {
                dp[next] = dp[current];
                q.add(next);
            }
            next = current - 1;
            if (next != X && next >= 0 && (dp[next] == 0 || dp[next] > dp[current] + 1)) {
                dp[next] = dp[current] + 1;
                q.add(next);
            }
            next = current + 1;
            if (next != X && next <= 100000 && (dp[next] == 0 || dp[next] > dp[current] + 1)) {
                dp[next] = dp[current] + 1;
                q.add(next);
            }
        }

        return dp[K] - 1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());

        System.out.println(bfs(N, K));
    }

}
