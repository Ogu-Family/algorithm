package week36.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1759
 * 메모리: 17768 KB
 * 시간: 132 ms
 * 시간 복잡도: O(N^M) 인가...?
 * 공간 복잡도: O(N)
 */

/*
1. 암호로 사용했을 법한 문자의 종류를 입력받아
2. 백트래킹으로 가능한 모든 경우의 수 계산
3. 결과 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class p2_letskuku {

    static int l, c;
    static List<String> list;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            list.add(st.nextToken());
        }
        Collections.sort(list);

        func(0, 0, -1, "");

        System.out.println(sb);
    }

    static void func(int one, int two, int last, String pw) {
        if (pw.length() == l) {
            if (one >= 1 && two >= 2) {
                sb.append(pw + '\n');
            }

            return;
        }

        for (int i = last + 1; i < c; i++) {
            if (list.get(i).equals("a") || list.get(i).equals("e") || list.get(i).equals("i") ||
                    list.get(i).equals("o") || list.get(i).equals("u")) {
                func(one + 1, two, i, pw + list.get(i));
            } else {
                func(one, two + 1, i, pw + list.get(i));
            }
        }
    }
}
