package org.example.interfaces;

import org.example.entity.Point;

public interface ClosestAlgorithm {
    double findClosest(Point[] points);
    String name();
}
