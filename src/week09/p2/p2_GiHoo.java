package src.week09.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2206
 * 메모리: 161600 KB
 * 시간: 680 ms
 * 시간 복잡도: O(NlogN) // 아마..?
 * 공간 복잡도: O(N^2)
 */

/**
 * 풀이 - 참고하여 푼 문제
 * 처음 문제를 풀 때는 시작 위치에서 벽이 있다면 부수면서 이동했다.
 * 그렇게 벽이 있고 없고를 판단하여 탐색을 하니 시간 초과가 발생했음
 *
 * 해당 문제에서 포인트는 Node 에 벽을 부순 유무를 포함시켜 BFS 를 돌리는 것이다.
 * 그렇게 전달받은 유무를 삼중 배열 visited 에 0과 1을 통해 구분하여 풀이
 */

public class p2_GiHoo {

    static int N;
    static int M;
    static int[][] matrix;

    static int[][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }

        }

        System.out.print(bfs());
    }

    private static int bfs() {
        boolean[][][] visited = new boolean[2][N][M]; // 방문 여부 체크. 0은 벽 안 부쉈고, 1은 벽 부수고 이동

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, 1)); // 시작
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node current = q.poll();

            if (current.x == N - 1 && current.y == M - 1) {
                return current.dis;
            }

            for (int i = 0; i < 4; i++) {
                int dx = current.x + move[i][0];
                int dy = current.y + move[i][1];
                int cnt = current.dis;
                int wall = current.wall;
                if (dx < 0 || dx >= N || dy < 0 || dy >= M || visited[wall][dx][dy]) {
                    continue;
                }
                if (matrix[dx][dy] == 0) { // 0이라면 그냥 이동
                    visited[wall][dx][dy] = true;
                    q.add(new Node(dx, dy, wall, cnt + 1));
                } else if (matrix[dx][dy] == 1 && wall == 0) { // 1이라면 벽을 부수지 않은 경우만 이동
                    visited[wall][dx][dy] = true;
                    q.add(new Node(dx, dy, wall + 1, cnt + 1));
                }
            }
        }

        return -1;
    }

    static class Node {

        int x, y, wall, dis; // 벽 부쉈는지 여부(0 또는 1), 이동 거리

        public Node(int x, int y, int wall, int dis) {
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.dis = dis;
        }
    }
}



