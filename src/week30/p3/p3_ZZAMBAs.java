package src.week30.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/21609
 * 메모리: 375844 KB
 * 시간: 1856 ms
 * 시간 복잡도: O(NM)
 * 공간 복잡도: O(NM)
 */

/**
 * bfs
 *
 * 벽 부수고 이동하기와 완전히 동일한 문제.
 * 기존 BFS의 방문 배열을 벽 부순 경우랑 안 부순 경우 두 개로 나누어서 해결.
 */

import java.util.*;
import java.util.stream.*;

public class p3_ZZAMBAs{
    static final int MAXIMUM_BREAK_COUNT = 1;
    static final int WALL = 1;

    static final Scanner sc = new Scanner(System.in);
    static final int[][] dl = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
    static int N, M, Hx, Hy, Ex, Ey;
    static int[][] board;
    static boolean[][][] visited;

    public static void main(String[] args) {
        N = sc.nextInt(); M = sc.nextInt();
        Hx = sc.nextInt() - 1; Hy = sc.nextInt() - 1;
        Ex = sc.nextInt() - 1; Ey = sc.nextInt() - 1;
        board = new int[N][M];
        visited = new boolean[N][M][2];
        IntStream.range(0, N).forEach(i -> IntStream.range(0, M).forEach(j -> board[i][j] = sc.nextInt()));

        System.out.print(bfs());
    }

    static int bfs() {
        int ret = 0;
        Queue<Pair<Pair<Integer, Integer>, Integer>> q = new ArrayDeque<>();
        q.add(new Pair<>(new Pair<>(Hx, Hy), 0));
        visited[Hx][Hy][0] = true;

        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Pair<Pair<Integer, Integer>, Integer> curP = q.poll();
                Pair<Integer, Integer> coor = curP.t;
                int breakCnt = curP.r;

                if (coor.t == Ex && coor.r == Ey)
                    return ret;

                for (int j = 0; j < dl.length; j++) {
                    int nextR = coor.t + dl[j][0];
                    int nextC = coor.r + dl[j][1];

                    if (!inRange(nextR, nextC))
                        continue;

                    if (board[nextR][nextC] != WALL && !visited[nextR][nextC][breakCnt]) {
                        visited[nextR][nextC][breakCnt] = true;
                        q.add(new Pair<>(new Pair<>(nextR, nextC), breakCnt));
                    }

                    if (board[nextR][nextC] == WALL && breakCnt < MAXIMUM_BREAK_COUNT && !visited[nextR][nextC][breakCnt + 1]) {
                        visited[nextR][nextC][1] = true;
                        q.add(new Pair<>(new Pair<>(nextR, nextC), breakCnt + 1));
                    }
                }
            }

            ret++;
        }

        return -1;
    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    static class Pair<T, R> {
        T t;
        R r;

        Pair (T t, R r) {
            this.t = t;
            this.r = r;
        }
    }
}

