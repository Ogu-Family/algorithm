package week07.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class p2_eugene {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int [][] time = new int[N][2];

        StringTokenizer st;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            time[i][0] = Integer.parseInt(st.nextToken());
            time[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) return o1[0] - o2[0];

                return o1[1] - o2[1];
            }
        });

        int cnt = 0;
        int prev_time = 0;

        for(int i=0;i<N;i++){
            if(prev_time <= time[i][0]){
                prev_time = time[i][1];
                cnt++;
            }
        }

        System.out.println(cnt);
    }

}

/**
 * 문제 링크: https://www.acmicpc.net/problem/1931
 * 메모리: 44044 KB
 * 시간: 512 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 *
 * 풀이
 * 각 회의 시간을 시작 시간 기준 비교, 정렬해 풀이
 */
