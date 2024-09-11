package algorithm.src.week39.p2;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/27497
 * 메모리: 238396 KB
 * 시간: 624 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 구현
 * 앞뒤로 값을 추가하거나 삭제하고, 이전 내역들을 저장하기 위해 Deque 사용
 * LinkedList 사용시 시간초과, StringBuilder 사용 안해도 시간초과 발생
 * ArrayDeque와 StringBuilder 사용 필요
 */

public class p2_JeongeunChoi {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Deque<String> alpabets = new ArrayDeque<>();
        Deque<Integer> orderSt = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            switch (Integer.parseInt(st.nextToken())) {
                case 1:
                    alpabets.offerLast(st.nextToken());
                    orderSt.push(1);
                    break;
                case 2:
                    alpabets.offerFirst(st.nextToken());
                    orderSt.push(2);
                    break;
                case 3:
                    if (!alpabets.isEmpty()) {
                        int before = orderSt.pop();
                        if (before == 1) {
                            alpabets.pollLast();
                        } else if (before == 2) {
                            alpabets.pollFirst();
                        }
                    }
                    break;
            }
        }

        if (alpabets.isEmpty()) {
            System.out.println("0");
        } else {
            Iterator<String> it = alpabets.iterator();
            StringBuilder sb = new StringBuilder();
            while (it.hasNext()) {
                sb.append(it.next());
            }
            System.out.println(sb);
        }
    }

}
