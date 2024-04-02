package src.week18;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1339
 * 메모리: 18292 KB
 * 시간: 224 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 어려운 그리디. 처음에 앞자리만 고려하다 틀림.
 *
 * 1. 수는 최대 8자리이고, N은 10 이하이므로, 수를 돌면서 각 알파벳에 대해 자릿 수에 해당하는 부분에 1을 더한다.
 * 예를 들어 수가 ABC, BA 이런 식으로 나오면 A = 101 (백의 자리 1번, 일의 자리 1번)이고 같은 논리로 B = 20, C = 1이다.
 * 2. 이것들을 전부 내림차순 정렬하고, 순서대로 9 ~ 0까지 값을 매긴다. 이후 답을 구한다.
 */

import java.util.*;
import java.util.stream.*;

public class p1_ZZAMBAs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String[] s = new String[N];
        int[] mapping = new int[26]; // 알파벳 -> 숫자
        Pair<Integer, Integer>[] priority = new Pair[26]; // 알파벳 우선순위 표현
        IntStream.range(0, 26).forEach(i -> priority[i] = new Pair(-1, i));

        // 1.
        IntStream.range(0, N).forEach(i -> {
            s[i] = sc.next();
            IntStream.range(0, s[i].length()).forEach(j -> {
                int idx = s[i].charAt(j) - 65;

                if (priority[idx].x < 0)
                    priority[idx].x = 0;

                int numLocation = s[i].length() - j;

                priority[idx].x += mul(numLocation);
            });
        });

        // 2.
        Arrays.sort(priority, (p1, p2) -> p2.x - p1.x);

        IntStream.range(0, 26).forEach(i -> mapping[priority[i].y] = 9 - i);

        int res = 0;
        for (String str : s) {
            int num = 0;

            for (int i = 0; i < str.length(); i++)
                num += mapping[str.charAt(i) - 65] * mul(str.length() - i);

            res += num;
        }

        System.out.println(res);
    }

    static int mul(int numLocation) { // 자릿 수 값을 구하는 메서드
        int ret = 1;

        for (int i = 0; i < numLocation - 1; i++)
            ret *= 10;

        return ret;
    }

    static class Pair<T, R> { // 이거 미리 만들어 두면 매우 편합니다
        T x;
        R y;

        Pair (T x, R y) {
            this.x = x;
            this.y = y;
        }
    }
}
