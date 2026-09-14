package day003;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        int n = sc.nextInt();
        int[] processCount = Arrays.stream(sc2.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Deque<Integer> nQueue = new ArrayDeque<>();
        Deque<Integer> processQueue = new ArrayDeque<>();
        Deque<Integer> resultQueue = new ArrayDeque<>();
        for (int i=0; i<n; i++) {
            nQueue.offer(i+1);
            processQueue.offer(processCount[i]);
        }

        while (!processQueue.isEmpty()) {
            int process = processQueue.poll() - 1;
            if (process == 0) {
                resultQueue.offer(nQueue.poll());
            } else {
                processQueue.offer(process);
                nQueue.offer(nQueue.poll());
            }
        }

        while (!resultQueue.isEmpty()) {
            System.out.print(resultQueue.poll());
            System.out.print(' ');
        }
    }
}
