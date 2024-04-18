package src.week19.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/18513
 * 메모리: 64076 KB
 * 시간: 660 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 샘터의 위치를 입력 받은 후, 해당 샘터의 위치를 queue 에 저장
 * bfs 방식을 통해 샘터의 위치를 빼고 샘터 위치 기준으로 좌,우(-1, 1)에 집을 짓는다.
 * 집을 지은 후, 해당 집에서부터 그 옆으로 집을 지어야하기 때문에 집의 위치를 queue에 저장한다.
 * 이미 방문(집을 지은 곳, 샘터)한 곳은 set을 통해 해당 위치의 중복을 참고한다.
 *
 * 처음에 풀 때, 방문 여부를 배열로 해결하려 했는데 -1억 ~ 1억이라 포기..
 * 찾기 힘들었던 반례
 * 1 1
 * 0
 */

public class p3_GiHoo {
    static long[] moves = {-1, 1}; // 좌, 우 이동을 위한 배열
    static int N;
    static int K;
    static Queue<Node> queue = new LinkedList<>();
    static Set<Long> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long input = Long.parseLong(st.nextToken());
            queue.add(new Node(input, input));
            visited.add(input);
        }

        System.out.print(solution());
    }

    private static long solution() {
        long unhappiness = 0;
        long house_count = 0;

        while (!queue.isEmpty() && house_count < K) {
            Node node = queue.poll();
            long well = node.well;
            long house = node.house;

            for (long move : moves) {
                long new_house = house + move;
                if (!visited.contains(new_house) && house_count < K) {
                    queue.add(new Node(well, new_house));
                    visited.add(new_house);
                    unhappiness += Math.abs(well - new_house);
                    house_count++;
                }
            }
        }

        return unhappiness;
    }

    static class Node {
        long well;
        long house;

        public Node(long well, long house) {
            this.well = well;
            this.house = house;
        }
    }
}
