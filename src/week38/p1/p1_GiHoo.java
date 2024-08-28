package src.week38.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제 링크: https://www.acmicpc.net/problem/9935
 * 메모리: 23548 KB
 * 시간: 336 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N)
 */

/**
 * 문자열 문제 / 스택 문제
 *
 * 타겟 문자열에서 폭탄 문자열을 제거하고, 제거한 이후에도 남았다면 없어질 때 까지 제거하기
 * StringBuilder를 사용하여 폭탄 문자열만큼 쌓이면 비교하여 제거
 *
 * 처음에는 String 객체만 사용해서 문제를 풀었는데 불변 객체라 메모리 초과 발생
 * 해당 부분만 고쳐서 StringBuilder로 작성하였습니다.
 *
 * 이번 문제를 풀다가 String.replaceFirst() 를 알게 됐는데
 * 메모리만 충분하다면 while(true) 해놓고 무지성 replaceFirst 해도 괜찮을 것 같은 유형이라고 생각합니다.
 */

public class p1_GiHoo {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String bomb = br.readLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));

            if (sb.length() >= bomb.length()) {
                boolean flag = true;

                for (int j = 0; j < bomb.length(); j++) {
                    if (sb.charAt(sb.length() - bomb.length() + j) != bomb.charAt(j)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    sb.delete(sb.length() - bomb.length(), sb.length());
                }
            }
        }

        if (sb.length() == 0) {
            System.out.print("FRULA");
        } else {
            System.out.print(sb);
        }
    }
}
