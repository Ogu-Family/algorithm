/**
 * 문제 링크: https://www.acmicpc.net/problem/21758
 * 메모리: 26724 KB
 * 시간: 316 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 꿀을 채취할 수 있는 경우의 수 세 가지에 대해 각각 최대값을 구하는 문제
 *
 * 다음과 같은 경우의 수에 대해 각각 최대값을 구한 뒤, 그 중 제일 큰 값을 반환하면 된다.
 * 1. 벌 꿀 벌
 * 2. 벌 벌 꿀
 * 3. 꿀 벌 벌
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PickingHoney {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        System.out.print(
                solution(
                        Arrays.stream(bufferedReader.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray()
                )
        );
    }

    private static int solution(int[] honey) {
        int length = honey.length;
        int[] sum = getPrefixSum(honey, length);

        int result = 0;

        for (int i = 1; i < length - 1; i++) {
            int case1 = sum[length - 1] - honey[0] - honey[i] + sum[length - 1] - sum[i];
            int case2 = sum[length - 2] - sum[i - 1] + sum[i] - sum[0];
            int case3 = sum[length - 2] - honey[i] + sum[i - 1];
            result = Math.max(result, Math.max(case1, Math.max(case2, case3)));
        }

        return result;
    }

    private static int[] getPrefixSum(int[] honey, int length) {
        int[] sum = new int[length];

        for (int i = 0; i < length; i++) {
            sum[i] = honey[i];
            if (i > 0) {
                sum[i] += sum[i - 1];
            }
        }
        return sum;
    }
}
