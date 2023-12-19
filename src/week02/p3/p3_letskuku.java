package week02.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1303
 * 메모리: 16584 KB
 * 시간: 160 ms
 * 시간 복잡도: O(N * M)
 * 공간 복잡도: O(N * M)
 */

/*
1. 전쟁터에서의 병사 배치를 입력 받아
2. bfs를 통해 같은 팀끼리 뭉쳐있는 병사들의 수를 구해 각 팀의 위력을 계산
3. 계산 결과 출력
 */

import java.io.*;
import java.util.*;

public class p3_letskuku {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] battles = new char[n][m];
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                battles[j][i] = line.charAt(j);
            }
        }

        //상하좌우 순회용 배열
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        int powerBlue = 0;
        int powerWhite = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) {
                    continue;
                }

                Queue<Pair> q = new LinkedList<>();
                q.add(new Pair(i, j));
                visited[i][j] = true;
                int total = 0; // 뭉쳐있는 총 인원 수
                while (!q.isEmpty()) {
                    Pair pair = q.poll();
                    total++;

                    for (int dir = 0; dir < 4; dir++) {
                        int nx = pair.x + dx[dir];
                        int ny = pair.y + dy[dir];

                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                            continue;
                        }

                        if (visited[nx][ny] || battles[nx][ny] != battles[i][j]) {
                            continue;
                        }

                        visited[nx][ny] = true;
                        q.add(new Pair(nx, ny));
                    }
                }

                // 위력 계산
                if (battles[i][j] == 'B') {
                    powerBlue += (total * total);
                } else {
                    powerWhite += (total * total);
                }
            }
        }

        String ans = "" + powerWhite + " " + powerBlue;
        System.out.println(ans);
    }

    public static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
