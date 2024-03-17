package src.week15.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2559
 * 메모리: 24284 KB
 * 시간: 292 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 슬라이딩 윈도우 문제
 * 연속되는 K일의 합을 알아야 하는 문제
 * for 문을 통해 먼저 진행하는 rt와 이후에 따라가는 lt의 차이가 K - 1이라면 합을 구해 최대를 비교한다.
 *
 * lt는 연속에서 제외되는 값을 지우기 위해 존재함.
 */

public class p2_GiHoo {

    static int N;
    static int K;
    static int[] school;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        school = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            school[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }

    private static int solution() {
        int lt = 0;
        int sum = 0;
        int max = Integer.MIN_VALUE;

        for (int rt = 0; rt < school.length; rt++) {
            sum += school[rt];

            if (rt - lt + 1 == K) {
                max = Integer.max(max, sum);

                sum -= school[lt++];
            }
        }

        return max;
    }
}
