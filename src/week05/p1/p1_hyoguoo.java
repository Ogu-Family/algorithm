/**
 * 문제 링크: https://www.acmicpc.net/problem/1654
 * 메모리: 18300 KB
 * 시간: 228 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * 이분 탐색 문제
 *
 * left = 1, right = 입력 받은 가장 긴 랜선의 길이(최대 길이)
 * mid 기준으로 랜선을 잘라서 만들 수 있는 랜선의 개수를 구하여 targetCount와 비교
 * targetCount보다 크거나 같으면 left = mid + 1
 * targetCount보다 작으면 right = mid - 1
 *
 * mid 값이 int 범위를 넘어갈 수 있으므로 long 타입으로 선언해야하므로 left, right, mid 모두 long 타입으로 선언
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LanCableCut {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] cables = new int[info[0]];
        int targetCount = info[1];

        for (int i = 0; i < cables.length; i++) {
            cables[i] = Integer.parseInt(bufferedReader.readLine());
        }

        System.out.println(solution(cables, targetCount));
    }

    private static long solution(int[] cables, int targetCount) {
        long left = 1;
        long right = Arrays.stream(cables).max().orElse(0);

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = getCount(cables, mid);

            if (count >= targetCount) left = mid + 1;
            else right = mid - 1;
        }

        return right;
    }

    private static long getCount(int[] cables, long mid) {
        return Arrays.stream(cables).mapToLong(cable -> cable / mid).sum();
    }
}
