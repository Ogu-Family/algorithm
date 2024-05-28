package src.week24.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/3987
 * 메모리: 85832 KB
 * 시간: 552 ms
 * 시간 복잡도: O(N * M)
 * 공간 복잡도: O(N * M)
 */

/**
 * 1. 좌하우상 순으로 탐색
 * 2. 탐색 중 블랙홀을 만나거나 맵을 벗어나면 탐색 종료
 * 3. / \ 인 경우 방향 변경
 * 4. 적당히 큰 수 설정해서 무한히 돌고 있는지 확인 (방문 횟수가 적당히 큰 수보다 높으면 무한히 돌고 있는 것)
 * 5. 최대 시간과 방향을 저장하여 반환
 */

import java.util.*;
import java.util.stream.*;

public class p3_ZZAMBAs{
    static int N, M, res = 1, resD;
    static String[] board;
    static int[][] d = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} }; // 상우하좌
    static int[] ds = {1, 0, 3, 2}, drs = {3, 2, 1, 0}; // /, \
    static char[] dName = {'U', 'R', 'D', 'L' };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        board = new String[N];
        IntStream.range(0, N).forEach(i -> board[i] = sc.next());

        bfs(sc.nextInt() - 1, sc.nextInt() - 1);

        System.out.println(dName[resD]);
        System.out.print(res > 750 * 750 ? "Voyager" : res);
    }

    static void bfs(int r, int c) {
        if (board[r].charAt(c) == 'C') {
            res = 0;
            resD = 0;
            return;
        }

        Queue<Quad<Integer, Integer, Integer, Integer>> q = new ArrayDeque<>();
        IntStream.range(0, 4).forEach(i -> addNextLoc(r, c, 3 - i, 3 - i, q));

        while (!q.isEmpty() && res <= 750 * 750) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Quad<Integer, Integer, Integer, Integer> front = q.poll();
                int curR = front.t, curC = front.r, curD = front.u, initD = front.w;

                if(!addNextLoc(curR, curC, curD, initD, q))
                    resD = initD;
            }

            res++;
        }

        while (!q.isEmpty())
            resD = q.poll().w;
    }

    static boolean addNextLoc(int curR, int curC, int curD, int initD,
        Queue<Quad<Integer, Integer, Integer, Integer>> q) {
        int nextR = curR + d[curD][0], nextC = curC + d[curD][1];
        if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < M && board[nextR].charAt(nextC) != 'C') {
            if (board[nextR].charAt(nextC) == '/')
                curD = ds[curD];
            else if (board[nextR].charAt(nextC) == '\\')
                curD = drs[curD];

            q.add(new Quad<>(nextR, nextC, curD, initD));

            return true;
        }

        return false;
    }

    static class Quad<T, R, U, W> {
        T t; // row
        R r; // col
        U u; // current direction
        W w; // initial direction

        Quad(T t, R r, U u, W w) {
            this.t = t;
            this.r = r;
            this.u = u;
            this.w = w;
        }
    }
}
