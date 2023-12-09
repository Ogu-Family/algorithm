package src.week01.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2606
 * 메모리: 71188 KB
 * 시간: 772 ms
 * 시간 복잡도: O(MlogM)
 * 공간 복잡도: O(N + M)
 */

/*
듣도 못한 사람 저장할 Set / 듣도 보도 못한 사람 저장할 우선순위 큐 생성
1. n만큼 듣도 못한 사람 입력 받아 Set에 저장
2. m만큼 보도 못한 사람 입력 받아 Set에 존재하는 사람이라면 우선순위 큐에 추가
3. 우선순위 큐 전부 꺼내면서 출력
 */


import java.util.Scanner;
import java.util.HashSet;
import java.util.PriorityQueue;

public class p2_ZZAMBAs{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<String> pq = new PriorityQueue<>();
        HashSet<String> hs = new HashSet<>();

        int N = sc.nextInt(), M = sc.nextInt();

        for(int i = 0; i < N; i++) { // O(N)
            String s = sc.next();
            hs.add(s);
        }
        for(int i = 0; i < M; i++) { // M번 실행
            String s = sc.next();
            if(hs.contains(s))
                pq.add(s); // O(logN)
        }

        System.out.println(pq.size());
        while(!pq.isEmpty()) // O(MlogM)
            System.out.println(pq.poll());
    }
}
