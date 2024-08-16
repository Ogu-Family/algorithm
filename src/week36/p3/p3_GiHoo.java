package src.week36.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/28018
 * 메모리: 59356 KB
 * 시간: 900 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N, 1000002)
 */

/**
 * 누적합 문제
 * 시작 시간과 종료 시간을 통해 누적 합 배열을 구현
 * 시작 시간은 증가, 종료 시간 + 1은 감소 - 이후 [i] += [i - 1] 과정을 통해 배열 정상화
 * 종료 시간이 포함되기 때문에 [종료시간 + 1]-- 연산을 진행해야 한다.
 */

public class p3_GiHoo {

    static int[] tables = new int[1000002];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            tables[start]++;
            tables[end + 1]--;
        }

        for (int i = 1; i < tables.length; i++) {
            tables[i] += tables[i - 1];
        }

        int Q = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            System.out.println(tables[Integer.parseInt(st.nextToken())]);
        }
    }
}

