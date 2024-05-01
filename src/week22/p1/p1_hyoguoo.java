/**
 * 문제 링크: https://www.acmicpc.net/problem/23835
 * 메모리: 83164 KB
 * 시간: 1212 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 1. 시직 시간 순으로 정렬 된 배열과 종료 시간 순 우선순위 큐 생성
 * 2. 시간 순으로 정렬 된 배열을 순회
 * 3. 우선순위 큐에서 종료 시간이 현재 회의 시작 시간보다 빨리 끝나는 회의들을 제거
 * 4. 우선순위 큐에 현재 회의 추가
 * 5. 최대 회의실 개수 갱신(남은 회의실의 갯수 == 겹치는 회의실의 갯수)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumNumberOfRooms {

    public static void main(String[] args) throws IOException {
        Meeting[] meetings = parseMeetings();

        System.out.print(solution(meetings));
    }

    private static int solution(Meeting[] meetings) {
        Arrays.sort(
                meetings,
                Comparator.comparingInt(o -> o.start)
        );
        PriorityQueue<Meeting> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(o -> o.end)
        );

        int count = 0;

        for (Meeting meeting : meetings) {
            deleteNonOverlappingMeeting(meeting, priorityQueue);

            priorityQueue.add(meeting);
            count = Math.max(count, priorityQueue.size());
        }

        return count;
    }

    private static void deleteNonOverlappingMeeting(
            Meeting meeting,
            PriorityQueue<Meeting> priorityQueue
    ) {
        while (!priorityQueue.isEmpty() &&
                priorityQueue.peek().end <= meeting.start) {
            priorityQueue.poll();
        }
    }


    private static Meeting[] parseMeetings() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int meetingCount = Integer.parseInt(bufferedReader.readLine());

        Meeting[] meetings = new Meeting[meetingCount];

        while (meetingCount-- > 0) {
            int[] meetingInfo = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            meetings[meetingCount] = new Meeting(meetingInfo[0], meetingInfo[1]);
        }

        return meetings;
    }

    static class Meeting {

        private final int start;
        private final int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
