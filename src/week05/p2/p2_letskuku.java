package week05.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/7576
 * 메모리: 120272 KB
 * 시간: 636 ms
 * 시간 복잡도: O(N * M)
 * 공간 복잡도: O(N * M)
 */

/*
1. 토마토를 입력 받아 익은 토마토를 큐에 저장
2. 큐에서 토마토를 하나씩 꺼내 bfs를 돌면서 익지 않은 토마토를 익히고, 익을 때까지 걸린 시간 저장
3. 익을 때까지 걸린 시간 중 최댓값 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2_letskuku {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] tomato = new int[n][m];
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());

                if (tomato[i][j] == 1) {
                    q.add(new Pair(i, j));
                }
            }
        }

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        while (!q.isEmpty()) {
            Pair p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (tomato[nx][ny] != 0) {
                    continue;
                }

                tomato[nx][ny] = tomato[p.x][p.y] + 1;
                q.add(new Pair(nx, ny));
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tomato[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }

                ans = Math.max(ans, tomato[i][j]);
            }
        }

        System.out.println(ans - 1); // 익은 토마토를 1로 계산했으므로 최종 값은 1을 빼준다
    }

    public static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
