/**
 * 문제 링크: https://www.acmicpc.net/problem/27649 메모리: 38976 KB 시간: 568 ms 시간 복잡도: O(N) 공간 복잡도: O(N)
 */

/**
 * 구분자를 만나기 전 까지 저장하는 currentToken을 만들어 구분자를 만나면 result에 추가하는 방식으로 토큰화
 * &와 |는 2개 연속으로 나오므로 이를 고려하여 구현
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tokenizer {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(solution(bufferedReader.readLine()));
    }

    public static String solution(String command) {
        StringBuilder result = new StringBuilder();
        StringBuilder currentToken = new StringBuilder();

        int index = 0;
        // 입력 받은 문자열 순서대로 탐색
        while (index < command.length()) {
            char c = command.charAt(index);
            // 토큰을 만들기 위한 조건문 1(단일)
            if (c == '<' || c == '>' || c == '(' || c == ')') {
                // currentToken에 저장된 문자열을 result에 추가
                appendToken(currentToken, result);
                // 구분자 추가
                result.append(c).append(" ");
                // 토큰을 만들기 위한 조건문 2(2개 연속)
            } else if ((c == '&' || c == '|') &&
                    index + 1 < command.length() &&
                    command.charAt(index + 1) == c) {
                appendToken(currentToken, result);
                // 구분자 추가
                result.append(c).append(c).append(" ");
                // 2개 연속이므로 index를 1 증가
                index++;
                // 토큰을 만들기 위한 조건문 3(공백)
            } else if (c == ' ') {
                // currentToken에 저장된 문자열을 result에 추가
                appendToken(currentToken, result);
            } else {
                // currentToken에 문자 추가
                currentToken.append(c);
            }

            index++;
        }

        // 남아있는 currentToken이 있다면 result에 추가
        if (currentToken.length() > 0) {
            result.append(currentToken.toString().trim());
        }

        return result.toString();
    }

    private static void appendToken(StringBuilder currentToken, StringBuilder result) {
        if (currentToken.length() > 0) {
            result.append(currentToken.toString().trim()).append(" ");
            currentToken.delete(0, currentToken.length());
        }
    }
}
