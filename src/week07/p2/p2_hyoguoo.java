/**
 * 문제 링크: https://www.acmicpc.net/problem/1931
 * 메모리: 76752 KB
 * 시간: 884 ms
 * 시간 복잡도: O(N logN)
 * 공간 복잡도: O(N)
 */

/**
 * 그리디 알고리즘
 *
 * 끝 시간이 가장 앞에 있는 것부터 선택(회의 시간이 빨리 끝나는 것부터 선택)
 * 만약 끝 시간이 같다면 시작 시간이 빠른 것부터 선택
 *
 * 웰노운이라 금방 풀었지만...
 */

package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingRoomAssignment {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(bufferedReader.readLine());
        List<Meeting> meetingList = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            int[] meeting = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            meetingList.add(new Meeting(meeting[0], meeting[1]));
        }

        System.out.print(getCountList(meetingList));
    }

    private static int getCountList(List<Meeting> meetingList) {
        meetingList.sort((o1, o2) -> {
            if (o1.endTime != o2.endTime) return o1.endTime - o2.endTime;
            return o1.startTime - o2.startTime;
        });

        int count = 0;
        int endTime = 0;

        for (Meeting meeting : meetingList) {
            if (endTime <= meeting.startTime) {
                endTime = meeting.endTime;
                count++;
            }
        }

        return count;
    }

    static class Meeting {
        public final int startTime;
        public final int endTime;

        public Meeting(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
