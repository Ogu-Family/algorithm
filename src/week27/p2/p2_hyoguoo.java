/**
 * 문제 링크: https://www.acmicpc.net/problem/21608
 * 메모리: 28228 KB
 * 시간: 240 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2)
 */

/**
 * 1. 학생 리스트 순회
 * 2. 최적의 좌표 계산 및 학생 배치
 * 3. 만족도 계산
 *
 * 이번에도 최대한 객체지향적으로 설계해 보았습니다~~
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class SharkElementarySchool {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int sizeN = Integer.parseInt(bufferedReader.readLine());
        Student[] students = new Student[sizeN * sizeN];

        for (int n = 0; n < sizeN * sizeN; n++) {
            int[] info = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int number = info[0];
            int[] preferences = Arrays.copyOfRange(info, 1, info.length);
            students[n] = new Student(number, preferences);
        }

        System.out.print(solution(new ClassRoom(sizeN, students)));
    }

    private static int solution(ClassRoom classRoom) {
        classRoom.assignAllStudentsByPreferences(); // 학생들을 선호도에 따라 배치
        return classRoom.getAllHappiness(); // 만족도 계산
    }

    enum Direction {
        UP(-1, 0), RIGHT(0, 1), DOWN(1, 0), LEFT(0, -1);

        private final int n;
        private final int m;

        Direction(int n, int m) {
            this.n = n;
            this.m = m;
        }

        public Direction getNext() {
            switch (this) {
                case DOWN:
                    return RIGHT;
                case RIGHT:
                    return UP;
                case LEFT:
                    return DOWN;
                case UP:
                    return LEFT;
            }
            throw new IllegalArgumentException();
        }
    }

    static class ClassRoom {

        private static final int[] HAPPINESS = {0, 1, 10, 100, 1000};
        private final Student[][] studentMap;
        private final List<Student> studentList;

        public ClassRoom(int sizeN, Student[] students) {
            this.studentMap = new Student[sizeN][sizeN];
            this.studentList = Arrays.asList(students);
        }

        public void assignAllStudentsByPreferences() {
            this.studentList.forEach(this::assignStudent); // 학생들을 선호도에 따라 배치
        }

        private void assignStudent(Student student) {
            Coordinate coordinate = findPreferenceCoordinate(student); // 선호도에 따른 좌표 찾기
            this.studentMap[coordinate.getN()][coordinate.getM()] = student; // 학생 배치
        }

        private Coordinate findPreferenceCoordinate(Student student) {
            Coordinate coordinate = null;

            PreferenceScore bestPreferenceScore = new PreferenceScore(-1, -1);

            // 상->하 좌->우 순 탐색으로 가장 왼쪽 위의 좌표를 찾을 수 있도록 함
            for (int n = 0; n < this.studentMap.length; n++) {
                for (int m = 0; m < this.studentMap[n].length; m++) {
                    Coordinate current = new Coordinate(n, m);

                    // 이미 학생이 배치된 경우 넘어감
                    if (!this.isEmptyAt(current)) {
                        continue;
                    }

                    // 선호도 점수 계산
                    PreferenceScore currentPreferenceScore =
                            calculatePreferenceScore(student, current);

                    // 현재 좌표가 더 좋은 경우 좌표와 점수 갱신
                    if (currentPreferenceScore.isBetterThan(bestPreferenceScore)) {
                        bestPreferenceScore = currentPreferenceScore;
                        coordinate = current;
                    }
                }
            }

            if (coordinate == null) {
                throw new IllegalArgumentException();
            }

            return coordinate;
        }

        private PreferenceScore calculatePreferenceScore(Student student, Coordinate current) {
            int currentPreferenceCount = 0;
            int currentEmptyCount = 0;

            for (Direction direction : Direction.values()) {
                Coordinate next = current.getNext(direction);

                if (this.isOutBound(next)) {
                    continue;
                }

                if (this.isEmptyAt(next)) {
                    currentEmptyCount++; // 빈 칸이면 빈 칸 카운트 증가
                } else {
                    // 선호 학생 순회
                    for (int studentNumber : student.getPreferenceStudentNumbers()) {
                        if (this.getStudentAtCoordinate(next).isSameNumber(studentNumber)) {
                            currentPreferenceCount++; // 선호 학생이면 선호 학생 카운트 증가
                        }
                    }
                }
            }

            return new PreferenceScore(currentPreferenceCount, currentEmptyCount);
        }


        private boolean isEmptyAt(Coordinate coordinate) {
            return this.getStudentAtCoordinate(coordinate) == null;
        }

        private boolean isOutBound(Coordinate coordinate) {
            return 0 > coordinate.getN() || this.studentMap.length <= coordinate.getN()
                    || 0 > coordinate.getM() || this.studentMap[0].length <= coordinate.getM();
        }

        private Student getStudentAtCoordinate(Coordinate coordinate) {
            return studentMap[coordinate.getN()][coordinate.getM()];
        }

        public int getAllHappiness() {
            int totalHappiness = 0;

            for (int n = 0; n < this.studentMap.length; n++) {
                for (int m = 0; m < this.studentMap[n].length; m++) {
                    Coordinate current = new Coordinate(n, m);
                    totalHappiness += calculateHappiness(
                            current,
                            this.getStudentAtCoordinate(current)
                    );
                }
            }

            return totalHappiness;
        }

        private int calculateHappiness(Coordinate current, Student student) {
            int count = 0;

            for (Direction direction : Direction.values()) {
                Coordinate next = current.getNext(direction);

                if (this.isOutBound(next)) {
                    continue;
                }

                Student nextStudent = this.getStudentAtCoordinate(next);

                // 선호 학생인 경우 카운트 증가
                for (int preferenceStudentNumber : student.getPreferenceStudentNumbers()) {
                    if (nextStudent.isSameNumber(preferenceStudentNumber)) {
                        count++;
                    }
                }
            }

            return HAPPINESS[count];
        }
    }

    private static class PreferenceScore {

        public final int preferenceCount;
        public final int emptyCount;

        public PreferenceScore(int preferenceCount, int emptyCount) {
            this.preferenceCount = preferenceCount;
            this.emptyCount = emptyCount;
        }

        // 선호 학생이 더 많거나, 선호 학생 수가 같으면 빈 칸이 더 많은 경우
        public boolean isBetterThan(PreferenceScore preferenceScore) {
            return this.preferenceCount > preferenceScore.preferenceCount ||
                    (this.preferenceCount == preferenceScore.preferenceCount &&
                            this.emptyCount > preferenceScore.emptyCount);
        }
    }


    static class Coordinate {

        private final int n;
        private final int m;

        public Coordinate(int n, int m) {
            this.n = n;
            this.m = m;
        }

        public int getN() {
            return n;
        }

        public int getM() {
            return m;
        }

        public Coordinate getNext(Direction direction) {
            return new Coordinate(n + direction.n, m + direction.m);
        }
    }

    static class Student {

        private final int number;
        private final int[] preferenceStudentNumbers;

        public Student(int number, int[] preferenceStudentNumbers) {
            this.number = number;
            this.preferenceStudentNumbers = preferenceStudentNumbers;
        }

        public int[] getPreferenceStudentNumbers() {
            return preferenceStudentNumbers;
        }

        public boolean isSameNumber(int number) {
            return this.number == number;
        }
    }
}
