package src.week11.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제 링크: https://www.acmicpc.net/problem/5639
 * 메모리: 18316 KB
 * 시간: 512 ms
 * 시간 복잡도: O(N) or O(logN)
 * 공간 복잡도: O(N)
 */

/**
 * 입력 받는 것이 어려웠던,,
 * 이진 트리 정석 방식처럼 노드를 구성하고
 * 노드에 insert 메서드를 구성하여 자신보다 크면 오른쪽, 작으면 왼쪽으로 삽입한다.
 * 이후 후위 탐색을 진행하고, StringBuilder 에 저장하여 출력
 */

class p1_GiHoo {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 입력은 루트
        Node node = new Node(Integer.parseInt(br.readLine()));

        while (true) {
            String input = br.readLine();
            if (input == null || input.equals("")) {
                break;
            }

            int num = Integer.parseInt(input);
            node.insert(num);
        }

        search(node);

        System.out.println(sb);
    }

    // 탐색
    private static void search(Node node) {
        if (node.left != null) {
            search(node.left);
        }
        if (node.right != null) {
            search(node.right);
        }
        sb.append(node.num).append("\n");
    }

    static class Node {

        int num;
        Node left = null;
        Node right = null;

        public Node(int num) {
            this.num = num;
        }

        void insert(int input) {
            // 삽입 노드가 현재 노드보다 작을 때(왼쪽 삽입)
            if (this.num > input) {
                if (this.left == null) {
                    this.left = new Node(input);
                } else {
                    this.left.insert(input);
                }
            }

            // 삽입 노드가 현재 노드보다 클 때(오른쪽 삽입)
            else {
                if (this.right == null) {
                    this.right = new Node(input);
                } else {
                    this.right.insert(input);
                }
            }

        }
    }
}
