package org.example.adapters;

import org.example.algorithms.ClosestPair;
import org.example.entity.Point;
import org.example.interfaces.ClosestAlgorithm;

public class ClosestPairAdapter implements ClosestAlgorithm {
    @Override
    public double findClosest(Point[] points) {
        return ClosestPair.findClosest(points);
    }

    @Override
    public String name() {
        return ClosestPair.class.getSimpleName();
    }
}
