package week30.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/14923
 * 메모리: 187568 KB
 * 시간: 1092 ms
 * 시간 복잡도: O(N * M)
 * 공간 복잡도: O(N * M)
 */

/*
1. 미로 배열 입력 받아 bfs로 최단 경로 길이 계산
2. 마법 사용 유무에 따라 visited 배열 나눠서 경로 계산
3. 최단 경로 길이 출력

마법 사용 유무에 따라 최단 경로의 길이가 달라지기 때문에 visited 배열이 3차원이 되어야한다는 점을 알아야하는 문제...
3차원이 되어야 하는 이유는 특정 지점에 마법을 사용한 경로가 먼저 도착하더라도
결과적으로는 마법을 사용하지 않은 경로가 최단 길이가 될 수도 있기 때문
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p3_letskuku {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int hX = Integer.parseInt(st.nextToken());
        int hY = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int eX = Integer.parseInt(st.nextToken());
        int eY = Integer.parseInt(st.nextToken());

        int[][] board = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        int[][][] visited = new int[n + 1][m + 1][2]; // 세번째 원소가 0이면 마법 사용 x, 1이면 마법 사용 o
        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(hX, hY, false));
        while (!q.isEmpty()) {
            Tuple t = q.poll();

            for (int i = 0; i < 4; i++) {
                int x = t.x + dx[i];
                int y = t.y + dy[i];

                if ((x == hX && y == hY) || x < 1 || x > n || y < 1 || y > m) {
                    continue;
                }

                if (board[x][y] == 0) {
                    if (t.usedMagic && (visited[x][y][1] == 0)) {
                        q.add(new Tuple(x, y, true));
                        visited[x][y][1] = visited[t.x][t.y][1] + 1;
                    } else if (!t.usedMagic && (visited[x][y][0] == 0)) {
                        q.add(new Tuple(x, y, false));
                        visited[x][y][0] = visited[t.x][t.y][0] + 1;
                    }
                } else {
                    if (!t.usedMagic && (visited[x][y][1] == 0)) {
                        q.add(new Tuple(x, y, true));
                        visited[x][y][1] = visited[t.x][t.y][0] + 1;
                    }
                }
            }
        }

        int yesMagic = visited[eX][eY][1] == 0 ? 987654321 : visited[eX][eY][1];
        int noMagic = visited[eX][eY][0] == 0 ? 987654321 : visited[eX][eY][0];
        int ans = Math.min(yesMagic, noMagic);

        if (ans == 987654321) { // 마법 사용 유무와 상관없이 출구에 도달하지 못함
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    static class Tuple {
        int x, y;
        boolean usedMagic; // 이전에 마법을 사용했는지 여부

        Tuple(int x, int y, boolean usedMagic) {
            this.x = x;
            this.y = y;
            this.usedMagic = usedMagic;
        }
    }
}
