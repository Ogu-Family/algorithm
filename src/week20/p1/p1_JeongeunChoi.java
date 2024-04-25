package algorithm.src.week20.p1;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/14503
 * 메모리: 14432 KB
 * 시간: 128 ms
 * 시간 복잡도: O(N*M)
 * 공간 복잡도: O(N*M)
 */

/**
 * 구현
 * 1. 반시계 방향으로 돌며 빈칸 발견 시 전진하여 청소
 * 2. 청소 할거 없는 경우 뒤로 한칸 후진가능하면 후진
 */

public class p1_JeongeunChoi {

    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1}; // 북 동 남 서
    private static int N, M, answer = 0;
    private static int[][] room;
    private static boolean[][] visited;

    private static void cleanRoom(int x, int y, int dIdx) {
        if (!visited[x][y]) {
            answer++;
            visited[x][y] = true;
        }
        int nx, ny;
        boolean canClean = false;
        // 반시계 방향으로 돌며 빈칸 발견 시 전진하여 청소
        for (int i = 1; i <= 4; i++) {
            int nDIdx = (dIdx - i < 0 ? dIdx - i + 4 : dIdx - i);
            nx = x + dx[nDIdx];
            ny = y + dy[nDIdx];
            if (!outOfBound(nx, ny) && room[nx][ny] == 0 && !visited[nx][ny]) {
                canClean = true;
                cleanRoom(nx, ny, nDIdx);
                break;
            }
        }
        if (!canClean) { // 청소 할거 없는 경우 뒤로 한칸 후진가능하면 후진
            nx = x + dx[dIdx] * (-1);
            ny = y + dy[dIdx] * (-1);
            if (!outOfBound(nx, ny) && room[nx][ny] == 0) {
                cleanRoom(nx, ny, dIdx);
            }
        }
    }

    private static boolean outOfBound(int x, int y) {
        return !(x >= 0 && x < N && y >= 0 && y < M);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int x, y, dIdx;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        dIdx = Integer.parseInt(st.nextToken());
        room = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cleanRoom(x, y, dIdx);

        System.out.println(answer);
    }
}
