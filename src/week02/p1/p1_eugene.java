package week02.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1_eugene {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static char[] table;
    private static int ans = 0;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        table = br.readLine().toCharArray();

        for(int i = 0; i < N; i++) {
            if(table[i] == 'P') {
                int idx = Math.max(i - K, 0);
                boolean flag = false;

                for (int j = idx; j < i; j++) { // 사람 기준 왼쪽 탐색
                    if (isPossible(j)) {
                        flag = true;
                        break;
                    }
                }

                if(!flag) { // 왼쪽에 먹을 수 있는 햄버거가 없었을 때 오른쪽 탐색
                    idx = i + K >= N ? N - 1 : i + K;
                    for(int j=i+1; j<=idx; j++) {
                        if(isPossible(j)) {
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(ans);
    }

    private static boolean isPossible(int idx) { // 햄버거 먹을 수 있는지 확인
        if(table[idx] == 'H') {
            table[idx] = 'X';  // 먹은 햄버거 처리
            ans++;
            return true;
        }
        return false;
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/19941
 * 메모리: 14376 KB
 * 시간: 136 ms
 * 시간 복잡도: O(N * K)
 * 공간 복잡도: O(N)
 *
 * char[] table 배열 탐색
 * 사람이면 사람기준 왼쪽 탐색 -> 먹을 햄버거 없으면 -> 오른쪽 탐색
 * 새로운 boolean 배열을 사용하지 않고, char[] table 배열에서 먹은 햄버거는 'X' 처리
 */
