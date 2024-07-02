package algorithm.src.week30.p2;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/6497
 * 메모리: 236784 KB
 * 시간: 844 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 풀이 참고한 문제
 * 최소 스패닝 트리, 크루스칼 알고리즘
 * 1. 최소 가중치 간선 골라서
 * 2. 사이클이 없는 경우 해당 간선 선택
 * 3. 간선이 집의 개수 - 1이 될 때까지 반복
 */

public class p2_JeongeunChoi {

    private static int[] parent;

    private static int findRoot(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = findRoot(parent[x]);
    }

    private static void unionRoot(int x, int y) {
        x = findRoot(x);
        y = findRoot(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    private static int getWeightSumByKruskal(Edge[] edges, int houseCnt) {
        int weightSum = 0, selectedEdgeCnt = 0;

        for (int i = 0; i < edges.length; i++) {
            int s = edges[i].s, e = edges[i].e, w = edges[i].w;

            if (findRoot(s) == findRoot(e)) {
                continue;
            } else {
                weightSum += w;
                selectedEdgeCnt++;
                unionRoot(s, e);
            }

            if (selectedEdgeCnt == houseCnt - 1) {
                break;
            }
        }

        return weightSum;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int houseCnt = Integer.parseInt(st.nextToken()), wayCnt = Integer.parseInt(st.nextToken()), totalWeightSum = 0;
            if (houseCnt == 0 && wayCnt == 0) {
                break;
            }
            parent = new int[houseCnt];
            for (int i = 0; i < houseCnt; i++) {
                parent[i] = i;
            }
            Edge[] edges = new Edge[wayCnt];
            for (int i = 0; i < wayCnt; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken()), z = Integer.parseInt(st.nextToken());
                totalWeightSum += z;
                edges[i] = new Edge(x, y, z);
            }
            Arrays.sort(edges);

            System.out.println(totalWeightSum - getWeightSumByKruskal(edges, houseCnt));
        }

    }

    private static class Edge implements Comparable<Edge> {

        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        public int compareTo(Edge other) {
            return this.w - other.w;
        }
    }

}
