package src.week06.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2665
 * 메모리: 17912 KB
 * 시간: 224 ms
 * 시간 복잡도: O(nlogn)
 * 공간 복잡도: O(n^2)
 */

/**
 * 다익스트라
 * 검은 방과 연결된 간선을 가중치가 1인 간선으로 보면 된다.
 *
 * 문제에서 중요한 부분
 * - 검은 방에서 흰 방으로 바꾸어야 할 최소의 수를 구하는 프로그램을 작성하시오.
 */

import java.util.Scanner;
import java.util.PriorityQueue;

public class p3_ZZAMBAs{
    static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
    static String[] maze;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        maze = new String[n];
        for (int i = 0; i < n; i++)
            maze[i] = sc.next();

        System.out.println(dijkstra(n));
    }

    static int dijkstra(int n) {
        boolean[][] isVisited = new boolean[n][n];
        PriorityQueue<Triple> pq = new PriorityQueue<>();
        pq.add(new Triple(0, 0, 0));

        while(!pq.isEmpty()) {
            Triple curTriple = pq.poll();

            if (curTriple.r == n - 1 && curTriple.c == n - 1)
                return curTriple.w;

            for(int i = 0; i < 4; i++) {
                int nextR = curTriple.r + dr[i];
                int nextC = curTriple.c + dc[i];

                if(nextR >= 0 && nextR < n && nextC >= 0 && nextC < n && !isVisited[nextR][nextC]) {
                    isVisited[nextR][nextC] = true;
                    int plusW = maze[nextR].charAt(nextC) == '0' ? 1 : 0;

                    pq.add(new Triple(curTriple.w + plusW, nextR, nextC));
                }

            }
        }

        return -1;
    }

    static class Triple implements Comparable<Triple>{
        public int w, r, c;

        Triple(int w, int r, int c) {
            this.w = w;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Triple other) {
            return this.w - other.w;
        }
    }
}
