package week08.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/20300
 * 메모리: 18952 KB
 * 시간: 244 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/*
1. 운동기구 N개의 각 근손실 정도를 배열로 입력받아 정렬
2. 배열의 처음(최솟값)과 끝(최댓값)에서부터 순서대로 두 개씩 묶어서 합이 최대인 것을 계산
3. 최댓값 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2_letskuku {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long[] loss = new long[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            loss[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(loss);
        long max = 0;
        if (n % 2 == 1) {
            max = loss[n - 1];
            for (int i = 0; i < n / 2; i++) {
                max = Math.max(max, loss[i] + loss[n - i - 2]);
            }
        } else {
            for (int i = 0; i < n / 2; i++) {
                max = Math.max(max, loss[i] + loss[n - i - 1]);
            }
        }

        System.out.println(max);
    }
}
