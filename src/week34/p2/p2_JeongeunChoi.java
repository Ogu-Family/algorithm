package algorithm.src.week34.p2;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/16918
 * 메모리: 53684 KB
 * 시간: 704 ms
 * 시간 복잡도: O(N^3)
 * 공간 복잡도: O(N^2)
 */

/**
 * 구현
 * 지도에 폭탄이 없는 경우 -1, 있는 경우 설치된 후 경과된 시간으로 표시한다.
 * 봄버맨의 행동을 정해진 시간 동안 반복한다.
 */

public class p2_JeongeunChoi {

    private static int R, C;
    private static int[][] map;

    private static void increaseBombsT() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] >= 0) {
                    map[i][j]++;
                }
            }
        }
    }

    private static void installBomb() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) {
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void explodeBomb() {
        int[][] copiedMap = new int[R][C];
        for (int i = 0; i < R; i++) {
            copiedMap[i] = Arrays.copyOf(map[i], map[i].length);
        }
        int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (copiedMap[i][j] == 3) {
                    map[i][j] = -1;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k], ny = j + dy[k];
                        if (!outOfBound(nx, ny)) {
                            map[nx][ny] = -1;
                        }
                    }
                }
            }
        }
    }

    private static boolean outOfBound(int x, int y) {
        return !(x >= 0 && x < R && y >= 0 && y < C);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'O') {
                    map[i][j] = 1;
                } else if (map[i][j] == '.') {
                    map[i][j] = -1;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            increaseBombsT();
            if (i % 2 == 0) {
                installBomb();
            } else {
                explodeBomb();
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) {
                    System.out.print(".");
                } else if (map[i][j] >= 0) {
                    System.out.print("O");
                }
            }
            System.out.println();
        }


    }

}
