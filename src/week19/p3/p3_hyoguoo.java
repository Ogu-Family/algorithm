/**
 * 문제 링크: https://www.acmicpc.net/problem/18513
 * 메모리: 58084 KB
 * 시간: 568 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * BFS를 수행하여 집 갯수 만큼 방문하는 문제
 *
 * 수행하는 도중 시작한 지점으로부터 방문한 정점의 거리를 누적하여 결과를 반환
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SpringSite {

    static final int[] DIRECTIONS = {-1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int houseCount = info[1];
        int[] springSites = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.print(solution(springSites, houseCount));
    }

    private static long solution(int[] springSites, int houseCount) {
        Queue<Node> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        // 큐 초기화 및 해당 지점 방문 처리
        for (int springSite : springSites) {
            queue.add(new Node(springSite, 0));
            visited.add(springSite);
        }

        int visitCount = 0;
        long totalDistance = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int direction : DIRECTIONS) {
                int nextIndex = current.currentIndex + direction;
                // 방문한 정점인 경우 건너뜀
                if (visited.contains(nextIndex)) {
                    continue;
                }
                visitCount++;
                totalDistance += current.distance + 1; // 직전 거리의 +1하여 더함

                // 모든 집을 방문한 경우
                if (visitCount == houseCount) {
                    return totalDistance;
                }

                visited.add(nextIndex);
                queue.add(new Node(nextIndex, current.distance + 1));
            }
        }

        return totalDistance;
    }

    static class Node {

        int currentIndex;
        int distance;

        public Node(int currentIndex, int distance) {
            this.currentIndex = currentIndex;
            this.distance = distance;
        }
    }
}
