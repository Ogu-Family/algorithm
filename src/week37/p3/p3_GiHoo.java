package src.week37.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 문제 링크: https://www.acmicpc.net/problem/14677
 * 메모리: 16540 KB
 * 시간: 112 ms
 * 시간 복잡도: O(2^N)
 * 공간 복잡도: O(N)
 */

/**
 * BFS 문제
 * input 문자열 중 아침 -> 점심 -> 저녁 순으로 먹을 수 있는 최대 약의 개수를 확인하는 문제
 * 시작이 아침이니까 편의상 큐에 저녁약을 시작으로 두고 입력 문자열의 앞, 뒤를 체크하여 순서를 확인하고 큐에 삽입
 * 메모리 초과를 방지하기 위해 map을 사용해서 중복체크
 */

public class p3_GiHoo {

    static char[] BLD = {'B', 'L', 'D'};

    static Queue<Pill> queue = new LinkedList<>();
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String input = br.readLine();

        int answer = 0;

        queue.add(new Pill(input, 0, 2));
        map.put(input, map.getOrDefault(input, 0) + 1);
        while (!queue.isEmpty()) {
            Pill pill = queue.poll();

            answer = Math.max(answer, pill.eatPillCounts);

            String remainingPills = pill.remainingPills;
            int nextEatingPillNumber = (pill.previousEatingPillNumber + 1) % 3;
            char nextEatingPill = BLD[nextEatingPillNumber];

            if (!remainingPills.isEmpty()) {
                if (remainingPills.charAt(0) == nextEatingPill) {
                    String tmp = remainingPills.substring(1);

                    validateDuplication(tmp, pill, nextEatingPillNumber);
                }

                if (remainingPills.charAt(remainingPills.length() - 1) == nextEatingPill) {
                    String tmp = remainingPills.substring(0, remainingPills.length() - 1);

                    validateDuplication(tmp, pill, nextEatingPillNumber);
                }
            }
        }

        System.out.print(answer);
    }

    private static void validateDuplication(String tmp, Pill pill, int nextEatingPillNumber) {
        if (!map.containsKey(tmp)) {
            queue.add(new Pill(tmp, pill.eatPillCounts + 1, nextEatingPillNumber));
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
        }
    }

    private static class Pill {
        String remainingPills;
        int eatPillCounts;
        int previousEatingPillNumber;

        public Pill(String remainingPills, int eatPillCounts, int previousEatingPillNumber) {
            this.remainingPills = remainingPills;
            this.eatPillCounts = eatPillCounts;
            this.previousEatingPillNumber = previousEatingPillNumber;
        }
    }
}
