package src.week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1339
 * 메모리: 14272 KB
 * 시간: 124 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N)
 */

/**
 * 처음 문제 접근 방법
 *  들어온 String 값을 크기 순으로 정렬 o2.length - o1.length
 *  루프 돌면서 첫 값에 가중치를 주면서 바로바로 해결
 *
 * 문제점
 *  예제 입력 2의 경우 ACDEB애서 D까지는 값이 정확하게 들어가는데
 *  E보다 GCF의 G가 더 큰 가중치를 가지고 있어야 하는 부분 때문에 문제 발생
 *
 * 풀이 참고
 * 해당 알파벳의 자리수를 얻고 알파벳 배열에 자리수를 더해준다.
 * 자리수를 크기 순으로 정렬하여 가중치를 곱해주고 값을 반환
 */

public class p1_GiHoo {

    static int[] nums = new int[26]; // A B C D E F G H I J... 점수

    public static void main(String[] args) throws IOException {
        int answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] inputs = new String[N];
        for (int i = 0; i < N; i++) {
            inputs[i] = br.readLine();
        }

        for (int i = 0; i < inputs.length; i++) {
            String target = inputs[i];

            for (int j = 0; j < target.length(); j++) {
                int index = target.charAt(j) - 'A';

                nums[index] += Math.pow(10, target.length() - j - 1); // 해당 숫자의 자리수 정렬
            }
        }

        Arrays.sort(nums); // 크기 순으로 정렬

        int weight = 9;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) continue;
            answer += nums[i] * weight;
            weight--;
        }

        System.out.println(answer);
    }
}
