package week33.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/13549
 * 메모리: 30880 KB
 * 시간: 416 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/*
1. 수빈이와 동생의 위치를 입력받아
2. 수빈이가 이동할 수 있는 경우의 수 3가지를 우선순위 큐에 넣고 bfs 실행
3. 결과 출력
 */

public class p1_letskuku {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[200001];
        int[] dist = new int[200001];

        PriorityQueue<Pair> q = new PriorityQueue<>((o1, o2) -> {
            if (o1.dist == o2.dist) {
                return o1.loc - o2.loc;
            }

            return o1.dist - o2.dist;
        });
        q.add(new Pair(n, 0));
        visited[n] = true;
        while (!q.isEmpty()) {
            Pair p = q.poll();

            int[] move = {p.loc * 2, p.loc + 1, p.loc - 1};
            for (int i = 0; i < 3; i++) {
                if (move[i] < 0 || move[i] > 200000 || visited[move[i]]) {
                    continue;
                }

                visited[move[i]] = true;
                dist[move[i]] = dist[p.loc];
                if (i != 0) {
                    dist[move[i]]++;
                }

                q.add(new Pair(move[i], dist[move[i]]));
            }
        }

        System.out.println(dist[k]);
    }

    static class Pair {
        int loc, dist;

        Pair(int loc, int dist) {
            this.loc = loc;
            this.dist = dist;
        }
    }
}
