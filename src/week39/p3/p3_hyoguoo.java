/**
 * 문제 링크: https://www.acmicpc.net/problem/9011
 * 메모리: 18276 KB
 * 시간: 156 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 1. 사용 가능한 숫자 목록을 내림차순으로 생성
 * 2. 입력받은 수열 r을 뒤에서부터 순회하며 nums에서 ri번째 값을 계산
 *   - ri가 음수이면 IMPOSSIBLE 출력
 * 3. 숫자 목록에서 ri번째 값을 제거
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCount = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        while (testCount-- > 0) {
            bufferedReader.readLine();
            int[] numbers = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            stringBuilder.append(solution(numbers)).append("\n");
        }

        System.out.print(stringBuilder.toString().trim());
    }

    private static String solution(int[] numbers) {
        List<Integer> availableNumberList = getAvailableNumberList(numbers);
        int[] answer = new int[numbers.length];

        for (int i = numbers.length - 1; i >= 0; i--) {
            int diff = i - numbers[i];

            if (diff < 0) {
                return "IMPOSSIBLE";
            }

            answer[i] = availableNumberList.remove(diff);
        }

        return Arrays.toString(answer).replaceAll("[\\[\\],]", "");
    }

    private static List<Integer> getAvailableNumberList(int[] numbers) {
        List<Integer> availableNumberList = new ArrayList<>();
        for (int i = numbers.length; i > 0; i--) {
            availableNumberList.add(i);
        }
        return availableNumberList;
    }
}
