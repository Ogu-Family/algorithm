/**
 * 문제 링크: https://www.acmicpc.net/problem/28018
 * 메모리: 87088 KB
 * 시간: 880 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 누적 합 문제
 *
 * 1. 주어진 학생들의 시작 시간에 해당하는 prefixSum 배열 값을 증가시키고, 종료 시간의 다음 시간에 해당하는 값을 감소
 * 2. 그런 다음 prefixSum 배열을 순차적으로 누적하여 특정 시간에 겹치는 학생 수 계산
 * 3. 구한 prefixSum 배열을 통해 질의된 시간마다 겹치는 학생 수 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TimeOverlap {

    private static final int MAX_TIME = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int studentCount = Integer.parseInt(bufferedReader.readLine());
        Student[] students = new Student[studentCount];
        for (int i = 0; i < studentCount; i++) {
            int[] studentInfo = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            students[i] = new Student(studentInfo[0], studentInfo[1]);
        }
        bufferedReader.readLine();
        int[] times = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.print(solution(students, times));
    }

    private static String solution(Student[] students, int[] times) {
        int[] prefixSum = new int[MAX_TIME + 2];

        for (Student student : students) {
            prefixSum[student.startTime]++;
            prefixSum[student.endTime + 1]--;
        }

        for (int i = 1; i <= MAX_TIME; i++) {
            prefixSum[i] += prefixSum[i - 1];
        }

        return resultToString(times, prefixSum);
    }

    private static String resultToString(int[] times, int[] prefixSum) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int time : times) {
            stringBuilder.append(prefixSum[time]).append("\n");
        }

        return stringBuilder.toString().trim();
    }

    static class Student {

        private final int startTime;
        private final int endTime;

        public Student(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
