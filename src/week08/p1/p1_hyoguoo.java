/**
 * 문제 링크: https://www.acmicpc.net/problem/26007
 * 메모리: 360400 KB
 * 시간: 1676 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 누적합
 *
 * 1. 최초 레이팅과 증감 레이팅을 이용하여 각 라운드 후 레이팅 계산
 * 2. 레이팅이 목표보다 낮은 라운드 개수 계산(누적하여 계산)
 * 3. 쿼리에 대한 결과 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Codepowers {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int queryCount = info[1];
        int targetRating = info[2];
        int initRating = info[3];

        int[] ratingIncreases = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Query> queries = new ArrayList<>();
        for (int i = 0; i < queryCount; i++) {
            int[] input = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            queries.add(new Query(input[0], input[1]));
        }

        solution(targetRating, initRating, ratingIncreases, queries);
    }

    private static void solution(int targetRating, int initRating, int[] ratingIncreases, List<Query> queries) {
        // 1. 레이팅 계산
        int[] ratings = calculateRating(initRating, ratingIncreases);
        // 2. 레이팅이 목표보다 낮은 라운드 개수 계산(누적하여 계산)
        int[] lowRatingCount = calculateLowRatingCount(targetRating, ratings);

        // 3. 쿼리에 대한 결과 출력
        printResult(queries, lowRatingCount);
    }

    private static int[] calculateRating(int initRating, int[] ratingIncreases) {
        int[] ratings = new int[ratingIncreases.length + 1];
        ratings[0] = initRating;

        for (int i = 1; i < ratings.length; i++) {
            ratings[i] = ratings[i - 1] + ratingIncreases[i - 1];
        }

        return ratings;
    }

    private static int[] calculateLowRatingCount(int targetRating, int[] ratings) {
        int[] llowRatingCount = new int[ratings.length];

        for (int i = 1; i < llowRatingCount.length; i++) {
            // 레이팅이 목표보다 낮으면 이전 라운드에서 계산한 누적 개수 + 1
            if (ratings[i] < targetRating) llowRatingCount[i] = llowRatingCount[i - 1] + 1;
            // 레이팅이 목표보다 높거나 같으면 이전 라운드에서 계산한 값 그대로
            else llowRatingCount[i] = llowRatingCount[i - 1];
        }

        return llowRatingCount;
    }

    private static void printResult(List<Query> queries, int[] lowRatingCount) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Query query : queries) {
            stringBuilder.append(lowRatingCount[query.rightRound - 1] - lowRatingCount[query.leftRound - 1]).append("\n");
        }

        System.out.print(stringBuilder.toString().trim());
    }

    static class Query {
        int leftRound;
        int rightRound;

        public Query(int leftRound, int rightRound) {
            this.leftRound = leftRound;
            this.rightRound = rightRound;
        }
    }
}
