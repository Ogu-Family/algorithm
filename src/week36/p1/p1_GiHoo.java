package src.weewk36.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2589
 * 메모리: 295168 KB
 * 시간: 440 ms
 * 시간 복잡도: O(??) 모르겠슴다...
 * 공간 복잡도: O(N * M)
 */

/**
 * 가장 멀리 떨어져있는 두 육지 사이를 찾아내는 문제
 * 처음에는 어떻게 두 육지를 고를지 고민했음. "멀리 돌아가서는 안된다" 라는 조건 때문에 고민했던 것 같음
 * BFS를 사용하면 멀리 돌아가지 않고, 최단 거리로 가장 멀리 떨어져있는 두 육지를 구할 수 있음.
 *
 * L 인 지점부터 BFS를 돌려 masDist를 구해 answer와 비교하여 풀이
 */

public class p1_GiHoo {

    static int n, m;
    static char[][] map;
    static boolean[][] visited;
    static int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'L') {
                    visited = new boolean[n][m];
                    answer = Math.max(answer, bfs(i, j));
                }
            }
        }

        System.out.println(answer);
    }

    static int bfs(int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y, 0));
        visited[x][y] = true;
        int maxDist = 0;

        while (!queue.isEmpty()) {
            Node currNode = queue.poll();
            int currX = currNode.x;
            int currY = currNode.y;
            int currDist = currNode.moveDistance;

            maxDist = Math.max(maxDist, currDist);

            for (int i = 0; i < 4; i++) {
                int nx = currX + move[i][0];
                int ny = currY + move[i][1];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && map[nx][ny] == 'L') {
                    visited[nx][ny] = true;
                    queue.add(new Node(nx, ny, currDist + 1));
                }
            }
        }

        return maxDist;
    }

    static class Node {
        int x;
        int y;
        int moveDistance;

        public Node(int x, int y, int moveDistance) {
            this.x = x;
            this.y = y;
            this.moveDistance = moveDistance;
        }
    }
}

