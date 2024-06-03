package algorithm.src.week25.p3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/31863
 * 메모리: 215364 KB
 * 시간: 940 ms
 * 시간 복잡도: O(N*M)
 * 공간 복잡도: O(N*M)
 */

/**
 * BFS
 * 1. 본진에 대해 뻗어나가며 해당 위치의 지진 횟수를 하나 증가시키고 큐에 위치를 저장한다.
 * 2. 큐에서 하나씩 꺼내며, 해당 위치에 지진 횟수를 바탕으로 건물이 무너지게 되는지 판단한다.
 * 3. 건물이 무너지면 해당 위치로부터 여진을 전파한다.
 */

public class p3_JeongeunChoi {

    private static char ROOT = '@', ROAD = '.', STRONG_BUILDING = '#', WEAK_BUILDING = '*', OBSTACLE = '|';
    private static int N, M;
    private static char[][] map;
    private static int[][] earthquakeCnt;
    private static boolean[][] isDestroyed;
    private static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    private static int buildingCnt = 0, destroyedBuildingCnt = 0;
    private static Queue<Earthquake> q = new LinkedList<>();

    private static void spreadEarthquake(Earthquake rootEarthquake) {
        for (int j = 0; j < 4; j++) {
            for (int i = 1; i <= 2; i++) {
                int nx = rootEarthquake.x + dx[j] * i, ny = rootEarthquake.y + dy[j] * i;
                if (!outOfBound(nx, ny)) {
                    if (map[nx][ny] == OBSTACLE) {
                        break;
                    } else {
                        earthquakeCnt[nx][ny]++;
                        q.add(new Earthquake(nx, ny));
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            Earthquake eq = q.poll();
            boolean canDestroy = false;
            if (map[eq.x][eq.y] == STRONG_BUILDING && earthquakeCnt[eq.x][eq.y] >= 2
                    && !isDestroyed[eq.x][eq.y]) {
                destroyedBuildingCnt++;
                canDestroy = true;
            } else if (map[eq.x][eq.y] == WEAK_BUILDING && earthquakeCnt[eq.x][eq.y] >= 1 && !isDestroyed[eq.x][eq.y]) {
                destroyedBuildingCnt++;
                canDestroy = true;
            }
            if (canDestroy) {
                isDestroyed[eq.x][eq.y] = true;
                for (int j = 0; j < 4; j++) {
                    int nx = eq.x + dx[j], ny = eq.y + dy[j];
                    if (!outOfBound(nx, ny) && map[nx][ny] != OBSTACLE) {
                        earthquakeCnt[nx][ny]++;
                        q.add(new Earthquake(nx, ny));
                    }
                }
            }
        }
    }

    private static boolean outOfBound(int x, int y) {
        return !(x >= 0 && x < N && y >= 0 && y < M);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        earthquakeCnt = new int[N][M];
        isDestroyed = new boolean[N][M];
        Earthquake rootEarthquake = null;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char ch = str.charAt(j);
                if (ch == ROOT) {
                    rootEarthquake = new Earthquake(i, j);
                } else if (ch == STRONG_BUILDING || ch == WEAK_BUILDING) {
                    buildingCnt++;
                }
                map[i][j] = ch;
            }
        }

        spreadEarthquake(rootEarthquake);

        System.out.println(destroyedBuildingCnt + " " + (buildingCnt - destroyedBuildingCnt));
    }

    private static class Earthquake {

        int x, y;

        Earthquake(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
