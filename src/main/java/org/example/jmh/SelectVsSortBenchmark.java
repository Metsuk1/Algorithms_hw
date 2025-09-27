package org.example.jmh;

import org.example.algorithms.DeterministicSelect;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * JMH Benchmark: Compare selection of k-th element via
 *  - Deterministic Select (Median of Medians)
 *  - Full Sort + Index
 */
@BenchmarkMode(Mode.AverageTime)          // среднее время на операцию
@OutputTimeUnit(TimeUnit.MICROSECONDS)    // результат в микросекундах
@State(Scope.Thread)
public class SelectVsSortBenchmark {

    @Param({"1000", "5000", "10000"})     // размеры входных данных
    private int n;

    private int[] data;
    private int k;

    @Setup(Level.Invocation)              // выполняется перед каждым запуском бенчмарка
    public void setup() {
        Random rnd = new Random(42);
        data = rnd.ints(n, -10000, 10000).toArray();
        k = n / 2; // ищем медиану
    }

    @Benchmark
    public int selectMoM() {
        return DeterministicSelect.select(data.clone(), k);
    }

    @Benchmark
    public int selectSort() {
        int[] copy = data.clone();
        Arrays.sort(copy);
        return copy[k];
    }
}
