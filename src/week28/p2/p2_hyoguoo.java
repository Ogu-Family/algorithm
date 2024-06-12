/**
 * 문제 링크: https://www.acmicpc.net/problem/14891
 * 메모리: 15060 KB
 * 시간: 160 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(1)
 */

/**
 * 빡구현 문제
 * 1. 회전하게 되는 톱니바퀴들을 구함
 *   a. 현재 톱니바퀴의 왼쪽방향으로 탐색하여 맞닿아 있는지 확인
 *   b. 현재 톱니바퀴의 오른쪽방향으로 탐색하여 맞닿아 있는지 확인
 *   c. 맞닿아 있다면 회전하게 되는 톱니바퀴로 추가
 *   d. 방향은 현재 톱니바퀴의 인덱스를 기준으로 %2 == 0 이면 같은 방향, % 2 == 1 이면 반대 방향
 * 2. 회전하게 되는 톱니바퀴들을 회전시킴
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class GearSystem {

    private static final int GEAR_COUNT = 4;
    private static final int GEAR_SIZE = 8;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Gear[] gears = new Gear[GEAR_COUNT];

        for (int i = 0; i < GEAR_COUNT; i++) {
            int[] teeth = Arrays.stream(bufferedReader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            gears[i] = new Gear(teeth);
        }

        int rotateCount = Integer.parseInt(bufferedReader.readLine());
        RotateQuery[] rotateQueries = new RotateQuery[rotateCount];

        for (int i = 0; i < rotateCount; i++) {
            int[] info = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            rotateQueries[i] = new RotateQuery(info[0], info[1]);
        }

        System.out.print(solution(gears, rotateQueries));
    }

    private static int solution(Gear[] gears, RotateQuery[] rotateQueries) {
        for (RotateQuery rotateQuery : rotateQueries) {
            List<Rotate> rotateList = getRotateList(
                    gears,
                    rotateQuery.getGearIndex(),
                    rotateQuery.getDirection()
            );
            rotateList.forEach(Rotate::rotate);
        }

        return calculateScore(gears);
    }

    private static List<Rotate> getRotateList(
            Gear[] gears,
            int startGearIndex,
            Direction direction
    ) {
        List<Rotate> rotateList = new ArrayList<>();

        rotateList.add(
                new Rotate(
                        gears[startGearIndex],
                        direction
                )
        );

        int leftIndex = startGearIndex - 1;
        while (leftIndex >= 0) {
            if (!gears[leftIndex].isConnectRightGear(gears[leftIndex + 1])) {
                break;
            }
            rotateList.add(
                    new Rotate(
                            gears[leftIndex],
                            getNextDirection(direction, startGearIndex, leftIndex)
                    )
            );
            leftIndex--;
        }

        int rightIndex = startGearIndex + 1;
        while (rightIndex < GEAR_COUNT) {
            if (!gears[rightIndex].isConnectLeftGear(gears[rightIndex - 1])) {
                break;
            }
            rotateList.add(
                    new Rotate(
                            gears[rightIndex],
                            getNextDirection(direction, startGearIndex, rightIndex)
                    )
            );
            rightIndex++;
        }

        return rotateList;
    }

    private static Direction getNextDirection(
            Direction startDirection,
            int startIndex,
            int currentIndex
    ) {
        if ((startIndex - currentIndex) % 2 == 0) {
            return startDirection;
        }

        return startDirection.nextDirection();
    }

    private static int calculateScore(Gear[] gears) {
        int score = 0;

        for (int i = 0; i < GEAR_COUNT; i++) {
            if (gears[i].isSameToothAtFirst(Tooth.S)) {
                score += (int) Math.pow(2, i);
            }
        }

        return score;
    }

    enum Direction {
        CLOCKWISE(1),
        COUNTER_CLOCKWISE(-1);

        private final int directionValue;

        Direction(int directionValue) {
            this.directionValue = directionValue;
        }

        public static Direction of(int directionValue) {
            return Arrays.stream(values())
                    .filter(direction -> direction.directionValue == directionValue)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }

        public Direction nextDirection() {
            switch (this) {
                case CLOCKWISE:
                    return COUNTER_CLOCKWISE;
                case COUNTER_CLOCKWISE:
                    return CLOCKWISE;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    enum Tooth {
        N(0),
        S(1);

        private final int toothValue;

        Tooth(int toothValue) {
            this.toothValue = toothValue;
        }

        public static Tooth of(int toothValue) {
            return Arrays.stream(values())
                    .filter(tooth -> tooth.toothValue == toothValue)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }

        public boolean isConnect(Tooth tooth) {
            switch (this) {
                case N:
                    return tooth == S;
                case S:
                    return tooth == N;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    static class Rotate {

        private final Gear gear;

        private final Direction direction;

        public Rotate(Gear gear, Direction direction) {
            this.gear = gear;
            this.direction = direction;
        }

        public Direction getDirection() {
            return direction;
        }

        public void rotate() {
            gear.rotate(direction);
        }
    }

    static class Gear {

        private static final int RIGHT_TOOTH_INDEX = 2;
        private static final int LEFT_TOOTH_INDEX = 6;
        private final Deque<Tooth> teethDeque;

        public Gear(int[] teeth) {
            this.teethDeque = new ArrayDeque<>();
            for (int i = 0; i < GEAR_SIZE; i++) {
                this.teethDeque.add(Tooth.of(teeth[i]));
            }
        }

        public void rotate(Direction direction) {
            if (direction == Direction.CLOCKWISE) {
                teethDeque.addFirst(teethDeque.pollLast());
            } else {
                teethDeque.addLast(teethDeque.pollFirst());
            }
        }

        public boolean isConnectLeftGear(Gear leftGear) {
            return getLeftTooth().isConnect(leftGear.getRightTooth());
        }

        public boolean isConnectRightGear(Gear rightGear) {
            return getRightTooth().isConnect(rightGear.getLeftTooth());
        }

        public boolean isSameToothAtFirst(Tooth tooth) {
            return getFirstTooth() == tooth;
        }

        private Tooth getFirstTooth() {
            return teethDeque.peekFirst();
        }

        public Tooth getLeftTooth() {
            int index = 0;

            for (Tooth tooth : teethDeque) {
                if (index == LEFT_TOOTH_INDEX) {
                    return tooth;
                }
                index++;
            }

            throw new IllegalArgumentException();
        }

        public Tooth getRightTooth() {
            int index = 0;

            for (Tooth tooth : teethDeque) {
                if (index == RIGHT_TOOTH_INDEX) {
                    return tooth;
                }
                index++;
            }

            throw new IllegalArgumentException();
        }
    }

    static class RotateQuery {

        private final Direction direction;
        private final int gearIndex;

        public RotateQuery(int gearIndex, int direction) {
            this.gearIndex = gearIndex - 1;
            this.direction = Direction.of(direction);
        }

        public Direction getDirection() {
            return direction;
        }

        public int getGearIndex() {
            return gearIndex;
        }
    }
}
