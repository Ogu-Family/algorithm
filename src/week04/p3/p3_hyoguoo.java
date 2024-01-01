/**
 * 문제 링크: https://www.acmicpc.net/problem/1747
 * 메모리: 316856 KB
 * 시간: 3004 ms
 * 시간 복잡도: O(N * ?)
 * 공간 복잡도: O(1)
 */

/**
 * 팰린드롬 + 소수 판정
 *
 * 1. 다음 소수를 찾음
 * 2. 소수인지 판정
 * 3. 팰린드롬인지 판정
 * 4. 둘 다 만족하면 리턴
 * 5. 아니면 1을 더해서 다시 2번으로
 *
 * 모든 알고리즘을 자바 라이브러리로 풀이해보았습니다.
 * 사실 무조건 시간 초과가 날 것으로 예상했는데 통과가 되어 신기해서 올려봅니다..
 * Java 그룹 중 뒤에서 두 번쨰로 느린 풀이 달성ㅎㅎ
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class PrimeAndPalindrome {

    public static void main(String[] args) throws IOException {
        System.out.println(solution(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine())));
    }

    private static int solution(int n) {
        BigInteger bigInteger = BigInteger.valueOf(n);

        while (true) {
            if (bigInteger.isProbablePrime(10) && // 소수 판정
                isPalindrome(bigInteger.toString())) // 팰린드롬 판정
                return bigInteger.intValue(); // 둘 다 만족하면 리턴
            bigInteger = bigInteger.add(BigInteger.ONE); // 다음 수로 넘어감
        }
    }

    private static boolean isPalindrome(String string) {
        return string.contentEquals(new StringBuilder(string).reverse());
    }
}
