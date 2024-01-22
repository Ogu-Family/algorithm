package week08.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2_eugene {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static long ans;
    static long[] ar;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        ar = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ar[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(ar);

        ans = 0;
        if(N%2==0) {
            for(int i=0; i<N/2; i++) {
                ans = Math.max(ar[i]+ar[N-1-i], ans);
            }
        } else {
            ans = ar[N-1];
            for (int i=0; i<(N-1)/2; i++) {
                ans = Math.max(ar[i] + ar[N-2-i], ans);
            }
        }

        System.out.println(ans);
    }
}

/*
  문제 링크: https://www.acmicpc.net/problem/20300
  메모리: 18764 KB
  시간: 236 ms
  시간 복잡도: O(NlogN)
  공간 복잡도: O(N)
 */

/*
  그리디 알고리즘
  맨 앞과 맨 뒤를 더해서 가장 큰 수 찾기
  개수가 홀수인 경우 마지막 숫자 따로
 */
