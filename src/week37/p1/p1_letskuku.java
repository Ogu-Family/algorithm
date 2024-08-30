package week37.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1806
 * 메모리: 24388 KB
 * 시간: 252 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/*
1. 수열을 입력받아
2. 투 포인터로 수열을 이동하며 s 이상 값이 될 때의 길이를 비교
3. 결과 출력
 */

public class p1_letskuku {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int start = 0, end = 0;
        int ans = 100001;
        while (end < n) {
            while (sum < s && end < n) {
                sum += nums[end];
                end++;
            }

            while (sum >= s && start <= end) {
                ans = Math.min(ans, (end - start));

                sum -= nums[start];
                start++;
            }
        }

        ans = ans == 100001? 0 : ans;
        System.out.println(ans);
    }
}
