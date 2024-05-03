package src.week22.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/23835
 * 메모리: 50940 KB
 * 시간: 744 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * 미팅 시간을 meeting 리스트에 넣고, 다음과 같이 정렬
 *  시작 시간 기준으로 오름차순 정렬을 하고, 만약 시작 시간이 같다면 종료 시간을 기준으로 오룸차순 정렬
 *
 * 위와 같이 정렬된 리스트를 우선순위 큐에 종료 시간을 넣는다.
 * 우선순위 큐 최상단에 있는 노드가 가장 빨리 끝나는 회의 시간이 될 것이고, 이를 다음 meetingList의 시작시간과 비교한다.
 *
 * 저번에 풀어봤던 문제라 어렵지 않았떤 것 같습니다~
 */

public class p1_GiHoo {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        List<Meeting> meetingList = new ArrayList<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            meetingList.add(new Meeting(start, end));
        }

        meetingList.sort(((o1, o2) -> {
            if (o1.start == o2.start) return o1.end - o2.end;
            else return o1.start - o2.start;
        }));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (Meeting meeting : meetingList) {
            if (pq.isEmpty()) {
                pq.add(meeting.end);
            } else {
                if (pq.peek() <= meeting.start) {
                    pq.poll();
                    pq.add(meeting.end);
                } else {
                    pq.add(meeting.end);
                }
            }
        }

        System.out.print(pq.size());
    }

    static class Meeting {

        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }
}
