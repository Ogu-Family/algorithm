package src.week13.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/16139
 * 메모리: 97688 KB
 * 시간: 780 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2)
 */

/**
 * 누적합
 * 누적합을 위한 배열 생성 -> dp
 * 배열을 [테스트케이스 + 1][알파벳의 수, 26] 크기로 생성한다.
 *
 * 처음에 케이스마다 따로 계산을 했었는데, 시간초과 발생해서 시작부터 누적합 배열을 만들고 시작하니까 성공
 * 하지만 사용하지도 않는 알파벳에 대해서도 누적합을 생성하는 것이 좋은가? 라는 의문이 ㅎ..
 */

public class p1_GiHoo {

    static int[][] dp;
    static String input;
    static int alphabet = 26;
    static int tc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        input = br.readLine();
        tc = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st;
        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());

            char target = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(dp[end + 1][target - 'a'] - dp[start][target - 'a']).append("\n");
        }

        System.out.println(sb);
    }

    private static void init() {
        dp = new int[input.length() + 1][alphabet];

        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j < alphabet; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            dp[i][input.charAt(i - 1) - 'a'] += 1;
        }
    }
}

