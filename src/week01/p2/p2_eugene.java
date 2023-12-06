package week01.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class p2_eugene {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N,M;
    static HashSet<String> A = new HashSet<>();
    static ArrayList<String> result = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            A.add(br.readLine());
        }

        for(int i=0; i<M; i++) {
            String str = br.readLine();
            if(A.contains(str)) {
                result.add(str);
            }
        }

        Collections.sort(result);
        sb.append(result.size()).append("\n");
        for(String str: result) {
            sb.append(str).append("\n");
        }
        System.out.print(sb);
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/1764
 * 메모리: 25852 KB
 * 시간: 292 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 * * *
 * 듣도 못한 N 명과 보도 못한 M 명
 * HashSet 에 듣도 못한 사람 N명 추가
 * HashSet 에 존재 하면 듣도 보도 못한 사람 -> ArrayList 에 추가
 * 사전순 : Collections.sort()
 */