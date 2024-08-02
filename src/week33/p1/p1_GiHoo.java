package src.week33.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/13549
 * 메모리: 30052 KB
 * 시간: 200 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * 기존 숨바꼭질 문제는 BFS만 진행하면 풀 수 있음
 * 해당 문제는 순간이동을 하는 경우 시간이 증가하지 않기 때문에 순간이동 행위를 우선으로 수행해야 함
 * 순간이동 - 왼쪽 이동 - 오른쪽 이동 순으로 해서 BFS 진행
 * 반복 수행을 하지 않기 위해 Set 사용
 */

public class p1_GiHoo {

    static int start, end;
    static Deque<Node> deque = new LinkedList<>();
    static Set<Integer> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        deque.add(new Node(start, 0));
        visited.add(start);

        int answer = 0;
        while (!deque.isEmpty()) {
            Node currNode = deque.poll();

            if (currNode.currentPosition == end) {
                answer = currNode.spendTime;
                break;
            }

            currNode.move();
        }

        System.out.print(answer);
    }

    static class Node {

        int currentPosition;
        int spendTime;

        public Node(int currentPosition, int spendTime) {
            this.currentPosition = currentPosition;
            this.spendTime = spendTime;
        }

        public void move() {
            teleport();
            moveLeft();
            moveRight();
        }

        private void teleport() {
            int nextPosition = this.currentPosition * 2;
            if (canMove(nextPosition)) {
                visited.add(nextPosition);
                deque.addFirst(new Node(nextPosition, this.spendTime));
            }
        }

        private void moveLeft() {
            int nextPosition = this.currentPosition - 1;
            if (canMove(nextPosition)) {
                visited.add(nextPosition);
                deque.addLast(new Node(nextPosition, this.spendTime + 1));
            }
        }

        private void moveRight() {
            int nextPosition = this.currentPosition + 1;
            if (canMove(nextPosition)) {
                visited.add(nextPosition);
                deque.addLast(new Node(nextPosition, this.spendTime + 1));
            }
        }

        private boolean canMove(int nextPosition) {
            return (nextPosition >= 0 && nextPosition <= 100000) && (!visited.contains(nextPosition));
        }
    }
}
