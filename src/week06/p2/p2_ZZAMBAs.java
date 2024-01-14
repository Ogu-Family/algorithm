package src.week06.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/22859
 * 메모리: 305424 KB
 * 시간: 1540 ms
 * 시간 복잡도: O(N) 으로 추정 (String을 직접 연결하고, 일부 String 메서드로 더 길어졌을 가능성이 존재함)
 * 공간 복잡도: O(N)
 */

/**
 * (미친) 구현 문제
 * 아래는 내 풀이를 쉽게 풀어 해설했다. 코드는 이해하기 어려울 것이다.
 * 문자열을 앞에서부터 끝까지 읽어나간다. main 태그는 무시한다.
 * div 태그는 p 태그보다 반드시 앞선다. div 태그 내부를 파싱하는 여부를 체크하는 inDiv, p 태그 내부를 파싱하고 있는지 여부를 체크하는 inP 변수를 선언한다.
 * div 태그임이 확실하다면 title만 가져오고 전부 무시한다. (addTitle 메서드로 구현)
 * p 여는 태그임이 확실하다면 내부 값들을 저장하되, 닫는 p 태그가 아닌 한, 내부 태그들을 전부 무시한다. (태그 내부 값인지 여부를 판단하는 isOpen 변수를 선언했다.)
 * p 태그 내부 값들의 공백을 잘 처리한다. (앞뒤 잘라내고, 공백은 1개여야 함)
 * 이것들을 전부 저장한다. 이후 출력.
 *
 * 추후 풀이를 보니 정규표현식으로 간단히 해결도 가능했다.
 *
 * 문제에서 중요한 부분
 * - <div>, </div> 사이에 하나의 문단이 존재하고, <p>, </p> 사이에 하나의 문장이 존재한다.
 * - <p>, </p> 사이에는 main 태그, div 태그, p 태그를 제외한 다른 태그들이 존재할 수도 있다. (매우 중요)
 * - p 태그 안에 있는 문장 시작과 끝에 공백이 있다면 지운다.
 * - <p>, </p> 사이에는 <br>와 같이 여는 태그만 존재할 수도 있다.
 * - 문장(p 태그)에서 공백(space)이 2개 이상 연속적으로 붙어있다면 이를 하나의 공백으로 바꾼다.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p2_ZZAMBAs {
    static List<String> res = new ArrayList<>();
    static String s;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        String parsingStr = "";
        int sSize = s.length(), flag = 0;
        int openDivFlag = (27 << 15) + (3 << 10) + (8 << 5) + 21;
        int pFlag = (27 << 10) + (15 << 5) + 28;
        boolean inP = false, inDiv = false, isOpen = false;

        for(int i = 6; i < sSize; i++) {
            if(s.charAt(i) == '/')
                continue;

            if(s.charAt(i) == '<' || s.charAt(i) == '>') {
                flag = (s.charAt(i) == '<') ? (flag << 5) + 27 : (flag << 5) + 28;
                isOpen = !isOpen;
            }
            else
                flag = s.charAt(i) != ' ' ? (flag << 5) + (s.charAt(i) - 97) : 30;

            if (!inP && (flag & 0b11111111111111111111) == openDivFlag) {

                if(inDiv)
                    inDiv = false;
                else {
                    i = addTitle(i + 9);
                    inDiv = true;
                }
                parsingStr = "";
                continue;
            }

            if ((flag & 0b111111111111111) == pFlag) {
                inP = !inP;

                if (!inP && (flag & 0b111111111111111) == pFlag) {
                    addTrimStr(parsingStr);
                    parsingStr = "";
                }

                continue;
            }

            if (!isOpen && s.charAt(i) != '>')
                parsingStr += s.charAt(i);
        }

        for(String resStr : res)
            System.out.println(resStr);
    }

    static void addTrimStr(String str1) {
        String trimedStr = "";
        String str = str1.trim();
        int sSize = str.length();
        boolean isBlank = false, inTag = false;

        for(int i = 0; i < sSize; i++) {
            if(str.charAt(i) == '<' || inTag) {
                inTag = true;
                continue;
            }
            else if(str.charAt(i) == '>') {
                inTag = false;
            }

            if (str.charAt(i) == ' ') {
                if (!isBlank) {
                    isBlank = true;
                    trimedStr += str.charAt(i);
                }
                continue;
            }
            isBlank = false;
            trimedStr += str.charAt(i);
        }

        res.add(trimedStr.trim());
    }

    static int addTitle(int i) {
        String str = "";
        while(s.charAt(i) != '\"')
            str += s.charAt(i++);

        res.add("title : " + str);

        return i;
    }
}
