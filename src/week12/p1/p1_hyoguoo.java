/**
 * 문제 링크: https://www.acmicpc.net/problem/11497
 * 메모리: 53628 KB
 * 시간: 600 ms
 * 시간 복잡도: O(N logN)
 * 공간 복잡도: O(N)
 */

/**
 * 주어진 나무를 지그재그로 정렬한 후, 각 인접한 두 값의 차이 중 최대값을 구하는 문제
 *
 * 1. 입력 받은 나무를 정렬
 * 2. 첫 번째 값부터 시작하여, 짝수번째 인덱스에는 왼쪽(작은 값)부터, 홀수번째 인덱스에는 오른쪽(높은 값)부터 삽입
 * 3. 결과적으로 지그재그로 정렬된 값들의 차이 중 최대값을 구함
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LogJumping {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCount = Integer.parseInt(bufferedReader.readLine());

        StringBuilder stringBuilder = new StringBuilder();

        while (testCount-- > 0) {
            bufferedReader.readLine();
            int[] numbers = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            stringBuilder.append(solution(numbers)).append("\n");
        }

        System.out.print(stringBuilder.toString().trim());
    }

    private static int solution(int[] numbers) {
        return calculateMaxDifference(
                numbers,
                getZigzagSort(numbers)
        );
    }

    private static int[] getZigzagSort(int[] numbers) {
        Arrays.sort(numbers);
        int[] result = new int[numbers.length];

        int left = 0;
        int right = numbers.length - 1;

        for (int i = 0; i < numbers.length; i++) {
            if (i % 2 == 0) {
                result[left++] = numbers[i];
            } else {
                result[right--] = numbers[i];
            }
        }

        return result;
    }

    private static int calculateMaxDifference(int[] numbers, int[] result) {
        int max = Math.abs(result[0] - result[numbers.length - 1]);

        for (int i = 0; i < numbers.length - 1; i++) {
            max = Math.max(max, Math.abs(result[i] - result[i + 1]));
        }

        return max;
    }
}
