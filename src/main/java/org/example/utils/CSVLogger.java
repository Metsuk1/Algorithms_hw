package org.example.utils;

import java.io.FileWriter;
import java.io.IOException;

public class CSVLogger {
    private final FileWriter writer;


    public CSVLogger(String fileName) throws IOException {
        writer = new FileWriter(fileName);
        writer.write("Algorithm_Name,n,timeMs,comparisons,allocations,maxDepth\n");
    }

    public void log(String algorithmName,int n, double time, long comps, long allocs, int depth) throws IOException {
        writer.write(algorithmName + "," + n + "," + time + "," + comps + "," + allocs + "," + depth + "\n");
        writer.flush();
    }

    public void close() throws IOException {
        writer.close();
    }


}
