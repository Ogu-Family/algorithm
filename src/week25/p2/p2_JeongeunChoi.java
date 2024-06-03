package algorithm.src.week25.p2;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/17141
 * 메모리: 72864 KB
 * 시간: 312 ms
 * 시간 복잡도: O(10CM^2)
 * 공간 복잡도: O(N*N)
 */

/**
 * 조합 + BFS
 * 1. 바이러스를 놓을 수 있는 위치를 리스트에 저장해두고, 놓을 수 있는 바이러스 개수 만큼 위치를 선택한다.
 * 2. 선택한 위치에 대해 바이러스를 놓으며 BFS를 통해 퍼뜨린다.
 * 3. 바이러스를 모두 퍼뜨리는데 가장 적게 소요된 시간을 구한다. 다 퍼뜨리지 못한 경우에는 -1을 출력하도록 한다.
 */

public class p2_JeongeunChoi {

    private static int labSize, virusCnt, minTime = Integer.MAX_VALUE, requiredVirusCnt;
    private static int[][] originLab, copyLab;
    private static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    private static ArrayList<Location> virusLocations = new ArrayList<>();
    private static boolean[] isVirusLocated;

    private static void selectVirusLocation(int putVirusCnt, int virusLocationsIdx) {
        if (putVirusCnt == virusCnt) {
            minTime = Math.min(minTime, spreadVirus());
        } else {
            for (int i = virusLocationsIdx; i < virusLocations.size(); i++) {
                if (!isVirusLocated[i]) {
                    isVirusLocated[i] = true;
                    selectVirusLocation(putVirusCnt + 1, i + 1);
                    isVirusLocated[i] = false;
                }
            }
        }
    }

    private static int spreadVirus() {
        int time = 0, existingVirusCnt = 0;
        for (int i = 0; i < labSize; i++) {
            copyLab[i] = Arrays.copyOf(originLab[i], originLab[i].length);
        }
        boolean[][] visited = new boolean[labSize][labSize];
        Queue<Location> q = new LinkedList<>();
        for(int i=0; i<virusLocations.size(); i++){
            if(isVirusLocated[i]){
                q.add(virusLocations.get(i));
                visited[virusLocations.get(i).x][virusLocations.get(i).y] = true;
                existingVirusCnt++;
            }
        }
        while (!q.isEmpty()) {
            Location loc = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = loc.x + dx[i], ny = loc.y + dy[i];
                if (!outOfBound(nx, ny) && copyLab[nx][ny] != -1 && !visited[nx][ny]) {
                    copyLab[nx][ny] = copyLab[loc.x][loc.y] + 1;
                    visited[nx][ny] = true;
                    q.add(new Location(nx, ny));
                    time = Math.max(time, copyLab[nx][ny]);
                    existingVirusCnt++;
                }
            }
        }

        return existingVirusCnt == requiredVirusCnt ? time : Integer.MAX_VALUE;
    }

    private static boolean outOfBound(int x, int y) {
        return !(x >= 0 && x < labSize && y >= 0 && y < labSize);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        labSize = Integer.parseInt(st.nextToken());
        virusCnt = Integer.parseInt(st.nextToken());
        originLab = new int[labSize][labSize];
        copyLab = new int[labSize][labSize];
        requiredVirusCnt = labSize * labSize;
        for (int i = 0; i < labSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < labSize; j++) {
                originLab[i][j] = Integer.parseInt(st.nextToken());
                if (originLab[i][j] == 2) {
                    virusLocations.add(new Location(i, j));
                    originLab[i][j] = 0;
                } else if (originLab[i][j] == 1) {
                    originLab[i][j] = -1;
                    requiredVirusCnt--;
                }
            }
        }
        isVirusLocated = new boolean[virusLocations.size()];

        selectVirusLocation(0, 0);

        System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);
    }

    private static class Location {

        int x, y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
