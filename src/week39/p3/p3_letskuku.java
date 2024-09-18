package week39.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/9011
 * 메모리: 15872 KB
 * 시간: 148 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/*
1. 수열 r을 입력받아
2. 1부터 n을 순서대로 둔 수열 nums를 가지고 r의 뒤에서부터 nums에서 ri번째 값을 계산
3. 결과 출력
 */

public class p3_letskuku {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            List<Integer> s = new ArrayList<>();
            List<Integer> nums = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                s.add(Integer.parseInt(st.nextToken()));
                nums.add(j);
            }

            StringBuilder sb = new StringBuilder();
            for (int j = n - 1; j >= 0; j--) {
                int tmp = s.get(j);

                if (tmp + 1 > nums.size()) {
                    sb = new StringBuilder("IMPOSSIBLE");
                    break;
                }

                sb.insert(0, nums.get(tmp) + " ");
                nums.remove(tmp);
            }

            System.out.println(sb);
        }
    }
}
