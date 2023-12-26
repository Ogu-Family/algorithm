/**
 * 문제 링크: https://www.acmicpc.net/problem/2606
 * 메모리: 14700 KB
 * 시간: 144 ms
 * 시간 복잡도: O(1)
 * 공간 복잡도: O(1)
 */

/**
 * 단순 구현 알고리즘
 * 각 입력 방향에 따라 조금씩 다른 순서의 반복문을 사용하여 구현
 *
 * 2147483648이 int로 표현되지 않기 때문에 long 사용!!
 * Enum으로 무언가 해보려 했으나 실패하였고.. 지저분하게 분기 처리하여 풀이하게 되었습니다..
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Game2147483648 {

    static final int N = 8;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        long[][] board = new long[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        }

        Direction direction = Direction.of(bufferedReader.readLine().charAt(0));

        solution(board, direction);
    }

    private static void solution(long[][] board, Direction direction) {
        switch (direction) {
            case UP:
                for (int m = 0; m < N; m++) {
                    long[] column = new long[N];
                    int index = 0;
                    for (int n = 0; n < N; n++) {
                        if (board[n][m] != 0) {
                            column[index++] = board[n][m];
                        }
                    }
                    for (int n = 0; n < N - 1; n++) {
                        if (column[n] == column[n + 1]) {
                            column[n] *= 2;
                            column[n + 1] = 0;
                        }
                    }
                    index = 0;
                    for (int n = 0; n < N; n++) {
                        if (column[n] != 0) {
                            board[index++][m] = column[n];
                        }
                    }
                    for (int n = index; n < N; n++) {
                        board[n][m] = 0;
                    }
                }
                break;
            case DOWN:
                for (int m = 0; m < N; m++) {
                    long[] column = new long[N];
                    int index = N - 1;
                    for (int n = N - 1; n >= 0; n--) {
                        if (board[n][m] != 0) {
                            column[index--] = board[n][m];
                        }
                    }
                    for (int n = N - 1; n > 0; n--) {
                        if (column[n] == column[n - 1]) {
                            column[n] *= 2;
                            column[n - 1] = 0;
                        }
                    }
                    index = N - 1;
                    for (int n = N - 1; n >= 0; n--) {
                        if (column[n] != 0) {
                            board[index--][m] = column[n];
                        }
                    }
                    for (int n = index; n >= 0; n--) {
                        board[n][m] = 0;
                    }
                }
                break;
            case LEFT:
                for (int n = 0; n < N; n++) {
                    long[] row = new long[N];
                    int index = 0;
                    for (int m = 0; m < N; m++) {
                        if (board[n][m] != 0) {
                            row[index++] = board[n][m];
                        }
                    }
                    for (int m = 0; m < N - 1; m++) {
                        if (row[m] == row[m + 1]) {
                            row[m] *= 2;
                            row[m + 1] = 0;
                        }
                    }
                    index = 0;
                    for (int m = 0; m < N; m++) {
                        if (row[m] != 0) {
                            board[n][index++] = row[m];
                        }
                    }
                    for (int m = index; m < N; m++) {
                        board[n][m] = 0;
                    }
                }
                break;
            case RIGHT:
                for (int n = 0; n < N; n++) {
                    long[] row = new long[N];
                    int index = N - 1;
                    for (int m = N - 1; m >= 0; m--) {
                        if (board[n][m] != 0) {
                            row[index--] = board[n][m];
                        }
                    }
                    for (int m = N - 1; m > 0; m--) {
                        if (row[m] == row[m - 1]) {
                            row[m] *= 2;
                            row[m - 1] = 0;
                        }
                    }
                    index = N - 1;
                    for (int m = N - 1; m >= 0; m--) {
                        if (row[m] != 0) {
                            board[n][index--] = row[m];
                        }
                    }
                    for (int m = index; m >= 0; m--) {
                        board[n][m] = 0;
                    }
                }
                break;
        }

        printResult(board);
    }

    private static void printResult(long[][] board) {
        StringBuilder stringBuilder = new StringBuilder();

        for (long[] row : board) {
            for (long value : row) {
                stringBuilder.append(value).append(" ");
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder);
    }

    enum Direction {
        UP('U'), DOWN('D'), LEFT('L'), RIGHT('R');

        final char value;

        Direction(char value) {
            this.value = value;
        }

        static Direction of(char direction) {
            return Arrays.stream(Direction.values())
                    .filter(value -> value.value == direction)
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }
}
