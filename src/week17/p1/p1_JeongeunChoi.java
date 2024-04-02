package algorithm.src.week17.p1;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/27980
 * 메모리: 113396 KB
 * 시간: 388 ms
 * 시간 복잡도: O(N*M)
 * 공간 복잡도: O(N*M)
 */

/**
 * dfs로 풀다가 시간초과 나서, 알고리즘 분류보고 풀이 참고하여 다시 문 푼제
 * 두 문자열에 대해, dp 2차원 배열을 두고 왼쪽, 오른쪽에서 오는 경우 중 큰 값을 더해준다.
 */

public class p1_JeongeunChoi {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, M, answer = 0;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] dp = new int[M][N];

        String board, myStr;
        board = br.readLine();
        myStr = br.readLine();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (myStr.charAt(i) == board.charAt(j)) {
                    dp[i][j]++;
                }
            }
        }

        for (int i = 1; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int l = j - 1, r = j + 1;
                dp[i][j] += Math.max(l >= 0 ? dp[i - 1][l] : 0, r < N ? dp[i - 1][r] : 0);
                // 여기 안에서 바로 최댓값 계산하면, 84% 쯤에서 오답뜨는데 왤까요..?
//                if (i == M - 1) {
//                    answer = Math.max(answer, dp[i][j]);
//                }
            }
        }

        for(int i=0; i<N; i++){
            answer = Math.max(answer, dp[M-1][i]);
        }

        System.out.println(answer);
    }

}