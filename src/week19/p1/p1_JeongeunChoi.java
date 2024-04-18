package algorithm.src.week19.p1;

import java.util.*;

/**
 * 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/12978
 * 시간 복잡도: O(MlogN) M은 간선의 개수, N은 노드의 개수
 * 공간 복잡도: O(N^2)
 */

/**
 * 다익스트라 알고리즘
 * 풀이 참고한 문제
 * 1. 방문하지 않은 나라에서 갈 수 있는 가장 거리가 짧은 나라를 방문한다.
 * 2. 해당 나라를 거쳐 갈 수 있는 정점의 거리가 이전에 계산한 값보다 짧으면 갱신한다.
 * 우선순위큐 활용하여 특정 나라에서 다른 나라까지 가는 최소 거리 저장. 우선순위는 거리가 짧을수록 높다.
 * visited 배열로 나라 방문 여부 체크
 */

public class p1_JeongeunChoi {

    private int[] minDistance;
    private final ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    private final PriorityQueue<Node> pq = new PriorityQueue<>();
    private boolean[] visited;

    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        minDistance = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            minDistance[i] = Integer.MAX_VALUE;
            graph.add(new ArrayList<Node>());
        }

        for (int i = 0; i < road.length; i++) {
            int a = road[i][0], b = road[i][1], w = road[i][2];
            graph.get(a).add(new Node(b, w));
            graph.get(b).add(new Node(a, w));
        }
        pq.add(new Node(1, 0));
        minDistance[1] = 0;
        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int current = currentNode.n;
            if (!visited[current]) {
                visited[current] = true;
                for (int i = 0; i < graph.get(currentNode.n).size(); i++) {
                    Node nextNode = graph.get(currentNode.n).get(i);
                    int next = nextNode.n;
                    if (minDistance[next] > minDistance[current] + nextNode.dis) {
                        minDistance[next] = minDistance[current] + nextNode.dis;
                        pq.add(new Node(next, minDistance[next]));
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (minDistance[i] <= K) {
                answer++;
            }
        }

        return answer;
    }

    class Node implements Comparable<Node> {

        int n, dis;

        Node(int n, int dis) {
            this.n = n;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node other) {
            if (this.dis < other.dis) {
                return -1;
            } else if (this.dis > other.dis) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
