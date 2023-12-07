package algorithm.src.week01.p1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class p1_JeongeunChoi {
    static int n;
    static int[][] computerMatrix;
    static boolean[] visited;
    static int virusCnt = 0;
    static Queue<Integer> queue = new LinkedList<>();

    public static void spreadVirus(int start) {
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            Integer virus = queue.poll();
            for (int i = 1; i <= n; i++) {
                if (!visited[i] && computerMatrix[virus][i] == 1) {
                    virusCnt++;
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int pairCnt = sc.nextInt();

        computerMatrix = new int[n + 1][n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < pairCnt; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            computerMatrix[v1][v2] = 1;
            computerMatrix[v2][v1] = 1;
        }
        spreadVirus(1);

        System.out.println(virusCnt);
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/2606
 * 메모리: 17808 KB
 * 시간: 248 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2)
 */

/**
 * BFS로 구현
 * 인접 행렬을 통해, 그래프를 표현
 * 큐를 이용하여 현재 위치에서 갈 수 있는 정점을 모두 큐에 넣으며 바이러스를 전파
 * 이 때, 이미 감염된 바이러스는 visited 배열을 따로 두어 체크
 */