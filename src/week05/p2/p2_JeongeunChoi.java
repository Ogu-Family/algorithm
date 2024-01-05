package algorithm.src.week05.p2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2_JeongeunChoi {

    static int N, M;
    static int[][] box = new int[1000][1000];
    static boolean[][] visited = new boolean[1000][1000];
    static Queue<RipeTomato> ripeTomatoQ = new LinkedList<>();
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    static int answer = 0;

    private static void spreadRipeTomatoes() {
        while (!ripeTomatoQ.isEmpty()) {
            RipeTomato ripeTomato = ripeTomatoQ.poll();
            int x = ripeTomato.x, y = ripeTomato.y, day = ripeTomato.day;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                    if (box[nx][ny] == 0 && !visited[nx][ny]) {
                        box[nx][ny] = 1;
                        if (day + 1 > answer) {
                            answer = box[nx][ny];
                        }
                        ripeTomatoQ.add(new RipeTomato(nx, ny, day + 1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) {
                    ripeTomatoQ.add(new RipeTomato(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        spreadRipeTomatoes();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (box[i][j] == 0) {
                    answer = -1;
                    break;
                }
            }
        }

        System.out.println(answer);
    }

    static class RipeTomato {

        final int x, y, day;

        public RipeTomato(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/7576
 * 메모리: 120764 KB
 * 시간: 652 ms
 * 시간 복잡도: O(N*M)
 * 공간 복잡도: O(1000*1000)
 */

/**
 * BFS 풀이
 * 큐에 익은 토마토를 넣어두고, 상하좌우로 다른 토마토가 익도록 함.
 * 이 때, 큐에 넣는 익은 토마토 객체는 x, y 위치 값과 며칠만에 익었는지 day 값을 가짐.
 * day 값을 이용해 다음 토마토가 익으면 day+1을 해주며 퍼져나감.
 * 큐가 빌때까지 반복 후, 익지 않은 토마토가 있는 경우 -1을, 모든 토마토가 익은 경우 day의 최대값을 출력함.
 */