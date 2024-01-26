package src.week08.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p3_GiHoo {

    static int N;
    static int[][] forest;
    static int[][] dp;
    static int[][] move = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        insertInput();

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.max(answer, DFS(i, j)); // i, j 시작
            }
        }

        System.out.print(answer);
    }

    private static int DFS(int i, int j) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        dp[i][j] = 1;

        for (int k = 0; k < move.length; k++) {
            int nx = i + move[k][0];
            int ny = j + move[k][1];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                if (forest[nx][ny] > forest[i][j]) {
                    dp[i][j] = Math.max(dp[i][j], DFS(nx, ny) + 1);
                }
            }
        }

        return dp[i][j];
    }

    private static void insertInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        forest = new int[N][N];
        dp = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                forest[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/1937 메모리: 37312 KB 시간: 500 ms 시간 복잡도: ??? -> N^2 이상일 것 같은데
 * 잘 모르겠습니다. 공간 복잡도: O(N^2)
 * <p>
 * 풀이 처음에는 매 지점마다 DFS 를 수행하려 했지만, N 이 500 이라 불가능 문제 해결 포인트는 시작 지점이다. 특정 시작 지점만 다를 뿐, 한 점에서 이동할 수 있는
 * 최대 갯수는 항상 동일하다. 시작 지점을 다르게 DFS 를 수행하고,DP 를 이용하여 해결
 */
