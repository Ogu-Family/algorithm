/**
 * 문제 링크: https://www.acmicpc.net/problem/1469
 * 메모리: 15680 KB
 * 시간: 160 ms
 * 시간 복잡도: O(N!)
 * 공간 복잡도: O(N)
 */

/**
 * 1. 사전 순으로 가장 빠른 것을 출력하기 위해 입력 값 오름차순 정렬
 * 2. 앞에서부터 순차적으로 탐색하며 해당 인덱스에 값을 넣고, 해당 인덱스 + 값 + 1(숌 사이 인덱스)에도 값 추가
 * 3. 만약 숌 사이 인덱스가 배열의 길이를 넘어가거나 이미 값이 존재한다면 해당 값은 사용할 수 없으므로 다음 인덱스로 넘어감
 * 4. 백트래킹 방식을 사용해 방문 체크 및 해제를 통해 모든 경우의 수를 탐색
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SequencesBetweenShims {

    private static final int NOT_VISITED = -1;
    private static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        int[] numbers = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.print(solution(numbers));
    }

    private static String solution(int[] numbers) {
        Arrays.sort(numbers);
        int[] temp = new int[numbers.length * 2];
        Arrays.fill(temp, NOT_VISITED);
        recursive(numbers, 0, temp, new boolean[numbers.length]);

        return result == null
                ? "-1"
                : Arrays.toString(result).replaceAll("[\\[\\],]", "");
    }

    private static boolean recursive(int[] numbers, int index, int[] temp, boolean[] isUsed) {
        if (isAllUsed(isUsed)) {
            result = Arrays.copyOf(temp, temp.length);
            return true;
        }

        for (int i = index; i < temp.length; i++) {
            if (temp[i] != NOT_VISITED) {
                continue;
            }

            for (int j = 0; j < numbers.length; j++) {
                int value = numbers[j];
                int shimIndex = i + value + 1;
                if (isUsed[j] ||
                        shimIndex >= temp.length ||
                        temp[shimIndex] != NOT_VISITED) {
                    continue;
                }

                temp[i] = value;
                temp[shimIndex] = value;
                isUsed[j] = true;
                if (recursive(numbers, i + 1, temp, isUsed)) {
                    return true;
                }
                temp[i] = NOT_VISITED;
                temp[shimIndex] = NOT_VISITED;
                isUsed[j] = false;
            }
        }

        return false;
    }

    private static boolean isAllUsed(boolean[] isUsed) {
        for (boolean used : isUsed) {
            if (!used) {
                return false;
            }
        }

        return true;
    }
}
