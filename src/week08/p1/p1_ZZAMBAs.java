package src.week08.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/26007
 * 메모리: 306000 KB
 * 시간: 1480 ms
 * 시간 복잡도: O(N + M)
 * 공간 복잡도: O(N + M)
 */

/**
 * 누적 합
 * 이 문제 Scanner로 풀 수 없다. 이유가 뭘까. 궁금해서 질문을 올려놓았다.
 * https://www.acmicpc.net/board/view/134743
 *
 * 문제에서 중요한 부분
 * - l번째 라운드에 참여한 직후부터 r번째 라운드에 참여하기 직전까지 레이팅이 K보다 낮은 횟수를 출력.
 */

import java.io.*;
import java.util.Arrays;

public class p1_ZZAMBAs{
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = info[0], M = info[1], K = info[2], X = info[3];
        int[] prefixSum = new int[N + 1], res = new int[M];

        int[] ratingIncreases = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < N; i++) {
            X = X + ratingIncreases[i];
            prefixSum[i + 1] = (X < K) ? prefixSum[i] + 1 : prefixSum[i];
        }

        for (int i = 0; i < M; i++) {
            int[] input = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int l = input[0], r = input[1];

            res[i] = prefixSum[r - 1] - prefixSum[l - 1];
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int r : res) {
            bw.write(String.valueOf(r));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
