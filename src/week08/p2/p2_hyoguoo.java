/**
 * 문제 링크: https://www.acmicpc.net/problem/20300
 * 메모리: 19168 KB
 * 시간: 232 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * Greedy
 *
 * 1. 오름차순으로 정렬
 * 2. 가장 작은 값부터(인덱스 0..) 가장 큰 값부터(인덱스 N-1..) 더해가며 최대값을 찾음
 * 3. 홀수인 경우 가장 큰 값을 따로 수행하는 것으로 처리
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SeogangMuscleMan {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        long[] numbers = Arrays.stream(bufferedReader.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        System.out.println(solution(numbers));
    }

    private static long solution(long[] numbers) {
        Arrays.sort(numbers);

        long maxValue = Long.MIN_VALUE;

        if (numbers.length == 1) {
            maxValue = numbers[0];
        } else if (numbers.length % 2 == 0) {
            for (int i = 0; i < numbers.length / 2; i++) {
                maxValue = Math.max(maxValue, numbers[i] + numbers[numbers.length - 1 - i]);
            }
        } else {
            for (int i = 0; i < numbers.length / 2; i++) {
                maxValue = Math.max(maxValue, numbers[i] + numbers[numbers.length - 2 - i]);
            }
            maxValue = Math.max(maxValue, numbers[numbers.length - 1]); // 홀수인 경우 가장 큰 값 따로
        }

        return maxValue;
    }
}
