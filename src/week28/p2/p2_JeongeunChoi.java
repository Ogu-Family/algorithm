package algorithm.src.week28.p2;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1647
 * 메모리: 14408 KB
 * 시간: 136 ms
 * 시간 복잡도: O(N) 회전횟수보다 큼
 * 공간 복잡도: O(1)
 */

/**
 * 구현
 * 회전 시작하는 바퀴부터 시작하여 양옆에 맞닿아있는 바퀴의 상태와 비교하며 회전
 */

public class p2_JeongeunChoi {

    private static int[][] gear = new int[4][9];
    private static Map<Integer, ArrayList<Pair>> touchedGearMap = new HashMap<>();

    private static void checkGearStatus(int n, int d) {
        int[][] beforeRotateGear = new int[4][9];
        for (int i = 0; i < 4; i++) {
            beforeRotateGear[i] = Arrays.copyOf(gear[i], gear[i].length);
        }
        boolean[] visited = new boolean[4];

        rotate(n, d);
        visited[n] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(n, d)); // 톱니바퀴번호와 돌린 방향 넣기
        while (!q.isEmpty()) {
            Pair currentGear = q.poll();
            int currentGearNum = currentGear.a, currentGearDir = currentGear.b;
            ArrayList<Pair> touchedGears = touchedGearMap.get(currentGearNum);
            for (int i = 0; i < touchedGears.size(); i++) {
                if (!visited[touchedGears.get(i).a]) {
                    int nextGearNum = touchedGears.get(i).a, nextWheelNum = touchedGears.get(i).b;
                    int currentWheelNum = nextWheelNum == 2 ? 6 : 2;
                    if (beforeRotateGear[currentGearNum][currentWheelNum]
                            != beforeRotateGear[nextGearNum][nextWheelNum]) {
                        rotate(nextGearNum, currentGearDir * -1);
                        visited[nextGearNum] = true;
                        q.add(new Pair(nextGearNum, currentGearDir * -1));
                    }
                }
            }

        }
    }

    private static void rotate(int gearNum, int dir) {
        if (dir == 1) {
            gear[gearNum][8] = gear[gearNum][7];
            for (int i = 7; i >= 1; i--) {
                gear[gearNum][i] = gear[gearNum][i - 1];
            }
            gear[gearNum][0] = gear[gearNum][8];
        } else if (dir == -1) {
            gear[gearNum][8] = gear[gearNum][0];
            for (int i = 0; i < 8; i++) {
                gear[gearNum][i] = gear[gearNum][i + 1];
            }
            gear[gearNum][7] = gear[gearNum][8];
        }
    }

    private static void setTouchedGearMap() {
        for (int i = 0; i < 4; i++) {
            touchedGearMap.put(i, new ArrayList<>());
        }
        touchedGearMap.get(0).add(new Pair(1, 6)); // 0번 톱니바퀴와 맞닿아있는 톱니바퀴 번호와 바퀴 번호
        touchedGearMap.get(1).add(new Pair(0, 2));
        touchedGearMap.get(1).add(new Pair(2, 6));
        touchedGearMap.get(2).add(new Pair(1, 2));
        touchedGearMap.get(2).add(new Pair(3, 6));
        touchedGearMap.get(3).add(new Pair(2, 2));
    }

    private static int calculateAnswer() {
        int answer = 0;
        for (int i = 0; i < 4; i++) {
            answer += (gear[i][0] == 0 ? 0 : Math.pow(2, i));
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            String gearStr = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = Integer.parseInt(gearStr.charAt(j) + "");
            }
        }
        setTouchedGearMap();

        int rotateCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < rotateCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()) - 1, d = Integer.parseInt(st.nextToken());
            checkGearStatus(n, d);
        }

        System.out.println(calculateAnswer());
    }

    private static class Pair {

        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

}
