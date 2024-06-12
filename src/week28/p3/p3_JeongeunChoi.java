package algorithm.src.week28.p3;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/17394
 * 메모리: 108700 KB
 * 시간: 108700 ms
 * 시간 복잡도: O(N) 테스트 케이스 수
 * 공간 복잡도: O(1)
 */

/**
 * 풀이 참고한 문제
 * BFS + 구현
 * 1. 목표 범위 내 소수 만들 수 있는지 확인
 * 2. 핑거 스냅 4가지 방법으로 숫자를 만들며 횟수 저장
 * 3. 목표범위 내의 소수가 나올때까지 반복
 */

public class p3_JeongeunChoi {

    private static int fingerSnap(int N, int A, int B) {
        int minCnt = 0;
        int[] distance = new int[1000001];
        distance[N] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        while (!q.isEmpty()) {
            int num = q.poll();
            if (num >= A && num <= B && isPrime(num)) {
                minCnt = distance[num] - 1;
                break;
            } else {
                for (int i = 1; i <= 4; i++) {
                    int nextNum = changeN(num, i);
                    if (nextNum >= 0 && nextNum <= 1000000 && distance[nextNum] == 0) {
                        q.add(nextNum);
                        distance[nextNum] = distance[num] + 1;
                    }
                }
            }
        }

        return minCnt;
    }

    private static int changeN(int N, int methodNum) {
        switch (methodNum) {
            case 1:
                return N / 2;
            case 2:
                return N / 3;
            case 3:
                return N + 1;
            case 4:
                return N - 1;
        }

        return N;
    }

    private static boolean isPrime(int N) {
        if (N == 1) {
            return false;
        }
        if (N == 2) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (N % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static boolean hasPrimeBetweenAandB(int A, int B) {
        boolean hasPrime = false;
        for (int i = A; i <= B; i++) {
            if (isPrime(i)) {
                hasPrime = true;
                break;
            }
        }

        return hasPrime;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine()), N, A, B;
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            if (!hasPrimeBetweenAandB(A, B)) {
                System.out.println(-1);
            } else {
                System.out.println(fingerSnap(N, A, B));
            }
        }
    }

}
