package src.week10.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1205
 * 메모리: 17684 KB
 * 시간: 228 ms
 * 시간 복잡도: O(N) 또는 O(P)
 * 공간 복잡도: O(1)
 */

/**
 * 입력을 받는 즉시 처리하도록 한다.
 * 입력 값이 태수 점수보다 높거나 같으면 현재 등수를 + 1 한다.
 * 입력 값이 태수 점수보다 낮으면 전 입력 값과 비교하여 전 입력값과 동일 값이면 현재 등수를, 동일하지 않다면 현재 인덱스 + 1을 등수로 설정한다.
 * 예외적 상황으로 N < P이고, 태수 현재 등수가 아직 정해지지 않았다면 이미 존재하는 점수 맨 마지막 값과 태수 값을 비교해 동일하면 현재 등수로, 동일하지 않으면 -1로 처리한다.
 * 또한 N = 0이면 100% 확률로 1등이므로, 등수를 1로 초기화한다.
 * 의외로 까다로웠다.
 *
 * 1. 랭킹 리스트의 등수는 보통 위에서부터 몇 번째 있는 점수인지로 결정한다. 하지만, 같은 점수가 있을 때는 그러한 점수의 등수 중에 **가장 작은 등수**가 된다.
 * 2. 랭킹 리스트가 꽉 차있을 때, 새 점수가 이전 점수보다 더 좋을 때만 점수가 바뀐다.
 */

import java.util.Scanner;

public class p3_ZZAMBAs{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(), taesueScore = sc.nextInt(), P = sc.nextInt(), nowRank = 0, preScore = -1, res = -1;

        for (int i = 0; i < Math.min(N, P); i++) {
            int nowScore = sc.nextInt();

            if (nowScore < taesueScore) {
                if (taesueScore == preScore)
                    res = nowRank;
                else
                    res = i + 1;

                break;
            }

            nowRank = preScore == nowScore ? nowRank : i + 1;
            preScore = nowScore;
        }

        if (res == -1 && N < P)
            res = taesueScore == preScore ? nowRank : N + 1;

        System.out.println(N == 0 ? 1 : res);
    }
}