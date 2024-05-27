package algorithm.src.week24.p1;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/28449
 * 메모리: 44544 KB
 * 시간: 700 ms
 * 시간 복잡도: O(N*M)
 * 공간 복잡도: O(N+M)
 */

/**
 * 구현
 * 1. HI팀과 ARC팀의 참가자 코딩실력을 오름차순으로 정렬
 * 2. HI팀 한명에 대해 ARC 팀의 모든 참가자와 코딩실력 비교하는 과정을 반복
 * 2-1. 이전 HI 참가자가 이긴횟수와 비긴횟수 반영
 * 2-2. HI가 이기는 횟수, HI가 비기는 횟수 저장
 * 2-2. HI 참가자 실력보다 큰 ARC 참가자 발견 시,
 *      해당 참가자부터는 모두 ARC가 이기므로 ARC 이긴 횟수 저장
 *      해당 참가자 인덱스 저장 후, 다음 탐색 시 이용
 */

public class p1_JeongeunChoi {

    private static int HICnt, ARCCnt;
    private static long HIWinCnt = 0, ARCWinCnt = 0, tieCnt = 0;
    private static int[] HI, ARC;
    private static long[] HIWins, HITies;

    private static void getGameResult() {
        int startJ = 1;
        boolean hasBigger = true;
        for (int i = 1; i <= HICnt; i++) {
            HIWins[i] = HIWins[i - 1];
            if (HI[i] > HI[i - 1]) {
                HIWins[i] += HITies[i - 1];
            } else if (HI[i] == HI[i - 1]) {
                HITies[i] = HITies[i - 1];
            }
            if (hasBigger) {
                hasBigger = false;
                for (int j = startJ; j <= ARCCnt; j++) {
                    startJ = j;
                    if (HI[i] > ARC[j]) {
                        HIWins[i]++;
                    } else if (HI[i] == ARC[j]) {
                        HITies[i]++;
                    } else if (HI[i] < ARC[j]) {
                        hasBigger = true;
                        ARCWinCnt += ARCCnt - j + 1;
                        break;
                    }
                }
            }
            HIWinCnt += HIWins[i];
            tieCnt += HITies[i];
        }
    }

    private static void input() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        HICnt = Integer.parseInt(st.nextToken());
        ARCCnt = Integer.parseInt(st.nextToken());
        HI = new int[HICnt + 1];
        ARC = new int[ARCCnt + 1];
        HIWins = new long[HICnt + 1];
        HITies = new long[HICnt + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= HICnt; i++) {
            HI[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= ARCCnt; i++) {
            ARC[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws Exception {
        input();

        Arrays.sort(HI);
        Arrays.sort(ARC);
        getGameResult();

        System.out.println(HIWinCnt + " " + ARCWinCnt + " " + tieCnt);
    }
}
