package src.week33.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/20366
 * 메모리: 14648 KB
 * 시간: 784 ms
 * 시간 복잡도: O(N^3)
 * 공간 복잡도: O(N)
 */

/**
 * 이중 투포인터 문제(투포인터 + 슬라이딩) N이 600이길래 N^3으로 풀었습니다.
 *
 * 기준이 되는 눈사람 bigSnowman 을 2차 반복문을 통해 정함
 * i, j를 제외한 나머지 값에 대하여 투포인터 진행 후 smallSnowman 생성
 * 두 눈사람 차이 확인 후, 값을 통해 투포인터 방향 설정
 */

public class p2_GiHoo {

    static int N;
    static int[] snowballs;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();

        solution();

        System.out.print(answer);
    }

    private static void solution() {
        Arrays.sort(snowballs);

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int bigSnowman = snowballs[i] + snowballs[j];

                int lt = 0;
                int rt = N - 1;

                while (lt < rt) {
                    if (lt == i || lt == j) {
                        lt++;
                        continue;
                    }
                    if (rt == i || rt == j) {
                        rt--;
                        continue;
                    }

                    int smallSnowman = snowballs[lt] + snowballs[rt];
                    answer = Math.min(answer, Math.abs(bigSnowman - smallSnowman));

                    if (bigSnowman > smallSnowman) lt++;
                    else rt--;
                }
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        snowballs = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snowballs[i] = Integer.parseInt(st.nextToken());
        }
    }
}

