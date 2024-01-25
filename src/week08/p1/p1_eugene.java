package week08.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1_eugene {
    static int N, M, K, X;
    static int[] ar;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 라운드 참여 횟수
        M = Integer.parseInt(st.nextToken()); // 쿼리 개수
        K = Integer.parseInt(st.nextToken()); // 레이팅 점수 K 보다 낮은 횟수
        X = Integer.parseInt(st.nextToken()); // 초기 레이팅 점수

        ar = new int[N+1];
        ar[0] = X;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            ar[i] = ar[i-1] + Integer.parseInt(st.nextToken());
        }

        int[] cnt = new int[N+1];
        for (int i = 1; i <= N; i++) {
            if(ar[i] < K) cnt[i] = cnt[i - 1] + 1;
            else cnt[i] = cnt[i-1];
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken()) - 1;

            sb.append(cnt[end] - cnt[start - 1]).append("\n");
        }

        System.out.println(sb);
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/26007
 * 메모리: 254208 KB
 * 시간: 1164 ms
 * 시간 복잡도: O(N + M)
 * 공간 복잡도: O(N + M)
 *
 * 누적 합 이용
 * K 보다 낮은 경우 누적합 배열 이전 원소 값에 + 1
 * K 보다 낮은 경우를 누적합 배열 이용해 구하기
 */
