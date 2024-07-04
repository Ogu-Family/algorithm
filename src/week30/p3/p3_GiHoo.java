package src.week30.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/14923
 * 메모리: 190408 KB
 * 시간: 1244 ms
 * 시간 복잡도: O(N * M)
 * 공간 복잡도: O(N * M)
 */

/**
 * 벽을 하나만 깰 수 있는 미로 문제
 *
 * 이전에도 비슷한 문제를 풀어봤지만, 유의해야 하는 것은 메모리
 * 방문 여부와 부순 벽 여부를 한번에 체크하는 visited 배열을 통해 해결
 */

public class p3_GiHoo {

    static int N, M;
    static int[][] miro;
    static int startX, startY, endX, endY;

    static int[][] moves = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        input();

        System.out.print(solution());
    }

    private static int solution() {
        int answer = Integer.MAX_VALUE;

        Queue<Current> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2];

        queue.add(new Current(startX, startY));
        visited[startX][startY][0] = true;

        while (!queue.isEmpty()) {
            Current current = queue.poll();

            if (current.x == endX && current.y == endY) {
                answer = Integer.min(answer, current.moveDistance);
                continue;
            }

            for (int i = 0; i < moves.length; i++) {
                int nx = current.x + moves[i][0];
                int ny = current.y + moves[i][1];

                if (isValidMove(nx, ny)) {
                    if (miro[nx][ny] == 0 && !visited[nx][ny][current.crashChange ? 1 : 0]) { // 벽이 아니고 방문하지 않은 경우
                        queue.add(new Current(nx, ny, current.crashChange, current.moveDistance + 1));
                        visited[nx][ny][current.crashChange ? 1 : 0] = true;
                    }

                    if (miro[nx][ny] == 1 && !current.crashChange && !visited[nx][ny][1]) { // 벽이고 아직 벽을 부수지 않은 경우
                        queue.add(new Current(nx, ny, true, current.moveDistance + 1));
                        visited[nx][ny][1] = true;
                    }
                }
            }
        }

        return (answer == Integer.MAX_VALUE) ? -1 : answer;
    }

    private static boolean isValidMove(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < N && ny < M;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        miro = new int[N][M];

        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken()) - 1;
        startY = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        endX = Integer.parseInt(st.nextToken()) - 1;
        endY = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                miro[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static class Current {
        int x;
        int y;
        boolean crashChange;
        int moveDistance;

        public Current(int x, int y) {
            this.x = x;
            this.y = y;
            this.crashChange = false;
            this.moveDistance = 0;
        }

        public Current(int x, int y, boolean crashChange, int moveDistance) {
            this.x = x;
            this.y = y;
            this.crashChange = crashChange;
            this.moveDistance = moveDistance;
        }
    }
}
