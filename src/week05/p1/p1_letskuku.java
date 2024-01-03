package week05.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1654
 * 메모리: 17492 KB
 * 시간: 188 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/*
1. 이미 가지고 있는 랜선의 개수와 길이, 필요한 랜선의 개수를 입력 받아
2. 가지고 있는 랜선 중 가장 긴 것을 기준으로 이진 탐색, n개 만들 수 있으면 기준 값 오른쪽, 만들 수 없으면 왼쪽으로 범위 줄임
3. 만들 수 있는 최대 랜선의 길이 출력

k <= n 이라는 조건을 보고 단순하게 모든 랜선을 사용한다고 생각해서 처음엔 이진 탐색 최댓값을 가지고 있는 랜선들 중 가장 짧은 것으로 잡았다...
그 다음엔 이진 탐색 중 left + right 계산 시 int 오버플로우가 날 수 있다는 것을 고려하지 못해서 또 틀렸다...
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1_letskuku {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] lans = new int[k];
        int maxLen = 0;
        for (int i = 0; i < k; i++) {
            lans[i] = Integer.parseInt(br.readLine());

            if (maxLen < lans[i]) {
                maxLen = lans[i];
            }
        }

        long ans = 0;
        long left = 1;
        long right = maxLen;
        long mid;
        while (left <= right) {
            mid = (left + right) / 2;

            int tmp = 0;
            for (int i = 0; i < k; i++) {
                tmp += lans[i] / mid;
            }

            if ((tmp >= n) && (mid > ans)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(ans);
    }
}
