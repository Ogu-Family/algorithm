package src.week32.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2470
 * 메모리: 32496 KB
 * 시간: 404 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(NlogN)
 */

/**
 * 이분 탐색으로 풀었으나 투 포인터가 더 최적화된 풀이라 생각함.
 *
 * 1. 용액 오름차순 정렬
 * 2. 용액들을 전부 보면서 해당 용액 * (-1)한 값과 가장 가까운 용액을 찾는다.
 * 3. 두 용액의 합이 0과 가장 가까운 용액 리스트를 찾고 그것을 출력(오름차순).
 */

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class p3_ZZAMBAs {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        List<Integer> liquor = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt)
            .sorted(Comparator.naturalOrder()).collect(Collectors.toList()); // 스트림에 정렬을 섞을 수 있네요.

        List<Integer> foundTwoLiq = findTwoLiquorNearestZero(liquor);

        System.out.print(foundTwoLiq.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    static List<Integer> findTwoLiquorNearestZero(List<Integer> liquor) {
        int diff = Integer.MAX_VALUE;
        List<Integer> ret = new ArrayList<>();

        for (int l : liquor) {
            int mixLiq = binarySearch(liquor, -l);

            if (mixLiq == l)
                continue;

            int liqSum = Math.abs(l + mixLiq);

            if (liqSum < diff) {
                ret = new ArrayList<>(2);
                ret.add(l);
                ret.add(mixLiq);

                diff = liqSum;
            }
        }
        ret.sort(Comparator.naturalOrder());

        return ret;
    }

    static int binarySearch(List<Integer> arr, int val) {
        int s = 0;
        int e = arr.size() - 1;

        while (s <= e) {
            int mid = (s + e) / 2;

            if (arr.get(mid) <= val)
                s = mid + 1;
            else
                e = mid - 1;
        }

        if (e == -1)
            return arr.get(0);
        if (e == arr.size() - 1)
            return arr.get(arr.size() - 1);

        return (Math.abs(val - arr.get(e)) < Math.abs(val - arr.get(e + 1))) ? arr.get(e) : arr.get(e + 1);
    }
}
