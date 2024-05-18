package src.week23.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/15686
 *
 * 메모리: 20872 KB
 * 시간: 256 ms
 * 시간 복잡도: O(치킨 집 개수 ^ 집 개수)
 * 공간 복잡도: O(N^2)
 */

/**
 *
 * 조합 / dfs 문제
 * 집, 치킨 집 위치를 따로 list로 저장해두고 조합을 선택
 * 남겨 둘 치킨 집 M개를 조합으로 선택하고, dfs가 M에 도달했을 떄 치킨 거리 계산
 * 미리 저장해 둔 집과 치킨 집 사이 거리를 계산하여 최소값을 찾는다.
 *
 */

public class p1_GiHoo {

    static int N, M;
    static int[][] town;
    static List<House> houseList;
    static List<Chicken> chickenList;
    static List<Chicken> currentChickenList;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        houseList = new ArrayList<>();
        chickenList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        town = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 1) houseList.add(new House(i, j));
                else if (tmp == 2) chickenList.add(new Chicken(i, j));
                town[i][j] = tmp;
            }
        }

        visited = new boolean[chickenList.size()];
        currentChickenList = new ArrayList<>();

        dfs(0, 0);

        System.out.print(answer);
    }

    private static void dfs(int start, int depth) {
        if (depth == M) {
            int currentSum = 0;
            for (int i = 0; i < houseList.size(); i++) {
                int houseSum = Integer.MAX_VALUE;

                House house = houseList.get(i);
                int houseC = house.x;
                int houseR = house.y;
                for (int j = 0; j < currentChickenList.size(); j++) {
                    Chicken chicken = currentChickenList.get(j);
                    int chickenC = chicken.x;
                    int chickenR = chicken.y;

                    int chickenDistance = getChickenDistance(houseC, houseR, chickenC, chickenR);
                    houseSum = Integer.min(houseSum, chickenDistance);
                }
                currentSum += houseSum;

                if (currentSum >= answer) return;
            }

            answer = Integer.min(answer, currentSum);
        }

        for (int i = start; i < chickenList.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                currentChickenList.add(chickenList.get(i));
                dfs(i + 1, depth + 1);
                visited[i] = false;
                currentChickenList.remove(currentChickenList.size() - 1);
            }
        }
    }

    private static int getChickenDistance(int houseC, int houseR, int chickenC, int chickenR) {
        int absC = Math.abs(houseC - chickenC);
        int absR = Math.abs(houseR - chickenR);
        return absC + absR;
    }

    static class Chicken {
        int x;
        int y;

        public Chicken(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class House {
        int x;
        int y;

        public House(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
