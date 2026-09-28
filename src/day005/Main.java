package day005;

import java.util.Scanner;

public class Main {

    static int n;
    static int m;

    static int[] selected;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        visited = new boolean[n];
        selected = new int[m];

        dfs(0, numbers);
    }

    static void dfs(int depth, int[] numbers) {

        if (depth == m) {
            for (int i = 0; i < selected.length; i++) {
                System.out.print(selected[i] + " ");
            }

            System.out.println();
            return;
        }

        for (int i = 0; i < n; i++) {

            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            selected[depth] = numbers[i];

            dfs(depth + 1, numbers);

            visited[i] = false;
        }
    }
}