package chap01;

import java.util.Scanner;

public class OrderCalculator {

    // 상품 단가 입력
    public static long readUnitPrice(Scanner scanner) {

        while (true) {
            System.out.print("상품 단가를 입력하세요: ");
            String input = scanner.nextLine().strip();

            if (input.isBlank()) {
                System.out.println("상품 단가를 입력해야 합니다.");
                continue;
            }

            try {
                // 앞뒤 공백을 제거한 문자열을 long으로 변환한다. (파싱)
                long unitPrice = Long.parseLong(input);

                // 조건 검사
                if (unitPrice < 1 || unitPrice > 100_000_000) { // 조건 불일치
                    System.out.println("상품 단가는 1원부터 100,000,000원까지 입력할 수 있습니다.");
                    continue;
                }

                return unitPrice;
            } catch (NumberFormatException e) {
                System.out.println("상품 단가는 입력 가능한 범위의 정수로 입력해야 합니다.");
            }
        }
    }

    // 주문 수량 입력
    public static int readQuantity(Scanner scanner) {
        while (true) {
            System.out.print("주문 수량을 입력하세요: ");
            String input = scanner.nextLine().strip();

            if (input.isBlank()) {
                System.out.println("주문 수량을 입력해야 합니다.");
                continue;
            }

            try {
                int quantity = Integer.parseInt(input);

                if (quantity < 1 || quantity > 99) {
                    System.out.println("주문 수량은 1개부터 99개까지 입력할 수 있습니다.");
                    continue;
                }

                return quantity;
            } catch (NumberFormatException e) {
                System.out.println("주문 수량은 정수로 입력해야 합니다.");
            }
        }
    }

    // 회원 여부 입력
    public static boolean readMembership(Scanner scanner) {

        while (true) {
            System.out.print("회원입니까? (Y/N): ");
            String membership = scanner.nextLine().strip();

            if (membership.isBlank()) {
                System.out.println("회원 여부를 입력해야 합니다.");
                continue;
            }

            if (membership.equalsIgnoreCase("Y")) {
                return true;
            } else if (membership.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println("회원 여부는 Y 또는 N으로 입력해야 합니다.");
        }
    }

    // 쿠폰 정책
    public static String readCouponCode(Scanner scanner) {

        System.out.print("쿠폰 코드를 입력하세요 (없으면 Enter): ");
        String couponCode = scanner.nextLine().strip();

        // 빈입력 : 쿠폰 사용 안함
        if (couponCode.isBlank()){
            return "";
        }
        // 유효한 쿠폰
        if (couponCode.equalsIgnoreCase("SAVE3000")) {
            return "SAVE3000";
        } else {
            // 잘못된 쿠폰
            System.out.println("유효하지 않은 쿠폰입니다. 쿠폰 없이 진행합니다.");
            return "";
        }
    }

    // main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 단계
        long unitPrice = readUnitPrice(scanner);    // 상품 단가 확인
        int quantity = readQuantity(scanner);       // 주문 수량 확인
        boolean isMember = readMembership(scanner); // 회원 여부 확인
        String couponCode = readCouponCode(scanner); // 쿠폰 여부 확인
        boolean hasCoupon = couponCode.equalsIgnoreCase("SAVE3000"); // 이부분 true 인지 확인..


        // 계산 단계
        long totalPrice = unitPrice * quantity; // Java의 산술 연산에서는 두 피연산자의 자료형이 다르면 더 큰 자료형에 맞춰서 계산. long과 int를 곱하면 int가 long으로 자동 승격되어 결과도 long이 된다.
        long memberDiscount = isMember ? totalPrice / 10 : 0; // 회원할인 = 상품금액 / 10
        long priceAfterMemberDiscount = totalPrice - memberDiscount;
        boolean isCouponApplicable = hasCoupon && priceAfterMemberDiscount >= 30_000L;
        long couponDiscount = isCouponApplicable ? 3_000L : 0L;
        long priceAfterDiscounts = priceAfterMemberDiscount - couponDiscount;


        // 출력 단계
        System.out.println();
        System.out.println("===== 계산 결과 =====");
        System.out.printf("상품 단가: %,d원%n", unitPrice);
        System.out.printf("주문 수량: %d개%n", quantity);
        System.out.printf("상품 금액: %,d원%n", totalPrice);
        if (isMember) {
            System.out.printf("회원 여부: 회원%n");
            System.out.printf("회원 할인: -%,d원%n", memberDiscount);
        } else {
            System.out.printf("회원 여부: 비회원%n");
            System.out.printf("회원 할인: %d원%n", memberDiscount);
        }
        System.out.printf("회원 할인 후 금액: %,d원%n", priceAfterMemberDiscount);
        if (isCouponApplicable) {
            System.out.println("쿠폰: " + couponCode);
            System.out.printf("쿠폰 할인: -%,d원%n", couponDiscount);
        } else if (hasCoupon) {
            System.out.println("쿠폰: " + couponCode + " 적용 조건 미충족");
            System.out.println("쿠폰은 회원 할인 후 금액이 30,000원 이상일 때 사용할 수 있습니다.");
            System.out.printf("쿠폰 할인: %,d원%n", couponDiscount);
        } else {
            System.out.println("쿠폰: 사용 안함");
            System.out.printf("쿠폰 할인: %,d원%n", couponDiscount);
        }

        System.out.printf("모든 할인 후 금액: %,d원%n", priceAfterDiscounts);
        scanner.close();
    }
}
