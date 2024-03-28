/**
 * 문제 링크: https://www.acmicpc.net/problem/2004
 * 메모리: 14832 KB
 * 시간: 148 ms
 * 시간 복잡도: O(log N)
 * 공간 복잡도: O(1)
 */

/**
 * 끝 자리 0의 개수 = 10의 제곱수의 개수
 * 전부 계산하게 되면 시간 초과가 발생하므로 2와 5의 개수를 계산하여 더 작은 값을 반환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumberCombinationZeros {

    static final List<Integer> TEN_MEAURES = Arrays.asList(2, 5);

    public static void main(String[] args) throws IOException {
        long[] info = Arrays.stream(
                        new BufferedReader(new InputStreamReader(System.in))
                                .readLine()
                                .split(" ")
                )
                .mapToLong(Long::parseLong)
                .toArray();

        System.out.print(solution(info[0], info[1]));
    }

    private static long solution(long n, long m) {
        return TEN_MEAURES.stream()
                .map(
                        num ->
                                getMultiplyCount(n, num)
                                        - getMultiplyCount(m, num)
                                        - getMultiplyCount(n - m, num)
                )
                .collect(Collectors.toList()).stream()
                .min(Long::compareTo)
                .orElse(0L);
    }

    private static long getMultiplyCount(long n, long k) {
        long count = 0;

        while (k <= n) {
            count += n / k;
            n /= k;
        }

        return count;
    }
}
