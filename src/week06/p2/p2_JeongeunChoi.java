package algorithm.src.week06.p2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 문제 링크: https://www.acmicpc.net/problem/22859
 * 메모리: 239228 KB
 * 시간: 1544 ms
 * 시간 복잡도: O(N) 입력받은 html의 길이에 비례
 * 공간 복잡도: O(N) 입력받은 html의 길이에 비례
 */

/**
 * "<>"를 기준으로 태그 추출하며 dfs 돌리기
 * div와 p를 stack을 이용하여 시작 태그는 넣고 종료 태그는 stack에서 꺼낸 값과 비교하여 같으면 종료 태그로 판별
 * div 시작 태그 발견 시, 제목 출력 후 스택에 넣기
 * p 시작 태그 발견 시, 시작 인덱스 값 pStartIdx에 저장
 * p 종료 태그 발견 시, 마지막 인덱스 값 pEndIdx에 저장 후,
 * pStartIdx에서 pEndIdx에 해당하는 문자열 추출 후 정규표현식으로 문제 조건에 맞게 출력
 */

public class p2_JeongeunChoi {

    static String startTag = "<", endTag = ">", input;
    static Stack<String> st = new Stack<>();
    static int pStartIdx, pEndIdx;

    private static void dfs(int startTagIdx, int endTagIdx) {
        String tag = input.substring(startTagIdx, endTagIdx + 1).replaceAll("[<>/]", "");
        if (!st.isEmpty() && st.peek().equals(tag)) {
            if (st.peek().equals("p")) {
                pEndIdx = endTagIdx;
                String pStr = input.substring(pStartIdx, pEndIdx + 1)
                        .replaceAll("<(/?([a-z])* ?)>", "")
                        .replaceAll("^ +| +$", "")
                        .replaceAll(" {2,}", " ");
                System.out.println(pStr);
            }
            st.pop();
        } else if (tag.length() >= 4 && tag.startsWith("div ")) {
            extractTitle(tag, "\"([a-zA-Z0-9_ ]*)\"");
            st.push("div");
        } else if (tag.equals("p")) {
            pStartIdx = startTagIdx;
            st.push(tag);
        }

        startTagIdx = input.indexOf(startTag, endTagIdx + 1);
        endTagIdx = input.indexOf(endTag, endTagIdx + 1);
        if (startTagIdx != -1) {
            dfs(startTagIdx, endTagIdx);
        }
    }

    private static void extractTitle(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println("title : " + matcher.group(1));
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine();

        int startTagIdx = input.indexOf(startTag);
        int endTagIdx = input.indexOf(endTag);

        dfs(startTagIdx, endTagIdx);
    }

}
