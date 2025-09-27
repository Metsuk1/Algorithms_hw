package org.example.jmh;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JmhRunner {
    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include("SelectVsSortBenchmark")
                .forks(1)
                .warmupIterations(1)
                .measurementIterations(3)
                .build();

        new Runner(opt).run();
    }
}
