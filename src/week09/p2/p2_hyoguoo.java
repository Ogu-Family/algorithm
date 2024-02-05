/**
 * 문제 링크: https://www.acmicpc.net/problem/2206
 * 메모리: 205564 KB
 * 시간: 1328 ms
 * 시간 복잡도: O(N * M)
 * 공간 복잡도: O(N * M)
 */

/**
 * 벽 부순 차원이 추가된 3차원 배열을 사용하여 BFS를 돌리는 문제
 *
 * 1. 벽을 부순 횟수를 포함한 3차원 배열을 사용하여 BFS
 * 2 - 1. 벽을 부순 횟수가 남아있고, 다음 위치가 벽일 경우, 다음 깊이부터 최대 깊이까지 거리를 갱신
 * 2 - 2. 다음 위치가 빈 공간일 경우, 현재 깊이부터 최대 깊이까지 거리를 갱신
 * 3. 목적지에 도착했을 경우, 거리를 반환
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BreakWallGo {

    static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static final int WALL = -1;
    static final int EMPTY = 0;
    static final int START_N = 0;
    static final int START_M = 0;
    static final int NOT_FOUND = -1;
    static final int BREAK_LIMIT = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = info[0];
        int m = info[1];
        // 벽을 부순 횟수를 포함한 3차원 배열
        int[][][] map = new int[BREAK_LIMIT + 1][n][m];

        for (int i = 0; i < n; i++) {
            String[] line = bufferedReader.readLine().split("");
            for (int j = 0; j < m; j++) {
                int value = Integer.parseInt(line[j]);
                if (value == 1) value = WALL;
                for (int k = 0; k <= BREAK_LIMIT; k++) map[k][i][j] = value;
            }
        }
        System.out.println(bfs(map, n, m));
    }

    private static int bfs(int[][][] map, int n, int m) {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(START_N, START_M, 0, 1));

        map[0][0][0] = 1;

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            // 목적지에 도착했을 경우
            if (current.n == n - 1 && current.m == m - 1) return current.distance;

            for (int[] direction : DIRECTIONS) {
                int nextN = current.n + direction[0];
                int nextM = current.m + direction[1];

                if (!isInBound(n, m, nextN, nextM)) continue;

                // 벽을 부순 횟수가 남아있고, 다음 위치가 벽일 경우
                if (current.depth < BREAK_LIMIT &&
                    map[current.depth][nextN][nextM] == WALL) {
                    // 다음 깊이부터 최대 깊이까지 거리를 갱신
                    for (int depth = current.depth + 1; depth <= BREAK_LIMIT; depth++) {
                        map[depth][nextN][nextM] = current.distance + 1;
                        queue.add(new Coordinate(nextN, nextM, depth, current.distance + 1));
                    }
                }

                // 다음 위치가 빈 공간일 경우
                if (map[current.depth][nextN][nextM] == EMPTY) {
                    // 현재 깊이부터 최대 깊이까지 거리를 갱신
                    for (int depth = current.depth; depth <= BREAK_LIMIT; depth++) {
                        map[depth][nextN][nextM] = current.distance + 1;
                    }
                    queue.add(new Coordinate(nextN, nextM, current.depth, current.distance + 1));
                }
            }
        }

        return NOT_FOUND;
    }

    private static boolean isInBound(int limitN, int limitM, int n, int m) {
        return 0 <= n && n < limitN && 0 <= m && m < limitM;
    }

    static class Coordinate {
        int n;
        int m;
        int depth;
        int distance;

        public Coordinate(int n, int m, int depth, int distance) {
            this.n = n;
            this.m = m;
            this.depth = depth;
            this.distance = distance;
        }
    }
}
