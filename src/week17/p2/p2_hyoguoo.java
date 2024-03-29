/**
 * 문제 링크: https://www.acmicpc.net/problem/23815
 * 메모리: 130280 KB
 * 시간: 892 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * Round: 라운드별 선택지 리스트 저장
 * Option: 값과 연산자 저장
 * Operation Enum을 이용하여 연산자 처리
 *
 * 1. 라운드별 선택지 리스트 생성
 * 2. 라운드별 선택지를 고름에 따라 최댓값을 계산
 * 3. - 혹은 / 연산자인 경우 SKIP_COUNT 만큼 이전 라운드의 최댓값을 현재 라운드에도 적용할 수 있도록 처리
 * 4. 최종 최댓값 반환
 *
 * 심심해서 SKIP COUNT도 동적으로 할당할 수 있게 짜보았습니닷 ㅎㅎ
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongBinaryOperator;

public class PoopGame {

    static final int INIT_SCORE = 1;
    static final int SKIP_COUNT = 1;
    static final int END_GAME_SCORE = 0;
    static final String GAME_OVER_MESSAGE = "ddong game";

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        List<Round> roundList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            roundList.add(new Round(bufferedReader.readLine()));
        }

        long result = solution(roundList);
        System.out.print(
                result <= END_GAME_SCORE
                        ? GAME_OVER_MESSAGE
                        : result
        );
    }

    private static long solution(List<Round> roundList) {
        long[][] dp = new long[SKIP_COUNT + 1][roundList.size() + 1];
        dp[0][0] = INIT_SCORE;

        for (int r = 1; r <= roundList.size(); r++) {
            List<Option> optionList = roundList.get(r - 1).optionList;

            for (Option option : optionList) {
                for (int s = 0; s < dp.length; s++) {
                    if (dp[s][r - 1] <= 0) {
                        continue;
                    }
                    dp[s][r] = Math.max(
                            dp[s][r],
                            option.operation.calculate(dp[s][r - 1], option.value)
                    );
                }

                if (option.operation == Operation.DIVIDE ||
                        option.operation == Operation.SUBTRACT) {
                    for (int j = 0; j < dp.length - 1; j++) {
                        dp[j + 1][r] = Math.max(
                                dp[j + 1][r],
                                dp[j][r - 1]
                        );
                    }
                }
            }
        }

        return getMaxScore(dp);
    }

    private static long getMaxScore(long[][] dp) {
        long maxScore = 0;

        for (long[] score : dp) {
            maxScore = Math.max(maxScore, score[score.length - 1]);
        }

        return maxScore;
    }

    enum Operation {
        ADD("+", (a, b) -> a + b),
        SUBTRACT("-", (a, b) -> a - b),
        MULTIPLY("*", (a, b) -> a * b),
        DIVIDE("/", (a, b) -> a / b);

        private final String operator;
        private final LongBinaryOperator operationFunction;

        Operation(String operator, LongBinaryOperator operationFunction) {
            this.operator = operator;
            this.operationFunction = operationFunction;
        }

        public static Operation fromString(String operator) {
            return Arrays.stream(values())
                    .filter(value -> value.operator.equals(operator))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid operator"));
        }

        public long calculate(long a, long b) {
            return operationFunction.applyAsLong(a, b);
        }
    }

    static class Round {

        List<Option> optionList;

        public Round(String optionRawString) {
            this.optionList = new ArrayList<>();
            String[] optionsStringArray = optionRawString.split(" ");

            for (String optionString : optionsStringArray) {
                String operator = optionString.substring(0, 1);
                int value = Integer.parseInt(optionString.substring(1));
                optionList.add(new Option(operator, value));
            }
        }
    }

    static class Option {

        Operation operation;
        int value;

        public Option(String operation, int value) {
            this.operation = Operation.fromString(operation);
            this.value = value;
        }
    }
}
