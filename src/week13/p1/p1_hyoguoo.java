/**
 * 문제 링크: https://www.acmicpc.net/problem/16139
 * 메모리: 119640 KB
 * 시간: 856 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2)
 */

/**
 * 누적합
 * [문자열 길이 + 1][알파벳의 수, 26] 크기로 생성
 *
 * 1. 누적합 배열 생성
 * 2. 구간별로 누적합을 이용하여 계산
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanComputerInteraction {

    static final int ASCII_SMALL_A = 97;
    static final int ALPHABET_COUNT = 26;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        int questionCount = Integer.parseInt(bufferedReader.readLine());

        Query[] queries = new Query[questionCount];

        for (int i = 0; i < questionCount; i++) {
            String[] info = bufferedReader.readLine().split(" ");
            queries[i] = new Query(
                    info[0].charAt(0),
                    Integer.parseInt(info[1]),
                    Integer.parseInt(info[2])
            );
        }

        System.out.print(solution(input, queries));
    }

    private static String solution(String input, Query[] queries) {
        StringBuilder stringBuilder = new StringBuilder();

        int[][] alphabetCount = new int[input.length() + 1][ALPHABET_COUNT];

        // 누적합 배열 생성
        for (int i = 0; i < input.length(); i++) {
            System.arraycopy(alphabetCount[i], 0, alphabetCount[i + 1], 0, ALPHABET_COUNT);
            alphabetCount[i + 1][input.charAt(i) - ASCII_SMALL_A]++;
        }

        // 구간별로 누적합을 이용하여 계산
        for (Query query : queries) {
            int count = alphabetCount[query.end + 1][query.target - ASCII_SMALL_A]
                    - alphabetCount[query.start][query.target - ASCII_SMALL_A];

            stringBuilder.append(count).append("\n");
        }

        return stringBuilder.toString().trim();
    }

    static class Query {

        char target;
        int start;
        int end;

        public Query(char target, int start, int end) {
            this.target = target;
            this.start = start;
            this.end = end;
        }
    }
}
