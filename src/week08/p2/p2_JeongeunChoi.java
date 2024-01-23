package algorithm.src.week08.p2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/20300
 * 메모리: 19032 KB
 * 시간: 240 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * 그리디 알고리즘
 * 입력받은 운동기구 근손실 정도 배열을 오름차순 정렬 후
 * 마지막 배열의 값(-> 최대값)을 minM에 저장해둔다.
 * 홀수 개인 경우, 한개를 뒤로 빼두고
 * 짝수 개인 상태에서 양 끝의 값을 더하며
 * minM과 비교하여 더 큰 값인 경우 minM 값에 대입한다.
 */

public class p2_JeongeunChoi {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long[] t = new long[n];
        long minM;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            t[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(t);
        minM = t[n - 1];
        if (n % 2 != 0) {
            n -= 1;
        }
        for (int i = 0; i < n / 2; i++) {
            long M = t[i] + t[n - 1 - i];
            if (M > minM) {
                minM = M;
            }
        }

        System.out.println(minM);
    }
}