package src.week03.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p2_GiHoo {

    static int N;
    static int M;
    static int X;
    static int[] forward_distance;
    static int[] reverse_distance;
    static List<List<Node>> forward = new ArrayList<>(); // 정방향 최단 거리
    static List<List<Node>> reverse = new ArrayList<>(); // 역방향 최단 거리
    static int answer = Integer.MIN_VALUE;

    static void dijkstra(List<List<Node>> list, int[] distance, int start) {
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        distance[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            int idx = pq.poll().index;

            if (visited[idx]) {
                continue;
            }
            visited[idx] = true;

            for (Node node : list.get(idx)) {
                if (distance[node.index] > distance[idx] + node.weight) {
                    distance[node.index] = distance[idx] + node.weight;
                    pq.add(new Node(node.index, distance[node.index]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        forward_distance = new int[N + 1];
        reverse_distance = new int[N + 1];

        Arrays.fill(forward_distance, Integer.MAX_VALUE);
        Arrays.fill(reverse_distance, Integer.MAX_VALUE);

        for (int i = 0; i <= N; i++) {
            forward.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            forward.get(start).add(new Node(end, weight));
            reverse.get(end).add(new Node(start, weight));
        }

        dijkstra(forward, forward_distance, X);
        dijkstra(reverse, reverse_distance, X);

        for (int i = 1; i <= N; i++) {
            answer = Integer.max(answer, forward_distance[i] + reverse_distance[i]);
        }

        System.out.println(answer);
    }

    static class Node implements Comparable<Node> {

        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/1238
 * 메모리: 18196 KB
 * 시간: 212 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N^2)
 *
 * 맞는 풀이인지는 모르겠으나 제 풀이입니당.
 * 현재 학생들이 살고있는 마을에서 X 마을로 가는 정방향 distance
 * 그리고, X 마을에서 현재 학생들이 살고 있는 마을로 가는 역방향 distance 배열과 list 를 구현
 *
 * 그 후 X 마을 기준으로 정방향과 역방향으로 다익스트라 알고리즘을 사용한 탐색을 수행 - 76, 77라인
 * 다익스트라 알고리즘 개념이 헷갈리다면 https://code-lab1.tistory.com/29 를 참고
 *
 *  이후 채워진 배열을 통해 왕복 거리에 대한 최대 거리를 찾아 반환 - 79라인
 */
