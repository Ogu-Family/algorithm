package src.week32.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/27649
 * 메모리: 131268 KB
 * 시간: 908 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 파싱
 * 1. 구분자들을 미리 배열에 저장해둔다. 그리고 StringBuilder 하나를 선언한다.
 * 2. 문자열을 처음부터 읽어가면서 현재 위치가 구분자인지 아닌지 구분자 배열을 통해 검증한다.
 * 2-1. 구분자가 아니면 StringBuilder에 해당 문자를 넣는다.
 * 2-2 구분자이면 지금까지 StringBuilder로 만든 문자열을 결과 배열에 구분자와 함께 넣는다.
 * 3. 공백 구분자를 제외한 모든 문자열을 공백 단위로 구분하며 출력
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class p1_ZZAMBAs {
    static Scanner sc = new Scanner(System.in);
    static List<String> tokens = new ArrayList<>();
    static List<String> delimiters = List.of("<", ">", "&&", "||", "(", ")", " ");

    public static void main(String[] args) {
        String str = sc.nextLine();
        parsing(str);
        System.out.print(tokens.stream().filter(t -> !t.isBlank()).collect(Collectors.joining(" ")));
    }

    static void parsing(String str) {
        int idx = 0;
        int strSize = str.length();
        StringBuilder tokenBuilder = new StringBuilder();

        while (idx < strSize) {
            boolean isDelimeter = false;

            for (String delimiter : delimiters) {
                if (idx + delimiter.length() > strSize)
                    continue;

                isDelimeter = isDelimiter(str, delimiter, idx);

                if (isDelimeter) {
                    tokens.add(tokenBuilder.toString());
                    tokens.add(delimiter);
                    tokenBuilder = new StringBuilder();

                    idx += delimiter.length();
                    break;
                }
            }

            if (!isDelimeter)
                tokenBuilder.append(str.charAt(idx++));
        }

        tokens.add(tokenBuilder.toString());
    }

    static boolean isDelimiter(String str, String delimiter, int startIdx) {
        boolean ret = true;

        for (int j = 0; j < delimiter.length(); j++)
            if (str.charAt(startIdx + j) != delimiter.charAt(j)) {
                ret = false;
                break;
            }

        return ret;
    }
}
