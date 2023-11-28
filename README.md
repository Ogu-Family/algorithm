# 🧩 Algorithm Study

기업 코딩테스트에서 요구하는 알고리즘 학습 및 다양한 문제 풀이 경험을 목표로 합니다.

## 👥 멤버

|  Name   |             [권효승](https://github.com/hyoguoo)              |             [김남규](https://github.com/GiHoo)              |             [박유진](https://github.com/eugene225)              |             [이유정](https://github.com/letskuku)              |             [조인수](https://github.com/ZZAMBAs)              |             [최정은](https://github.com/JeongeunChoi)              |
|:-------:|:----------------------------------------------------------:|:--------------------------------------------------------:|:------------------------------------------------------------:|:-----------------------------------------------------------:|:----------------------------------------------------------:|:---------------------------------------------------------------:|
| Profile | <img width="100px" src="https://github.com/hyoguoo.png" /> | <img width="100px" src="https://github.com/GiHoo.png" /> | <img width="100px" src="https://github.com/eugene225.png" /> | <img width="100px" src="https://github.com/letskuku.png" /> | <img width="100px" src="https://github.com/ZZAMBAs.png" /> | <img width="100px" src="https://github.com/JeongeunChoi.png" /> |

<br>

## 🗓️ 일정표

| 주차 | 문제 1 | 문제 2 | 문제 3 |
|:--:|:----:|:----:|:----:|
| 1  |  -   |  -   |  -   |

** 문제 출제자는 마크다운 문법(`[문제 이름](링크)`)을 사용하여 해당 주차에 문제를 추가

<br>

## 🔁 진행 순서

일주일 단위로 아래의 순서로 진행합니다.

1. `문제 출제`: 문제 출제자가 진행 해당 주차 시작 전 일요일 자정까지 문제 선정
2. `문제 제출`: 해당 주 월/수/금 자정까지 Pull Request로 문제 제출(해당 주 3회 중 2회 이상 제출 필수)
3. `리뷰`: 다음 문제 제출 기한 전까지 완료

<br>

## 📜 제출 방법

1. (Fork X) 본인 계정 이름으로 브랜치 생성
2. 해당 브랜치에 풀이한 코드 커밋
    - 경로: `/src/week*/p*_{닉네임}.java`(예시: `/src/week1/p1_ogu.java`)
3. Main 브랜치로 Pull Request
4. 각자 리뷰 완료 후 Merge

** 상세한 컨벤션은 스터디 진행하면서 결정

<br>

## 📝 코드 작성 가이드

풀이 코드와 함께 아래의 내용을 주석으로 작성합니다.

- 코드 앞 부분에 문제 링크 / 메모리 / 시간 / 시간복잡도 / 공간복잡도 추가
- 주요 알고리즘과 문제에서 중요한 부분 설명
- 필요한 경우 코드 내에 주석 추가

```java
/**
 * 문제 링크: https://www.acmicpc.net/problem/1000
 * 메모리: 14448 KB
 * 시간: 132 ms
 * 시간 복잡도: O(1)
 * 공간 복잡도: O(1)
 */

/*
1. 두 정수 A와 B를 입력받은 후
2. A+B 계산
3. 결과 출력
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 입력을 받아 공백으로 분리
        String[] input = bufferedReader.readLine().split(" ");

        // 분리 된 문자열을 정수로 변환
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);

        System.out.println(a + b);
    }
}
```
