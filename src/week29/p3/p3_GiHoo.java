package src.week29.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제 링크: https://www.acmicpc.net/problem/15922
 * 메모리: 43564 KB
 * 시간: 364 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 좌표평면으로 생각해서 되게 오래걸린 문제,, 문제 잘 읽어야겠다...
 *
 * 들어오는 x, y 값을 3가지 케이스로 나눈다.
 * 1. 들어온 좌표의 x가 현재 좌표의 y보다 작은 경우
 *  1-1. 들어온 좌표의 y도 현재 좌표의 y보다 작은 경우 현재 좌표를 유지한디.
 *  1-2. 그렇지 않은 경우 현재 좌표의 y값을 새로 들어온 좌표의 y값으로 변경한다.
 * 2. 그렇지 않은 경우 현재 x, y 좌표를 새로 들어온 x, y 좌표로 변경한다.
 *
 * 들어오는 값이 x 증가 순으로, x가 같다면 y가 증가하는 순으로 들어오기 때문에 3가지 케이스만 고려하면 된다.
 */

public class p3_GiHoo {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Node> inputList = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            inputList.add(new Node(start, end));
        }

        int answer = 0;

        int currStart = inputList.get(0).start;
        int currEnd = inputList.get(0).end;
        inputList.remove(0);

        for (Node node : inputList) {
            int start = node.start;
            int end = node.end;

            if (start <= currEnd) {
                if (end <= currEnd) {
                    continue;
                } else {
                    currEnd = end;
                }
            } else {
                answer += (currEnd - currStart);

                currStart = start;
                currEnd = end;
            }
        }

        answer += (currEnd - currStart);

        System.out.println(answer);
    }

    static class Node {
        int start;
        int end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
