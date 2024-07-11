package week31.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1461
 * 메모리: 14256 KB
 * 시간: 124 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/*
1. 책의 원래 위치, 세준이가 한 번에 들 수 있는 책의 수를 입력받아
2. 책의 원래 위치를 양수, 음수로 나눠 저장한 것을 정렬
3. 각각 멀리서부터 책을 갖다놓았을 때 필요한 왕복 걸음 수 계산
4. 양수 위치와 음수 위치 중 가장 먼 곳을 마지막에 간다고 가정하고, 계산한 값에서 가장 먼 곳 거리 차감
5. 결과 출력

3에서 멀리서부터 책을 갖다놓는다고 가정해야 마지막 순서에 세준이가 한 번에 들 수 있는 책의 수보다
갖다놓을 책의 수가 더 적을 경우 필요한 걸음 수를 최소한으로 계산 가능
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class p1_letskuku {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer> minus = new ArrayList<>();
        List<Integer> plus = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(st.nextToken());

            if (t > 0) {
                plus.add(t);
            } else {
                minus.add(-t);
            }
        }

        Collections.sort(minus);
        Collections.sort(plus);
        int minusIdx = minus.size() - 1;
        int plusIdx = plus.size() - 1;
        int ans = 0;
        for (int i = minusIdx; i >= 0; i -= m) {
            ans += (minus.get(i) * 2);
        }
        for (int i = plusIdx; i >= 0; i -= m) {
            ans += (plus.get(i) * 2);
        }

        int minusMax = minusIdx < 0 ? 0 : minus.get(minusIdx);
        int plusMax = plusIdx < 0 ? 0 : plus.get(plusIdx);
        ans -= Math.max(minusMax, plusMax);

        System.out.println(ans);
    }
}
