package src.week34.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/31963
 * 메모리: 240036 KB
 * 시간: 1304 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 수학
 *
 * 풀이 참조.. (https://www.ohnimdev.com/entry/%EB%B0%B1%EC%A4%80-31963%EB%B2%88-%EB%91%90-%EB%B0%B0)
 * A_{i - 1} * 2^x <= A_i * 2^y 를 이용하는 문제.(x = (i - 1) 항에서 곱한 지수, y = i항에서 곱한 지수)
 *
 * 1. 최초 원소의 x는 0이라고 둔다.
 * 2. 다음 원소부터 아래를 반복.
 * 2-1. 위 식을 적절히 변형하면 log_2 { A_{i - 1} / A_i } + x <= y 가 된다.
 * 2-2. 이에 부합하는 최소 y값을 찾고, 결과 변수에 더한다.
 * 3. 결과 변수를 출력한다.
 */

import java.util.*;
import java.util.stream.*;

public class p3_ZZAMBAs{
    static Scanner sc = new Scanner(System.in);
    static int N;
    static long res;
    static List<Integer> arr;
    static List<Long> mul = new ArrayList<>();

    public static void main(String[] args) {
        N = sc.nextInt();
        arr = IntStream.range(0, N).map(i -> sc.nextInt()).boxed().collect(Collectors.toList());
        mul.add(0L);

        for (int i = 1; i < N; i++) {
            int pre = arr.get(i - 1);
            int cur = arr.get(i);

            long preMulVal = mul.get(i - 1);
            long calMinMul = (long) Math.ceil(Math.log((double) pre / cur) / Math.log(2) + preMulVal);
            long curMulVal = Math.max(0L, calMinMul);

            res += curMulVal;
            mul.add(curMulVal);
        }

        System.out.print(res);
    }
}
