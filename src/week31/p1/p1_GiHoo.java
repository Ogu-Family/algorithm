package src.week31.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1461
 * 메모리: 14180 KB
 * 시간: 124 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * 0으로 부터 가장 멀리 떨어진 곳을 편도 이동 -> 가장 적게 이동할 수 있음
 * 음수와 양수 좌표를 나눔 - 0을 지나게 되면 결국 책을 새로 주울 수 있기 때문
 *
 * 음수 / 양수로 입력 값을 나누고 가장 큰 값을 편도로 이동
 * 나머지는 최대로 들 수 있는 책의 수만큼 이동
 */

public class p1_GiHoo {

    static int bookCount, maxBookCarried;
    static List<Integer> positive = new ArrayList<>();
    static List<Integer> negative = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();

        System.out.print(solution());
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        bookCount = Integer.parseInt(st.nextToken());
        maxBookCarried = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < bookCount; i++) {
            int position = Integer.parseInt(st.nextToken());

            if (position < 0) negative.add(Math.abs(position));
            else positive.add(position);
        }
    }

    private static int solution() {
        positive.sort(Collections.reverseOrder());
        negative.sort(Collections.reverseOrder());

        int answer = 0;

        if (positive.isEmpty()) answer += negative.get(0);
        else if (negative.isEmpty()) answer += positive.get(0);
        else {
            answer += 2 * Math.min(positive.get(0), negative.get(0));
            answer += Math.max(positive.get(0), negative.get(0)); // 가장 큰 값은 편도로 이동
        }

        for (int i = maxBookCarried; i < positive.size(); i += maxBookCarried) {
            answer += 2 * positive.get(i);
        }

        for (int i = maxBookCarried; i < negative.size(); i += maxBookCarried) {
            answer += 2 * negative.get(i);
        }

        return answer;
    }
}

