/**
 * 문제 링크: https://www.acmicpc.net/problem/1205
 * 메모리: 17684 KB
 * 시간: 228 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 0. 0명인 경우 1등이므로 1을 출력하고 바로 종료
 * 1. 본인의 점수보다 높은 점수가 나오면 등수를 1씩 증가 / 같은 점수가 나오면 카운트
 * 2. 본인의 등수 + 같은 점수의 개수가 limit보다 크면 -1 출력
 * 3. 그 외의 경우 본인의 등수 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FindRank {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays
                .stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        if (info[0] == 0) {
            System.out.print(1);
            return;
        }

        int[] scores = Arrays
                .stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.print(solution(scores, info[1], info[2]));
    }

    private static int solution(int[] scores, int myScore, int rankLimit) {
        int rank = 1;
        int sameScoreCount = 0;

        for (int score : scores) {
            if (score > myScore) {
                rank++;
            } else if (score == myScore) {
                sameScoreCount++;
            }
        }

        return rank + sameScoreCount > rankLimit
                ? -1
                : rank;
    }
}
