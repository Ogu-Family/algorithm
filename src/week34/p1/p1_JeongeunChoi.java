package algorithm.src.week34.p1;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2565
 * 메모리: 14084 KB
 * 시간: 128 ms
 * 시간 복잡도: O(1)
 * 공간 복잡도: O(1)
 */

/**
 * dp
 * electricWires[시작전봇대] = [끝전봇대]
 * electricWires의 인덱스 증가에 따라, 배열 값 또한 증가해야 겹치지 않으므로 증가하는 길이의 최대값을 구한다.
 * 여기까지 생각한 후 풀이 참고
 * dp[i] = electricWires 1~i번째까지 증가하는 값의 수
 */

public class p1_JeongeunChoi {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int electricWireCnt = Integer.parseInt(br.readLine()), maxIdx = 0, increaseLength = 0;
        int[] electricWires = new int[501];
        for (int i = 0; i < electricWireCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            electricWires[a] = b;
            maxIdx = Math.max(maxIdx, a);

        }

        int[] dp = new int[maxIdx + 1];
        for (int i = 1; i <= maxIdx; i++) {
            if (electricWires[i] > 0) {
                dp[i] = 1;
                for (int j = i - 1; j >= 0; j--) {
                    if (electricWires[j] != 0 && electricWires[j] < electricWires[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }
            increaseLength = Math.max(increaseLength, dp[i]);
        }

        System.out.println(electricWireCnt - increaseLength);
    }

}
