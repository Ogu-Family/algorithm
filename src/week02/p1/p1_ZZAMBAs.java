package src.week02.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2606
 * 메모리: 18432 KB
 * 시간: 256 ms
 * 시간 복잡도: O(N * K)
 * 공간 복잡도: O(N)
 */

/**
 * 그리디 알고리즘. 맨 앞부터 햄버거를 먹는 것이 아닌 경우보다 항상 좋거나 같은 시간이 걸림
 * 만약 앞에서부터 안 먹는다고 해보자. 이미 반례가 있다. 예를 들어 HHPP, K = 2 인 경우, 앞에서부터 안먹으면 답이 2가 안나옴.
 *
 * 중요한 부분
 * - 자신의 위치에서 K 거리 이내 햄버거를 먹을 수 있다.
 * - 햄버거를 먹는 사람 수가 최대여야 한다.
 */

import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class p1_ZZAMBAs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), K = sc.nextInt(), res = 0;
        String s = sc.next();
        int sLen = s.length();
        boolean[] isEaten = new boolean[sLen]; // 햄버거가 먹혔는지 확인용

        for(int i = 0; i < sLen; i++) { // s 전체를 훑으면서
            if (s.charAt(i) != 'P') // 햄버거면 그냥 패스
                continue;
            for (int j = max(0, i - K); j <= min(sLen - 1, i + K); j++) { // 사람이면 앞에서부터 먹을 수 있는 햄버거 찾음.
                if (s.charAt(j) == 'H' && !isEaten[j]) {
                    isEaten[j] = true;
                    res++;
                    break;
                }
            }
        }

        System.out.println(res);
    }
}
