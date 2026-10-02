package day007;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] array = new int[n];

        for (int i=0; i<n; i++) {
            array[i] = sc.nextInt();
        }

        int target = sc.nextInt();

        Arrays.sort(array);

        int left = 0;
        int right = array.length - 1;
        int result = 0;

        while (left < right) {
            int sum = array[left] + array[right];

            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                result++;
                left++;
                right--;
            }
        }

        System.out.println(result);
    }
}
