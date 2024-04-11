/**
 * 문제 링크: https://www.acmicpc.net/problem/16926
 * 메모리: 36148 KB
 * 시간: 792 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N*M)
 */

/**
 * 사각형 테두리를 하나씩 안으로 만들어가면서 회전
 *
 * 방향을 Enum으로 정의하여 회전 순서 및 방향을 정의하여 풀이해보았습니다~
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RotateArray1 {

    static final BufferedReader BUFFERED_READER =
            new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] info = Arrays.stream(BUFFERED_READER.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rotateCount = info[2];
        int[][] array = parseArray(info);

        System.out.print(solution(array, rotateCount));
    }

    private static int[][] parseArray(int[] info) throws IOException {
        int nSize = info[0];
        int mSize = info[1];
        int[][] array = new int[nSize][mSize];

        for (int n = 0; n < nSize; n++) {
            array[n] = Arrays.stream(BUFFERED_READER.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        return array;
    }

    private static String solution(int[][] array, int rotateCount) {
        rotateArrayByGroup(
                array,
                rotateCount,
                Math.min(array.length, array[0].length) / 2,
                0
        );

        return toStringArray(array);
    }

    private static void rotateArrayByGroup(
            int[][] array,
            int rotateCount,
            int groupLength,
            int offset
    ) {
        if (offset >= groupLength) { // 중심점까지 도달하면 종료
            return;
        }

        for (int r = 0; r < rotateCount; r++) { // 회전 횟수만큼 회전
            rotateGroup(array, offset, groupLength);
        }

        rotateArrayByGroup(array, rotateCount, groupLength, offset + 1); // 다음 그룹(안쪽)으로 이동
    }

    private static void rotateGroup(int[][] array, int offset, int groupLength) {
        int n = offset;
        int m = offset;
        // 왼 쪽위부터 시작
        int temp = array[offset][offset];

        // 오른쪽 -> 아래 -> 왼쪽 -> 위 순서로 이동
        for (Direction direction : Direction.values()) {
            while (true) {
                int nextN = n + direction.n;
                int nextM = m + direction.m;

                // 범위를 벗어나면 종료, while을 종료하여 다음 방향으로 변경
                if (!isInBound(array, offset, nextN, nextM)) {
                    break;
                }

                array[n][m] = array[nextN][nextM];
                n = nextN;
                m = nextM;
            }
        }

        // 그룹의 길이가 1보다 크면 임시로 저장한 값을 할당
        if (isNotSingleElement(offset, groupLength)) {
            array[offset + 1][offset] = temp;
        }
    }

    private static boolean isInBound(int[][] array, int offset, int n, int m) {
        return offset <= n && n < array.length - offset &&
                offset <= m && m < array[0].length - offset;
    }

    private static boolean isNotSingleElement(int offset, int groupLength) {
        return offset + 1 <= groupLength;
    }

    private static String toStringArray(int[][] rotatedArray) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int[] line : rotatedArray) {
            for (int m = 0; m < rotatedArray[0].length; m++) {
                stringBuilder.append(line[m]).append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString().trim();
    }

    enum Direction {
        RIGHT(0, 1),
        DOWN(1, 0),
        LEFT(0, -1),
        UP(-1, 0);

        private final int n;
        private final int m;

        Direction(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }
}
