package chap01;

import java.util.Scanner;

public class StudyTimeEvaluator {
    //2. 구현 목적
    //사용자의 이름, 목표 공부시간, 실제 공부시간을 입력받아 공부 목표 달성 정도를 판단하는 콘솔 프로그램을 작성한다.

    // 이름 메서드
    public static String readName(Scanner scanner) {
        String name;
        while (true) {
            System.out.print("이름을 입력하세요 : ");
            name = scanner.nextLine();
            if (!name.isBlank()) { //검증 조건문
                return name.trim();
            }

            System.out.println("이름을 입력해야 합니다.");
        }
    }

    // 목표 공부시간 메서드
    public static double readTargetStudyTime(Scanner scanner) {
        while (true) {
            System.out.print("오늘 목표 공부시간을 입력하세요 : ");
            String input = scanner.nextLine();

            //아무것도 입력하지 않았거나 공백만 입력한경우
            if (input.isBlank()) {
                System.out.println("오늘 목표 공부시간을 입력해야 합니다.");
                continue;
            }

            try {
                double targetStudyTime = Double.parseDouble(input.trim());

                //0또는 음수를 입력한 경우
                if (targetStudyTime <= 0) {
                    System.out.println("목표 공부시간은 0보다 커야 합니다.");
                    continue;
                }
                return targetStudyTime;
            } catch (NumberFormatException e) {
                //숫자로 바꿀 수 없는 값을 입력한 경우
                System.out.println("목표 공부시간은 숫자로 입력해야 합니다.");
            }
        }
    }

    // 실제 공부 시간 메서드
    public static double readStudyTime(Scanner scanner) {
        while (true) {
            System.out.print("실제 공부한 시간을 입력하세요 : ");
            String input = scanner.nextLine();

            //아무것도 입력하지 않았거나 공백만 입력한경우
            if (input.isBlank()) {
                System.out.println("실제 공부한 시간을 입력해야 합니다.");
                continue;
            }

            try {
                double studyTime = Double.parseDouble(input.trim());

                //음수를 입력한 경우
                if (studyTime < 0) {
                    System.out.println("실제 공부한 시간은 0 이상이어야 합니다.");
                    continue;
                }
                return studyTime;
            } catch (NumberFormatException e) {
                //숫자로 바꿀 수 없는 값을 입력한 경우
                System.out.println("실제 공부한 시간은 숫자로 입력해야 합니다.");
            }
        }
    }

    //정수 처리 메서드
    public static String formatHours(double hours) {
        if (hours == (long) hours) {
            return String.valueOf((long) hours);
        }

        return String.valueOf(hours);
    }

    public static void main(String[] args) {

        //입력 안내 문구 예시:
        Scanner scanner = new Scanner(System.in);

        //1. 이름을 입력하세요:
        String name = readName(scanner);

        //2. 오늘 목표 공부시간을 입력하세요:
        double targetStudyTime = readTargetStudyTime(scanner);

        //3. 실제 공부시간을 입력하세요:
        double studyTime = readStudyTime(scanner);

        // 목표 달성 상태 판단.

        System.out.println("===== 공부 결과 =====");
        System.out.println(name + "님의 목표 공부시간은 " + formatHours(targetStudyTime) + "시간입니다.");
        System.out.println("실제 공부시간은 " + formatHours(studyTime) + "시간입니다.");

        if (studyTime >= targetStudyTime) {
            System.out.println("오늘의 공부 목표를 달성했습니다.");
            System.out.println("오늘의 목표 시간을 모두 채웠습니다.");
            System.out.println("오늘도 수고하셨습니다.");
        } else {
            double remainingTime = targetStudyTime - studyTime;

            if (studyTime >= targetStudyTime / 2) {
                System.out.println("목표의 절반 이상을 달성했습니다.");
                System.out.println("목표까지 " + formatHours(remainingTime) + "시간 남았습니다.");
                System.out.println("화이팅 합시다!");
            } else {
                System.out.println("조금 더 집중할 필요가 있습니다.");
                System.out.println("목표까지 " + formatHours(remainingTime) + "시간 남았습니다.");
                System.out.println("조금 더 분발해야 합니다.");
            }

        }
        scanner.close();
    }
}

