package src.week08.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2_GiHoo {

    static int N;
    static long[] muscleLossArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        muscleLossArray = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            muscleLossArray[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(muscleLossArray);

        long muscleLoss = getMuscleLoss();

        System.out.println(muscleLoss);
    }

    private static long getMuscleLoss() {
        long muscleLoss;

        long tmp = 0;
        if (N % 2 == 0) {
            for (int i = 0; i < N / 2; i++) {
                tmp = Long.max(tmp, muscleLossArray[i] + muscleLossArray[N - 1 - i]);
            }
        } else {
            for (int i = 0; i < N / 2; i++) {
                tmp = Long.max(tmp, muscleLossArray[i] + muscleLossArray[N - 2 - i]);
            }
            tmp = Long.max(tmp, muscleLossArray[N - 1]);
        }
        muscleLoss = tmp;

        return muscleLoss;
    }
}

/*
  문제 링크: https://www.acmicpc.net/problem/20300
  메모리: 19024 KB
  시간: 240 ms
  시간 복잡도: O(NlogN)
  공간 복잡도: O(N)

  풀이
  기구의 수가 홀수일 때와 짝수일 때를 나누어 생각

  1. 공통
  배열을 오름차순으로 정렬한다.

  1-1) 홀수
  배열의 마지막 값을 제외한 값들을 짝지어 비교한다.
  1번째 + N - 1번째, 2번쪠 + N - 2번째 ...
  마지막으로 이 값들과 N번째 값을 비교한다.

  1-2) 짝수
  홀수의 마지막 과정만 제외하고 같은 방식으로 진행한다.
 */
