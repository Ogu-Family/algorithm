package algorithm.src.week34.p3;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/31963
 * 메모리: 37716 KB
 * 시간: 424 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 수학
 * 풀이 참고한 문제
 * num[i-1] <= num[i] * 2^x;
 * num[i-1]/num[i] <= 2^x;
 * log(num[i-1]/num[i]) <= x
 * -> 해당 수식으로, x값 찾은 후 num[i] 값 업데이트 해주며 진행하니 서브태스크 1, 2, 3만 맞아서 풀이 참고(왜 안되는지 모르겠네여..ㅎㅎ)
 *
 * num[i] 값을 직접 업데이트 하지 않고, 이전 값과 비교하여 2를 곱하는 횟수만 저장하며 계산
 */

public class p3_JeongeunChoi {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long answer = 0;
        int[] num = new int[N];
        long[] mulCnt = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            long cnt = (long) (Math.ceil(Math.log(num[i - 1] / (double) num[i]) / Math.log(2) + mulCnt[i-1]));
            mulCnt[i] = Math.max(0, cnt);
            answer += mulCnt[i];
        }

        // 아래와 같이 풀었더니, 서브태스크 1 2 3만 정답
//        for (int i = 1; i < N; i++) {
//            if (num[i - 1] > num[i]) {
//                long cnt = (long) (Math.ceil(Math.log(num[i - 1] / (double) num[i]) / Math.log(2)));
//                answer += cnt;
//                num[i] *= (long) Math.pow(2, cnt);
//            }
//        }

        System.out.println(answer);
    }

}
