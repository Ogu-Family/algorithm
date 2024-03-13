package algorithm.src.week15.p1;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/150367
 * 시간 복잡도: O(logN)
 * 공간 복잡도: O(N)
 */

/**
 * 풀이 참고한 문제
 * 이진 트리 탐색
 * 1. 입력받은 number값을 이진수로 바꾸고 포화 이진트리를 위해 빈 부분은 0으로 채우기
 * 2. 이진수 값을 이진트리로 표현할 수 있는지 확인 -> 부모가 없는데 자식이 있는 경우 불가능
 * 2-1. 루트 노드를 기준으로 왼쪽과 오른쪽으로 나누어 재귀함수를 호출
 * 2-2. 루트 노드가 0인 경우(부모 노드가 없는 경우)에, 자식 노드들 중 값이 1이 있다면(자식 노드가 있는 경우)가 있다면 이진트리로 표현이 불가능
 */

class p1_JeongeunChoi {

    static String binaryNumber;
    static int canExpress;

    private static void dfs(int s, int root, int e) {
        if (root - s == 0 || e - root == 0) {
            return;
        }
        if (canExpress == 1 && binaryNumber.charAt(root) == '0') {
            for (int i = s; i <= e; i++) {
                if (binaryNumber.charAt(i) == '1') {
                    canExpress = 0;
                    break;
                }
            }
        }
        if (canExpress == 1) {
            dfs(s, (root - 1 + s) / 2, root - 1);
            dfs(root + 1, (root + 1 + e) / 2, e);
        }
    }

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            binaryNumber = "";
            Stack<String> st = new Stack<>();
            while (numbers[i] != 0) {
                st.push(numbers[i] % 2 + "");
                numbers[i] /= 2;
            }
            int n = 1;
            while (true) {
                int s = (int) Math.pow(2, n - 1), e = (int) (Math.pow(2, n) - 2);
                if (s <= st.size() && st.size() <= e) {
                    for (int j = 1; j <= e + 1 - st.size(); j++) {
                        binaryNumber += "0";
                    }
                    break;
                } else if (st.size() == (int) (Math.pow(2, n) - 1)) {
                    break;
                }
                n++;
            }
            while (!st.empty()) {
                binaryNumber += st.pop();
            }
            int s = 0, e = binaryNumber.length() - 1;
            canExpress = 1;
            dfs(s, (s + e) / 2, e);
            answer[i] = canExpress;
        }

        return answer;
    }
}