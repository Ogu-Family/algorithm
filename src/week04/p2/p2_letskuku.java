package week04.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/20115
 * 메모리: 31612 KB
 * 시간: 540 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/*
1. 에너지 드링크들의 양을 입력 받아 오름차순으로 정렬
2. 가장 양이 많은 드링크를 기준으로 두고, 나머지 드링크들을 반만큼 부음
3. 합산한 에너지 드링크의 양 출력
 */

import java.io.*;
import java.util.*;

public class p2_letskuku {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        double ans = 0;
        List<Integer> drinks = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            drinks.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(drinks);

        ans += drinks.get(n - 1);
        for (int i = 0; i < n - 1; i++) {
            ans += ((double) drinks.get(i)) / 2;
        }

        System.out.println(ans);
    }
}
