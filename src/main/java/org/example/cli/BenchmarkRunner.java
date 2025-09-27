package org.example.cli;

import org.example.adapters.ClosestPairAdapter;
import org.example.adapters.DeterministicSelectAdapter;
import org.example.adapters.MergeSortAdapter;
import org.example.adapters.QuickSortAdapter;
import org.example.entity.Point;
import org.example.interfaces.ClosestAlgorithm;
import org.example.interfaces.SelectAlgorithm;
import org.example.interfaces.SortAlgorithm;
import org.example.utils.CSVLogger;
import org.example.utils.Metrics;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BenchmarkRunner {
    private static final String FILE_NAME = "results.csv";
    private static final int[] SIZES = {100, 500, 1000, 5000, 10000};

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Benchmark CLI ---");
        System.out.println("1) Run ALL benchmarks");
        System.out.println("2) Run Sorting benchmarks");
        System.out.println("3) Run Selection benchmarks");
        System.out.println("4) Run Closest Pair benchmarks");
        System.out.println("5) Exit");
        System.out.print("Choose option: ");

        int choice = scanner.nextInt();

        try (CSVLogger logger = new CSVLogger(FILE_NAME)) {
            switch (choice) {
                case 1 -> {
                   benchmarkAll();
                }
                case 2 -> benchmarkSorts(logger);
                case 3 -> benchmarkSelects(logger);
                case 4 -> benchmarkClosest(logger);
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
        System.out.println("Benchmarks finished, results written to " + FILE_NAME);
    }

    public static void benchmarkAll() throws IOException {
        try (CSVLogger logger = new CSVLogger(FILE_NAME)) {
            benchmarkSorts(logger);
            benchmarkSelects(logger);
            benchmarkClosest(logger);
        }
        System.out.println("CSV results written to " + FILE_NAME);
    }

    public static  void benchmarkSorts(CSVLogger logger) throws IOException {
        List<SortAlgorithm> algorithms = List.of(
                new MergeSortAdapter(),
                new QuickSortAdapter()
        );

        for (SortAlgorithm algorithm : algorithms) {
            for (int n : SIZES) {
                int[] arr = new Random().ints(n, -10000, 10000).toArray();

                Metrics.reset();
                long start = System.nanoTime();
                algorithm.sort(arr);
                long end = System.nanoTime();

                double time = (end - start) / 1_000_000.0;

                logger.log(algorithm.name(), n, time,
                        Metrics.getComparisons(),
                        Metrics.getAllocations(),
                        Metrics.getMaxDepth());
            }
        }
    }

    public static  void benchmarkSelects(CSVLogger logger) throws IOException {
        List<SelectAlgorithm> algorithms = List.of(
                new DeterministicSelectAdapter()
        );

        for (SelectAlgorithm algorithm : algorithms) {
            for (int n : SIZES) {
                int[] originalArr = new Random().ints(n, -10000, 10000).toArray();

                int[] testPositions = {0, n / 4, n / 2, 3 * n / 4, n - 1};
                String[] positionNames = {"Min", "Q1", "Median", "Q3", "Max"};

                for (int i = 0; i < testPositions.length; i++) {
                    int k = testPositions[i];
                    String task = "Find" + positionNames[i];

                    Metrics.reset();
                    long start = System.nanoTime();
                    algorithm.select(originalArr.clone(), k);
                    long end = System.nanoTime();

                    double time = (end - start) / 1_000_000.0;

                    logger.log(algorithm.name() + "_" + task, n, time,
                            Metrics.getComparisons(),
                            Metrics.getAllocations(),
                            Metrics.getMaxDepth());
                }
            }
        }
    }

    public static void benchmarkClosest(CSVLogger logger) throws IOException {
        List<ClosestAlgorithm> algorithms = List.of(
                new ClosestPairAdapter()
        );

        for (ClosestAlgorithm algorithm : algorithms) {
            for (int n : SIZES) {
                Point[] points = new Point[n];
                Random rnd = new Random();
                for (int i = 0; i < n; i++) {
                    points[i] = new Point(rnd.nextDouble(0, 10000),
                            rnd.nextDouble(0, 10000));
                }

                Metrics.reset();
                long start = System.nanoTime();
                algorithm.findClosest(points);
                long end = System.nanoTime();

                double time = (end - start) / 1_000_000.0;

                logger.log(algorithm.name(), n, time,
                        Metrics.getComparisons(),
                        Metrics.getAllocations(),
                        Metrics.getMaxDepth());
            }
        }
    }
}
