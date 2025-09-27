package org.example;

import org.example.cli.BenchmarkRunner;
import org.junit.jupiter.api.Test;



public class BenchmarkTest {

    // testing all benchmarkTest for all algorithms
    @Test
    void benchmarkTest() throws Exception {
        BenchmarkRunner.benchmarkAll();
    }
}
