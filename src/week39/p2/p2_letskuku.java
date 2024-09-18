package week39.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/27497
 * 메모리: 243300 KB
 * 시간: 636 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/*
1. 버튼을 입력받아 1 또는 2이면 덱에 블록 추가 후 스택에 버튼 저장
2. 버튼이 3이고 덱이 비어있지 않으면 스택에서 가장 마지막 버튼 확인해 블록 제거
3. 결과 출력
 */

public class p2_letskuku {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Stack<String> buttons = new Stack<>();
        Deque<String> ans = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String button = st.nextToken();

            if (button.equals("1")) {
                ans.offerLast(st.nextToken());
                buttons.push(button);
            } else if (button.equals("2")) {
                ans.offerFirst(st.nextToken());
                buttons.push(button);
            } else {
                if (!ans.isEmpty()) {
                    String lst = buttons.pop();

                    if (lst.equals("1")) {
                        ans.removeLast();
                    } else {
                        ans.removeFirst();
                    }
                }
            }
        }

        if (ans.isEmpty()) {
            System.out.println(0);
        } else {
            StringBuilder sb = new StringBuilder();
            while (!ans.isEmpty()) {
                sb.append(ans.remove());
            }

            System.out.println(sb);
        }
    }
}
