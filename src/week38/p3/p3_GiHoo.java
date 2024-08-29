package src.week38.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/26009
 * 메모리: 341000 KB
 * 시간: 964 ms
 * 시간 복잡도: O(N ^ 2)
 * 공간 복잡도: O(N ^2)
 */

/**
 * 풀이 참고한 문제 - 테두리 설정
 *
 * 처음에는 R, C 기준으로 D만큼 BFS를 돌려 위험 구역을 설정했음
 * 그런데 67% 에서 계속 실패하길래 시간 초과가 실패로 뜨나 싶음.. (기존 코드랑 성공 코드 모두 정체 구역은 동일하게 설정되는데 시간이 오래 걸려서 실패하는 것 같음..)
 *
 * 그래서 BFS로 모든 정체 구역을 설정하는게 아닌 테두리만 설정할 수 있는 풀이 선택
 * R, C 기준으로 시계방향으로 테두리를 설정
 *
 * 이후에는 기본적인 BFS
 */

public class p3_GiHoo {

    static int N, M;
    static int[][] moves = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int R = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken()) - 1;
            int D = Integer.parseInt(st.nextToken());

            // D가 0일 경우를 가정하여 중앙 테두리
            matrix[R][C] = 1;

            // 중앙 기준 테두리 만들기
            for (int j = 0; j < D; j++) {
                if (checkBoundary(R - D + j, C + j)) matrix[R - D + j][C + j] = 1; // 1사분면
                if (checkBoundary(R + j, C + D - j)) matrix[R + j][C + D - j] = 1; // 4사분면
                if (checkBoundary(R + D - j, C - j)) matrix[R + D - j][C - j] = 1; // 3사분면
                if (checkBoundary(R - j, C - D + j)) matrix[R - j][C - D + j] = 1; // 2사분면
            }
        }

        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.x == N - 1 && node.y == M - 1) {
                System.out.println("YES");
                System.out.println(node.distance);

                return;
            }

            for (int i = 0; i < moves.length; i++) {
                int nx = node.x + moves[i][0];
                int ny = node.y + moves[i][1];

                if (checkBoundary(nx, ny) && matrix[nx][ny] == 0 && !visited[nx][ny]) {
                    queue.add(new Node(nx, ny, node.distance + 1));
                    visited[nx][ny] = true;
                }
            }
        }

        System.out.println("NO");
    }

    private static boolean checkBoundary(int x, int y) {
        return (x >= 0 && y >= 0 && x < N && y < M);
    }

    static class Node {
        int x;
        int y;
        int distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}

