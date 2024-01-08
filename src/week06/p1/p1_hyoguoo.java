/**
 * 문제 링크: https://www.acmicpc.net/problem/1932
 * 메모리: 28924 KB
 * 시간: 328 ms
 * 시간 복잡도: O(N * N)
 * 공간 복잡도: O(N)
 */

/**
 * Dynamic Programming 문제
 *
 * 가장 왼쪽과 오른쪽은 각각 왼쪽 대각선 위의 값과 오른쪽 대각선 위의 값만 더해주고,
 * 가운데 값들은 왼쪽 대각선 위의 값과 오른쪽 대각선 위의 값 중 큰 값을 더하여 가장 큰 값만 남기면서 내려감
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TheTriangle {

    static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int length = Integer.parseInt(bufferedReader.readLine());

        System.out.print(solution(length));
    }

    private static int solution(int length) throws IOException {
        int[] dp = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 2; i <= length; i++) {
            int[] numbers = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < i; j++) {
                if (j == 0) numbers[j] += dp[j]; // 가장 왼쪽 값
                else if (j == i - 1) numbers[j] += dp[j - 1]; // 가장 오른쪽 값
                else numbers[j] += Math.max(dp[j - 1], dp[j]); // 가운데 값, 왼쪽 대각선 위의 값과 오른쪽 대각선 위의 값 중 큰 값만 더함
            }

            dp = numbers; // dp 배열 갱신
        }

        return getMaxValue(dp);
    }

    private static int getMaxValue(int[] dp) {
        return Arrays.stream(dp).max().orElseThrow();
    }
}
