package day006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

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
        int startCol = -1;

        int endRow = -1;
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
        boolean[][] visited = new boolean[rows][cols];
        int[][] distance = new int[rows][cols];
        Position start = new Position(startRow, startCol);

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        queue.offer(start);
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {

            Position currentPosition = queue.poll();

            for (int i=0; i<4; i++) {
                int nextRow = currentPosition.row + dr[i];
                int nextCol = currentPosition.col + dc[i];

                if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols) {
                    continue;
                }

                if (map[nextRow][nextCol] == '#') {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                distance[nextRow][nextCol] = distance[currentPosition.row][currentPosition.col] + 1;

                queue.offer(
                        new Position(nextRow, nextCol)
                );
            }
        }

        if (!visited[endRow][endCol]) {
            System.out.println(-1);
        } else {
            System.out.println(distance[endRow][endCol]);
        }

    }
}
