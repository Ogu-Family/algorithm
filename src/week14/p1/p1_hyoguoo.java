/**
 * 문제 링크: https://www.acmicpc.net/problem/1992
 * 메모리: 17980 KB
 * 시간: 192 ms
 * 시간 복잡도: O(logN)
 * 공간 복잡도: O(N^2)
 */

/**
 * 분할 정복 알고리즘 문제
 *
 * 길이를 2로 나누어 4개의 영역으로 나누고, 각 영역에 대해 같은 숫자로 이루어져 있는지 확인
 * 만약 같은 숫자로 이루어져 있지 않다면 다시 4개의 영역으로 나누어 확인
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class QuadTree {

    static final char OPEN_PARENTHESIS = '(';
    static final char CLOSE_PARENTHESIS = ')';
    static final StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        char[][] matrix = new char[n][n];

        for (int i = 0; i < n; i++) {
            matrix[i] = bufferedReader.readLine().toCharArray();
        }

        solution(matrix, 0, 0, n);
        System.out.println(stringBuilder);
    }

    private static void solution(char[][] matrix, int n, int m, int length) {
        recursion(matrix, n, m, length);
    }

    private static void recursion(char[][] matrix, int n, int m, int length) {
        if (checkIsCompleted(matrix, n, m, length)) {
            stringBuilder.append(matrix[n][m]);
            return;
        }

        length /= 2;
        stringBuilder.append(OPEN_PARENTHESIS);
        if (length == 0) {
            solution(matrix, n, m, length);
        }

        solution(matrix, n, m, length);
        solution(matrix, n, m + length, length);
        solution(matrix, n + length, m, length);
        solution(matrix, n + length, m + length, length);

        stringBuilder.append(CLOSE_PARENTHESIS);
    }

    private static boolean checkIsCompleted(char[][] matrix, int n, int m, int length) {
        char reference = matrix[n][m];

        return IntStream.range(n, n + length)
                .allMatch(i -> IntStream.range(m, m + length)
                        .allMatch(j -> matrix[i][j] == reference));
    }
}
