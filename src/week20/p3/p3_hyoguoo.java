/**
 * 문제 링크: https://www.acmicpc.net/problem/2660
 * 시간 복잡도: O(N + M)
 * 공간 복잡도: O(M)
 * N: 회원의 수, M: 관계의 수
 */

/**
 * BFS를 이용하여 풀이
 *
 * 1. 인접 리스트를 순회하되 현재 큐에 저장된 값만 순회하여 방문하지 않은 인접 노드를 큐에 추가
 * 2. 저장된 값만 순회한 뒤엔 관계 깊이를 증가
 * 3. 모든 회원에 대해 BFS를 수행하여 각 회원의 점수를 계산
 * 4. 최솟값을 가지는 회원을 찾아 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ElectingPresident {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        Map<Integer, List<Integer>> adjacentVertices = new HashMap<>();

        while (true) {
            int[] edgeInfo = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (edgeInfo[0] == -1 && edgeInfo[1] == -1) {
                break;
            }
            adjacentVertices.computeIfAbsent(edgeInfo[0], v -> new ArrayList<>()).add(edgeInfo[1]);
            adjacentVertices.computeIfAbsent(edgeInfo[1], v -> new ArrayList<>()).add(edgeInfo[0]);
        }

        solution(adjacentVertices, n);
    }

    private static void solution(Map<Integer, List<Integer>> adjacentVertices, int n) {
        int[] distances = new int[n + 1]; // 각 회원의 점수 저장 배열
        int minDistance = Integer.MAX_VALUE;
        List<Integer> candidates = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            distances[i] = bfs(adjacentVertices, i, n);
            minDistance = Math.min(minDistance, distances[i]); // 최솟값 갱신
        }

        for (int i = 1; i <= n; i++) {
            if (distances[i] == minDistance) {
                candidates.add(i); // 최솟값을 가지는 회원 저장
            }
        }

        // 결과 출력
        System.out.println(minDistance + " " + candidates.size());
        candidates.forEach(candidate -> System.out.print(candidate + " "));
    }

    private static int bfs(Map<Integer, List<Integer>> adjacentVertices, int start, int n) {
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;

        int distance = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int size = queue.size(); // 현재 큐에 저장된 값만 순회하기 위해 큐의 사이즈 저장
            for (int i = 0; i < size; i++) { // 현재 큐에 저장된 값만 순회
                Integer current = queue.poll();

                // 큐 순회하며 방문하지 않은 인접 노드 큐에 추가
                for (Integer next : adjacentVertices.getOrDefault(current, new ArrayList<>())) {
                    if (visited[next]) {
                        continue;
                    }
                    queue.add(next);
                    visited[next] = true;
                }
            }
            distance++; // 관계 깊이 증가
        }

        return distance - 1;
    }
}
