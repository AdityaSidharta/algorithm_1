import java.util.Arrays;

/* *****************************************************************************
 *  Name: Aditya Sidharta
 *  Date: 22 November 2018
 *  Description: Algorithm 1 - Week 1
 **************************************************************************** */
public class Percolation {
    private int[][] grid;
    private int class_n;

    private void is_legal(int row, int col) {
        if (row <= 0 || row > class_n) {
            throw new IllegalArgumentException("row cannot be lt 1 / mt n");
        }
        if (col <= 0 || col > class_n) {
            throw new IllegalArgumentException("col cannot be lt 1 / mt n");
        }
    }

    public Percolation(int n) {
        class_n = n;
        grid = new int[n][n];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                grid[x][y] = 0;
            }
        }
    }

    public void open(int row, int col) {
        is_legal(row, col);
        grid[row - 1][col - 1] = 1;
    }

    public boolean isOpen(int row, int col) {
        is_legal(row, col);
        return grid[row - 1][col - 1] == 0;
    }
    
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Percolation percolation = new Percolation(n);
        percolation.open(2, 3);
        for (int x = 0; x < n; x++) {
            System.out.println(Arrays.toString(percolation.grid[x]));
        }
    }
}
