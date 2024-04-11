package src.week19.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/3613
 * 메모리: 18116 KB
 * 시간: 216 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 파싱
 *
 * 문제에서 하란 대로. if-else 문 난사.
 * '_' == 95
 */

import java.util.*;

public class p2_ZZAMBAs{
    static final int UNDEFINED = 0;
    static final int C_PLUS_PLUS = 1;
    static final int JAVA = 2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int state = UNDEFINED;
        boolean errorSw = false;
        String variable = sc.next();
        StringBuilder sb = new StringBuilder();

        if (variable.charAt(0) < 97 || variable.charAt(variable.length() - 1) == '_') // 최초 오류 검사
            errorSw = true;

        for (int i = 0; i < variable.length() && !errorSw; i++) {
            char curChar = variable.charAt(i);

            if (curChar >= 97) // 소문자는 무조건 변환
                sb.append(curChar);
            else if (state == UNDEFINED) { // 타입이 아직 안 정해진 경우
                if (curChar >= 65 && curChar <= 90) {
                    state = JAVA;
                    i--;
                } else if (curChar == '_') {
                    state = C_PLUS_PLUS;
                    i--;
                }
            }
            else if (state == JAVA) { // 자바인 경우
                if (curChar >= 65 && curChar <= 90)
                    sb.append("_").append((char) (curChar + 32));
                else
                    errorSw = true;
            }
            else if (state == C_PLUS_PLUS) { // C++인 경우
                if (curChar == '_' && variable.charAt(i - 1) != '_' && variable.charAt(i + 1) >= 97) {
                    sb.append((char)(variable.charAt(i + 1) - 32));
                    i++;
                }
                else
                    errorSw = true;
            }
        }

        System.out.println(errorSw ? "Error!" : sb.toString());
    }
}
