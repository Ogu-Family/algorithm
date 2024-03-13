package algorithm.src.week15.p2;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2559
 * 메모리: 24024 KB
 * 시간: 284 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 온도 값을 입력받으며 누적합 값을 저장하고
 * K개 이상의 값이 들어오면 연속 K일 누적 온도합 중 가장 큰 값을 찾는다.
 */

public class p2_JeongeunChoi {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, K, answer = 0;
        int[] t;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        t = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            t[i] = t[i - 1] + Integer.parseInt(st.nextToken());
            if(i == K){
                answer = t[i];
            } else if (i > K) {
                answer = Math.max(answer, t[i] - t[i - K]);
            }
        }

        System.out.println(answer);
    }
}
