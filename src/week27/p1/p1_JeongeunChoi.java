package algorithm.src.week27.p1;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/14940
 * 메모리: 73360 KB
 * 시간: 2560 ms
 * 시간 복잡도: O(N*M)
 * 공간 복잡도: O(N*M)
 */

/**
 * bfs
 * 목표 지점부터 시작하여, 방문하지 않은 갈 수 있는 땅의 거리를 현재 땅 거리에서 하나 증가한 후 큐에 넣으며 큐가 빌때까지 반복한다.
 */


public class p1_JeongeunChoi {

    private static int n, m;
    private static int[][] map, distance;
    private static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    private static void calculateDistance(int x, int y) {
        Queue<Location> q = new LinkedList<>();
        q.add(new Location(x, y));

        while (!q.isEmpty()) {
            Location location = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = location.x + dx[i], ny = location.y + dy[i];
                if (!outOfBound(nx, ny) && distance[nx][ny] == -1 && map[nx][ny] == 1) {
                    distance[nx][ny] = distance[location.x][location.y] + 1;
                    q.add(new Location(nx, ny));
                }
            }
        }
    }

    private static boolean outOfBound(int x, int y) {
        return !(x >= 0 && x < n && y >= 0 && y < m);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        distance = new int[n][m];
        int sx = 0, sy = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    sx = i;
                    sy = j;
                    distance[i][j] = 0;
                } else if (map[i][j] == 0) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = -1;
                }
            }
        }

        calculateDistance(sx, sy);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static class Location {

        int x, y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
