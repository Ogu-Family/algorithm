/**
 * 문제 링크: https://www.acmicpc.net/problem/16938
 * 메모리: 14460 KB
 * 시간: 132 ms
 * 시간 복잡도: O(2^N)
 * 공간 복잡도: O(N)
 */

/**
 * 1. 재귀적으로 모든 경우의 수를 탐색
 * 2. 최소 문제 갯수 + 합의 범위 + 최소값과 최대값의 차이 조건을 만족하면 결과 증가
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PreparingCamp {

    private static final int MINIMUM_COUNT = 2;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int lowerBound = info[1];
        int upperBound = info[2];
        int difference = info[3];
        int[] numbers = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.print(solution(numbers, lowerBound, upperBound, difference));
    }

    private static int solution(
            int[] numbers,
            int lowerBound,
            int upperBound,
            int difference
    ) {
        recursion(
                numbers,
                0,
                0,
                0,
                lowerBound,
                upperBound,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                difference
        );
        return result;
    }

    private static void recursion(
            int[] numbers,
            int index,
            int depth,
            int sum,
            int lowerBound,
            int upperBound,
            int min,
            int max,
            int difference
    ) {
        // 최소 문제 갯수 + 합의 범위 + 최소값과 최대값의 차이
        if (MINIMUM_COUNT <= depth &&
                lowerBound <= sum &&
                sum <= upperBound &&
                difference <= max - min) {
            result++;
        }

        // 재귀적으로 모든 경우의 수를 탐색
        for (int i = index; i < numbers.length; i++) {
            recursion(
                    numbers,
                    i + 1,
                    depth + 1,
                    sum + numbers[i],
                    lowerBound,
                    upperBound,
                    Math.min(min, numbers[i]),
                    Math.max(max, numbers[i]),
                    difference
            );
        }
    }
}
