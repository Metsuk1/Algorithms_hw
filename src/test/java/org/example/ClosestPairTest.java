package org.example;

import org.example.algorithms.ClosestPair;
import org.example.entity.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {
    @Test
    void testTwoPoints() {
        Point[] points = {
                new Point(0, 0),
                new Point(3, 4)  // distance = 5
        };

        double dist = ClosestPair.findClosest(points);
        assertEquals(5.0, dist, 1e-6);
    }

    @Test
    void testDuplicatePoints() {
        Point[] points = {
                new Point(2, 2),
                new Point(2, 2),
                new Point(5, 5)
        };

        double dist = ClosestPair.findClosest(points);
        assertEquals(0.0, dist, 1e-6); // duplicates -> distance = 0
    }
}
