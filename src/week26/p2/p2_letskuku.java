package week26.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/3107
 * 메모리: 14172 KB
 * 시간: 120 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/*
1. 축약형 IPvv6 주소를 입력받아
2. 콜론을 기준으로 주소 복원
3. 결과 출력
 */

import java.io.*;

public class p2_letskuku {

    static StringBuilder sb;

    static int prevCol = -1; // 직전 콜론 위치
    static int doubleCol = -1; // 연속된 콜론 있는 경우 첫번재 콜론 위치
    static int group = 0; // 연속된 콜론에 들어갈 그룹을 제외한 그룹 수
    static int index = 0; // 현재 탐색 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder(br.readLine());

        if ((sb.charAt(0) == ':') && (sb.charAt(1) == ':')) {
            doubleCol = 0;
            prevCol = 1;
            index = 2;
        }

        while (index < sb.length()) {
            if (sb.charAt(index) == ':') {
                int zeroNum = addZero();
                index += zeroNum;

                prevCol = index;
                index++;

                // 다음 문자도 콜론인지 검사
                if ((index < sb.length()) && (sb.charAt(index) == ':')) {
                    doubleCol = prevCol;
                    prevCol = index;
                    index++;
                }
            } else {
                index++;
            }
        }

        // 마지막 그룹에 대한 생략된 0 복원
        if (index == sb.length()) {
            addZero();
        }

        // 연속된 콜론 2개에 대한 복원
        if (doubleCol != -1) {
            StringBuilder zeros = new StringBuilder();
            for (int i = 0; i < 8 - group; i++) {
                zeros.append("0000:");
            }
            sb.replace(doubleCol + 1, doubleCol + 2, zeros.toString());

            // ::~의 구성일 경우 앞 콜론 삭제
            if (doubleCol == 0) {
                sb = new StringBuilder(sb.substring(1));
            }
        }

        System.out.println(sb);
    }

    static int addZero() {
        int zeroNum = 4 - (index - prevCol -1); // 생략된 0 개수 계산
        StringBuilder zeros = new StringBuilder();
        for (int i = 0 ; i < zeroNum; i++) {
            zeros.append(0);
        }
        sb.insert(prevCol + 1, zeros);
        group++;

        return zeroNum;
    }
}
