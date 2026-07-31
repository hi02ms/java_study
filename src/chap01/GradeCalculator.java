package chap01;

import java.text.*;
import java.util.*;
import java.util.regex.Pattern;

public class GradeCalculator {

    private static final Pattern NAME_PATTERN = Pattern.compile(
            "^[\\p{L}\\p{M}]+(?:[ '’·-][\\p{L}\\p{M}]+)*$");

    private static final String[] SUBJECTS = {"국어", "영어", "수학"};

    public static String readStudentName(Scanner scanner) {
        while (true) {
            System.out.print("학생 이름을 입력하세요: ");
            String input = scanner.nextLine();

            String name = Normalizer.normalize(input.trim(), Normalizer.Form.NFC);

            if (name.isBlank()) {
                System.out.println("학생 이름을 입력해야 합니다.");
                continue;
            }

            int nameLength = name.codePointCount(0, name.length());

            if (nameLength > 50) {
                System.out.println("학생 이름은 50자 이하여야 합니다.");
                continue;
            }

            if (!NAME_PATTERN.matcher(name).matches()) {
                System.out.println("이름에는 문자, 공백, 하이픈, 작은따옴표, 가운데점만 사용할 수 있습니다.");
                continue;
            }
            return name;
        }
    }

    public static int[] readStudentScores(Scanner scanner) {
        int[] studentScores = new int[SUBJECTS.length];

        for (int i = 0; i < SUBJECTS.length; i++) {
            while (true) {
                System.out.print(SUBJECTS[i] + "점수를 입력하세요: ");
                String input = scanner.nextLine();

                // 아무것도 입력하지 않은경우.
                if (input.isBlank()) {
                    System.out.println("점수를 입력해야 합니다.");
                    continue;
                }

                // 문자열을 정수로 변환
                //→ 범위 검사
                //→ 유효한 경우에만 배열 저장
                try {
                    int score = Integer.parseInt(input.trim());

                    // 성적이 범위밖인경우. (0점 ~ 100점)
                    if (score < 0 || score > 100) {
                        System.out.println("점수는 0부터 100 사이여야 합니다.");
                        continue;
                    }
                    studentScores[i] = score;
                    break;

                } catch (NumberFormatException e) {
                    System.out.println("점수는 정수로 입력해야 합니다.");
                }
            }
        }
        return studentScores;
    }

    // 정수 처리 메서드.
//    public static String formatHours(double hours) {
//        if (hours == (int)hours){
//            return String.valueOf((int) hours);
//        }
//            return String.valueOf(hours);
//    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;

        //1.학생 이름
        String name = readStudentName(scanner);

        //2.국어점수 3.영어점수 4.수학점수
        int[] studentScores = readStudentScores(scanner);

//        for (int i = 0; i < studentScores.length; i++) {
//            totalScore += studentScores[i];
//        }

        // 총점.
        for (int studentScore : studentScores) {
            totalScore += studentScore;
        }

        // 평균.
        double rawAverage = (double) totalScore / studentScores.length;
        int roundedAverage = (int) Math.round(rawAverage); // 가장 가까운 정수로 반올림후 long을 반환.

        // 학점 계산.
        String gpa;

        if (roundedAverage >= 90) {
            gpa = "A";
        } else if (roundedAverage >= 80) {
            gpa = "B";
        } else if (roundedAverage >= 70) {
            gpa = "C";
        } else if (roundedAverage >= 60) {
            gpa = "D";
        } else {
            gpa = "F";
        }

        // 출력.
        System.out.println();
        System.out.println("===== 성적 결과 =====");
        System.out.println("학생 이름: " + name);
        for (int i = 0; i < SUBJECTS.length; i++) {
            System.out.println(SUBJECTS[i] + "점수: " + studentScores[i] + "점");
        }
        System.out.println("총점: " + totalScore + "점");
        System.out.println("평균: " + roundedAverage + "점");
        System.out.println("학점: " + gpa);

        scanner.close();
    }
}