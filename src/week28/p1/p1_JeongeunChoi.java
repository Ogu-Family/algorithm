package algorithm.src.week28.p1;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/9205
 * 메모리: 14888 KB
 * 시간: 156 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N)
 */

/**
 * 풀이 참고한 문제
 * BFS
 * 1. 집의 위치를 큐에 넣는다.
 * 2. 큐에서 위치를 하나 뽑는다. 해당 위치에서 페스티벌로 이동가능한지 확인한다. 이동가능하면 happy 출력
 * 3. 페스티벌로 이동 불가능한 경우, 현재 위치에서 방문하지 않은 편의점 중 갈 수 있는 편의점을 큐에 넣는다.
 * 4. 2번으로 가서 반복한다. 큐가 종료된 이후에도 이동하지 못하였다면 sad 출력
 */

public class p1_JeongeunChoi {

    private static int maxBeerCnt = 20, unitMeter = 50;

    private static boolean canGoFestival(Location home, ArrayList<Location> gs25, Location festival) {
        Queue<Location> q = new LinkedList<>();
        q.add(home);
        boolean[] visited = new boolean[gs25.size()];
        while (!q.isEmpty()) {
            Location current = q.poll();
            if (canGoAtoB(current, festival)) {
                return true;
            } else {
                for (int i = 0; i < gs25.size(); i++) {
                    Location next = gs25.get(i);
                    if (visited[i]) {
                        continue;
                    } else if (canGoAtoB(current, next)) {
                        visited[i] = true;
                        q.add(next);
                    }
                }
            }

        }

        return false;
    }

    private static boolean canGoAtoB(Location a, Location b) {
        return (Math.abs(a.x - b.x) + Math.abs(a.y - b.y) <= maxBeerCnt * unitMeter);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        Location home, festival;
        ArrayList<Location> gs25 = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            int gs25Cnt = Integer.parseInt(br.readLine());
            gs25.clear();
            st = new StringTokenizer(br.readLine());
            home = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            for (int j = 0; j < gs25Cnt; j++) {
                st = new StringTokenizer(br.readLine());
                gs25.add(new Location(Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())));
            }
            st = new StringTokenizer(br.readLine());
            festival = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            if (canGoFestival(home, gs25, festival)) {
                System.out.println("happy");
            } else {
                System.out.println("sad");
            }
        }

    }

    private static class Location {

        int x, y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
