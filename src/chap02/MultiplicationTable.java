package chap02;

import java.util.Scanner;

public class MultiplicationTable {
    public static int readDan(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().strip();

            if (input.isBlank()) {
                System.out.println("공백은 허용되지 않습니다.");
                continue;
            }

            try {
                int dan = Integer.parseInt(input);

                if (dan < 2 || dan > 9) {
                    System.out.println("2~9 사이의 정수를 입력해주세요.");
                    continue;
                }
                return dan;

            } catch (NumberFormatException e) {
                System.out.println("2~9 사이의 정수를 입력해주세요.");
            }
        }
    }

    //구구단 출력 → main()에서 직접 구현
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int startDan = readDan(scanner, "시작 단을 입력하세요: ");
        int endDan = readDan(scanner, "끝 단을 입력하세요: ");

        while (startDan > endDan) {
            System.out.println("시작 단은 끝 단보다 클 수 없습니다.");

            endDan = readDan(scanner, "끝 단을 다시 입력하세요 (범위: 2~9): ");
        }

        System.out.println();
        System.out.println("===== 구구단 =====");
        System.out.println();

        for (int i = startDan; i <= endDan; i++) {
            System.out.println("[" + i + "단]");
            for (int j = 1; j <= 9; j++) {
                int result = i * j;
                System.out.println(i + " * " + j + " = " + result);
            }
            System.out.println();
        }

        scanner.close();
    }
}



