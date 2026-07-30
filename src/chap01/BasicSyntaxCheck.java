package chap01;

public class BasicSyntaxCheck {
    public static void main(String[] args) {
        String name = "배민수";
        int age = 24;
        String goal = "AX 서비스 백엔드 개발자";
        int studyTime = 6;
        boolean hasStartedJava = true;
        System.out.println("이름: " + name);
        System.out.println("나이: " + age + "세");
        System.out.println("목표 직무: " + goal);
        System.out.println("하루 목표 공부시간: " + studyTime + "시간");
        System.out.println("공부 시작 여부: " + hasStartedJava);

        if (hasStartedJava) {
            System.out.println("오늘도 Java 공부를 진행합니다.");
        } else {
            System.out.println("지금부터 Java 공부를 시작합니다.");
        }
    }
}

//이름: 배민수
//나이: 24세
//목표 직무: AX 서비스 백엔드 개발자
//하루 목표 공부시간: 6시간
//Java 공부 시작 여부: true

//추가로 if문을 사용해서:
//공부를 시작했다면 오늘도 Java 공부를 진행합니다.
//시작하지 않았다면 지금부터 Java 공부를 시작합니다.
//를 출력해.

//이번 단계의 규칙
//AI에게 전체 코드를 작성해 달라고 하지 않기
//오류가 생기면 먼저 오류 문장을 직접 읽기
//최소 15분은 혼자 해결해 보기
//변수명은 영어로 작성하기
//실행 결과가 Process finished with exit code 0으로 끝나는지 확인하기