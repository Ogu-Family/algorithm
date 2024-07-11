package week32.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2470
 * 메모리: 31412 KB
 * 시간: 372 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/*
1. 용액의 특성값을 입력받아
2. 정렬 후 투 포인터로 합이 0에 가까운 두 용액 탐색
3. 결과 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class p3_letskuku {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        int prev = 2000000001;
        int start = 0;
        int end = n - 1;
        int[] ans = new int[2];
        while (start < end) {
            int tmp = list.get(start) + list.get(end);

            if (tmp == 0) {
                System.out.println(list.get(start) + " " + list.get(end));
                return;
            } else if (tmp > 0) {
                if (tmp < prev) {
                    prev = tmp;
                    ans[0] = list.get(start);
                    ans[1] = list.get(end);
                }

                end--;
            } else {
                tmp = Math.abs(tmp);

                if (tmp < prev) {
                    prev = tmp;
                    ans[0] = list.get(start);
                    ans[1] = list.get(end);
                }

                start++;
            }
        }

        System.out.println(ans[0] + " " + ans[1]);
    }
}
