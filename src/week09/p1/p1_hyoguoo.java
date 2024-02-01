/**
 * 문제 링크: https://www.acmicpc.net/problem/15486
 * 메모리: 351540 KB
 * 시간: 1228 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * Dynamic Programming
 *
 * dp[i] i일 까지 상담을 했을 때 최대 금액
 * 1. i일에 할당 된 상담 조회
 * 2. i일에 할당 된 상담을 진행 했을 때의 금액과 진행하지 않았을 때의 금액을 비교하여 최대 금액을 저장
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Leave2 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int day = Integer.parseInt(bufferedReader.readLine());

        Schedule[] schedules = new Schedule[day + 1];

        for (int i = 1; i <= day; i++) {
            int[] info = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            schedules[i] = new Schedule(info[0], info[1]);
        }

        System.out.print(getMaxPay(schedules, day));
    }

    private static int getMaxPay(Schedule[] schedules, int maxDay) {
        int[] dp = new int[maxDay + 1]; // 상담일 크기의 DP 배열 생성, dp[i] = i일 까지 상담을 했을 때 최대 금액

        for (int day = 1; day <= maxDay; day++) {
            int time = schedules[day].time - 1; // 상담 소요 시간(당일 포함이기 때문에 -1)
            int pay = schedules[day].pay;
            dp[day] = Math.max(dp[day], dp[day - 1]); // 전날까지의 최대 금액과 해당일의 최대 금액을 비교하여 최대 금액을 저장
            if (day + time <= maxDay) { // 퇴사 전에 상담을 끝낼 수 있는 경우
                dp[day + time] = Math.max(dp[day + time], dp[day - 1] + pay); // 상담이 종료 예정 일 = Max(구해진 현재 금액, 현재 상담을 진행 했을 때의 금액)
            }
        }

        return dp[maxDay];
    }

    static class Schedule {
        int time;
        int pay;

        public Schedule(int time, int pay) {
            this.time = time;
            this.pay = pay;
        }
    }
}
