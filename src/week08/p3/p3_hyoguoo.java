/**
 * 문제 링크: https://www.acmicpc.net/problem/1937
 * 메모리: 44688 KB
 * 시간: 568 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2)
 */

/**
 * DFS + DP
 *
 * 방문하지 않은 지점을 시작으로 DFS를 수행하고, 각 지점에서 DFS를 수행하면서 해당 지점의 최대 이동 칸 수를 dp 배열에 저장
 * 다른 지점에서 DFS를 수행하다가 이미 방문한 지점(DFS가 수행된 지점)을 만나면 해당 지점의 최대 이동 칸 수를 반환하여 중복 계산을 방지(-> DP)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GreedyPanda {

    static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static final int NOT_VISITED = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            graph[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.print(solution(graph));
    }

    private static int solution(int[][] graph) {
        int[][] dp = initArray(graph); // -1로 초기화
        int max = 0;

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                // DFS 수행
                max = Math.max(max, dfs(graph, dp, i, j));
            }
        }

        return max;
    }

    private static int[][] initArray(int[][] graph) {
        int[][] array = new int[graph.length][graph.length];

        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(array[i], NOT_VISITED);
        }
        return array;
    }

    private static int dfs(int[][] graph, int[][] dp, int n, int m) {
        // 이미 방문한 경우 반환
        if (dp[n][m] != NOT_VISITED) {
            return dp[n][m];
        }

        dp[n][m] = 1;

        for (int[] direction : DIRECTIONS) {
            int nextN = n + direction[0];
            int nextM = m + direction[1];

            if (!isInBound(graph, nextN, nextM)) continue;

            if (graph[n][m] < graph[nextN][nextM]) { // 현재 대나무가 다음 대나무보다 작은 경우(= 이동 가능한 경우)
                // 다음 대나무를 기준으로 DFS 수행
                dp[n][m] = Math.max(dp[n][m], dfs(graph, dp, nextN, nextM) + 1);
            }
        }

        return dp[n][m];
    }

    private static boolean isInBound(int[][] graph, int n, int m) {
        return 0 <= n && n < graph.length && 0 <= m && m < graph.length;
    }
}
