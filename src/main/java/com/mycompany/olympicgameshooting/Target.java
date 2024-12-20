package com.mycompany.olympicgameshooting;

import java.util.Random;

/**
 * @author Chun
 */
public class Target {
    public static final double[] SCORING_RINGS = {0.575, 1.375, 2.175, 2.975, 3.775, 4.575, 5.375, 6.175, 6.975, 7.775};
    public static final double OUTER_RADIUS = 8.5;

    public static double calculateScore(double x, double y) {
        double distance = Math.sqrt(x * x + y * y);

        if (distance > OUTER_RADIUS) {
            return 0.0;
        }

        for (int i = 0; i < SCORING_RINGS.length; i++) {
            if (distance < SCORING_RINGS[i]) {
                return 10.9 - 0.1 * i;
            }
        }

        return 0.0;
    }

    public static double[] generateShot() {
        Random random = new Random();
        double x = random.nextDouble(-OUTER_RADIUS, OUTER_RADIUS);
        double y = random.nextDouble(-OUTER_RADIUS, OUTER_RADIUS);

        while (Math.sqrt(x * x + y * y) > OUTER_RADIUS) {
            x = random.nextDouble(-OUTER_RADIUS, OUTER_RADIUS);
            y = random.nextDouble(-OUTER_RADIUS, OUTER_RADIUS);
        }

        return new double[]{x, y};
    }
}
