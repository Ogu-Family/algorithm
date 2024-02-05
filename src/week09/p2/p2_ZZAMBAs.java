package src.week09.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2206
 * 메모리: 74676 KB
 * 시간: 740 ms
 * 시간 복잡도: O(N + M)
 * 공간 복잡도: O(N + M)
 */

/**
 * 벽 부순 차원이 추가된 3차원 배열을 사용하여 BFS를 돌리는 문제
 * visited[i][j][k]: 벽을 i번 부순 j행 k열 위치를 방문했는지 여부.
 *
 * 1. 벽을 부순 횟수를 포함한 3차원 배열을 사용하여 BFS
 * 2 - 1. 벽을 부순 횟수가 남아있고, 다음 위치가 벽일 경우, 벽을 부수고 큐에 그 위치를 넣음
 * 2 - 2. 다음 위치가 빈 공간일 경우, 큐에 그 위치를 넣음.
 * 3. 목적지에 도착했을 경우(벽을 부쉈던지 안부쉈던지 간에), 바로 거리를 반환
 */

import java.util.*;

public class p2_ZZAMBAs{
    static int N;
    static int M;
    static int[] dr = {1, 0, -1, 0}, dc = {0, 1, 0, -1};
    static int[][] board;
    static boolean[][][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        board = new int[N][M];
        visited = new boolean[2][N][M];

        for (int i = 0; i < N; i++) {
            String s = sc.next();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                if (c == '0')
                    board[i][j] = 0;
                else
                    board[i][j] = 1;
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        int level = 1;
        Queue<Triple> q = new ArrayDeque<>();
        q.add(new Triple(0, 0, 0));
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Triple curT = q.poll();
                int curDepth = curT.depth;
                int curRow = curT.row;
                int curCol = curT.col;

                if (curRow == N - 1 && curCol == M - 1)
                    return level;

                for (int j = 0; j < 4; j++) {
                    int nextRow = curRow + dr[j];
                    int nextCol = curCol + dc[j];

                    if (!(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M))
                        continue;

                    if (board[nextRow][nextCol] == 0 && !visited[curDepth][nextRow][nextCol]) { // 다음이 벽이 아닐 때
                        visited[curDepth][nextRow][nextCol] = true;
                        q.add(new Triple(curDepth, nextRow, nextCol));
                    }

                    if (curDepth == 0 && board[nextRow][nextCol] == 1) { // 벽 부순 적 없고, 다음이 벽일 때
                        if (!visited[1][nextRow][nextCol]) {
                            visited[1][nextRow][nextCol] = true;
                            q.add(new Triple(1, nextRow, nextCol));
                        }
                    }
                }
            }
            level++;
        }

        return -1;
    }

    public static class Triple {
        public int depth, row, col;

        Triple (int depth, int row, int col) {
            this.depth = depth;
            this.row = row;
            this.col = col;
        }
    }
}
