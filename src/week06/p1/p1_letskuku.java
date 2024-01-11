package week06.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1932
 * 메모리: 27072 KB
 * 시간: 288 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2)
 */

/*
1. 삼각형을 입력 받아
2. 맨 위층부터 내려오면서 아래층 대각선 왼쪽/오른쪽에 따라 수의 합 계산하여 최댓값 저장
3. 맨 아래층에서 수의 합 최댓값 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1_letskuku {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] triangle = new int[n][n];
        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
                sum[i][j] = 0;
            }
        }

        sum[0][0] = triangle[0][0];
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j <= i; j++) {
                sum[i + 1][j] = Math.max(sum[i + 1][j], sum[i][j] + triangle[i + 1][j]); // 대각선 왼쪽
                sum[i + 1][j + 1] = Math.max(sum[i + 1][j + 1], sum[i][j] + triangle[i + 1][j + 1]); // 대각선 오른쪽
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, sum[n - 1][i]); // 맨 아래층 최댓값 계산
        }

        System.out.println(ans);
    }
}
