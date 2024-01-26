package algorithm.src.week08.p1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/26007
 * 메모리: 246704 KB
 * 시간: 1024 ms
 * 시간 복잡도: O(N + M)
 * 공간 복잡도: O(N + M)
 */

/**
 * 누적 합을 이용
 * 이전 라운드 점수를 바탕으로 현재 라운드 점수를 계산하며
 * 점수 K보다 낮은 경우 누적합 배열 이전 원소 값에 + 1 한다.
 * 마지막에 특정 구간에 K보다 낮은 경우의 횟수를 누적합 배열 원소 값의 차이를 통해 구한다.
 *
 * 근데 마지막에 출력할때 StringBuilder 사용안하고 System.out.println 사용해서 바로 출력하면 시간초과가 납니다 ...
 */

public class p1_JeongeunChoi {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, M, K, X;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        int[] lessThanKSumCnt = new int[N + 2];
        int beforeRoundScore = X;
        lessThanKSumCnt[1] = X < K ? 1 : 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= N + 1; i++) {
            beforeRoundScore += Integer.parseInt(st.nextToken());
            lessThanKSumCnt[i] =
                    beforeRoundScore < K ? lessThanKSumCnt[i - 1] + 1 : lessThanKSumCnt[i - 1];
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            int lessThanKCnt = lessThanKSumCnt[r] - lessThanKSumCnt[l];
            sb.append(lessThanKCnt).append("\n");
        }

        System.out.print(sb);
    }
}
