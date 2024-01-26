package src.week08.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1_GiHoo {

    static int N; // 라운드에 참여한 횟수
    static int[] A; // 라운에 참여한 후 레이팅 증감 수열
    static int M; // 쿼리 수
    static int K; // 목표 레이팅 점수
    static int X; // 초기 레이팅 점수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        A = new int[N + 1];
        A[0] = X; // 초기 레이팅 점수 세팅
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = (A[i - 1] + Integer.parseInt(st.nextToken()));
        }

        int[] isLower = new int[N + 1]; // 누적합 배열
        for (int i = 1; i <= N; i++) {
            if (A[i] < K) isLower[i] = isLower[i -1] + 1;
            else isLower[i] = isLower[i-1];
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken()) - 1; // 전까지

            int cnt = isLower[end] - isLower[start - 1];

            sb.append(cnt).append("\n");
        }

        System.out.print(sb);
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/26007
 * 메모리: 254672 KB
 * 시간: 1172 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)

 * 풀이
 * 처음에는 신나게 풀다가 시간초과..
 *
 * 문제에서
 * l번째 라운드에 참여한 직후부터, r번째 라운드에 참여하기 직전까지 레이팅이 K보다 낮은 횟수를 물어봄
 * 이를 누적합으로 생각한다면, r-1번째 라운드에서 l번째 라운드까지의 낮은 횟수를 비교하면 O(n)으로 해결할 수 있다.
 * 누적합 배열 isLower 를 통해 해결
 */
