package src.week36.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/28018
 * 메모리: 265016 KB
 * 시간: 1464 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * 누적 합
 * 인데 이분 탐색으로 풀어보았습니다.
 *
 * 1. 우선순위 큐(studentTime) 생성. 원소는 {시간, 시작인지 여부}가 들어가고, 시간 순서대로 오름차순 정렬.
 * 2. 특정 시간의 사용할 수 없는 좌석 개수를 저장하는 리스트(unavailableSeatNum)를 생성. 원소는 {시간, 그 시간에서의 사용할 수 없는 좌석 개수}
 * 3. count 변수를 0으로 초기화하고, studentTime이 비워질 때까지 원소를 뽑으면서 아래를 수행
 * 3-1. 시작 시간이면 count++ 하고 unavailableSeatNum에 {시간, count} 삽입
 * 3-2. 종료 시간이면 count-- 하고 unavailableSeatNum에 {시간, count} 삽입
 * 4. 좌석 개수를 찾고자 하는 시간에 대해 upperbound 이분 탐색을 진행하여 정답을 출력. upperbound인 이유는 그것이 최신 좌석 개수를 반영하기 때문.
 *
 * 이 풀이는 예약 시간이 매우 커도 효과적으로 수행합니다.
 */

import java.util.*;
import java.util.stream.*;

public class p3_ZZAMBAs{
    static final Scanner sc = new Scanner(System.in);
    static final StringBuilder sb = new StringBuilder();

    static int N;
    static int Q;
    static PriorityQueue<Pair<Integer, Boolean>> studentTime = new PriorityQueue<>(Comparator.comparing(Pair::getX)); // true: 시작, false: 종료
    static List<Pair<Integer, Integer>> unavailableSeatNum = new ArrayList<>();

    public static void main(String[] args) {
        N = sc.nextInt();
        IntStream.range(0, N).forEach(i -> {
            studentTime.add(new Pair<>(sc.nextInt(), true));
            studentTime.add(new Pair<>(sc.nextInt() + 1, false)); // 종료 시각까지 자리를 지키므로, +1로 end를 표시
        });

        sortUnavailableSeatNum();

        Q = sc.nextInt();
        IntStream.range(0, Q).forEach(i -> {
            int seatNum = findUnavailableSeatNum(sc.nextInt());

            sb.append(seatNum).append('\n');
        });

        System.out.print(sb);
    }

    static void sortUnavailableSeatNum() { // 입력 받은 값을 시간 순으로 확인하며 누적 좌석 수를 세고 unavailableSeatNum 에 넣는다.
        int count = 0;

        while (!studentTime.isEmpty()) {
            Pair<Integer, Boolean> front = studentTime.poll();
            int time = front.x;
            boolean isStart = front.y;

            if (isStart)
                count++;
            else
                count--;

            unavailableSeatNum.add(new Pair<>(time, count));
        }
    }

    static int findUnavailableSeatNum(int time) { // unavailableSeatNum 에서 찾고자 하는 시간과 가장 가까운 시간을 찾고 제일 나중 값을 출력
        int s = 0, e = unavailableSeatNum.size() - 1;

        while (s <= e) {
            int m = (s + e) / 2;
            int curTime = unavailableSeatNum.get(m).x;

            if (curTime <= time)
                s = m + 1;
            else
                e = m - 1;
        }

        return e < 0 ? 0 : unavailableSeatNum.get(e).y;
    }

    static class Pair<T, R> {
        T x;
        R y;

        Pair(T x, R y) {
            this.x = x;
            this.y = y;
        }

        T getX() {
            return x;
        }
    }
}

