package day001;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 메뉴 주문 분석
 * 한 음식점에서 하루 동안 주문된 메뉴 번호 N개가 순서대로 기록되어 있다.
 * 관리자는 특정 메뉴가 가장 많이 주문된 메뉴인지 알고 싶다.
 * 주문 횟수가 가장 많은 메뉴를 출력하라.
 * 단, 가장 많이 주문된 횟수가 같은 메뉴가 여러 개라면 메뉴 번호가 가장 작은 메뉴를 출력한다.
 */
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Integer> map = new HashMap<>();

        int n = scanner.nextInt();
        for (int i=0; i<n; i++) {
            int menu = scanner.nextInt();
            map.put(menu, map.getOrDefault(menu, 0) + 1);
        }

        int maxMenu = 0;
        int maxCount = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int menu = entry.getKey();
            int count = entry.getValue();

            if (maxCount < count) {
                maxMenu = menu;
                maxCount = count;
            }

            if (maxCount == count) {
                if (maxMenu > menu) {
                    maxMenu = menu;
                }
            }
        }

        IO.println(maxMenu);
    }
}
