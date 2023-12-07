/**
 * 문제 링크: https://www.acmicpc.net/problem/2606
 * 메모리: 14624 KB
 * 시간: 140 ms
 * 시간 복잡도: O(V+E)
 * 공간 복잡도: O(E)
 */

/*
1번 정점에 대해 BFS 수행하여 방문되는 정점의 수를 출력하는 문제
연결 정점을 Map<정점, List<연결된 정점>>으로 표현
1번 정점부터 시작하여 연결된 정점을 Queue에 넣고 만나는 정점의 수를 세면 된다.
visited 배열을 사용해 이미 방문한 정점은 Queue에 넣지 않도록 한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Virus {

    static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static final int START_VERTEX = 1;

    public static void main(String[] args) throws IOException {
        int vertexCount = Integer.parseInt(bufferedReader.readLine());
        int edgeCount = Integer.parseInt(bufferedReader.readLine());

        Map<Integer, List<Integer>> adjacentVertices = initializeAdjacentVertices(edgeCount);

        System.out.println(solution(adjacentVertices, vertexCount));
    }

    private static Map<Integer, List<Integer>> initializeAdjacentVertices(int edgeCount) throws IOException {
        Map<Integer, List<Integer>> adjacentVertices = new HashMap<>();

        for (int e = 0; e < edgeCount; e++) {
            int[] edgeInfo = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            // 양방향 그래프이므로 양쪽에 모두 추가
            // computeIfAbsent: key가 존재하면 value를 반환하고, 없으면 새로운 value를 생성하여 반환
            adjacentVertices.computeIfAbsent(edgeInfo[0], v -> new ArrayList<>()).add(edgeInfo[1]);
            adjacentVertices.computeIfAbsent(edgeInfo[1], v -> new ArrayList<>()).add(edgeInfo[0]);
        }

        return adjacentVertices;
    }

    private static int solution(Map<Integer, List<Integer>> adjacentVertices, int vertexCount) {
        boolean[] visited = new boolean[vertexCount + 1];
        visited[START_VERTEX] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(START_VERTEX);

        int visitCount = 0;

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            visitCount++;

            // 연결된 정점 순회(없는 경우 빈 리스트 반환하여 에러 방지)
            for (Integer next : adjacentVertices.getOrDefault(current, Collections.emptyList())) {
                if (visited[next]) continue;
                queue.add(next);
                visited[next] = true;
            }
        }

        return visitCount - 1; // 1번 정점은 제외
    }
}
