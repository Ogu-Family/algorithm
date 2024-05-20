package src.week23.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/31423
 * 메모리: 207748 KB
 * 시간: 940 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 연결 리스트
 *
 * 문제에서 들어오는 첫번째 링크드 리스트 뒤에 두번째 링크드 리스트 연결
 * 시작 지점에서부터 링크드 리스트를 쭉 훑으며 출력
 *
 * 위상 정렬 DFS로 풀려 했으나 시간 초과 문제를 해결할 수 없었고, 알고리즘 분류를 보고 연결 리스트로 방향을 전환.
 * 너무 구현을 어렵게 한 듯한 감이 있음..
 */

import java.util.*;
import java.io.*;

public class p2_ZZAMBAs {
    static StringBuilder sb = new StringBuilder();
    static String[] univ;
    static LinkedList[] linkedList;
    static int N, start;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        univ = new String[N];
        linkedList = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            univ[i] = br.readLine();
            Node node = new Node();
            node.idx = i;
            linkedList[i] = new LinkedList(node);
        }

        for (int i = 0; i < N - 1; i++) {
            String s = br.readLine();
            StringTokenizer t = new StringTokenizer(s);
            int in = Integer.parseInt(t.nextToken()), out = Integer.parseInt(t.nextToken());

            linkedList[in - 1].add(linkedList[out - 1]);
            start = in - 1;
        }

        linkedList[start].merge();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Node {
        Node prev;
        Node next;
        int idx = -1;
    }

    static class LinkedList {
        Node head;
        Node tail;

        LinkedList(Node node) {
            head = new Node();
            tail = new Node();
            head.next = node;
            tail.prev = node;
            node.prev = head;
            node.next = tail;
        }

        void add(LinkedList list) {
            tail.prev.next = list.head.next;
            list.head.next.prev = tail.prev;
            tail.prev = list.head.next = null;

            list.tail.prev.next = tail;
            tail.prev = list.tail.prev;
            list.tail = null;
        }

        void merge() {
            Node curNode = head.next;
            while (curNode != tail) {
                sb.append(univ[curNode.idx]);
                curNode = curNode.next;
            }
        }
    }
}
