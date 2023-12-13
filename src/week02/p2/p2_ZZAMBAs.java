package src.week02.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/11000
 * 메모리: 316128 KB
 * 시간: 1528 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * 모든 강의에 대해 시작 시간과 끝나는 시간을 우선순위 큐에 넣는다. 그러면 우선순위 큐는 시간 순 정렬이 된다.
 * 이제 하나씩 꺼내면서 시작 시간이면 방을 1개 더하고, 끝나는 시간이면 방을 1개 뺀다. 이 과정에서 최대 방 개수를 계속 계산한다.
 * 마지막으로 최대 방 개수를 출력하면 끝.
 * (비트마스킹 방식으로 시작 시간인지 끝나는 시간인지 체킹한다.)
 *
 * 문제에서 중요한 부분
 * - 최소의 강의실을 사용해서 모든 수업을 가능하게 해야 한다.
 * - Ti ≤ Sj 일 경우 i 수업과 j 수업은 같이 들을 수 있다.
 */

import java.util.PriorityQueue;
import java.util.Scanner;

public class p2_ZZAMBAs {
    static int statusBit = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> subjects = new PriorityQueue<>();
        int N = sc.nextInt();
        int start, end, maxRoom = 0, nowRoom = 0;

        while (N-- > 0) {
            start = sc.nextInt() << 1;
            end = sc.nextInt() << 1;

            subjects.add(start);
            subjects.add(end);
        }

        while (!subjects.isEmpty()) {
            if (maxRoom < nowRoom)
                maxRoom = nowRoom;

            Integer top = subjects.poll();

            boolean isStart = (top & statusBit) == 1;

            if (isStart)
                nowRoom++;
            else
                nowRoom--;
        }

        System.out.println(maxRoom);
    }
}
