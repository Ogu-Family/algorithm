package algorithm.src.week30.p3;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/14923
 * 메모리: 130544 KB
 * 시간: 756 ms
 * 시간 복잡도: O(N*M)
 * 공간 복잡도: O(N*M)
 */

/**
 * 1. 벽 하나씩 없애고 BFS 모두 돌리다가 시간초과 발생
 * 2. 벽 부순 여부를 저장하여, 벽 부수지 않은 경우에 대해서만 이후에 벽 부술 수 있도록 함. visited 배열에 벽을 부순 경로와 벽을 부수지 않은 경로에 대한 방문 체크가 필요
 */

public class p3_JeongeunChoi {

    private static int w, h, minDistance = Integer.MAX_VALUE;
    private static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    private static void findShortestWay(int[][] lab, int x, int y, int ex, int ey) {
        Queue<Location> q = new LinkedList<>();
        q.add(new Location(x, y, 0, false));
        boolean[][][] visited = new boolean[w][h][2];
        visited[x][y][0] = true;
        while (!q.isEmpty()) {
            Location location = q.poll();
            if (location.x == ex && location.y == ey) {
                minDistance = Math.min(minDistance, location.dis);
                break;
            } else {
                for (int i = 0; i < 4; i++) {
                    int nx = location.x + dx[i], ny = location.y + dy[i];
                    int visitedWall = location.usedCane ? 1 : 0;
                    if (!outOfBound(nx, ny) && !visited[nx][ny][visitedWall]) {
                        if(lab[nx][ny] == 0){
                            q.add(new Location(nx, ny, location.dis + 1, location.usedCane));
                            visited[nx][ny][visitedWall] = true;
                        } else if(lab[nx][ny] == 1 && !location.usedCane){
                            q.add(new Location(nx, ny, location.dis + 1, true));
                            visited[nx][ny][visitedWall] = true;
                        }
                    }
                }
            }

        }
    }

    private static boolean outOfBound(int x, int y) {
        return !(x >= 0 && x < w && y >= 0 && y < h);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1, y = Integer.parseInt(st.nextToken()) - 1;
        st = new StringTokenizer(br.readLine());
        int ex = Integer.parseInt(st.nextToken()) - 1, ey = Integer.parseInt(st.nextToken()) - 1;
        int[][] lab = new int[w][h];

        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < h; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findShortestWay(lab, x, y, ex, ey);

        System.out.println(minDistance != Integer.MAX_VALUE ? minDistance : -1);

    }

    private static class Location {

        int x, y, dis;
        boolean usedCane;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
            this.dis = 0;
            this.usedCane = false;
        }

        Location(int x, int y, int dis, boolean usedCane) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.usedCane = usedCane;
        }
    }
}
