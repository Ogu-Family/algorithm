package algorithm.src.week09.p2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2206
 * 메모리: 150740 KB
 * 시간: 760 ms
 * 시간 복잡도: O(N*M)
 * 공간 복잡도: O(N*M)
 */

/**
 * 처음에는 벽 하나하나를 0으로 바꾼 후 dfs를 돌려서 벽 1개를 부술 때 최단 경로를 찾도록 하였으나 시간 초과 발생
 * 풀이 참고했습니다~
 * 벽은 한번만 부술 수 있으므로,
 * 벽을 만난 경우에 벽을 이미 부순 경우에는 더 부수지 못하고, 벽을 이미 부수지 않은 경우에는 벽을 부술 수 있다.
 * 따라서 벽을 부수었다면 부수었다는 정보를 계속해서 가져가야 한다.
 * 이를 위해 visited 배열을 3차원으로 구성하여, 벽을 부수었는지 아닌지 표시한다.
 */

public class p2_JeongeunChoi {

    static int N, M, answer = 1000000;
    static int[][] map;
    static boolean[][][] visited; // visited[][][0] 벽 부수지 않은 경우 방문 처리, visited[][][1] 벽 부순 경우 방문 처리
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    private static void bfs() {
        Queue<Loc> q = new LinkedList<>();

        q.add(new Loc(1, 1, 1, false));
        visited[1][1][0] = true;

        while (!q.isEmpty()) {
            Loc loc = q.poll();
            int x = loc.x, y = loc.y, dis = loc.dis;
            boolean brokeWall = loc.brokeWall;

            if (x == N && y == M) {
                answer = Math.min(answer, dis);
                break;
            } else {
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i], ny = y + dy[i];
                    if (!outOfRange(nx, ny)) {
                        if (map[nx][ny] == 1) { // 벽인 경우
                            if (!brokeWall && !visited[nx][ny][0]) { // 벽을 부순적 없는 경우
                                visited[nx][ny][0] = true;
                                q.add(new Loc(nx, ny, dis + 1, true));
                            }
                        } else { // 벽이 아닌 경우
                            if (brokeWall && !visited[nx][ny][1]) { // 벽을 부순적 있는 경우
                                visited[nx][ny][1] = true;
                                q.add(new Loc(nx, ny, dis + 1, true));
                            } else if (!brokeWall && !visited[nx][ny][0]) { // 벽을 부순적 없는 경우
                                visited[nx][ny][0] = true;
                                q.add(new Loc(nx, ny, dis + 1, false));
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean outOfRange(int x, int y) {
        return !(x >= 1 && x <= N && y >= 1 && y <= M);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1][2];

        for (int i = 1; i <= N; i++) {
            String row = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = row.charAt(j - 1) - '0';
            }
        }
        bfs();

        if (answer == 1000000) {
            answer = -1;
        }
        System.out.println(answer);
    }

    static class Loc {

        int x, y, dis;
        boolean brokeWall;

        Loc(int x, int y, int dis, boolean brokeWall) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.brokeWall = brokeWall;
        }
    }
}