/**
 * 문제 링크: https://www.acmicpc.net/problem/20055
 * 메모리: 17292 KB
 * 시간: 332 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 지문 요구사항에 따라 메서드를 구현하여 풀이
 *
 * 1. 벨트 + 로봇 한칸 회전
 * 2. 로봇 이동
 * 3. 로봇 올리기
 * 4. 내구도 0개 갯수 검사 및 반복문 종료
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RobotsOnConveyorBelt {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int limit = info[1];
        int[] conveyorBelt = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.print(solution(conveyorBelt, limit));
    }

    private static int solution(int[] conveyorBelt, int limit) {
        boolean[] robots = new boolean[conveyorBelt.length / 2];

        int count = 0;

        do {
            rotateConveyorBeltWithRobots(conveyorBelt, robots);
            moveRobots(conveyorBelt, robots);
            putRobot(conveyorBelt, robots);
            count++;
        } while (countZeroConveyorBelt(conveyorBelt) < limit);

        return count;
    }

    private static int countZeroConveyorBelt(int[] conveyorBelt) {
        int count = 0;

        for (int i : conveyorBelt) {
            if (i <= 0) {
                count++;
            }
        }

        return count;
    }

    private static void rotateConveyorBeltWithRobots(int[] conveyorBelt, boolean[] robots) {
        int last = conveyorBelt[conveyorBelt.length - 1];
        for (int i = conveyorBelt.length - 1; i > 0; i--) {
            conveyorBelt[i] = conveyorBelt[i - 1];
        }
        for (int i = robots.length - 1; i > 0; i--) {
            robots[i] = robots[i - 1];
        }
        conveyorBelt[0] = last;
        robots[0] = false;
    }

    private static void moveRobots(int[] conveyorBelt, boolean[] robots) {
        robots[robots.length - 1] = false;
        for (int i = robots.length - 2; i >= 0; i--) {
            if (!robots[i] ||
                    conveyorBelt[i + 1] == 0 ||
                    robots[i + 1]) {
                continue;
            }
            robots[i] = false;
            robots[i + 1] = true;
            conveyorBelt[i + 1]--;
        }
    }

    private static void putRobot(int[] conveyorBelt, boolean[] robots) {
        if (conveyorBelt[0] > 0) {
            robots[0] = true;
            conveyorBelt[0]--;
        }
    }
}
