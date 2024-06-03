package algorithm.src.week26.p2;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/3107
 * 메모리: 14256 KB
 * 시간: 108 ms
 * 시간 복잡도: O(1)
 * 공간 복잡도: O(1)
 */

/**
 * 구현
 * 1. ::가 존재하는 경우 ::의 기준으로 앞 뒤로 그룹 갯수를 세어, 생략된 그룹 갯수를 계산한다.
 * 2. 그룹을 8개 복원할 때까지 아래 과정을 반복한다.
 * 2-1: ::가 존재하고, :: 기준 앞의 그룹들이 모두 발견되면 0000:을 생략된 그룹 갯수만큼 추가한다.
 * 2-2: 그룹에 대해 길이가 4인 경우 그대로 반환하고, 4보다 작은 경우 앞을 0으로 채운다.
 */

public class p2_JeongeunChoi {

    private static int omittedCnt = 0, beforeTwoColumnCnt = 0, afterTwoColumnCnt = 0;

    private static void getOmittedCnt(String shorten) {
        int twoColumnSIdx = shorten.indexOf("::"), twoColumnEIdx = twoColumnSIdx + 1;
        for (int i = 0; i < shorten.length(); i++) {
            if (i >= twoColumnSIdx && i <= twoColumnEIdx) {
                continue;
            }
            if (shorten.charAt(i) == ':') {
                if (i < twoColumnSIdx) {
                    beforeTwoColumnCnt++;
                } else if (i > twoColumnEIdx) {
                    afterTwoColumnCnt++;
                }
            }
        }
        beforeTwoColumnCnt = beforeTwoColumnCnt > 0 || twoColumnSIdx > 0 ? beforeTwoColumnCnt + 1 : beforeTwoColumnCnt;
        afterTwoColumnCnt = afterTwoColumnCnt > 0 || twoColumnEIdx < shorten.length() - 1 ? afterTwoColumnCnt + 1 : afterTwoColumnCnt;
        omittedCnt = 8 - beforeTwoColumnCnt - afterTwoColumnCnt;
    }

    private static StringBuilder getOrigin(String shorten) {
        StringBuilder origin = new StringBuilder(shorten).reverse();

        if (shorten.length() < 4) {
            for (int i = 0; i < 4 - shorten.length(); i++) {
                origin.append("0");
            }
        }

        return origin.reverse();
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder origin = new StringBuilder();
        String shorten = br.readLine();
        st = new StringTokenizer(shorten);

        boolean containsTwoColumn = shorten.contains("::");
        if (containsTwoColumn) {
            getOmittedCnt(shorten);
        }

        int tokenCnt = 0;
        while (tokenCnt < 8) {
            if (containsTwoColumn && tokenCnt == beforeTwoColumnCnt) {
                for (int i = 0; i < omittedCnt; i++) {
                    origin.append("0000:");
                    tokenCnt++;
                }
            } else {
                origin.append(getOrigin(st.nextToken(":")) + ":");
                tokenCnt++;
            }
        }

        origin.deleteCharAt(origin.length() - 1);
        System.out.println(origin);
    }

}
