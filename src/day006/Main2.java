package day006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {

    static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int rows = Integer.parseInt(st.nextToken());
        int cols = Integer.parseInt(st.nextToken());

        char[][] map = new char[rows][cols];

        int startRow = -1;
        int endRow = -1;

        int startCol = -1;
        int endCol = -1;

        for (int r=0; r<rows; r++) {
            map[r] = br.readLine().toCharArray();

            for (int c=0; c<cols; c++) {

                if (map[r][c] == 'S') {
                    startRow = r;
                    startCol = c;
                }

                if (map[r][c] == 'E') {
                    endRow = r;
                    endCol = c;
                }
            }
        }

        Queue<Position> queue = new ArrayDeque<>();
        int[][] distance = new int[rows][cols];
        int shortestDistance = Integer.MAX_VALUE;
        boolean[][] visited = new boolean[rows][cols];

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        queue.offer(
                new Position(startRow, startCol)
        );
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {

            Position current = queue.poll();

            for (int i=0; i<4; i++) {
                int nextRow = current.row + dr[i];
                int nextCol = current.col + dc[i];

                if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols) {
                    continue;
                }

                if (map[nextRow][nextCol] == '#') {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                visited[nextRow][nextCol] = true; // 방문
                queue.offer(
                        new Position(nextRow, nextCol)
                );

                distance[nextRow][nextCol] = distance[current.row][current.col] + 1;

            }
        }

        if (!visited[endRow][endCol]) {
            System.out.println(-1);
        } else {
            System.out.println(distance[endRow][endCol]);
        }


    }
}
