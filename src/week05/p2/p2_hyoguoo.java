/**
 * 문제 링크: https://www.acmicpc.net/problem/7576
 * 메모리: 146052 KB
 * 시간: 880 ms
 * 시간 복잡도: O(N * M)
 * 공간 복잡도: O(N * M)
 */

/**
 * BFS 문제
 *
 * 익은 토마토를 큐에 넣고, 상하좌우로 토마토가 익도록 함
 * 익은 날은 기존 토마토의 날짜 + 1로 저장하면서 기존 입력 받은 이차원 배열에 갱신하는 방식으로 풀이
 * 0: 아직 익지 않은 토마토
 * -1: 토마토가 들어있지 않은 칸
 * 1 이상의 정수: 토마토가 익은 날짜 + 1(1부터 시작)
 * 큐가 빌 때까지 반복 후,
 * 익지 않은 토마토가 있는 경우(0이 있는 경우) -1을,
 * 모든 토마토가 익은 경우 최대 날짜를 출력함(최대 날짜는 기존 토마토가 익은 날짜 + 1이므로 -1을 해줌)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Tomato {

    static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static final int EXIST = 0;
    static final int RIPEN = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = info[0];
        int n = info[1];
        int[][] board = new int[n][m];

        Queue<Coordinate> ripenQueue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < m; j++) {
                if (input[j] == RIPEN) ripenQueue.add(new Coordinate(i, j));
                board[i][j] = input[j];
            }
        }

        System.out.println(solution(board, ripenQueue));
    }

    private static int solution(int[][] board, Queue<Coordinate> ripenQueue) {
        while (!ripenQueue.isEmpty()) {
            Coordinate current = ripenQueue.poll();

            for (int[] direction : DIRECTIONS) {
                int nextN = current.n + direction[0];
                int nextM = current.m + direction[1];

                if (!isInBound(nextN, nextM, board) ||
                    board[nextN][nextM] != EXIST) continue;

                board[nextN][nextM] = board[current.n][current.m] + 1;
                ripenQueue.add(new Coordinate(nextN, nextM));
            }
        }

        return getMaxDay(board);
    }

    private static int getMaxDay(int[][] board) {
        int max = 0;

        for (int[] row : board) {
            for (int tomato : row) {
                if (tomato == EXIST) return -1;
                max = Math.max(max, tomato);
            }
        }

        return max - 1;
    }

    private static boolean isInBound(int n, int m, int[][] board) {
        return 0 <= n && n < board.length && 0 <= m && m < board[0].length;
    }

    static class Coordinate {
        int n;
        int m;

        public Coordinate(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }
}
