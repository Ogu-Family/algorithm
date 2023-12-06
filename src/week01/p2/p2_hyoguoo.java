/**
 * 문제 링크: https://www.acmicpc.net/problem/2606
 * 메모리: 26480 KB
 * 시간: 304 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N + M)
 */

/*
듣도 못한 사람 저장할 Set / 듣도 보도 못한 사람 저장할 List 생성
1. n만큼 듣도 못한 사람 입력 받아 Set에 저장
2. m만큼 보도 못한 사람 입력 받아 Set에 존재하는 사람이라면 List에 추가
3. List 오름차순 정렬
4. List 출력
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Anonymous {

    static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] info = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = info[0];
        int m = info[1];

        solution(n, m);
    }

    private static void solution(int n, int m) throws IOException {
        Set<String> wordSet = getWordSet(n);
        List<String> duplicateList = getDuplicateList(m, wordSet);

        printResult(duplicateList);
    }

    private static void printResult(List<String> duplicateList) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(duplicateList.size()).append("\n");
        for (String word : duplicateList) {
            stringBuilder.append(word).append("\n");
        }

        System.out.print(stringBuilder);
    }

    private static List<String> getDuplicateList(int m, Set<String> wordSet) throws IOException {
        List<String> duplicateList = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String str = bufferedReader.readLine();
            if (wordSet.contains(str)) duplicateList.add(str);
        }

        Collections.sort(duplicateList);
        return duplicateList;
    }

    private static Set<String> getWordSet(int n) throws IOException {
        Set<String> wordSet = new HashSet<>();

        for (int i = 0; i < n; i++) wordSet.add(bufferedReader.readLine());

        return wordSet;
    }
}
