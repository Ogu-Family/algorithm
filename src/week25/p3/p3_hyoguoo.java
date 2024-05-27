/**
 * 문제 링크: https://www.acmicpc.net/problem/23835
 * 메모리: 303416 KB
 * 시간: 1164 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2)
 */

/**
 * 요구사항대로 클래스를 만들어 풀어보았습니다~~
 */

package implementation.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SeismicDesign {

    public static void main(String[] args) throws IOException {
        System.out.print(solution(parseMap()));
    }

    private static Map parseMap() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int sizeN = info[0];
        int sizeM = info[1];

        char[][] map = new char[sizeN][sizeM];

        for (int n = 0; n < sizeN; n++) {
            map[n] = bufferedReader.readLine().toCharArray();
        }

        return new Map(map);
    }

    private static Map solution(Map map) {
        map.simulate();
        return map;
    }

    enum MapObject {
        EPICENTER('@'),
        EMPTY('.'),
        NOT_SEISMIC('*'),
        SEISMIC('#'),
        BREAKWATER('|');

        private final char symbol;

        MapObject(char symbol) {
            this.symbol = symbol;
        }

        public static MapObject valueOf(char symbol) {
            return Arrays.stream(values())
                    .filter(mapObject -> mapObject.symbol == symbol)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }

    enum Direction {
        UP(-1, 0),
        RIGHT(0, 1),
        DOWN(1, 0),
        LEFT(0, -1);

        private final int n;
        private final int m;

        Direction(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }

    static class Map {

        private static final int EPICENTER_DAMAGE = 2;
        private static final int SEISMIC_DAMAGE = 1;
        private final MapObject[][] maps;
        private final Deque<Earthquake> earthquakes;
        private int totalBuildings;
        private int damagedBuildings;

        public Map(char[][] map) {
            this.maps = new MapObject[map.length][map[0].length];
            this.totalBuildings = 0;
            this.damagedBuildings = 0;
            earthquakes = new ArrayDeque<>();

            for (int n = 0; n < map.length; n++) {
                for (int m = 0; m < map[n].length; m++) {
                    MapObject mapObject = MapObject.valueOf(map[n][m]);
                    if (mapObject == MapObject.NOT_SEISMIC ||
                            mapObject == MapObject.SEISMIC) {
                        totalBuildings++;
                    }
                    if (mapObject == MapObject.EPICENTER) {
                        earthquakes.add(new Earthquake(new Coordinate(n, m), EPICENTER_DAMAGE));
                    }
                    this.maps[n][m] = mapObject;
                }
            }
        }

        public void simulate() {
            while (!this.earthquakeIsEmpty()) {
                Earthquake current = this.pollEarthquake();

                for (Direction direction : Direction.values()) {
                    damageInDirection(current, direction);
                }
            }
        }

        private void damageInDirection(Earthquake current, Direction direction) {
            for (int i = 1; i <= current.damage; i++) {
                Coordinate next = current.getEpicenterCoordinate().getNext(direction, i);
                if (this.isInBound(next)) {
                    if (this.isBreakwater(next)) {
                        break;
                    }
                    if (this.damage(next)) {
                        this.earthquakes.add(new Earthquake(next, SEISMIC_DAMAGE));
                    }
                }
            }
        }

        private Earthquake pollEarthquake() {
            if (earthquakes.isEmpty()) {
                throw new NullPointerException();
            }

            Earthquake poll = earthquakes.poll();
            this.maps[poll.getEpicenterCoordinate().n][poll.getEpicenterCoordinate().m] = MapObject.EMPTY;

            return poll;
        }

        private boolean earthquakeIsEmpty() {
            return this.earthquakes.isEmpty();
        }

        private boolean damage(Coordinate coordinate) {
            switch (maps[coordinate.n][coordinate.m]) {
                case EPICENTER:
                case EMPTY:
                case BREAKWATER:
                    return false;
                case NOT_SEISMIC:
                    maps[coordinate.n][coordinate.m] = MapObject.EMPTY;
                    damagedBuildings++;
                    return true;
                case SEISMIC:
                    maps[coordinate.n][coordinate.m] = MapObject.NOT_SEISMIC;
                    return false;
                default:
                    throw new IllegalArgumentException();
            }
        }

        private boolean isInBound(Coordinate coordinate) {
            return 0 <= coordinate.n && coordinate.n < maps.length &&
                    0 <= coordinate.m && coordinate.m < maps[0].length;
        }

        private boolean isBreakwater(Coordinate coordinate) {
            return maps[coordinate.n][coordinate.m] == MapObject.BREAKWATER;
        }

        @Override
        public String toString() {
            return damagedBuildings + " " + (totalBuildings - damagedBuildings);
        }
    }

    static class Earthquake {

        private final Coordinate epicenter;
        private final int damage;

        public Earthquake(Coordinate epicenter, int damage) {
            this.epicenter = epicenter;
            this.damage = damage;
        }

        public Coordinate getEpicenterCoordinate() {
            return epicenter;
        }
    }

    static class Coordinate {

        private final int n;
        private final int m;

        public Coordinate(int n, int m) {
            this.n = n;
            this.m = m;
        }

        public Coordinate getNext(Direction direction, int distance) {
            return new Coordinate(n + direction.n * distance, m + direction.m * distance);
        }
    }
}
