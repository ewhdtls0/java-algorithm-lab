package day004;
import java.util.*;

public class Main {
    void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] abilities = new int[n];

        for (int i=0; i<abilities.length; i++) {
            abilities[i] = sc.nextInt();
        }

        int topAbility = 0;
        for (int i=0; i<n; i++) {
            int nowAbility = 0;
            nowAbility += abilities[i];

            for (int j=i+1; j<n; j++) {

                nowAbility += abilities[j];

                for (int k=j+1; k<n; k++) {
                    int compareAbility = nowAbility;
                    compareAbility += abilities[k];
                    if (checkExceedAbility(m, compareAbility)) {
                        continue;
                    }

                    if (topAbility < compareAbility) {
                        topAbility = compareAbility;
                    }
                }
            }
        }

        if (topAbility == 0) {
            System.out.println(-1);
        } else {
            System.out.println(topAbility);
        }
    }

    private boolean checkExceedAbility(int max, int value) {
        return max < value;
    }
}
