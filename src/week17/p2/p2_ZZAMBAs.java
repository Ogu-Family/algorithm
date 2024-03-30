package src.week17.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/23815
 * 메모리: 109336 KB
 * 시간: 892 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * DP로 해결. 너무 복잡하게 코드 짠 듯한 감이 있다.
 *
 * dp[0][i]는 i번 연산한 후 건너뛰지 않았을 때의 최댓값, dp[1][i]는 i번 연산한 후 건너뛰었을 때의 최댓값이다.
 * 마지막 dp[0][N]과 dp[1][N] 중 더 큰 값을 반환한다. 0 이하이면 ddong game을 출력.
 *
 * 공간 복잡도 O(1) 풀이가 존재했다. 따로 DP를 전부 저장할 필요 없이 전까지 계산 값만 가지고 있어도 충분했다. (메모이제이션, 슬라이딩 윈도우)
 * https://my-coding-notes.tistory.com/423
 */

import java.util.*;
import java.util.stream.*;
import java.util.function.BiFunction;

public class p2_ZZAMBAs {
    static int N;
    static int[][] dp;
    static Pair<Operator, Integer>[][] op;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new int[2][N + 1];
        op = new Pair[2][N];

        IntStream.range(0, N).forEach(i -> {
            for (int j = 0; j < 2; j++) {
                String s = sc.next();
                Operator nowOp = null;

                switch (s.charAt(0)) {
                    case '+' : nowOp = Operator.PLUS; break;
                    case '-' : nowOp = Operator.MINUS; break;
                    case '*' : nowOp = Operator.MULTIPLY; break;
                    case '/' : nowOp = Operator.DIVIDE; break;
                }

                op[j][i] = new Pair<>(nowOp, s.charAt(1) - 48);
            }
        });
        dp[0][0] = 1;

        fdp();

        int res = Math.max(dp[0][N], dp[1][N]);

        System.out.println(res <= 0 ? "ddong game" : res);
    }

    static void fdp() {
        IntStream.range(0, N).forEach(i -> {
            if (i != 0 && dp[1][i] > 0) {
                dp[1][i + 1] = Math.max(
                    op[0][i].first.cal(dp[1][i], op[0][i].second),
                    op[1][i].first.cal(dp[1][i], op[1][i].second)
                );
            }

            if (dp[0][i] > 0) {
                dp[0][i + 1] = Math.max(
                    op[0][i].first.cal(dp[0][i], op[0][i].second),
                    op[1][i].first.cal(dp[0][i], op[1][i].second)
                );
                dp[1][i + 1] = Math.max(dp[1][i + 1], dp[0][i]);
            }
        });
    }

    enum Operator {
        PLUS((num1, num2) -> num1 + num2),
        MINUS((num1, num2) -> num1 - num2),
        MULTIPLY((num1, num2) -> num1 * num2),
        DIVIDE((num1, num2) -> num1 / num2);

        private BiFunction<Integer, Integer, Integer> fn;

        Operator(BiFunction<Integer, Integer, Integer> fn) {
            this.fn = fn;
        }

        public int cal(int num1, int num2) {
            return fn.apply(num1, num2);
        }
    }

    static class Pair<T, R> {
        T first;
        R second;

        Pair(T first, R second) {
            this.first = first;
            this.second = second;
        }
    }
}
