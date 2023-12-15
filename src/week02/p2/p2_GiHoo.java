package src.week02.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p2_GiHoo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int lectureCount = Integer.parseInt(br.readLine());

        Lecture[] lectures = new Lecture[lectureCount];

        StringTokenizer st;
        for (int i = 0; i < lectureCount; i++) {
            st = new StringTokenizer(br.readLine());
            int start_time = Integer.parseInt(st.nextToken());
            int end_time = Integer.parseInt(st.nextToken());

            lectures[i] = new Lecture(start_time, end_time);
        }

        Arrays.sort(lectures, (o1, o2) -> {
            if (o1.start_time == o2.start_time) return o1.end_time - o2.end_time; // 시작 시간이 같다면 끝나는 시간 기준으로 오름차순 정렬
            else return o1.start_time - o2.start_time; // 시작 시간을 기준으로 오름차순 정렬
        });

        Queue<Integer> pq = new PriorityQueue<>();

        pq.add(lectures[0].end_time); // 39라인 pq.peek()이 null을 반환하지 않도록 첫번째 값 미리 입력

        for (int i = 1; i < lectureCount; i++) {
            Lecture lecture = lectures[i];
            int start_time = lecture.start_time;
            int end_time = lecture.end_time;

            if (pq.peek() <= start_time) {
                pq.poll();
            }
            pq.add(end_time);
        }

        System.out.println(pq.size());
    }

    static class Lecture {
        int start_time;
        int end_time;

        public Lecture(int start_time, int end_time) {
            this.start_time = start_time;
            this.end_time = end_time;
        }
    }
}



/**
 * 문제 링크: https://www.acmicpc.net/problem/11000
 * 메모리: 68672 KB
 * 시간: 740 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 *
 * 풀이
 * 시작 시간과 종료 시간을 가지고 있는 Lecture 클래스
 * 입력을 받아 이를 배열에 저장한다.
 * 이를 우선순위 큐를 사용하기 위해 정렬하는데
 *  정렬은 시작 시간 기준으로 오름차순 정렬을 하고
 *  만약 시작 시간이 같다면 종료 시간을 기준으로 오름차순 정렬을 한다.
 * 우선순위 큐에는 종료 시간 값을 넣는데
 * 만일 우선순위 큐의 첫번째 값(pq.peek())이 시작 시간보다 작거나 같다면 이를 교체해주기 위함이다.
 *
 * 풀면서 했던 생각.. 주절주절
 * 문제를 처음 보고 했던 생각은 시간복잡도 O(nlogn)인 문제구나 였는데
 * 그래서 바로 우선순위 큐를 선택했다. 우선순위 큐 연산이 O(logn)인 줄 알았음..
 * 문제 제출 후 계속 시간 초과가 나서 확인해보니 pq.contains() 연산이 O(logn)아 아님을 알게됨..
 *
 * 이를 해결하기 위해 pq.peek() -> O(1) 연산으로 바꾸려고 하면서 오래 걸린 문제...
 */
