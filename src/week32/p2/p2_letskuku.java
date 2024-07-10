package week32.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/15989
 * 메모리: 14888 KB
 * 시간: 124 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/*
1. 1부터 10000까지 1, 2, 3의 합으로 나타내는 방법의 수를 구한 후
2. 입력 받은 수에 대해 결과 출력

아무리 생각해도 dp인데 점화식이 떠오르지 않아서 처음엔 이상한(?) 방법으로 풀어냈던 문제...
1, 2, 3의 합을 오름차순으로 적어봤으면 더 빨리 풀어냈을 것 같습니다...ㅎㅎㅎ
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2_letskuku {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        int[][] d = new int[10001][4];
        d[1][1] = 1;
        d[2][1] = 1;
        d[2][2] = 1;
        d[3][1] = 1;
        d[3][2] = 1;
        d[3][3] = 1;
        for (int i = 4; i <= 10000; i++) {
            d[i][1] = d[i - 1][1];
            d[i][2] = d[i - 2][1] + d[i - 2][2];
            d[i][3] = d[i - 3][1] + d[i - 3][2] + d[i - 3][3];
        }

        while (t-- > 0) {
            int tmp = Integer.parseInt(br.readLine());
            System.out.println(d[tmp][1] + d[tmp][2] + d[tmp][3]);
        }
    }
}
