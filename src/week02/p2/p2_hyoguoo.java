/**
 * 문제 링크: https://www.acmicpc.net/problem/11000
 * 메모리: 126164 KB
 * 시간: 1284 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * 시작/종료 시간 값을 가진 Meeting 리스트 초기화
 * List: 시작 시간 기준 오름차순 정렬
 * PriorityQueue: 종료 시간 기준 오름차순 우선순위 큐
 *
 * 모든 회의 순회하면서
 * 우선순위 큐에 있는 회의 중 현재 회의와 겹치지 않는 회의 삭제
 * 그 후 현재 회의를 추가한 뒤
 * 최대 회의실 개수 갱신
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MinimumNumberOfRooms {

    public static void main(String[] args) throws IOException {
        System.out.print(solution(initilizeMeetingList()));
    }

    private static List<Meeting> initilizeMeetingList() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        List<Meeting> meetingList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int[] meetingInfo = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            meetingList.add(new Meeting(meetingInfo[0], meetingInfo[1]));
        }
        return meetingList;
    }

    private static int solution(List<Meeting> meetingList) {
        meetingList.sort(Comparator.comparingInt(o -> o.start)); // 시작 시간 기준 오름차순 정렬
        PriorityQueue<Meeting> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.end)); // 종료 시간 기준 오름차순 우선순위 큐

        int count = 0;

        for (Meeting meeting : meetingList) { // 모든 회의 순회
            deleteNonOverlappingMeeting(meeting, priorityQueue); // 현재 회의와 겹치지 않는 회의 삭제

            priorityQueue.add(meeting); // 현재 회의 추가
            count = Math.max(count, priorityQueue.size()); // 최대 회의실 개수 갱신
        }

        return count;
    }

    private static void deleteNonOverlappingMeeting(Meeting meeting, PriorityQueue<Meeting> priorityQueue) {
        while (!priorityQueue.isEmpty() && // 우선순위 큐가 비어있지 않고
               priorityQueue.peek().end <= meeting.start) priorityQueue.poll(); // 겹치지 않으면(종료 시간이 시작 시간보다 작거나 같으면) 삭제
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
