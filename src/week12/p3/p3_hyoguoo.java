/**
 * 문제 링크: https://www.acmicpc.net/problem/1058
 * 메모리: 14448 KB
 * 시간: 136 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N)
 */

/**
 * depth가 2뿐이기 때문에 그래프 탐색 없이 단순 구현으로 풀어보았습니다~~
 *
 * 1. 모든 사람에 대해 countFriends 함수를 호출하여 친구의 수를 구함
 * 2. 입력 받은 배열을 통해 친구인지 확인
 * 3. 친구가 아니라면 친구의 친구인지 확인
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Friend {

    static final char IS_FRIEND = 'Y';

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        boolean[][] friends = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String line = bufferedReader.readLine();
            for (int j = 0; j < n; j++) {
                friends[i][j] = line.charAt(j) == IS_FRIEND;
            }
        }

        System.out.print(solution(friends));
    }

    private static int solution(boolean[][] friends) {
        return IntStream.range(0, friends.length)
                .map(i -> countFriends(friends, i))
                .max()
                .orElse(0);
    }

    private static int countFriends(boolean[][] friends, int me) {
        int count = 0;

        for (int firend = 0; firend < friends.length; firend++) {
            if (me == firend) {
                continue;
            }

            if (friends[me][firend]) {
                count++;
            } else {
                // 내 친구의 친구가 나와 친구인지 확인
                for (int middle = 0; middle < friends.length; middle++) {
                    if (friends[me][middle] && friends[firend][middle]) {
                        count++;
                        break;
                    }
                }
            }
        }

        return count;
    }
}
