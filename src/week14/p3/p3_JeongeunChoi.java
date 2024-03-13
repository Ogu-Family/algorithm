package algorithm.src.week14.p3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/14620
 * 메모리: 18984 KB
 * 시간: 264 ms
 * 시간 복잡도: O(N^3)
 * 공간 복잡도: O(N^2)
 */

/**
 * dfs를 통해
 * 꽃 3개를 피우는 모든 경우의 수 중
 * 최소 비용을 구한다.
 */


public class p3_JeongeunChoi {

    static int N, answer = 200000;
    static int[][] flowerBed = new int[12][12];
    static boolean[][] blossom = new boolean[12][12];
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    private static void dfs(int x, int y, int cnt, int cost) {
        if (cnt == 3) {
            answer = Math.min(answer, cost);
        } else {
            if (canBlossom(x, y)) {
                blossom[x][y] = true;
                int blossomCost = flowerBed[x][y];
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i], ny = y + dy[i];
                    blossom[nx][ny] = true;
                    blossomCost += flowerBed[nx][ny];
                }
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        dfs(i, j, cnt + 1, cost + blossomCost);
                    }
                }
                blossom[x][y] = false;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i], ny = y + dy[i];
                    blossom[nx][ny] = false;
                }
            }
        }
    }

    private static boolean outOfRange(int x, int y) {
        return !(x >= 1 && x <= N && y >= 1 && y <= N);
    }

    private static boolean canBlossom(int x, int y) {
        if (!outOfRange(x, y) && !blossom[x][y]) {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (outOfRange(nx, ny) || blossom[nx][ny]) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                flowerBed[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dfs(i, j, 0, 0);
            }
        }

        System.out.println(answer);
    }
}