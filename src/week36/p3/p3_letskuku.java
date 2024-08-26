package week36.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/28018
 * 메모리: 63220 KB
 * 시간: 488 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/*
1. 각 학생의 좌석 배정 시각과 종료 시각을 입력받아 배정 시각은 +1, 종료 시각 + 1에는 -1로 저장
2. 특정 시각에 선택할 수 없는 좌석 수 계산
3. 결과 출력

누적 합이라는 건 눈치 챘는데 어떻게 활용해야할지 몰라 결국 구글링 참고한 문제...
다들 풀이 잘 찾아내셨나요...ㅠㅠㅠ
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p3_letskuku {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] time = new int[1000002];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            time[Integer.parseInt(st.nextToken())]++;
            time[Integer.parseInt(st.nextToken()) + 1]--;
        }

        int[] seat = new int[1000001];
        for (int i = 1; i <= 1000000; i++) {
            seat[i] = seat[i - 1] + time[i];
        }

        int q = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < q; i++) {
            sb.append(seat[Integer.parseInt(st.nextToken())] + "\n");
        }

        System.out.println(sb);
    }
}
