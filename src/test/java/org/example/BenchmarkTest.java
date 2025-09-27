package org.example;

import org.example.adapters.MergeSortAdapter;
import org.example.adapters.QuickSortAdapter;
import org.example.interfaces.SortAlgorithm;
import org.example.utils.CSVLogger;
import org.example.utils.Metrics;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class BenchmarkTest {

    @Test
    void benchmarkMergeSortAndExportCSV() throws IOException {
        String fileName = "results.csv";
        CSVLogger logger = new CSVLogger(fileName);

        int[] sizes = {100, 500, 1000, 5000, 10000};

        List<SortAlgorithm> algorithms = List.of(
                new MergeSortAdapter(),
                new QuickSortAdapter()
        );

        for(SortAlgorithm algorithm : algorithms){
            for (int n : sizes) {
                int[] arr = new Random().ints(n, -10000, 10000).toArray();

                Metrics.reset();
                long start = System.nanoTime();
                algorithm.sort(arr);
                long end = System.nanoTime();

                long time = end - start;

                logger.log(algorithm.name(),n, time, Metrics.getComparisons(),
                        Metrics.getAllocations(), Metrics.getMaxDepth());
            }
        }


        logger.close();
        System.out.println("CSV results written to " + fileName);
    }
}
