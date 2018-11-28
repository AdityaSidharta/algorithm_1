/* *****************************************************************************
 *  Name: Aditya Sidharta
 *  Date: 22 November 2018
 *  Description: Algorithm 1 - Week 1
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF union;
    private int[][] grid;
    private int[][] map;
    private int n;
    private int n_sites;
    private int start_idx = 0;
    private int end_idx;
    private int n_open_sites = 0;

    private void init_n_sites(int n) {
        n_sites = (n * n) + 2;
        end_idx = n_sites - 1;
    }

    private void init_union(int n) {
        int x, y, idx;
        union = new WeightedQuickUnionUF(n_sites);
        x = n - 1;
        for (y = 0; y < n; y++) {
            idx = grid2map(x, y);
            union.union(end_idx, idx);
        }
    }

    private void init_map(int n) {
        int x, y, idx;
        map = new int[n][n];
        idx = 1;
        for (x = 0; x < n; x++) {
            for (y = 0; y < n; y++) {
                map[x][y] = idx;
                idx = idx + 1;
            }
        }
    }

    private void init_grid(int n) {
        grid = new int[n][n];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                grid[x][y] = 0;
            }
        }
    }

    public int grid2map(int x, int y) {
        return map[x][y];
    }

    public int[] map2grid(int idx) {
        int x, y;
        int[] value = new int[2];
        for (x = 0; x < n; x++) {
            for (y = 0; y < n; y++)
                if (map[x][y] == idx) {
                    value[0] = x;
                    value[1] = y;
                }
        }
        return value;
    }

    private void is_legal(int row, int col) {
        if (row <= 0 || row > n) {
            throw new IllegalArgumentException("row cannot be lt 1 / mt n");
        }
        if (col <= 0 || col > n) {
            throw new IllegalArgumentException("col cannot be lt 1 / mt n");
        }
    }

    public Percolation(int n) {
        this.n = n;
        init_n_sites(n);
        init_map(n);
        init_grid(n);
        init_union(n);
    }

    public void open(int row, int col) {
        int x, y, min_x, max_x, min_y, max_y, idx, adj_x, adj_y, adj_idx, adj_row, adj_col;
        if (!isOpen(row, col)) {
            is_legal(row, col);
            x = row - 1;
            y = col - 1;
            idx = grid2map(x, y);

            grid[x][y] = 1;
            n_open_sites = n_open_sites + 1;

            min_x = Math.min(x + 1, n - 1);
            max_x = Math.max(x - 1, 0);
            min_y = Math.min(y + 1, n - 1);
            max_y = Math.max(y - 1, 0);
            int[] adj_x_array = {min_x, max_x, x, x};
            int[] adj_y_array = {y, y, min_y, max_y};
            int[] range = {0, 1, 2, 3};

            if (x == 0) {
                union.union(idx, start_idx);
            }

            for (int i : range) {
                adj_x = adj_x_array[i];
                adj_y = adj_y_array[i];
                adj_row = adj_x + 1;
                adj_col = adj_y + 1;
                adj_idx = grid2map(adj_x, adj_y);
                if (isOpen(adj_row, adj_col)) {
                    union.union(idx, adj_idx);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        int x, y;
        is_legal(row, col);
        x = row - 1;
        y = col - 1;
        return grid[x][y] == 1;
    }

    public boolean isFull(int row, int col) {
        int x, y, idx;
        is_legal(row, col);
        x = row - 1;
        y = col - 1;
        idx = grid2map(x, y);
        return union.connected(start_idx, idx);
    }

    public int numberOfOpenSites() {
        return n_open_sites;
    }

    public boolean percolates() {
        return union.connected(start_idx, end_idx);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Percolation percolation = new Percolation(n);
    }
}
