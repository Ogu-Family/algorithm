/**
 * 문제 링크: https://www.acmicpc.net/problem/15922
 * 메모리: 75864 KB
 * 시간: 624 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 1. 선분 배열 저장(이미 정렬된 상태)
 * 2. 선분의 끝점을 저장할 변수 생성
 * 3. 선분을 순회하면서 선분이 완전히 겹치는 경우, 부분적으로 겹치는 경우, 겹치지 않는 경우로 나누어 처리
 *  3-1. 선분이 완전히 겹치는 경우: 아무것도 하지 않음
 *  3-2. 선분이 부분적으로 겹치는 경우: 겹치는 부분만큼 길이 추가
 *  3-3. 선분이 겹치지 않는 경우: 해당 선분의 길이만큼만 추가
 * 4. 결과 반환
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ooohhhhh {

    public static void main(String[] args) throws IOException {
        LineSegment[] lineSegments = parseLineSegments();

        System.out.print(solution(lineSegments));
    }

    private static LineSegment[] parseLineSegments() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int lineSegmentCount = Integer.parseInt(bufferedReader.readLine());

        LineSegment[] lineSegments = new LineSegment[lineSegmentCount];

        for (int i = 0; i < lineSegmentCount; i++) {
            int[] lineSegmentInfo = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            lineSegments[i] = new LineSegment(lineSegmentInfo[0], lineSegmentInfo[1]);
        }
        return lineSegments;
    }

    private static int solution(LineSegment[] lineSegments) {
        int end = lineSegments[0].getEnd();
        int result = lineSegments[0].getLength();

        for (LineSegment lineSegment : lineSegments) {
            if (lineSegment.isFullyOverlapped(end)) {
                continue;
            } else if (lineSegment.isPartiallyOverlapped(end)) {
                result += lineSegment.getEnd() - end;
            } else {
                result += lineSegment.getLength();
            }
            end = lineSegment.getEnd();
        }

        return result;
    }

    static class LineSegment {

        private final int start;
        private final int end;

        public LineSegment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getEnd() {
            return end;
        }

        public boolean isFullyOverlapped(int end) {
            return this.end <= end;
        }

        public boolean isPartiallyOverlapped(int end) {
            return this.start <= end;
        }

        public int getLength() {
            return end - start;
        }
    }
}
