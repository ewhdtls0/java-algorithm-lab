package day004;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] abilities = new int[n];

        for (int i = 0; i < n; i++) {
            abilities[i] = sc.nextInt();
        }

        int maxAbility = -1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {

                    int sum = abilities[i]
                            + abilities[j]
                            + abilities[k];

                    if (sum > m) {
                        continue;
                    }

                    maxAbility = Math.max(maxAbility, sum);
                }
            }
        }

        System.out.println(maxAbility);
    }
}