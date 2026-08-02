package chap01;

import java.util.Scanner;

public class OrderCalculator {

    // 상수
    private static final String COUPON_CODE = "SAVE3000";
    private static final long COUPON_DISCOUNT_AMOUNT = 3_000L;
    private static final long COUPON_MINIMUM_PRICE = 30_000L;
    private static final long FREE_SHIPPING_MINIMUM_PRICE = 50_000L;
    private static final long SHIPPING_FEE = 3_000L;

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

    // 쿠폰 코드 입력
    public static String readCouponCode(Scanner scanner) {

        System.out.print("쿠폰 코드를 입력하세요 (없으면 Enter): ");
        String couponCode = scanner.nextLine().strip();

        // 빈입력 : 쿠폰 사용 안 함
        if (couponCode.isBlank()) {
            return "";
        }
        // 유효한 쿠폰
        if (couponCode.equalsIgnoreCase(COUPON_CODE)) {
            return COUPON_CODE;
        } else {
            // 잘못된 쿠폰
            System.out.println("유효하지 않은 쿠폰입니다. 쿠폰 없이 진행합니다.");
            return "";
        }
    }

    // 모든 할인 후 금액을 기준으로 배송비 계산
    public static long calculateShippingFee(long priceAfterDiscounts) {
        if (priceAfterDiscounts >= FREE_SHIPPING_MINIMUM_PRICE) {
            return 0L;
        } else {
            return SHIPPING_FEE;
        }
    }

    // 상품 금액 계산
    public static long calculateTotalPrice(long unitPrice, int quantity) {
        // Java의 산술 연산에서는 두 피연산자의 자료형이 다르면 더 큰 자료형에 맞춰서 계산.
        // long과 int를 곱하면 int가 long으로 자동 승격되어 결과도 long이 된다.
        return unitPrice * quantity;
    }

    // 회원 할인 계산
    public static long calculateMemberDiscount(boolean isMember, long totalPrice) {
        return isMember ? totalPrice / 10L : 0L; // 회원할인 = 상품금액 / 10
    }

    //쿠폰 적용 가능 여부
    public static boolean isCouponApplicable(boolean hasCoupon, long priceAfterMemberDiscount) {
        return hasCoupon && priceAfterMemberDiscount >= COUPON_MINIMUM_PRICE;
    }

    // 쿠폰 할인액 계산
    public static long calculateCouponDiscount(boolean isCouponApplicable) {
        return isCouponApplicable ? COUPON_DISCOUNT_AMOUNT : 0L;
    }

    //최종 결제 금액 계산
    public static long calculateFinalPrice(long priceAfterDiscounts, long shippingFee) {
        return priceAfterDiscounts + shippingFee;
    }

    // main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 단계
        long unitPrice = readUnitPrice(scanner);    // 상품 단가 확인
        int quantity = readQuantity(scanner);       // 주문 수량 확인
        boolean isMember = readMembership(scanner); // 회원 여부 확인
        String couponCode = readCouponCode(scanner); // 쿠폰 여부 확인
        boolean hasCoupon = couponCode.equals(COUPON_CODE);

        // 계산 단계
        long totalPrice = calculateTotalPrice(unitPrice, quantity);
        long memberDiscount = calculateMemberDiscount(isMember, totalPrice);
        long priceAfterMemberDiscount = totalPrice - memberDiscount;
        boolean couponApplicable = isCouponApplicable(hasCoupon, priceAfterMemberDiscount);
        long couponDiscount = calculateCouponDiscount(couponApplicable);
        long priceAfterDiscounts = priceAfterMemberDiscount - couponDiscount;
        long shippingFee = calculateShippingFee(priceAfterDiscounts);
        long finalPrice = calculateFinalPrice(priceAfterDiscounts, shippingFee);

        // 출력 단계
        System.out.println();
        System.out.println("===== 계산 결과 =====");
        System.out.printf("상품 단가: %,d원%n", unitPrice);
        System.out.printf("주문 수량: %d개%n", quantity);
        System.out.printf("상품 금액: %,d원%n", totalPrice);
        // 회원 출력 if-else
        if (isMember) {
            System.out.printf("회원 여부: 회원%n");
            System.out.printf("회원 할인: -%,d원%n", memberDiscount);
        } else {
            System.out.printf("회원 여부: 비회원%n");
            System.out.printf("회원 할인: %d원%n", memberDiscount);
        }
        System.out.printf("회원 할인 후 금액: %,d원%n", priceAfterMemberDiscount);
        // 쿠폰출력 if-else
        if (couponApplicable) {
            System.out.println("쿠폰: " + couponCode);
            System.out.printf("쿠폰 할인: -%,d원%n", couponDiscount);
        } else if (hasCoupon) {
            System.out.println("쿠폰: " + couponCode + " 적용 조건 미충족");
            System.out.printf("쿠폰은 회원 할인 후 금액이 %,d원 이상일 때 사용할 수 있습니다.%n", COUPON_MINIMUM_PRICE);
            System.out.printf("쿠폰 할인: %,d원%n", couponDiscount);
        } else {
            System.out.println("쿠폰: 사용 안 함");
            System.out.printf("쿠폰 할인: %,d원%n", couponDiscount);
        }
        System.out.printf("모든 할인 후 금액: %,d원%n", priceAfterDiscounts);
        // 배송비 출력 if-else
        if (shippingFee == 0) {
            System.out.println("배송비: 무료");
        } else {
            System.out.printf("배송비: %,d원%n", shippingFee);
        }
        System.out.printf("최종 결제 금액: %,d원%n", finalPrice);

        scanner.close();
    }
}
