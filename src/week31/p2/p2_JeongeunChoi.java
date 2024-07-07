package algorithm.src.week31;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/30892
 * 메모리: 52804 KB
 * 시간: 1148 ms
 * 시간 복잡도: O(N*K)
 * 공간 복잡도: O(N)
 */

/**
 * 그리디, 스택 사용
 * 1. 스택에 상어 크기가 작은 것 부터 넣는다.
 * 2. 상어 크기가 큰 것 부터 꺼낸다.
 * 2-1. 현재 상어 크기보다 큰 경우 먹는다. 임시 스택에 저장된 상어를 스택에 넣어준다.
 * 2-2. 임시 스택에 상어를 저장한다.
 */

public class p2_JeongeunChoi {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sharkCnt = Integer.parseInt(st.nextToken()), canEatingCnt = Integer.parseInt(st.nextToken());
        long sharkSize = Integer.parseInt(st.nextToken());
        int[] sharks = new int[sharkCnt];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < sharkCnt; i++) {
            sharks[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sharks);

        int eatingCnt = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < sharkCnt; i++) {
            stack.push(sharks[i]);
        }

        Stack<Integer> stack2 = new Stack<>();
        while (!stack.isEmpty() && eatingCnt < canEatingCnt) {
            // 상어 크기가 큰 것부터 꺼내면서, 현재 상어 크기보다 작은 경우 먹기
            if (stack.peek() < sharkSize) {
                sharkSize += stack.pop();
                eatingCnt++;
                while (!stack2.isEmpty()) { // 다른 스택에 저장된 상어 빼서 넣기
                    stack.push(stack2.pop());
                }
            } else { // 현재 상어 크기보다 큰 경우, 다른 스택에 저장
                stack2.push(stack.pop());
            }
        }

        System.out.println(sharkSize);
    }
}
