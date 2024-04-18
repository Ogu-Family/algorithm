package src.week19.p1;

/**
 * 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/12978
 * 시간 복잡도: O(VlogE)
 * 공간 복잡도: O(V^2)
 * 최대 수행 시간: 3.48ms
 * 최대 수행 메모리: 85.5MB
 */

/**
 * 다익스트라, 플로이드-워셜
 *
 * 1. 우선순위 큐에 {위치, 가중치}로 값을 넣는다. 최초에 {1, 0}을 넣는다(1번 위치, 거리 0)
 * 2. 뻗어나가며 갱신한다. 그러나 뻗어나간 위치에서의 가중치가 이전보다 크다면 거기서 끝.
 * 3. 이것을 우선순위 큐가 비워질 때까지 반복한다.
 */

import java.util.*;

public class p1_ZZAMBAs {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[] dist = new int[N + 1];
        int[][] roadMatrix = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                roadMatrix[i][j] = Integer.MAX_VALUE;

        for(int i = 0; i < road.length; i++) {
            int[] nowRoad = road[i];
            roadMatrix[nowRoad[0]][nowRoad[1]] = Math.min(roadMatrix[nowRoad[0]][nowRoad[1]], nowRoad[2]);
            roadMatrix[nowRoad[1]][nowRoad[0]] = Math.min(roadMatrix[nowRoad[1]][nowRoad[0]], nowRoad[2]);
        }
        for (int i = 1; i <= N; i++)
            dist[i] = Integer.MAX_VALUE;

        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((p1, p2) -> p1.second - p2.second);
        pq.add(new Pair<>(1, 0));
        while(!pq.isEmpty()) {
            Pair<Integer, Integer> top = pq.poll();

            //System.out.println("Pair: " + top.first + ", " + top.second);

            if (dist[top.first] <= top.second)
                continue;

            dist[top.first] = top.second;

            for (int i = 1; i <= N; i++) {
                int roadDist = roadMatrix[top.first][i];

                //System.out.println("roadDist: " + roadMatrix[top.first][i]);

                if (roadDist == Integer.MAX_VALUE)
                    continue;

                if (top.second + roadDist < dist[i]) {
                    pq.add(new Pair<>(i, top.second + roadDist));
                }
            }
        }

        for (int i = 1; i <= N; i++)
            if (dist[i] <= K)
                answer++;

        return answer;
    }

    // 플로이드 워셜
    public int solution2(int N, int[][] road, int K) {
        int answer = 0;
        int[][] dist = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dist[i][j] = i == j ? 0 : 1000000000;
            }
        }

        for (int i = 0; i < road.length; i++) {
            int[] nowRoad = road[i];
            dist[nowRoad[0]][nowRoad[1]] = Math.min(dist[nowRoad[0]][nowRoad[1]], nowRoad[2]);
            dist[nowRoad[1]][nowRoad[0]] = Math.min(dist[nowRoad[1]][nowRoad[0]], nowRoad[2]);
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for (int i = 1; i <= N; i++)
            if (dist[1][i] <= K)
                answer++;

        return answer;
    }

    static class Pair<T, R> {
        T first;
        R second;

        Pair(T first, R second) {
            this.first = first;
            this.second = second;
        }
    }
}
