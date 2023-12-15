/**
 * 문제 링크: https://www.acmicpc.net/problem/1303
 * 메모리: 17968KB
 * 시간: 172ms
 * 시간 복잡도: O(N * M)
 * 공간 복잡도: O(N * M)
 */

/**
 * 일반적인 BFS 문제
 * 전체에 대해 반복문을 돌며 미방문 노드라면 BFS를 돌림
 * BFS를 돌릴 때마다 카운트를 증가시키고, BFS가 끝나면 카운트를 제곱하여 점수를 계산
 * BFS를 돌릴 때마다 방문한 노드는 빈 노드로 변경
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class WarBattle {

    static final char BLUE = 'B';
    static final char WHITE = 'W';
    static final char EMPTY = 'E';
    static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        solution(initializeGraph());
    }

    private static char[][] initializeGraph() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = info[0];
        int n = info[1];
        char[][] graph = new char[n][m];

        for (int i = 0; i < n; i++) graph[i] = bufferedReader.readLine().toCharArray();
        return graph;
    }

    private static void solution(char[][] graph) {
        int whiteScore = 0;
        int blueScore = 0;

        for (int n = 0; n < graph.length; n++) {
            for (int m = 0; m < graph[n].length; m++) {
                char c = graph[n][m];
                // 팀이 있는 노드에 대해 BFS
                if (c == WHITE) whiteScore += calculateScore(bfs(graph, n, m, c));
                if (c == BLUE) blueScore += calculateScore(bfs(graph, n, m, c));
            }
        }

        System.out.println(whiteScore + " " + blueScore);
    }

    private static int bfs(char[][] graph, int startN, int startM, char c) {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(startN, startM));

        int count = 1;
        graph[startN][startM] = EMPTY;

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            for (int[] direction : DIRECTIONS) {
                int nextN = current.n + direction[0];
                int nextM = current.m + direction[1];

                if (!isInBound(nextN, nextM, graph.length, graph[0].length) ||
                    graph[nextN][nextM] != c) continue;
                graph[nextN][nextM] = EMPTY;
                queue.add(new Coordinate(nextN, nextM));
                count++;
            }
        }

        return count;
    }

    private static boolean isInBound(int n, int m, int limitN, int limitM) {
        return 0 <= n && n < limitN && 0 <= m && m < limitM;
    }

    private static int calculateScore(int x) {
        return x * x;
    }

    static class Coordinate {
        int n;
        int m;

        public Coordinate(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }
}
