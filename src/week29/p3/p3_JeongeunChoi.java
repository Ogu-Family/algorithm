package algorithm.src.week29.p3;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/15922
 * 메모리: 40036 KB
 * 시간: 412 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(1)
 */

/**
 * 구현
 * x, y 좌표와 이후에 입력받은 nx, ny 좌표를 비교하여 겹치는 구간이 있으면 업데이트하고 그렇지 않으면 길이를 구하여 더한다.
 */

public class p3_JeongeunChoi {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long sum = 0;
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int nx = Integer.parseInt(st.nextToken()), ny = Integer.parseInt(st.nextToken());
            if (nx > y) {
                sum += y - x;
                x = nx;
                y = ny;
            } else if (nx <= y) {
                if (ny <= y) {
                    continue;
                } else if (ny > y) {
                    y = ny;
                }
            }
        }
        sum += (y - x);

        System.out.println(sum);
    }

}
