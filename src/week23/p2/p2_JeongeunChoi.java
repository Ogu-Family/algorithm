package algorithm.src.week23.p2;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/31423
 * 메모리: 148508 KB
 * 시간: 780 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 풀이 참고한 문제
 *
 * 입력받은 순서대로 문자열을 붙이는게 아닌, 붙이는 순서를 먼저 구하기
 * deque로 풀며, 매번 deque의 처음부터 끝까지 탐색하다보니 시간초과 발생
 * next와 tail 값을 업데이트해주어야 함.
 */

public class p2_JeongeunChoi {

    private static int schoolCnt;
    private static String[] schools;
    private static int[] next, tail;
    private static StringBuilder schoolName = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        schoolCnt = Integer.parseInt(br.readLine());
        schools = new String[schoolCnt + 1];
        next = new int[schoolCnt + 1];
        tail = new int[schoolCnt + 1];

        for (int i = 1; i <= schoolCnt; i++) {
            schools[i] = br.readLine();
            next[i] = i;
            tail[i] = i;
        }

        int cur = -1;
        for (int i = 0; i < schoolCnt - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            next[tail[left]] = right;
            tail[left] = tail[right];
            cur = left;
        }

        for (int i = 0; i < schoolCnt; i++) {
            schoolName.append(schools[cur]);
            cur = next[cur];
        }

        System.out.println(schoolName);
    }
}