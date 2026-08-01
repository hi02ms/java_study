package chap01;

import java.util.Scanner;

public class OrderCalculator {

    // 상품 단가 입력
    public static long readUnitPrice(Scanner scanner) {

        while (true) {
            System.out.print("상품 단가를 입력하세요: ");
            String input = scanner.nextLine();

            if (input.isBlank()) {
                System.out.println("상품 단가를 입력해야 합니다.");
                continue;
            }

            try {
                // 앞뒤 공백을 제거한 문자열을 long으로 변환한다. (파싱)
                long unitPrice = Long.parseLong(input.trim());

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
            String input = scanner.nextLine();

            if (input.isBlank()) {
                System.out.println("주문 수량을 입력해야 합니다.");
                continue;
            }

            try {
                int quantity = Integer.parseInt(input.trim());

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

    // main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long unitPrice = readUnitPrice(scanner);
        int quantity = readQuantity(scanner);

        // Java의 산술 연산에서는 두 피연산자의 자료형이 다르면 더 큰 자료형에 맞춰서 계산.
        long totalPrice = unitPrice * quantity;
//        long totalPrice = Math.multiplyExact(unitPrice, quantity);

        // 출력
        System.out.println();
        System.out.println("===== 계산 결과 =====");
        System.out.printf("상품 단가: %,d원%n", unitPrice);
        System.out.printf("주문 수량: %d개%n", quantity);
        System.out.printf("상품 금액: %,d원%n", totalPrice);

        scanner.close();
    }
}
