package algorithm.src.week21.p3;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1647
 * 메모리: 328136 KB
 * 시간: 1672 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(M)
 */

/**
 * 최소 스패닝 트리 문제
 * 크루스칼 알고리즘 사용
 * 풀이 참고한 문제
 * 최소 값으로 모든 정점들이 연결되고, 사이클을 포함해서는 안되는 최소 스패닝 트리 문제
 * 모두 연결 후 마지막에 연결한 길을 제외하면 마을을 2개로 나눌 수 있으므로 간선을 N-1개가 아닌 N-2개 선택할 때 까지 아래 과정을 반복한다.
 * 1. 간선 연결 비용 기준 오름차순 정렬
 * 2. 순서대로, 선택하며 두 정점 연결 시 사이클을 만들지 않는 경우 선택 후 연결
 */

public class p3_JeongeunChoi {

    private static int N, M, answer = 0, cnt = 0;
    private static ArrayList<Edge> edges;
    private static int[] parent;

    private static int getParent(int i) {
        if (parent[i] == i) {
            return i;
        } else {
            return getParent(parent[i]);
        }
    }

    private static void setParent(int s, int e) {
        s = getParent(s);
        e = getParent(e);
        if (s > e) {
            parent[e] = s;
        } else if {
            parent[s] = e;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        parent = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }
        Collections.sort(edges);

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (cnt == N - 2) {
                break;
            }
            if (getParent(edge.s) != getParent(edge.e)) {
                answer += edge.w;
                setParent(edge.s, edge.e);
                cnt++;
            }
        }

        System.out.println(answer);
    }

    public static class Edge implements Comparable<Edge> {

        int s, e, w;

        Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge other) {
            return this.w - other.w;
        }
    }
}
