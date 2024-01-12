package src.week06.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class p3_GiHoo {

    static int[][] move = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    static int N;
    static int[][] miro;
    static int[][] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        miro = new int[N][N];
        answer = new int[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                miro[i][j] = input.charAt(j) - '0';
                answer[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();

        System.out.println(answer[N - 1][N - 1]);
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(0, 0));
        answer[0][0] = 0;

        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            int x = poll.x;
            int y = poll.y;

            for (int[] mv : move) {
                int nx = x + mv[0];
                int ny = y + mv[1];

                if (isAvailable(nx, ny) && answer[nx][ny] > answer[x][y]) {
                    if (miro[nx][ny] == 1) {
                        answer[nx][ny] = answer[x][y];
                    } else {
                        answer[nx][ny] = answer[x][y] + 1;
                    }
                    queue.add(new Node(nx, ny));
                }
            }
        }


    }

    private static boolean isAvailable(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < N && ny < N;
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
 * 문제 링크: https://www.acmicpc.net/problem/2665
 * 메모리: 15532 KB
 * 시간: 144 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N^2)
 *
 * 또 DFS 로 풀다가 한참 걸린 문제..
 * 시작 지점을 기준으로 각 인덱스에는 도착 지점을 가기 위해 변경해야 하는 방 수를 작성한다.
 *
 * 기존에는 방문을 체크하는 배열이 있었지만
 * answer[i][j] = Integer.MAX_VALUE; 코드를 통해 방문 배열과 하지 않는 배열을 구분할 수 있다.
 * 반드시 0,0 시작 부분을 0으로 초기화해줘야 한다.
 */
