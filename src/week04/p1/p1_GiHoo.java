package src.week04.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1_GiHoo {

    static int N;
    static int M;
    static int[] matrix;
    static int left;
    static int right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N];

        left = 0;
        right = N - 1;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            matrix[i] = Integer.parseInt(st.nextToken());
            right = Integer.max(right, matrix[i]);
        }

        solve();

        System.out.print(right);
    }

    private static void solve() {
        while (left < right) {
            int mid = (left + right) / 2;
            if (canDivideUpToM(mid) <= M) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
    }

    private static int canDivideUpToM(int mid) {
        int count = 1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            min = Integer.min(min, matrix[i]);
            max = Integer.max(max, matrix[i]);
            if (max - min > mid) {
                count++;
                min = Integer.MAX_VALUE;
                max = Integer.MIN_VALUE;
                i--;
            }
        }
        return count;
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/13397
 * 메모리: 14952 KB
 * 시간: 160 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 *
 * 처음 풀 당시에는
 * 이분 탐색으로 left = 0, right = N -1을 대입하여 solve() 메서드를 수행했습니다.
 * 이럴 경우에는 풀리는 케이스도 있지만, 그렇지 못한 케이스가 존재합니다. (백준 기준 예제 4번, 5번)
 *
 * 배열 값을 입력받을 때 가장 큰 값을 저장하여
 * 그 값을 기준으로 solve() 메서드를 순회하며 값을 갱신합니다.
 *
 * canDivideUpToM() 메서드는 문제 조건인 M개 이하의 구간을 만족하기 위한 메서드입니다.
 * 새로 생성한 mid 값을 통해 조건을 만족하는지 확인합니다.
 *
 * 참고할만한 자료 - 파라메트릭 서치
 * https://marades.tistory.com/7
 */

