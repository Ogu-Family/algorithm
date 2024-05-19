package algorithm.src.week24.p3;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/3987
 * 메모리: 78088 KB
 * 시간: 396 ms
 * 시간 복잡도: O(N * M)
 * 공간 복잡도: O(N * M * 4)
 */

/**
 * 구현
 * 네 방향에 대해 각각 시그널을 전파한다.
 * 슬래시와 백슬래시를 마주치면 방향을 전환하며, 항성계 내부에 있는 시간이 최대가 되는 경우의 방향과 시간을 구한다.
 * 만약 똑같은 위치, 방향으로 방문한 경우가 있다면 무한히 전파가 가능한 것으로 판단한다.
 */

public class p3_JeongeunChoi {

    private static int N, M, maxTime = 0;
    private static char directionForMaxTime;
    private static char[][] map;
    private static boolean[][][] visited;
    private static char[] direction = {'U', 'R', 'D', 'L'};
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static int[] changeDirectionForBackSlash = {3, 2, 1, 0}, changeDirectionForSlash = {1, 0, 3, 2};
    private static boolean canSpreadInfinitely = false;

    private static void spreadSignal(int x, int y, int startDIdx, int dIdx, int time) {
        int nx = x + dx[dIdx], ny = y + dy[dIdx];
        if (visited[x][y][dIdx]) { // 똑같은 곳에 같은 방향으로 접근 시, 무한히 전파 가능
            canSpreadInfinitely = true;
            directionForMaxTime = direction[startDIdx];
        } else if (outOfBound(nx, ny) || map[nx][ny] == 'C') {
            if (maxTime < time + 1) {
                maxTime = time + 1;
                directionForMaxTime = direction[startDIdx];
            }
        } else if (map[nx][ny] == '.') {
            visited[x][y][dIdx] = true;
            spreadSignal(nx, ny, startDIdx, dIdx, time + 1);
        } else if (map[nx][ny] == '\\') {
            visited[x][y][dIdx] = true;
            spreadSignal(nx, ny, startDIdx, changeDirectionForBackSlash[dIdx], time + 1);
        } else if (map[nx][ny] == '/') {
            visited[x][y][dIdx] = true;
            spreadSignal(nx, ny, startDIdx, changeDirectionForSlash[dIdx], time + 1);
        }
    }

    private static boolean outOfBound(int x, int y) {
        return !(x >= 1 && x <= N && y >= 1 && y <= M);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String mapStr = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j + 1] = mapStr.charAt(j);
            }
        }
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 4; i++) {
            visited = new boolean[N + 1][M + 1][4];
            spreadSignal(x, y, i, i, 0);
            if (canSpreadInfinitely) {
                break;
            }
        }

        System.out.println(directionForMaxTime);
        if (canSpreadInfinitely) {
            System.out.println("Voyager");
        } else {
            System.out.println(maxTime);
        }
    }

}
