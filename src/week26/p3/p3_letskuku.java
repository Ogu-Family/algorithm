package week26.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/16938
 * 메모리: 14312 KB
 * 시간: 128 ms
 * 시간 복잡도: O(2^N)
 * 공간 복잡도: O(N)
 */

/*
1. 문제의 난이도를 입력받아
2. 재귀를 이용해 조건에 알맞은 문제집 조합 개수를 계산
3. 결과 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p3_letskuku {

    static int n, l, r, x, ans;
    static int[] level;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        level = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            level[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(level); // 난이도가 낮은 문제부터 문제집에 포함시키기 위해 오름차순 정렬

        ans = 0; // 문제집 개수
        func(0, 0, -1, 0);

        System.out.println(ans);
    }

    // 문제집 문제 수, 가장 난이도 낮은 문제의 난이도, 마지막으로 문제집에 넣은 문제의 인덱스, 문제집 난이도 합
    static void func(int size, int firLv, int lstIdx, int sum) {
        if (size == n || sum == r) {
            return;
        }

        for (int i = lstIdx + 1; i < n; i++) {
            if (size != 0) {
                int tmpSum = sum + level[i];
                if (tmpSum <= r) {
                    if ((tmpSum >= l) && (level[i] - firLv >= x)) {
                        ans++;
                    }

                    func(size + 1, firLv, i, tmpSum);
                } else {
                    return;
                }
            } else { // 문제집 사이즈 0이면 난이도가 r 이하인지만 확인
                if (level[i] <= r) {
                    func(size + 1, level[i], i, sum + level[i]);
                }
            }
        }
    }
}
