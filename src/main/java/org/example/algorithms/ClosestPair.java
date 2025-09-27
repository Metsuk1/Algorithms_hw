package org.example.algorithms;

import org.example.entity.Point;
import org.example.utils.Metrics;
import org.example.utils.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Closest Pair of Points in 2D plane
 * Divide-and-Conquer implementation, O(n log n).
 * Space: O(n) for auxiliary arrays
 */
public class ClosestPair extends algorithm {

    /**
     * Entry point. Computes the closest distance among all points.
     * @param points array of 2D points
     * @return the minimum Euclidean distance
     */
    public static double findClosest(Point[] points) {
        if (points.length < 2) {
            return Double.POSITIVE_INFINITY;
        }

        Metrics.reset();

        // Sort by x-coordinate
        Point[] sortedByX = points.clone();
        Metrics.recordAllocation();
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));

        // Sort by y-coordinate
        Point[] sortedByY = points.clone();
        Metrics.recordAllocation();
        Arrays.sort(sortedByY, Comparator.comparingDouble(p -> p.y));

        return closestRec(sortedByX, sortedByY, 1);
    }


    /**
     * Recursive function that splits the points and finds the closest pair.
     *
     * @param X points sorted by x
     * @param Y points sorted by y
     * @param depth recursion depth
     * @return minimum distance in this subproblem
     */
    private static double closestRec(Point[] X, Point[] Y, int depth) {
        Metrics.recordDepth(depth);


        int n = X.length;

        // Base case: brute force for <= 3 points
        if (n <= 3) {
            return bruteForce(X);
        }

        int mid = n / 2;
        Point midPoint = X[mid];

        // Split by X into left/right halves
        Point[] XL = Arrays.copyOfRange(X, 0, mid);
        Metrics.recordAllocation();

        Point[] XR = Arrays.copyOfRange(X, mid, n);
        Metrics.recordAllocation();

        // Split Y into left/right by comparing x-coordinates
        List<Point> leftY = new ArrayList<>();
        Metrics.recordAllocation();

        List<Point> rightY = new ArrayList<>();
        Metrics.recordAllocation();

        for (Point p : Y) {
            Metrics.recordComparison();
            if (p.x <= midPoint.x) {
                leftY.add(p);
            } else {
                rightY.add(p);
            }
        }

        // Recurse into left and right halves
        double dl = closestRec(XL, leftY.toArray(new Point[0]), depth + 1);
        Metrics.recordAllocation();

        double dr = closestRec(XR, rightY.toArray(new Point[0]), depth + 1);
        Metrics.recordAllocation();

        double d = Math.min(dl, dr);

        // Build strip of points within distance d from the middle line
        List<Point> strip = new ArrayList<>();
        Metrics.recordAllocation();
        for (Point p : Y) {
            Metrics.recordComparison();
            if (Math.abs(p.x - midPoint.x) < d) {
                strip.add(p);
            }
        }

        // Check only up to 7 neighbors for each point in strip
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && j <= i + 7; j++) {
                Metrics.recordComparison();
                d = Math.min(d, dist(strip.get(i), strip.get(j)));
            }
        }

        return d;
    }



}
