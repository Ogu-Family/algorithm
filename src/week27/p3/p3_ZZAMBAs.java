package src.week27.p3;

import java.util.*;
import java.util.stream.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/21609
 * 메모리: 85772 KB
 * 시간: 464 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2)
 */

/**
 * bfs + 구현
 * 하란 대로 구현했다.
 *
 * 반시계 회전: (a, b) -> (N - 1 - b, a), 시계 회전: (a, b) -> (b, N - 1 - a) (정사각형, 직사각형 동일)
 * https://velog.io/@danbibibi/2%EC%B0%A8%EC%9B%90-%EB%B0%B0%EC%97%B4%EC%97%90%EC%84%9C-90%EB%8F%84-%ED%9A%8C%EC%A0%84-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
 */

public class p3_ZZAMBAs{
    static Scanner sc = new Scanner(System.in);
    static int[][] board;
    static final int EMPTY = -100, BLACK = -1, RAINBOW = 0;
    static int[][] dl = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
    static int res, targetRow, targetCol, targetSize, targetRainbowCnt, N, M;

    // 기준 블록 개수 >= 2
    // 블록 그룹 선택 기준: 크기 -> 무지개 블록 수 -> targetRow 큰 순 -> targetCol 큰 순

    public static void main(String[] args) {
        N = sc.nextInt(); M = sc.nextInt();
        board = new int[N][N];
        IntStream.range(0, N).forEach(i -> IntStream.range(0, N).forEach(j -> board[i][j] = sc.nextInt()));

        while (existsTarget()) { // 제거할 타겟이 있는지 확인하고 있으면 계속 반복
            removeGroup(targetRow, targetCol, board[targetRow][targetCol]); // 타겟 그룹 제거
            receiveGravity(); // 중력 작용
            rotate(); // 반시계 회전
            receiveGravity(); // 중력 작용

            res += targetSize * targetSize;
        }

        System.out.print(res);
    }

    static boolean existsTarget() {
        targetRow = targetCol = targetSize = targetRainbowCnt = -1;
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                IntStream.range(0, N).forEach(k -> IntStream.range(0, N).forEach(l -> {
                    if (board[k][l] == RAINBOW)
                        visited[k][l] = false;
                }));

                if (visited[i][j] || board[i][j] == BLACK || board[i][j] == RAINBOW || board[i][j] == EMPTY)
                    continue;

                findTarget(i, j, visited);
            }
        }

        return targetRow != -1;
    }

    static void findTarget(int r, int c, boolean[][] visited) {
        Queue<Pair> q = new ArrayDeque<>();
        int size = 0, rainbowCnt = 0;
        q.add(new Pair(r, c));
        visited[r][c] = true;

        while (!q.isEmpty()) {
            Pair curP = q.poll();
            int curR = curP.a;
            int curC = curP.b;

            size++;
            if (board[curR][curC] == RAINBOW)
                rainbowCnt++;

            for (int i = 0; i < dl.length; i++) {
                int nextR = curR + dl[i][0];
                int nextC = curC + dl[i][1];

                if (inRange(nextR, nextC) && !visited[nextR][nextC] && (board[nextR][nextC] == board[r][c] || board[nextR][nextC] == RAINBOW)){
                    q.add(new Pair(nextR, nextC));
                    visited[nextR][nextC] = true;
                }

            }
        }

        if (!canBeTarget(r, c, size, rainbowCnt))
            return;

        targetRow = r;
        targetCol = c;
        targetSize = size;
        targetRainbowCnt = rainbowCnt;

    }

    static boolean canBeTarget(int r, int c, int size, int rainbowCnt) {
        if (size < Math.max(2, targetSize))
            return false;

        if (size > Math.max(1, targetSize))
            return true;

        if (rainbowCnt < targetRainbowCnt)
            return false;

        if (rainbowCnt > targetRainbowCnt)
            return true;

        if (targetRow < r)
            return true;

        if (targetRow > r)
            return false;

        if (targetCol < c)
            return true;

        return false;
    }

    static void removeGroup(int r, int c, int initColor) {
        board[r][c] = EMPTY;

        for (int i = 0; i < 4; i++) {
            int nextR = r + dl[i][0];
            int nextC = c + dl[i][1];

            if (inRange(nextR, nextC) && (board[nextR][nextC] == initColor || board[nextR][nextC] == RAINBOW))
                removeGroup(nextR, nextC, initColor);
        }
    }

    static void receiveGravity() {
        int[][] temp = new int[N][N];
        IntStream.range(0, N).forEach(i -> Arrays.fill(temp[i], EMPTY));

        for (int col = 0; col < N; col++) {
            int tempPointer = N - 1;

            for (int rowPointer = N - 1; rowPointer >= 0; rowPointer--) {
                if (board[rowPointer][col] == BLACK) {
                    temp[rowPointer][col] = BLACK;
                    tempPointer = rowPointer - 1;
                } else if (board[rowPointer][col] != EMPTY) {
                    temp[tempPointer--][col] = board[rowPointer][col];
                }
            }
        }

        IntStream.range(0, N).forEach(i -> IntStream.range(0, N).forEach(j -> board[i][j] = temp[i][j]));
    }

    static void rotate() {
        int[][] temp = new int[N][N];
        IntStream.range(0, N).forEach(i -> IntStream.range(0, N).forEach(j -> temp[N - 1 - j][i] = board[i][j]));
        IntStream.range(0, N).forEach(i -> IntStream.range(0, N).forEach(j -> board[i][j] = temp[i][j]));
    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static class Pair {
        int a;
        int b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
