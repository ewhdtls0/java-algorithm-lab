package day003;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {

    record Task(int number, int remain) {}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Deque<Task> queue = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            queue.offer(new Task(i, sc.nextInt()));
        }

        while (!queue.isEmpty()) {
            Task task = queue.poll();

            if (task.remain() == 1) {
                result.append(task.number()).append(' ');
            } else {
                queue.offer(new Task(task.number(), task.remain() - 1));
            }
        }

        System.out.println(result.toString().trim());
    }
}