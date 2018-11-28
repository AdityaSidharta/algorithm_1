/* *****************************************************************************
 *  Name: Aditya Sidharta
 *  Date: 22 November 2018
 *  Description: Algorithm 1 - Week 1
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int n;
    private int trials;
    private Percolation percolate;
    private double[] results;
    private double mean;
    private double stddev;
    private double confidencelo;
    private double confidencehi;

    private double doPercolate() {
        int rand_row, rand_col;
        double result;
        percolate = new Percolation(n);
        while (!percolate.percolates()) {
            rand_row = StdRandom.uniform(n) + 1;
            rand_col = StdRandom.uniform(n) + 1;
            percolate.open(rand_row, rand_col);
        }
        result = percolate.numberOfOpenSites() * 1.0 / (n * n);
        return result;
    }

    public PercolationStats(int n, int trials) {
        this.n = n;
        this.trials = trials;
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            double result = doPercolate();
            this.results[i] = result;
        }
        this.mean();
        this.stddev();
        this.confidenceLo();
        this.confidenceHi();
        //System.out.println(Arrays.toString(results));
    }

    public double mean() {
        this.mean = StdStats.mean(results);
        System.out.println(StdStats.mean(results));
        return this.mean;
    }

    public double stddev() {
        this.stddev = StdStats.stddev(results);
        return this.stddev;
    }

    public double confidenceLo() {
        this.confidencelo = this.mean - (1.96 * Math.sqrt(this.stddev) / Math.sqrt(this.trials));
        return this.confidencelo;
    }

    public double confidenceHi() {
        this.confidencehi = this.mean + (1.96 * Math.sqrt(this.stddev) / Math.sqrt(this.trials));
        return this.confidencehi;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats perc_stats = new PercolationStats(n, T);
        System.out.println("mean " + perc_stats.mean);
        System.out.println("stddev " + perc_stats.stddev);
        System.out.println("confidenceLo " + perc_stats.confidencelo);
        System.out.println("confidenceHi " + perc_stats.confidencehi);
    }
}
