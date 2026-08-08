package chap02;

import java.util.Scanner;

public class RangeAnalyzer {
    public static int readNumber(Scanner scanner) {
        while (true) {
            System.out.print("숫자를 입력하세요: ");
            String input = scanner.nextLine().strip();

            if (input.isBlank()) {
                System.out.println("빈 입력 값은 다시 입력");
                continue;
            }

            try {
                int num = Integer.parseInt(input);
                //1 이상 10,000 이하의 정수
                if (num >= 1 && num <= 10_000) {
                    return num;
                } else {
                    System.out.println("범위를 벗어난 값은 다시 입력");
                }
            } catch (NumberFormatException e) {
                System.out.println("문자 값은 다시 입력");
            }
        }
    }

    public static int calculateTotalSum(int num) {
        int sumAll = 0;
        for (int i = 1; i <= num; i++) {
            sumAll += i;
        }
        return sumAll;
    }

    public static int calculateEvenSum(int num) {
        int sumEvenNum = 0;
        for (int i = 2; i <= num; i += 2) {
            sumEvenNum += i;
        }
        return sumEvenNum;
    }

    public static int calculateOddSum(int num) {
        int sumOddNum = 0;
        for (int i = 1; i <= num; i += 2) {
            sumOddNum += i;
        }
        return sumOddNum;
    }

    public static int countEvenNumbers(int num) {
        int numOfEvenNum = 0;
        for (int i = 2; i <= num; i += 2) {
            numOfEvenNum++;
        }
        return numOfEvenNum;
    }

    public static int countOddNumbers(int num) {
        int numOfOddNum = 0;
        for (int i = 1; i <= num; i += 2) {
            numOfOddNum++;
        }
        return numOfOddNum;
    }


    public static void main(String[] args) {
        //Scanner 창 열기
        Scanner scanner = new Scanner(System.in);

        int num = readNumber(scanner);

        //전체 숫자의 합
        int sumAll = calculateTotalSum(num);
        //짝수의 합
        int sumEvenNum = calculateEvenSum(num);
        //홀수의 합
        int sumOddNum = calculateOddSum(num);
        //짝수의 개수
        int numOfEvenNum = countEvenNumbers(num);
        //홀수의 개수
        int numOfOddNum = countOddNumbers(num);

        // 출력
        System.out.println();
        System.out.println("===== 분석 결과 =====");
        System.out.println("범위: 1 ~ " + num);
        System.out.println("전체 합: " + sumAll);
        System.out.println("짝수 합: " + sumEvenNum);
        System.out.println("홀수 합: " + sumOddNum);
        System.out.println("짝수 개수: " + numOfEvenNum + "개");
        System.out.println("홀수 개수: " + numOfOddNum + "개");


        //Scanner 창 닫기
        scanner.close();

    }
}
