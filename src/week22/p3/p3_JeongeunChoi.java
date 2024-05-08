package algorithm.src.week22.p3;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/20055
 * 메모리: 17340 KB
 * 시간: 320 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 구현
 * 1. 벨트 회전 및 로봇 이동
 * 2. 로봇 이동
 * 3. 로봇 올리기
 * 4. 내구도가 0인 칸의 개수가 K개 이상이라면 종료 아니면 1번으로 돌아가기
 */

public class p3_JeongeunChoi {

    private static int beltLength, durabilityZeroCnt, descendIdx, ascendIdx;
    private static int[] beltDurability;
    private static boolean[] robotsOnBelt;

    private static int getStep() {
        int step = 0;

        do {
            // 벨트 회전 및 로봇 이동
            rotateBeltWithRobot();
            // 로봇 이동
            moveRobot();
            // 로봇 올리기
            raiseRobot();
            step++;
        } while (countDurabilityZero() < durabilityZeroCnt);

        return step;
    }

    private static void rotateBeltWithRobot() {
        beltDurability[0] = beltDurability[beltLength * 2];
        for (int i = beltLength * 2; i >= 1; i--) {
            beltDurability[i] = beltDurability[i - 1];
        }
        for (int i = beltLength; i >= 1; i--) {
            robotsOnBelt[i] = robotsOnBelt[i - 1];
        }
        robotsOnBelt[descendIdx] = false;
    }

    private static void moveRobot() {
        for (int i = beltLength - 1; i >= 1; i--) {
            if (!robotsOnBelt[i]) {
                continue;
            }
            if (!robotsOnBelt[i + 1] && beltDurability[i + 1] > 0) {
                robotsOnBelt[i] = false;
                robotsOnBelt[i + 1] = true;
                beltDurability[i + 1]--;
                if (i + 1 == descendIdx) {
                    robotsOnBelt[i + 1] = false;
                }
            }
        }
    }

    private static void raiseRobot() {
        if (!robotsOnBelt[ascendIdx] && beltDurability[ascendIdx] != 0) {
            robotsOnBelt[ascendIdx] = true;
            beltDurability[ascendIdx]--;
        }
    }

    private static int countDurabilityZero() {
        int cnt = 0;

        for (int i = 1; i <= beltLength * 2; i++) {
            if (beltDurability[i] == 0) {
                cnt++;
            }
        }

        return cnt;
    }

    private static void input() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        beltLength = Integer.parseInt(st.nextToken());
        durabilityZeroCnt = Integer.parseInt(st.nextToken());

        beltDurability = new int[beltLength * 2 + 1];
        robotsOnBelt = new boolean[beltLength + 1];
        descendIdx = beltLength;
        ascendIdx = 1;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= beltLength * 2; i++) {
            beltDurability[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws Exception {
        input();

        System.out.println(getStep());
    }
}
