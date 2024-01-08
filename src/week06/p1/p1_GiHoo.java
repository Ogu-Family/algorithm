package src.week06.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1_GiHoo {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int triangle_size = Integer.parseInt(br.readLine());
        int[][] triangle = new int[triangle_size][triangle_size];

        StringTokenizer st;
        for (int i = 0; i < triangle_size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = triangle_size - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.max(triangle[i][j] + triangle[i + 1][j], triangle[i][j] + triangle[i + 1][j + 1]);
            }
        }

        System.out.println(triangle[0][0]);
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/1932
 * 메모리: 25516 KB
 * 시간: 292 ms
 * 시간 복잡도: O(n^2)
 * 공간 복잡도: O(n^2)
 */

/**
 * 24번 라인 기준으로 설명
 * 삼각형을 위에서 아래로 값을 비교할 수 있지만
 * 아래에서부터 최대값을 비교하며 올라갈 수 있다.
 *
 * 마지막 변 - 1인 곳에서부터 자신의 값과 자신의 아래에 있는 왼, 오른 값의 합과 비교하여
 * 값을 비교하면 된다.
 */
