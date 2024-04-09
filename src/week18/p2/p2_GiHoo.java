package src.week18.p2;

import java.util.*;

/**
 * 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42586
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N)
 *
 * Deque 사용한 풀이
 * Deque 각각 진행률과 작업 속도를 만든다.
 * 진행률에 작업 속도를 더해주는 작업을 하고, 진행률이 100 이상인 경우에 poll 작업을 수행
 * poll 한 만큼 카운트하여 리스트에 값 넣고, 배열로 반환
 */

public class p2_GiHoo {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();

        Deque<Integer> d_progresses = new ArrayDeque<>();
        Deque<Integer> d_speeds = new ArrayDeque<>();

        for (int i = 0; i < progresses.length; i++) {
            d_progresses.addLast(progresses[i]);
            d_speeds.addLast(speeds[i]);
        }

        while (!d_progresses.isEmpty()) {
            int cnt = 0;

            for (int i = 0; i < d_progresses.size(); i++) {
                int progress = d_progresses.pollFirst();
                int speed = d_speeds.pollFirst();
                progress += speed;
                d_progresses.addLast(progress);
                d_speeds.addLast(speed);
            }

            // 100 이상이면 출하
            while (!d_progresses.isEmpty() && d_progresses.peekFirst() >= 100) {
                cnt++;
                d_progresses.pollFirst();
                d_speeds.pollFirst();
            }

            if(cnt != 0) answer.add(cnt);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
