/**
 * 문제 링크: https://www.acmicpc.net/problem/2591
 * 메모리: 14264 KB
 * 시간: 100 ms
 * 시간 복잡도: O(N) (= N은 40 이하)
 * 공간 복잡도: O(N) (= N은 40 이하)
 */

/**
 * DP
 *
 * 두 자리수 카드가 있는 것도 고려해서, 인덱스 범위와 점화식을 세울 때 해당 카드의 길이를 뺀 dp[i]를 더해주는 것이 중요
 *
 * dp[i] = i번째 자리까지의 카드 숫자를 만들 수 있는 경우의 수
 * 1. 숫자 자리수 까지 반복문 돌면서
 * 2. 1부터 34까지 카드 숫자와 일치하는지 확인
 * 3. 일치하면 경우의 수 추가
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumberCard {

    private static final int MAX_CARD_NUMBER = 34;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(solution(bufferedReader.readLine()));
    }

    private static int solution(String input) {
        int[] dp = new int[input.length() + 1];
        dp[0] = 1; // 아무 것도 없는 상태 = 1가지 경우의 수

        for (int i = 1; i <= input.length(); i++) { // i번째 자리까지 반복
            for (int num = 1; num <= MAX_CARD_NUMBER; num++) { // 카드 숫자는 1부터 34까지
                String cardNumber = String.valueOf(num);
                if (isMatched(input, i, cardNumber)) { // 숫자와 일치하면
                    dp[i] += dp[i - cardNumber.length()]; // 경우의 수 추가
                }
            }
        }

        return dp[input.length()];
    }

    private static boolean isMatched(String input, int index, String cardNumber) {
        if (index - cardNumber.length() > input.length() ||
                index < cardNumber.length()) {
            return false; // 인덱스 범위를 벗어나면 false
        }
        String subString = input.substring(index - cardNumber.length(), index); // 인덱스에 해당하는 문자열

        return subString.equals(cardNumber); // 일치하면 true
    }
}
