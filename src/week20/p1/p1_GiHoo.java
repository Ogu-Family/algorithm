package src.week20.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/14503
 * 메모리: 14424 KB
 * 시간: 128 ms
 * 시간 복잡도: O(N*M)
 * 공간 복잡도: O(N*M)
 */

/**
 * 1. 현재 칸이 청소되지 않으면 청 / 0 청소 x, 1 벽, 2 청소됨
 * 2. 현재 칸 기준 동,서,남,북에 0인 칸이 있음
 *   true: 반시계 방향으로 회전 후 앞으로 전진. 1번 진행
 *   false: 바라보는 방향 유지하며 한 칸 후진 | 후진이 가능하면 1번 진행, 불가능(벽)하면 종료
 *
 * 동, 서, 남, 북으로 이동하는 moves 배열을 잘 활용하지 못해 헤맨 문제..
 */

public class p1_GiHoo {

    static int N, M;
    static int[][] rooms;
    static int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북 0, 동 1, 남 2, 서 3

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        rooms = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                rooms[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = cleanRoom(x, y, direction);

        System.out.println(answer);
    }

    private static int cleanRoom(int x, int y, int direction) {
        int count = 0;

        while (true) {
            if (rooms[x][y] == 0) {
                rooms[x][y] = 2;
                count++;
            }

            boolean cleaned = false;
            for (int i = 0; i < 4; i++) {
                direction = (direction + 3) % 4; // 반시계 방향으로 회전
                int nx = x + moves[direction][0];
                int ny = y + moves[direction][1];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && rooms[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                    cleaned = true;
                    break;
                }
            }

            if (!cleaned) { // 모든 방향이 청소 or 벽인 경우
                int backward = (direction + 2) % 4; // 후진
                x += moves[backward][0];
                y += moves[backward][1];

                if (x < 0 || x >= N || y < 0 || y >= M || rooms[x][y] == 1) {
                    break; // 후진 불가능 시 종료
                }
            }
        }

        return count;
    }
}
