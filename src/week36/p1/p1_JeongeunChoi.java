package algorithm.src.weewk36.p1;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2589
 * 메모리: 295136 KB
 * 시간: 496 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N*M)
 */

/**
 * DFS
 * 보물섬의 육지 위치를 저장한다.
 * 육지 위치에 대해, 다른 육지로 가는 최단 거리를 계산하여 그 중 최대 거리를 찾는다.
 */

public class p1_JeongeunChoi {

    private final static char LAND = 'L', OCEAN = 'W';
    private static int w, h;
    private static char[][] map;

    private static int maxDistanceFrom(Location start) {
        int[][] distance = new int[h][w];
        boolean[][] visited = new boolean[h][w];
        int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
        int maxDistance = 0;
        Queue<Location> q = new LinkedList<>();
        q.add(start);
        visited[start.x][start.y] = true;
        while (!q.isEmpty()) {
            Location location = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = location.x + dx[i], ny = location.y + dy[i];
                if (!isOutOfBound(nx, ny) && !visited[nx][ny] && map[nx][ny] == LAND) {
                    distance[nx][ny] = distance[location.x][location.y] + 1;
                    q.add(new Location(nx, ny));
                    visited[nx][ny] = true;
                    maxDistance = Math.max(maxDistance, distance[nx][ny]);
                }
            }
        }

        return maxDistance;
    }

    private static boolean isOutOfBound(int x, int y) {
        return !(x >= 0 && x < h && y >= 0 && y < w);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        int maxDistance = 0;
        map = new char[h][w];
        ArrayList<Location> lands = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            String str = br.readLine();
            for (int j = 0; j < w; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == LAND) {
                    lands.add(new Location(i, j));
                }
            }
        }

        for (int i = 0; i < lands.size(); i++) {
            maxDistance = Math.max(maxDistance, maxDistanceFrom(lands.get(i)));
        }

        System.out.println(maxDistance);
    }

    static class Location {

        int x, y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
