package src.week03.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1238
 * 메모리: 37580 KB
 * 시간: 512 ms
 * 시간 복잡도: O(MlogM)
 * 공간 복잡도: O(N)
 */

/**
 * X에서 출발하는 다익스트라 1번, X로 들어오는 다익스트라 1번, 총 2번의 다익스트라로 해결한다.
 * inDegree를 outDegree처럼 생각하면 각 마을에서 X로 들어오는 최단 거리를 구할 수 있다.
 * Pair 쓰기 싫어서 비트마스킹 썼는데 이거 디버깅하기 너무 힘들어서 다음부터는 그냥 원래대로 Pair 쓸 예정...
 *
 * 문제에서 중요한 부분
 * - 시작점과 끝점이 같은 도로는 없으며, 시작점과 한 도시 A에서 다른 도시 B로 가는 도로의 개수는 최대 1개이다.
 * - 모든 학생들은 집에서 X에 갈수 있고, X에서 집으로 돌아올 수 있는 데이터만 입력으로 주어진다.
 * - 이 도로들은 단방향
 * - 학생들은 워낙 게을러서 최단 시간에 오고 가기를 원한다.
 * - N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class p2_ZZAMBAs {
    static int DESTINATION_BIT = 0b1111111111; // 값 = (가중치 비트 + 목적지 비트)이기에, 목적지 비트만 빼주기 위한 비트.

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), M = sc.nextInt(), X = sc.nextInt();
        long res = 0;
        long[] toXDist = new long[N + 1], fromXDist = new long[N + 1];
        ArrayList<Integer>[] inDeg = new ArrayList[N + 1], outDeg = new ArrayList[N + 1];

        Arrays.fill(toXDist, Integer.MAX_VALUE);
        Arrays.fill(fromXDist, Integer.MAX_VALUE);

        for (int i = 1; i <= N; i++) {
            inDeg[i] = new ArrayList<>();
            outDeg[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            int start = sc.nextInt(), end = sc.nextInt(), weight = sc.nextInt();

            inDeg[end].add((weight << 10) + start);
            outDeg[start].add((weight << 10) + end);
        }

        dijkstra(X, inDeg, toXDist);
        dijkstra(X, outDeg, fromXDist);

        for (int i = 1; i <= N; i++)
            if (i != X)
                res = Math.max(res, toXDist[i] + fromXDist[i]);

        System.out.println(res);
    }

    public static void dijkstra(int start, ArrayList<Integer>[] deg, long[] dist) { // start에서 출발하여 다른 노드들까지 최단 거리를 계산하는 메서드
        PriorityQueue<Long> pq = new PriorityQueue<>(); // 여기만 Long 인 이유는 가중치를 누적하다보면 32비트를 초과할 수도 있기 때문
        dist[start] = 0;
        pq.add((long) start);

        while (!pq.isEmpty()) {
            long minimum = pq.poll();

            int nowLoc = (int) (minimum & DESTINATION_BIT);
            long accWeight = minimum >> 10;

            for (int adv : deg[nowLoc]) { // 인접 노드들을 보면서
                int dest = adv & DESTINATION_BIT;
                int nextWeight = adv >> 10;

                if (dist[dest] > accWeight + nextWeight) { // 최단일 때만 갱신
                    dist[dest] = accWeight + nextWeight;

                    pq.add(((accWeight + nextWeight) << 10) + dest);
                }
            }

        }
    }
}
