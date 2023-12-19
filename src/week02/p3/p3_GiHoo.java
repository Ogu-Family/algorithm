package src.week02.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p3_GiHoo {
    static int N;
    static int M;
    static char[][] battleground;
    static boolean[][] isVisited;
    static char W = 'W'; // 아군
    static char B = 'B'; // 적군

    static int[] move_x = {1, 0, -1, 0};
    static int[] move_y = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        battleground = new char[N][M];
        isVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            battleground[i] = input.toCharArray();
        }

        int friendlySum = 0;
        int enemySum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!isVisited[i][j]) {
                    isVisited[i][j] = true;

                    if (battleground[i][j] == W) {
                        int sum = BFS(i, j, W);
                        friendlySum += (sum * sum);
                    } else {
                        int sum = BFS(i, j, B);
                        enemySum += (sum * sum);
                    }
                }
            }
        }

        System.out.println(friendlySum + " " + enemySum);
    }

    public static int BFS(int x, int y, char ch) {
        int count = 1;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int mx = node.x;
            int my = node.y;
            for (int i = 0; i < move_x.length; i++) {
                int nx = mx + move_x[i];
                int ny = my + move_y[i];
                if (isPossible(nx, ny) && battleground[nx][ny] == ch) {
                    isVisited[nx][ny] = true;
                    count++;
                    queue.add(new Node(nx, ny));
                }
            }
        }

        return count;
    }

    private static boolean isPossible(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < N && ny < M && !isVisited[nx][ny];
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/1303
 * 메모리: 17964 KB
 * 시간: 180	 ms
 * 시간 복잡도: O(N * M)
 * 공간 복잡도: O(N * M)
 *
 * 풀이
 * 아군 W, 적군 B를 배열에 저장한다.
 * 이때 조심해야할 부분은 가로가 N, 세로가 M이라는 부분
 * 배열 0,0 부터 for문을 돌며 방문한 배열이라면 BFS를 돌리지 않고
 * 방문하지 않은 배열이라면 BFS를 돌린다.
 */
