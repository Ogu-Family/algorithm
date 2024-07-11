package algorithm.src.week31.p1;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1461
 * 메모리: 14320 KB
 * 시간: 132 ms
 * 시간 복잡도: O(N*M)
 * 공간 복잡도: O(N)
 */

/**
 * 그리디 알고리즘
 * 책 위치 기준 내림차순으로 정렬한다.
 * 먼 곳부터 시작해서 0까지의 길에 최대 M개의 책을 제자리에 둔다. 이 때, 거리가 가장 먼 곳의 책은 두고 다시 0으로 돌아오지 않도록 한다.
 */

public class p1_JeongeunChoi {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int bookCnt = Integer.parseInt(st.nextToken()), holdCnt = Integer.parseInt(st.nextToken()), minDistance = 0;
        int[] books = new int[bookCnt];
        boolean[] visited = new boolean[bookCnt];
        st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((book1, book2) -> Integer.compare(Math.abs(book2), Math.abs(book1)));
        for (int i = 0; i < bookCnt; i++) {
            int book = Integer.parseInt(st.nextToken());
            books[i] = book;
            pq.add(book);
        }
        Arrays.sort(books);
        Map<Integer, Integer> bookMap = new HashMap<>();
        for (int i = 0; i < bookCnt; i++) {
            bookMap.put(books[i], i);
        }

        // 거리가 먼 곳부터 시작해서 0까지 돌아오는 길에 책 두기
        while (!pq.isEmpty()) {
            int bookIdx = bookMap.get(pq.poll());
            if (!visited[bookIdx]) {
                visited[bookIdx] = true;
                // 가장 먼 곳부터 0까지 최대 holdCnt 만큼 책을 제자리 두기 가능
                for (int i = 1; i < holdCnt; i++) {
                    if (books[bookIdx] < 0 && bookIdx + i < bookCnt && books[bookIdx + i] < 0) {
                        visited[bookIdx + i] = true;
                    } else if (books[bookIdx] > 0 && bookIdx - i >= 0 && books[bookIdx - i] > 0) {
                        visited[bookIdx - i] = true;
                    }
                }
                if(minDistance == 0){
                    minDistance += Math.abs(books[bookIdx]); // 제일 먼 곳에 책을 둔 후에는 0으로 돌아오지 않도록
                } else{
                    minDistance += Math.abs(books[bookIdx]) * 2;
                }

            }

        }

        System.out.println(minDistance);
    }

}
