package algorithm.src.week22.p2;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1469
 * 메모리: 16164 KB
 * 시간: 232 ms
 * 시간 복잡도: O(N!)
 * 공간 복잡도: O(N)
 */

/**
 * 백트래킹
 * 1. 입력받은 숫자 2개씩 배열에 넣고, 오름차순 정렬
 * 2. 재귀함수 돌며 두가지 조건 검사하며 수열 만들기
 * 2-1. 사용되지 않은 숫자 수열에 추가 및 위치 저장
 * 2-2. 같은 숫자 2개가 모두 사용된 경우, 위치를 바탕으로 사이에 해당 수 만큼 숫자가 존재하는지 판단
 */

public class p1_JeongeunChoi {

    private static int N;
    private static int[] nums, sequence, shomSequence, location;
    private static boolean hasShomSequence = false;

    private static void makeSequence(int cnt) {
        if (!hasShomSequence) {
            if (cnt == N * 2) {
                hasShomSequence = true;
                shomSequence = Arrays.copyOf(sequence, sequence.length);
            } else {
                for (int i = 0; i < N * 2; i++) {
                    if (location[i] == -1) {
                        sequence[cnt] = nums[i];
                        location[i] = cnt;
                        if (isShomSequence(i)) {
                            makeSequence(cnt + 1);
                        }
                        location[i] = -1;
                        if (i % 2 == 0) { // 같은 수에 대해 중복 검사 및 수열 생성하지 않도록 조건 추가(없으면 시간 초과 발생)
                            i++;
                        }
                    }
                }
            }
        }
    }

    private static boolean isShomSequence(int i) {
        if (i % 2 == 0 && location[i + 1] != -1) {
            if (!hasNumsBetween(location[i], location[i + 1], nums[i])) {
                location[i] = -1;
                return false;
            }
        } else if (i % 2 != 0 && location[i - 1] != -1) {
            if (!hasNumsBetween(location[i], location[i - 1], nums[i])) {
                location[i] = -1;
                return false;
            }
        }

        return true;
    }

    private static boolean hasNumsBetween(int l1, int l2, int num) {
        return Math.abs(l2 - l1) - 1 == num;
    }

    private static void input() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nums = new int[N * 2];
        sequence = new int[N * 2];
        shomSequence = new int[N * 2];
        location = new int[N * 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            nums[i * 2] = num;
            nums[i * 2 + 1] = num;
            location[i * 2] = -1;
            location[i * 2 + 1] = -1;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        Arrays.sort(nums);

        makeSequence(0);

        if (hasShomSequence) {
            for (int i = 0; i < N * 2; i++) {
                System.out.print(shomSequence[i] + " ");
            }
        } else {
            System.out.println(-1);
        }
    }

}
