package week39.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2294
 * 메모리: 14468 KB
 * 시간: 132 ms
 * 시간 복잡도: O(N*M)
 * 공간 복잡도: O(N)
 */

/*
1. 동전의 가치와 최종 도달해야 하는 가치 k를 입력받아
2. 1원부터 k원까지 주어진 동전 종류로 조합할 때 개수가 최소가 되는 값 계산
3. 결과 출력
 */

public class p1_letskuku {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Integer> coins = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            coins.add(Integer.parseInt(br.readLine()));
        }

        int[] dp = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            dp[i] = 10001;
        }

        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= coins.get(j)) {
                    dp[i] = Math.min(dp[i], dp[i - coins.get(j)] + 1);
                }
            }
        }

        if (dp[k] < 10001) {
            System.out.println(dp[k]);
        } else {
            System.out.println(-1);
        }
    }
}
