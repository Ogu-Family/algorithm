package weewk36.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2589
 * 메모리: 296384 KB
 * 시간: 500 ms
 * 시간 복잡도: O(NM^2)
 * 공간 복잡도: O(NM)
 */

/*
1. L과 W로 표시된 보물 지도를 입력받아
2. bfs로 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳을 계산
3. 결과 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1_letskuku {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n, m;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        char[][] board = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        int ans = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'W') {
                    continue;
                }

                int[][] visited = new int[n][m];
                int tmp = 0;
                Queue<Pair> q = new LinkedList<>();
                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];

                    if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] == 'W') {
                        continue;
                    }

                    visited[x][y] = visited[i][j] + 1;
                    tmp = Math.max(visited[x][y], tmp);
                    q.add(new Pair(x, y));
                }
                visited[i][j] = -1;

                while (!q.isEmpty()) {
                    Pair p = q.poll();

                    for (int k = 0; k < 4; k++) {
                        int x = p.x + dx[k];
                        int y = p.y + dy[k];

                        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] == 'W' || visited[x][y] != 0) {
                            continue;
                        }

                        visited[x][y] = visited[p.x][p.y] + 1;
                        tmp = Math.max(visited[x][y], tmp);
                        q.add(new Pair(x, y));
                    }
                }

                ans = Math.max(ans, tmp);
            }
        }

        System.out.println(ans);
    }

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
