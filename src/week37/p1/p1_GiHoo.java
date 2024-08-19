package src.week37.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1806
 * 메모리: 23748 KB
 * 시간: 260 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 투포인터 문제
 *
 * lt = 0, rt = 1 을 시작으로 해서 lt와 rt 사이에 존재하는 값들을 더해 S와 비교
 * lt < rt 조건으로 개수가 2개일 때 최소 조건을 만족하여 while 탈출
 * 이후 1개로 조건을 만족하는 경우를 확인하여 반환한다.
 */


public class p1_GiHoo {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = solution(arr, N, S);

        System.out.print(answer);
    }

    private static int solution(int[] arr, int N, int S) {
        int lt = 0;
        int rt = lt + 1;

        int answer = Integer.MAX_VALUE;
        int sum = (arr[lt] + arr[rt]);

        while (lt < rt && rt < N) {
            if (sum >= S) {
                int cnt = (rt - lt + 1);
                answer = Math.min(answer, cnt);

                sum -= arr[lt++];
            } else {
                sum += arr[++rt];
            }
        }

        for (int i = 0; i < N; i++) {
            if (arr[i] >= S) {
                answer = 1;
                break;
            }
        }

        return (answer == Integer.MAX_VALUE) ? 0 : answer;
    }
}
