package algorithm.src.week02.p2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p2_JeongeunChoi {

    static int lectureCnt;
    static PriorityQueue<Integer> lectureEnds = new PriorityQueue<>();
    static PriorityQueue<Lecture> lecturePQ = new PriorityQueue<>(new LectureComparator());

    private static void processLecture() {
        lectureEnds.add(lecturePQ.poll().end);

        while (!lecturePQ.isEmpty()) {
            Lecture nextLecture = lecturePQ.poll();
            if (!lectureEnds.isEmpty() && lectureEnds.peek() <= nextLecture.start) {
                lectureEnds.poll();
                lectureEnds.add(nextLecture.end);
            } else {
                lectureEnds.add(nextLecture.end);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        lectureCnt = Integer.parseInt(br.readLine());

        int start, end;
        for (int i = 0; i < lectureCnt; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            Lecture lecture = new Lecture(start, end);
            lecturePQ.add(lecture);
        }
        processLecture();

        System.out.println(lectureEnds.size());
    }

    static class Lecture {
        int start, end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static class LectureComparator implements Comparator<Lecture> {
        @Override
        public int compare(Lecture a, Lecture b) {
            if (a.start == b.start) {
                return Integer.compare(a.end, b.end);
            } else {
                return Integer.compare(a.start, b.start);
            }
        }
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/11000
 * 메모리: 69868KB
 * 시간: 740ms
 * 시간 복잡도: O(NlogN) N은 강의 수
 * 공간 복잡도: O(N) N은 강의 수
 */

/**
 * 그리디 알고리즘으로 구현
 * 강의를 우선순위 큐에 저장(시작 시간이 빠른게 우선, 같은 경우 종료 시간이 빠른게 우선)
 * 큐가 비어있을 때까지 강의를 하나씩 뽑아냄.
 * 강의 종료 시간을 저장하는 우선순위 큐를 이용(종료 시간이 빠른게 우선)
 * 강의실에서 진행한 마지막 강의 종료 시간이 다음 강의 시작 시간보다 작거나 같으면 해당 강의 종료 시간 뽑아내고 강의 종료 시간 추가
 * 그렇지 않은 경우 강의 종료 시간 추가
 * 강의 종료 시간을 저장하는 우선순위 큐의 크기가 최소 강의실 수가 됨.
 */