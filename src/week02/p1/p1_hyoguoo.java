/**
 * 문제 링크: https://www.acmicpc.net/problem/19941
 * 메모리: 14616 KB
 * 시간: 144 ms
 * 시간 복잡도: O(N * K)
 * 공간 복잡도: O(N)
 */

/**
 * 햄버거를 중심으로 한 그리디 알고리즘
 * 배열을 앞에서부터 순회하다가 햄버거를 만나면, 햄버거를 먹을 수 있는 사람을 탐색
 * 햄버거로부터 K 거리 이내에 있는 가장 왼쪽 사람에게 할당(배열에서 사람 제거)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DistributeHamburgers {

    static final char HAMBURGER = 'H';
    static final char PERSON = 'P';
    static final char EMPTY = 'X';

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int distanceLimit = info[1];
        char[] input = bufferedReader.readLine().toCharArray();

        System.out.println(solution(input, distanceLimit));
    }

    private static int solution(char[] input, int distanceLimit) {
        int count = 0;

        for (int currentIndex = 0; currentIndex < input.length; currentIndex++) {
            if (input[currentIndex] == HAMBURGER && // 햄버거를 찾으면
                eatBurger(input, distanceLimit, currentIndex)) count++; // 햄버거를 먹을 수 있는 사람을 탐색 후 추가
        }

        return count;
    }

    private static boolean eatBurger(char[] input, int distanceLimit, int currentIndex) {
        for (int distance = -distanceLimit; distance <= distanceLimit; distance++) { // K 거리 이내에 있는 범위 탐색
            int nextIndex = currentIndex + distance;
            if (!isValidIndex(input, nextIndex)) continue;
            if (input[nextIndex] == PERSON) { // 사람을 찾으면
                input[nextIndex] = EMPTY; // 사람을 제거
                return true;
            }
        }

        return false;
    }

    private static boolean isValidIndex(char[] input, int index) {
        return 0 <= index && index < input.length;
    }
}
