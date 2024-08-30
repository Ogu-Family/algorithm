package src.week37.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/14677
 * 메모리: 20012 KB
 * 시간: 180 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2)
 */

/**
 * BFS
 *
 * 방문 체크를 어떻게 해야할 지 상당한 고민을 들였습니다.
 * 약 봉지 앞 인덱스, 끝 인덱스를 기준으로 방문 체크를 합니다. visited가 그 역할을 합니다.
 * 앞 약을 먹으면 앞 인덱스가 + 1 되고, 뒤 약을 먹으면 뒤 인덱스가 - 1 됩니다. 각 인덱스를 s, e라고 한다면
 * visited[s][e] == true 면 그러한 약 순서를 먹은 적 있다는 뜻이므로 탐색을 중단합니다.
 * 반복해서 최대로 먹을 수 있는 약 개수를 출력합니다.
 */

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class p3_ZZAMBAs {
    static final Scanner sc = new Scanner(System.in);
    static final char[] medicine = { 'B', 'L', 'D' };

    static String s;
    static boolean[][] visited;
    static int N, res;

    public static void main(String[] args) {
        N = sc.nextInt();
        s = sc.next();
        visited = new boolean[3 * N + 1][3 * N + 1];

        bfs();

        System.out.print(res);
    }

    static void bfs() {
        int count = 0;
        int sIdx = 0;
        visited[0][N - 1] = true;

        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(0, 3 * N - 1));

        while (!q.isEmpty() && count != 3 * N) {
            int qSize = q.size();

            for (int i = 0; i < qSize; i++) {
                char curM = medicine[sIdx % 3];
                Pair curP = q.poll();

                if (s.charAt(curP.l) == curM && !visited[curP.l + 1][curP.r]) {
                    visited[curP.l + 1][curP.r] = true;
                    q.add(new Pair(curP.l + 1, curP.r));
                }

                if (s.charAt(curP.r) == curM && !visited[curP.l][curP.r - 1]) {
                    visited[curP.l][curP.r - 1] = true;
                    q.add(new Pair(curP.l, curP.r - 1));
                }
            }

            if (q.isEmpty())
                break;

            sIdx++;
            count++;
        }

        res = count;
    }

    static class Pair {
        int l;
        int r;

        Pair (int l, int r) {
            this.l = l;
            this.r = r;
        }
    }
}
