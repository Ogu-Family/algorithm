/**
 * 문제 링크: https://www.acmicpc.net/problem/21609
 * 메모리: 28956 KB
 * 시간: 192 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2)
 */

/**
 * 문제 요구 사항에 맞게 메서드를 객체지향적으로 분리하였습니다.(주석 참고)
 * 무지개블록은 방문 처리를 다른 방식으로 처리하는 부분이 중요한 문제였던 것 같습니다!
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class SharkMiddleSchool {

    public static void main(String[] args) throws IOException {
        BlockSimulator blockSimulator = createBlockSimulator();

        System.out.print(blockSimulator.simulate());
    }

    private static BlockSimulator createBlockSimulator() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int sizeN = info[0];

        int[][] map = new int[sizeN][sizeN];

        for (int n = 0; n < sizeN; n++) {
            map[n] = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        return new BlockSimulator(map);
    }

    enum Direction {
        UP(-1, 0), RIGHT(0, 1), DOWN(1, 0), LEFT(0, -1);

        private final int n;
        private final int m;

        Direction(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }

    static class BlockSimulator {

        private static final int EMPTY = -99;
        private static final int BLACK = -1;
        private static final int RAINBOW = 0;
        private static final int NORMAL_BLOCK_MINIMUM = 1;
        private static final int MINIMUM_BLOCK_COUNT = 2;
        private final int[][] map;

        public BlockSimulator(int[][] map) {
            this.map = map;
        }

        public int simulate() {
            int score = 0;

            while (true) {
                // 1. 크기가 가장 큰 블록 그룹을 찾는다.
                BlockGroup blockGroup = findMostBlockGroup();
                if (!blockGroup.getCount().isMoreThan(MINIMUM_BLOCK_COUNT)) {
                    break;
                }

                // 2. 1에서 찾은 블록 그룹의 모든 블록을 제거한다. 블록 그룹에 포함된 블록의 수를 B라고 했을 때, B2점을 획득한다.
                score += popBlock(blockGroup);
                // 3. 격자에 중력이 작용한다.
                gravity();
                // 4. 격자가 90도 반시계 방향으로 회전한다.
                rotate();
                // 5. 다시 격자에 중력이 작용한다.
                gravity();
            }

            return score;
        }

        private int popBlock(BlockGroup blockGroup) {
            removeBlockGroup(blockGroup.getCoordinateList());
            return (int) Math.pow(blockGroup.getCount().getTotalBlockCount(), 2);
        }

        private void gravity() {
            for (int m = 0; m < map[0].length; m++) {
                for (int n = map.length - 1; n >= 0; n--) {
                    if (map[n][m] == EMPTY || map[n][m] == BLACK) {
                        continue;
                    }

                    int value = map[n][m];
                    Coordinate coordinate = new Coordinate(n, m);

                    while (true) {
                        Coordinate next = coordinate.getNext(Direction.DOWN);
                        if (isOutBound(next) || map[next.n][next.m] != EMPTY) {
                            break;
                        }
                        map[next.n][next.m] = value;
                        map[coordinate.n][coordinate.m] = EMPTY;
                        coordinate = next;
                    }
                }
            }
        }

        private void removeBlockGroup(List<Coordinate> coordinateList) {
            coordinateList
                    .forEach(coordinate -> map[coordinate.n][coordinate.m] = EMPTY);
        }

        private BlockGroup findMostBlockGroup() {
            BlockGroup bestBlockGroup = new BlockGroup(NORMAL_BLOCK_MINIMUM - 1);

            // 일반 블록만 방문 처리하기 위한 배열
            boolean[][] normalBlockVisited = new boolean[map.length][map[0].length];

            for (int n = 0; n < map.length; n++) {
                for (int m = 0; m < map[n].length; m++) {
                    if (map[n][m] < NORMAL_BLOCK_MINIMUM || normalBlockVisited[n][m]) {
                        continue;
                    }

                    BlockGroup currentBlockGroup = findBlockGroup(new Coordinate(n, m), normalBlockVisited);
                    // 가장 오른쪽 아래의 그룹으로 갱신하기 위해 같은 경우에도 갱신
                    if (bestBlockGroup.getCount().isLessOrEqual(currentBlockGroup.getCount())) {
                        bestBlockGroup = currentBlockGroup;
                    }
                }
            }

            return bestBlockGroup;
        }

        private BlockGroup findBlockGroup(Coordinate start, boolean[][] normalBlockVisited) {
            // 블록 그룹을 찾기 위한 방문 처리 배열, 무지개 블록도 중복 방문을 방지하기 위해 체크
            boolean[][] visited = new boolean[map.length][map[0].length];
            Deque<Coordinate> deque = new ArrayDeque<>();
            deque.add(start);
            visited[start.n][start.m] = true;
            BlockGroup blockGroup = new BlockGroup(map[start.n][start.m]);

            while (!deque.isEmpty()) {
                Coordinate current = deque.poll();

                if (map[current.n][current.m] == RAINBOW) {
                    blockGroup.getCount().addRainbowBlockCount();
                } else if (map[current.n][current.m] == map[start.n][start.m]) {
                    normalBlockVisited[current.n][current.m] = true; // 일반 블록 방문 처리
                    blockGroup.getCount().addNormalBlockCount();
                }

                blockGroup.addCoordinate(current);

                for (Direction direction : Direction.values()) {
                    Coordinate next = current.getNext(direction);
                    if (isOutBound(next) || visited[next.n][next.m]) {
                        continue;
                    }

                    if (map[next.n][next.m] == RAINBOW ||
                            blockGroup.isBlockValueEqual(map[next.n][next.m])) {
                        deque.add(next);
                        visited[next.n][next.m] = true;
                    }
                }
            }

            return blockGroup;
        }

        private boolean isOutBound(Coordinate coordinate) {
            return coordinate.n < 0 || coordinate.n >= map.length ||
                    coordinate.m < 0 || coordinate.m >= map[0].length;
        }

        private void rotate() {
            int[][] temp = new int[map.length][map.length];

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    temp[i][j] = map[j][map.length - 1 - i];
                }
            }

            updateMap(temp);
        }

        private void updateMap(int[][] temp) {
            for (int i = 0; i < map.length; i++) {
                System.arraycopy(temp[i], 0, map[i], 0, map.length);
            }
        }
    }

    static class BlockGroup {

        private final Count count;
        private final int blockValue;
        private final List<Coordinate> coordinateList;

        public BlockGroup(int blockValue) {
            this.count = new Count();
            this.blockValue = blockValue;
            this.coordinateList = new ArrayList<>();
        }

        public boolean isBlockValueEqual(int blockValue) {
            return this.blockValue == blockValue;
        }

        public Count getCount() {
            return count;
        }

        public void addCoordinate(Coordinate coordinate) {
            coordinateList.add(coordinate);
        }

        public List<Coordinate> getCoordinateList() {
            return coordinateList;
        }
    }

    static class Count {

        private int normalBlockCount;
        private int rainbowBlockCount;

        public Count() {
            this.normalBlockCount = 0;
            this.rainbowBlockCount = 0;
        }

        public void addNormalBlockCount() {
            this.normalBlockCount++;
        }

        public void addRainbowBlockCount() {
            this.rainbowBlockCount++;
        }

        public boolean isLessOrEqual(Count count) {
            if (this.normalBlockCount + this.rainbowBlockCount
                    < count.normalBlockCount + count.rainbowBlockCount) {
                return true;
            }

            return this.normalBlockCount + this.rainbowBlockCount
                    == count.normalBlockCount + count.rainbowBlockCount
                    && this.rainbowBlockCount <= count.rainbowBlockCount;
        }

        public int getTotalBlockCount() {
            return this.normalBlockCount + this.rainbowBlockCount;
        }

        public boolean isMoreThan(int n) {
            return this.normalBlockCount + this.rainbowBlockCount >= n;
        }
    }

    static class Coordinate {

        private final int n;
        private final int m;

        public Coordinate(int n, int m) {
            this.n = n;
            this.m = m;
        }

        public Coordinate getNext(Direction direction) {
            return new Coordinate(n + direction.n, m + direction.m);
        }
    }
}
