package algorithm.src.week22.p1;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/19598
 * 메모리: 49916 KB
 * 시간: 700 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * 구현
 * 1. 회의 목록을 시작 시간 기준으로 오름차순 정렬(시작 시간 같은 경우 종료시간 기준 오름차순 정렬)
 * 2. 회의를 하나씩 진행한다.
 * 2-1. 가장 빨리 종료되는 회의 후에 진행 가능하면, 해당 종료 시간을 삭제한다.
 * 2-2. 현재 회의 종료 시간을 넣는다.
 * 3. 종료 시간의 개수가 필요한 회의실의 최소 개수가 된다.
 */


public class p1_JeongeunChoi {

    private static ArrayList<Meeting> meetings = new ArrayList<>();
    private static PriorityQueue<Integer> endTimes = new PriorityQueue<>();
    private static int meetingCnt;

    private static void proceedMeeting() {
        endTimes.add(meetings.get(0).e);
        for (int i = 1; i < meetingCnt; i++) {
            Meeting meeting = meetings.get(i);
            if (meeting.s >= endTimes.peek()) { // 2-1. 가장 빨리 종료되는 회의 후에 진행 가능하면, 해당 종료 시간을 삭제한다.
                endTimes.poll();
            }
            endTimes.add(meeting.e); // 2-2. 현재 회의 종료 시간을 넣는다.
        }
    }

    private static void input() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        meetingCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < meetingCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            meetings.add(new Meeting(s, e));
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        Collections.sort(meetings); // 1. 회의 목록을 시작 시간 기준으로 오름차순 정렬(시작 시간 같은 경우 종료시간 기준 오름차순 정렬)
        proceedMeeting(); // 2. 회의를 하나씩 진행한다.

        System.out.println(endTimes.size()); // 3. 종료 시간의 개수가 필요한 회의실의 최소 개수가 된다.
    }

    public static class Meeting implements Comparable<Meeting> {

        int s, e;

        public Meeting(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Meeting other) {
            if (this.s != other.s) {
                return this.s - other.s;
            } else {
                return this.e - other.e;
            }
        }
    }
}
