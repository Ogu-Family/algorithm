package algorithm.src.week23.p1;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/15686
 *
 * 메모리: 18356 KB
 * 시간: 592 ms
 * 시간 복잡도: O(N * M^3)
 * 공간 복잡도: O(N^2)
 */

/**
 * dfs
 * 치킨집 M개를 선택하는 모든 경우의 수를 구한다.
 * 선택한 치킨집 M개에 대해 집으로부터 가장 가까운 치킨집을 찾아 거리의 합을 중 가장 작은 합을 출력한다.
 */

public class p1_JeongeunChoi {

    private static final ArrayList<Location> houses = new ArrayList<>();
    private static final ArrayList<Location> stores = new ArrayList<>();
    private static int N, M, answer = Integer.MAX_VALUE;
    private static boolean[] selectedStores;

    private static void solution(int cnt, int storeIdx) {
        if (cnt == M) {
            int distanceSum = 0;
            for (int i = 0; i < houses.size(); i++) {
                Location houseLocation = houses.get(i);
                distanceSum += findNearStore(houseLocation.x, houseLocation.y);
            }
            answer = Math.min(answer, distanceSum);
        } else {
            for (int i = storeIdx; i < stores.size(); i++) {
                selectedStores[i] = true;
                solution(cnt + 1, i + 1);
                selectedStores[i] = false;
                solution(cnt, i + 1);
            }
        }
    }

    private static int findNearStore(int hx, int hy) {
        int distance = Integer.MAX_VALUE;

        for (int i = 0; i < stores.size(); i++) {
            if (selectedStores[i]) {
                Location storeLocation = stores.get(i);
                distance = Math.min(distance,
                        Math.abs(hx - storeLocation.x) + Math.abs(hy - storeLocation.y));
            }
        }

        return distance;
    }

    private static void input() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int info = Integer.parseInt(st.nextToken());
                if (info == 1) {
                    houses.add(new Location(i, j));
                } else if (info == 2) {
                    stores.add(new Location(i, j));
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        input();
        selectedStores = new boolean[stores.size()];
        solution(0, 0);
        System.out.println(answer);
    }

    public static class Location {

        int x, y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
