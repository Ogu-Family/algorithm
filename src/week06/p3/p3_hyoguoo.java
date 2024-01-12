/**
 * 문제 링크: https://www.acmicpc.net/problem/2665
 * 메모리: 16348 KB
 * 시간: 180 ms
 * 시간 복잡도: O(N long N)
 * 공간 복잡도: O(N^2)
 */

/**
 * BFS
 *
 * 일반적인 미로 탐색 문제와는 다르게 방문 여부를 체크하는 것이 아니라 방을 변경한 횟수를 기준으로 체크
 * 방 변경 횟수가 더 적은 곳을 방문한 경우에만 큐에 넣어주어 탐색하도록 함
 *
 * 만약 방문한 지역이 변경할 필요가 없다면 방 변경 횟수가 그대로 유지되고,
 * 변경할 필요가 있다면 방 변경 횟수가 1 증가시키면서 탐색을 진행
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CreateMaze {

    static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static final int WALL = 0;
    static final int ROAD = 1;
    static final int START_N = 0;
    static final int START_M = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(bufferedReader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(board));
    }

    private static int solution(int[][] board) {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(START_N, START_M));

        int[][] breakCount = new int[board.length][board[0].length]; // 방 변경 횟수를 저장하는 배열
        for (int[] row : breakCount) {
            Arrays.fill(row, Integer.MAX_VALUE); // 최대값으로 초기화
        }

        breakCount[START_N][START_M] = 0; // 시작점은 방 변경 횟수가 0

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            for (int[] direction : DIRECTIONS) {
                int nextN = current.n + direction[0];
                int nextM = current.m + direction[1];

                if (!isInBound(nextN, nextM, board) || // 범위를 벗어나거나
                    breakCount[nextN][nextM] <= breakCount[current.n][current.m]) continue; // 이미 방 변경 횟수가 더 적은 곳을 방문한 경우 skip

                // 다음 방이 ROAD인 경우는 방 변경 횟수가 그대로
                if (board[nextN][nextM] == ROAD) breakCount[nextN][nextM] = breakCount[current.n][current.m];
                // 다음 방이 WALL인 경우는 방 변경 횟수가 1 증가
                else if (board[nextN][nextM] == WALL) breakCount[nextN][nextM] = breakCount[current.n][current.m] + 1;
                queue.add(new Coordinate(nextN, nextM));
            }
        }

        return breakCount[board.length - 1][board[0].length - 1];
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
