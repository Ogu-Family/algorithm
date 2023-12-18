package week03.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2630
 * 메모리: 16500 KB
 * 시간: 168 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N * N)
 */

/*
1. 하얀색 또는 파란색으로 칠해져 있는 정사각형 모양의 종이를 입력 받아
2. 모든 칸이 하나의 색깔로 구성될 때까지 종이를 절반 크기로 자름
3. 하얀 색종이와 파란 색종이 개수 출력
 */

import java.io.*;
import java.util.*;

public class p1_letskuku {

    static int[] ans = {0, 0}; // ans[0]: 하얀 색종이, ans[1]: 파란 색종이
    static int[][] papers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        papers = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                papers[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (func(1, 1, n)) {
            ans[papers[1][1]]++;
        }

        System.out.println(ans[0]);
        System.out.println(ans[1]);
    }

    public static boolean func(int x, int y, int length) {
        if (length == 1) { // 하나의 정사각형 칸이 되어 더 이상 자를 수 없음
            return true;
        }

        int newLength = length / 2; // 절반 길이로 색종이 자름
        int[][] checkColor = {{x + newLength, y}, {x, y + newLength}, {x + newLength, y + newLength}, {x, y}};
        int[] tmp = {0, 0}; // 절반으로 자른 색종이 4개에 대한 색깔 임시로 저장할 배열
        for (int i = 0; i < 4; i++) {
            if (func(checkColor[i][0], checkColor[i][1], newLength)) { // newLength 길이를 가진 색종이 1개가 하나의 색깔로 구성됨
                tmp[papers[checkColor[i][0]][checkColor[i][1]]]++;
            }
        }

        if ((tmp[0] == 4) || (tmp[1] == 4)) {
            return true; // 4등분한 색종이가 모두 같은 색깔이면 true 반환
        } else {
            ans[0] += tmp[0];
            ans[1] += tmp[1];
            return false;
        }
    }
}
