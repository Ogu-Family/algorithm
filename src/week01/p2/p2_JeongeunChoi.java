package algorithm.src.week01.p2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class p2_JeongeunChoi {
    static int neverHeardPersonCnt, neverSeenPersonCnt;
    static HashSet<String> neverHeardPersons = new HashSet<>();
    static List<String> neverHeardAndSeenPersons = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        neverHeardPersonCnt = sc.nextInt();
        neverSeenPersonCnt = sc.nextInt();

        for (int i = 0; i < neverHeardPersonCnt; i++) {
            String name = sc.next();
            neverHeardPersons.add(name);
        }

        for (int i = 0; i < neverSeenPersonCnt; i++) {
            String name = sc.next();
            if (neverHeardPersons.contains(name)) {
                neverHeardAndSeenPersons.add(name);
            }
        }

        Collections.sort(neverHeardAndSeenPersons);
        System.out.println(neverHeardAndSeenPersons.size());
        for (String neverHeardAndSeenPerson : neverHeardAndSeenPersons) {
            System.out.println(neverHeardAndSeenPerson);
        }
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/2606
 * 메모리: 71480 KB
 * 시간: 764 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 * <p>
 * Set과 ArrayList 활용
 * 1. 듣도 못한 사람 입력 받아 Set에 저장
 * 2. 보도 못한 사람은 입력받을 때, 듣도 못한 사람의 Set에 존재하는 사람이라면 듣도 보도 못한 사람 리스트에 추가
 * 3. 리스트 오름차순 정렬
 * <p>
 * Set과 ArrayList 활용
 * 1. 듣도 못한 사람 입력 받아 Set에 저장
 * 2. 보도 못한 사람은 입력받을 때, 듣도 못한 사람의 Set에 존재하는 사람이라면 듣도 보도 못한 사람 리스트에 추가
 * 3. 리스트 오름차순 정렬
 */

/**
 * Set과 ArrayList 활용
 * 1. 듣도 못한 사람 입력 받아 Set에 저장
 * 2. 보도 못한 사람은 입력받을 때, 듣도 못한 사람의 Set에 존재하는 사람이라면 듣도 보도 못한 사람 리스트에 추가
 * 3. 리스트 오름차순 정렬
 */
