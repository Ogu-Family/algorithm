package week02.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/19941
 * 메모리: 14380 KB
 * 시간: 144 ms
 * 시간 복잡도: O(N * K)
 * 공간 복잡도: O(N)
 */

/*
1. 사람과 햄버거의 위치를 입력 받아
2. 입력받은 배열을 순회하면서 햄버거를 만나면 가능한 거리 K만큼 가장 왼쪽부터 먹일 사람을 탐색
3. 계산 결과 출력
 */

import java.io.*;
import java.util.*;

public class p1_letskuku {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        char[] hamAndPpl = st.nextToken().toCharArray();

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (hamAndPpl[i] == 'H') {
                for (int j = Math.max(0, i - k); j <= Math.min(n - 1, i + k); j++) {
                    if (hamAndPpl[j] == 'P') {
                        ans++;
                        hamAndPpl[j] = 'N';

                        break;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
