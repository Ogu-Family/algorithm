package algorithm.src.week06.p1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1932
 * 메모리: 25420 KB
 * 시간: 284 ms
 * 시간 복잡도: O(n^2)
 * 공간 복잡도: O(1)
 */

/**
 * 아래층은 위층의 대각선 오른쪽과 대각선 왼쪽에서만 선택가능하므로
 * 삼각형의 모든 값들을 위에서 부터 순서대로 탐색하며
 * 위층의 대각선 오른쪽과 왼쪽 중 큰 값을 현재층에 더해준다.
 */

public class p2_JeongeunChoi {

    static int n, answer = -1;
    static int[][] triangle = new int[501][501];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
                int upLeft = j - 1, upRight = j;
                if ((i - 1) >= 1) {
                    triangle[i][j] += Math.max(triangle[i - 1][upLeft], triangle[i - 1][upRight]);
                }
                if (i == n) {
                    answer = Math.max(triangle[i][j], answer);
                }
            }
        }

        System.out.println(answer);
    }
}
