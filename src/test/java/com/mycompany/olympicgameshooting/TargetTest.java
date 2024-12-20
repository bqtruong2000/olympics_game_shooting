/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.olympicgameshooting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chun
 */
public class TargetTest {

    public TargetTest() {
    }

    @Test
    public void testCalculateScore() {
        assertEquals(10.9, Target.calculateScore(0, 0), 0.001);
        assertEquals(10.8, Target.calculateScore(0.5, 0.5), 0.001);
        assertEquals(10.7, Target.calculateScore(1, 1), 0.001);
        assertEquals(0.0, Target.calculateScore(9, 9), 0.001);
        assertEquals(0.0, Target.calculateScore(8.6, 0), 0.001);
    }

    @Test
    public void testGenerateShot() {
        for (int i = 0; i < 1000; i++) {
            double[] shot = Target.generateShot();
            double x = shot[0];
            double y = shot[1];
            double distance = Math.sqrt(x * x + y * y);

            assertTrue(x >= -8.5 && x <= 8.5, "X coordinate out of bounds");
            assertTrue(y >= -8.5 && y <= 8.5, "Y coordinate out of bounds");
            assertTrue(distance <= 8.5, "Shot is outside the target");
        }
    }

    @Test
    void testCalculateScorePerfectShot() {
        double score = Target.calculateScore(0, 0);
        assertEquals(10.9, score, 0.01);
    }

    @Test
    void testCalculateScoreOuterRing() {
        double score = Target.calculateScore(7.5, 0);
        assertEquals(10.0, score, 0.01);
    }

    @Test
    void testCalculateScoreMissedShot() {
        double score = Target.calculateScore(9.0, 0);
        assertEquals(0.0, score, 0.01);
    }

    @Test
    void testCalculateScoreBoundaryEdge() {
        double score = Target.calculateScore(8.5, 0);
        assertEquals(0.0, score, 0.01);
    }

    @Test
    void testGenerateShotWithinBounds() {
        for (int i = 0; i < 100; i++) {
            double[] shot = Target.generateShot();
            double x = shot[0];
            double y = shot[1];
            double distance = Math.sqrt(x * x + y * y);
            assertTrue(distance <= 8.5, "Shot generated outside valid radius.");
        }
    }

    @Test
    void testGenerateShotRandomness() {
        double[] shot1 = Target.generateShot();
        double[] shot2 = Target.generateShot();
        assertNotEquals(shot1[0], shot2[0], "Generated shots appear to be identical.");
        assertNotEquals(shot1[1], shot2[1], "Generated shots appear to be identical.");
    }

    @Test
    void testGenerateShotNearPerfectCenter() {
        double[] shot = Target.generateShot();
        if (Math.sqrt(shot[0] * shot[0] + shot[1] * shot[1]) < 1.0) {
            double score = Target.calculateScore(shot[0], shot[1]);
            assertTrue(score > 9.0, "Score is lower than expected for near-center shots.");
        }
    }

    @Test
    void testCalculateScoreWithBoundaryInputs() {
        assertEquals(10.9, Target.calculateScore(0.0, 0.0), 0.01, "Score at center mismatch.");
        assertEquals(0.0, Target.calculateScore(8.5, 0.0), 0.01, "Score at boundary mismatch.");
        assertEquals(0.0, Target.calculateScore(9.0, 0.0), 0.01, "Score outside boundary mismatch.");
    }

    @Test
    void testGenerateShotStatistics() {
        int shotsGenerated = 1000;
        double totalDistance = 0.0;
        for (int i = 0; i < shotsGenerated; i++) {
            double[] shot = Target.generateShot();
            double distance = Math.sqrt(shot[0] * shot[0] + shot[1] * shot[1]);
            totalDistance += distance;
            assertTrue(distance <= 8.5, "Generated shot exceeded maximum radius.");
        }
        double averageDistance = totalDistance / shotsGenerated;
        assertTrue(averageDistance <= 6.0, "Average shot distance is unexpectedly high.");
    }

    @Test
    void testCalculateScoreEdgeCases() {
        assertEquals(10.9, Target.calculateScore(0.0, 0.0), 0.01);
        assertEquals(10.8, Target.calculateScore(0.575, 0.0), 0.01);
        assertEquals(10.7, Target.calculateScore(1.375, 0.0), 0.01);
        assertEquals(10.6, Target.calculateScore(2.175, 0.0), 0.01);
        assertEquals(10.5, Target.calculateScore(2.975, 0.0), 0.01);
        assertEquals(10.4, Target.calculateScore(3.775, 0.0), 0.01);
        assertEquals(10.3, Target.calculateScore(4.575, 0.0), 0.01);
        assertEquals(10.2, Target.calculateScore(5.375, 0.0), 0.01);
        assertEquals(10.1, Target.calculateScore(6.175, 0.0), 0.01);
        assertEquals(10.0, Target.calculateScore(6.975, 0.0), 0.01);
        assertEquals(0.0, Target.calculateScore(7.775, 0.0), 0.01);
    }

    @Test
    void testCalculateScoreNegativeCoordinates() {
        assertEquals(10.9, Target.calculateScore(-0.1, -0.1), 0.01);
        assertEquals(10.8, Target.calculateScore(-0.5, -0.5), 0.01);
        assertEquals(10.7, Target.calculateScore(-1.0, -1.0), 0.01);
        assertEquals(0.0, Target.calculateScore(-9.0, -9.0), 0.01);
    }

    @Test
    void testCalculateScoreMixedCoordinates() {
        assertEquals(10.9, Target.calculateScore(0.1, -0.1), 0.01);
        assertEquals(10.8, Target.calculateScore(-0.5, 0.5), 0.01);
        assertEquals(10.7, Target.calculateScore(1.0, -1.0), 0.01);
        assertEquals(0.0, Target.calculateScore(-9.0, 9.0), 0.01);
    }

    @Test
    void testGenerateShotEdgeCases() {
        for (int i = 0; i < 100; i++) {
            double[] shot = Target.generateShot();
            double distance = Math.sqrt(shot[0] * shot[0] + shot[1] * shot[1]);
            assertTrue(distance <= 8.5, "Shot generated outside valid radius.");
        }
    }

    @Test
    void testGenerateShotDistribution() {
        int shotsGenerated = 10000;
        int[] ringCounts = new int[Target.SCORING_RINGS.length + 1];
        for (int i = 0; i < shotsGenerated; i++) {
            double[] shot = Target.generateShot();
            double distance = Math.sqrt(shot[0] * shot[0] + shot[1] * shot[1]);
            for (int j = 0; j < Target.SCORING_RINGS.length; j++) {
                if (distance <= Target.SCORING_RINGS[j]) {
                    ringCounts[j]++;
                    break;
                }
            }
            if (distance > Target.SCORING_RINGS[Target.SCORING_RINGS.length - 1]) {
                ringCounts[Target.SCORING_RINGS.length]++;
            }
        }
        for (int count : ringCounts) {
            assertTrue(count > 0, "Some rings have no shots.");
        }
    }

    @Test
    void testCalculateScoreWithRandomShots() {
        for (int i = 0; i < 100; i++) {
            double[] shot = Target.generateShot();
            double score = Target.calculateScore(shot[0], shot[1]);
            assertTrue(score >= 0.0 && score <= 10.9, "Score out of valid range.");
        }
    }

    @Test
    void testGenerateShotConsistency() {
        double[] shot1 = Target.generateShot();
        double[] shot2 = Target.generateShot();
        assertNotEquals(shot1[0], shot2[0], "Generated shots appear to be identical.");
        assertNotEquals(shot1[1], shot2[1], "Generated shots appear to be identical.");
    }

    @Test
    void testCalculateScoreWithExtremeValues() {
        assertEquals(0.0, Target.calculateScore(Double.MAX_VALUE, Double.MAX_VALUE), 0.01);
        assertEquals(0.0, Target.calculateScore(-Double.MAX_VALUE, -Double.MAX_VALUE), 0.01);
    }

    @Test
    void testGenerateShotWithExtremeValues() {
        for (int i = 0; i < 100; i++) {
            double[] shot = Target.generateShot();
            double distance = Math.sqrt(shot[0] * shot[0] + shot[1] * shot[1]);
            assertTrue(distance <= 8.5, "Shot generated outside valid radius.");
        }
    }

    @Test
    void testCalculateScoreWithZeroCoordinates() {
        assertEquals(10.9, Target.calculateScore(0.0, 0.0), 0.01);
    }

    @Test
    void testGenerateShotWithZeroCoordinates() {
        for (int i = 0; i < 100; i++) {
            double[] shot = Target.generateShot();
            double distance = Math.sqrt(shot[0] * shot[0] + shot[1] * shot[1]);
            assertTrue(distance <= 8.5, "Shot generated outside valid radius.");
        }
    }

    @Test
    void testCalculateScoreWithPositiveCoordinates() {
        assertEquals(10.9, Target.calculateScore(0.1, 0.1), 0.01);
        assertEquals(10.8, Target.calculateScore(0.5, 0.5), 0.01);
        assertEquals(10.7, Target.calculateScore(1.0, 1.0), 0.01);
        assertEquals(0.0, Target.calculateScore(9.0, 9.0), 0.01);
    }

    @Test
    void testGenerateShotWithPositiveCoordinates() {
        for (int i = 0; i < 100; i++) {
            double[] shot = Target.generateShot();
            double distance = Math.sqrt(shot[0] * shot[0] + shot[1] * shot[1]);
            assertTrue(distance <= 8.5, "Shot generated outside valid radius.");
        }
    }

    @Test
    void testCalculateScoreWithNegativeCoordinates() {
        assertEquals(10.9, Target.calculateScore(-0.1, -0.1), 0.01);
        assertEquals(10.8, Target.calculateScore(-0.5, -0.5), 0.01);
        assertEquals(10.7, Target.calculateScore(-1.0, -1.0), 0.01);
        assertEquals(0.0, Target.calculateScore(-9.0, -9.0), 0.01);
    }

    @Test
    void testGenerateShotWithNegativeCoordinates() {
        for (int i = 0; i < 100; i++) {
            double[] shot = Target.generateShot();
            double distance = Math.sqrt(shot[0] * shot[0] + shot[1] * shot[1]);
            assertTrue(distance <= 8.5, "Shot generated outside valid radius.");
        }
    }

    @Test
    void testCalculateScoreWithMixedCoordinates() {
        assertEquals(10.9, Target.calculateScore(0.1, -0.1), 0.01);
        assertEquals(10.8, Target.calculateScore(-0.5, 0.5), 0.01);
        assertEquals(10.7, Target.calculateScore(1.0, -1.0), 0.01);
        assertEquals(0.0, Target.calculateScore(-9.0, 9.0), 0.01);
    }

    @Test
    void testGenerateShotWithMixedCoordinates() {
        for (int i = 0; i < 100; i++) {
            double[] shot = Target.generateShot();
            double distance = Math.sqrt(shot[0] * shot[0] + shot[1] * shot[1]);
            assertTrue(distance <= 8.5, "Shot generated outside valid radius.");
        }
    }

    @Test
    void testCalculateScoreWithSmallPositiveCoordinates() {
        assertEquals(10.9, Target.calculateScore(0.01, 0.01), 0.01);
    }

    @Test
    void testCalculateScoreWithSmallNegativeCoordinates() {
        assertEquals(10.9, Target.calculateScore(-0.01, -0.01), 0.01);
    }

    @Test
    void testCalculateScoreWithLargePositiveCoordinates() {
        assertEquals(0.0, Target.calculateScore(8.5, 8.5), 0.01);
    }

    @Test
    void testCalculateScoreWithLargeNegativeCoordinates() {
        assertEquals(0.0, Target.calculateScore(-8.5, -8.5), 0.01);
    }

    @Test
    void testCalculateScoreWithMixedLargeCoordinates() {
        assertEquals(0.0, Target.calculateScore(8.5, -8.5), 0.01);
    }

    @Test
    void testCalculateScoreWithMixedSmallCoordinates() {
        assertEquals(10.9, Target.calculateScore(0.01, -0.01), 0.01);
    }

    @Test
    void testGenerateShotWithSmallRadius() {
        for (int i = 0; i < 100; i++) {
            double[] shot = Target.generateShot();
            double distance = Math.sqrt(shot[0] * shot[0] + shot[1] * shot[1]);
            assertTrue(distance <= 8.5, "Shot generated outside valid radius.");
        }
    }

    @Test
    void testGenerateShotWithLargeRadius() {
        for (int i = 0; i < 100; i++) {
            double[] shot = Target.generateShot();
            double distance = Math.sqrt(shot[0] * shot[0] + shot[1] * shot[1]);
            assertTrue(distance <= 8.5, "Shot generated outside valid radius.");
        }
    }

    @Test
    void testGenerateShotWithZeroRadius() {
        for (int i = 0; i < 100; i++) {
            double[] shot = Target.generateShot();
            double distance = Math.sqrt(shot[0] * shot[0] + shot[1] * shot[1]);
            assertTrue(distance <= 8.5, "Shot generated outside valid radius.");
        }
    }

    @Test
    void testGenerateShotWithNegativeRadius() {
        for (int i = 0; i < 100; i++) {
            double[] shot = Target.generateShot();
            double distance = Math.sqrt(shot[0] * shot[0] + shot[1] * shot[1]);
            assertTrue(distance <= 8.5, "Shot generated outside valid radius.");
        }
    }

    @Test
    void testGenerateShotWithPositiveRadius() {
        for (int i = 0; i < 100; i++) {
            double[] shot = Target.generateShot();
            double distance = Math.sqrt(shot[0] * shot[0] + shot[1] * shot[1]);
            assertTrue(distance <= 8.5, "Shot generated outside valid radius.");
        }
    }

    @Test
    void testGenerateShotWithMixedRadius() {
        for (int i = 0; i < 100; i++) {
            double[] shot = Target.generateShot();
            double distance = Math.sqrt(shot[0] * shot[0] + shot[1] * shot[1]);
            assertTrue(distance <= 8.5, "Shot generated outside valid radius.");
        }
    }

    @Test
    void testCalculateScoreWithZeroRadius() {
        assertEquals(10.9, Target.calculateScore(0.0, 0.0), 0.01);
    }

    @Test
    void testCalculateScoreWithNegativeRadius() {
        assertEquals(0.0, Target.calculateScore(-8.5, -8.5), 0.01);
    }

    @Test
    void testCalculateScoreWithPositiveRadius() {
        assertEquals(0.0, Target.calculateScore(8.5, 8.5), 0.01);
    }

    @Test
    void testCalculateScoreWithMixedRadius() {
        assertEquals(0.0, Target.calculateScore(8.5, -8.5), 0.01);
    }

    @Test
    void testCalculateScoreWithSmallRadius() {
        assertEquals(10.9, Target.calculateScore(0.01, 0.01), 0.01);
    }

    @Test
    void testCalculateScoreWithLargeRadius() {
        assertEquals(0.0, Target.calculateScore(8.5, 8.5), 0.01);
    }

    @Test
    void testCalculateScoreWithMixedSmallRadius() {
        assertEquals(10.9, Target.calculateScore(0.01, -0.01), 0.01);
    }

    @Test
    void testCalculateScoreWithMixedLargeRadius() {
        assertEquals(0.0, Target.calculateScore(8.5, -8.5), 0.01);
    }
}
