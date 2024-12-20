/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.olympicgameshooting;

import com.mycompany.olympicgameshooting.exceptions.athlete.ShotException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chun
 */
public class QualifyingRoundTest {

    private List<Athlete> athletes;
    private QualifyingRound qualifyingRound;

    public QualifyingRoundTest() {
    }

    @BeforeEach
    void setUp() {
        athletes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            athletes.add(new Athlete(
                    "ID" + i,
                    "FirstName" + i,
                    "LastName" + i,
                    "Country" + i,
                    Math.random() * 100,
                    i,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>()
            ));
        }
        qualifyingRound = new QualifyingRound(athletes);
    }

    @Test
    void testSimulateShots() throws ShotException {
        qualifyingRound.simulateShots();
        for (Athlete athlete : athletes) {
            assertEquals(60, athlete.getQualificationShots().size(), "Each athlete should have 60 qualification shots.");
        }
    }

    @Test
    void testGetTop8AthletesSize() throws ShotException {
        qualifyingRound.simulateShots();
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "The top 8 athletes should be returned.");
    }

    @Test
    void testGetTop8AthletesSorted() throws ShotException {
        qualifyingRound.simulateShots();
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        for (int i = 0; i < top8Athletes.size() - 1; i++) {
            assertTrue(top8Athletes.get(i).getQualificationScore() >= top8Athletes.get(i + 1).getQualificationScore(),
                    "Athletes should be sorted by their qualification score in descending order.");
        }
    }

    @Test
    void testAllAthletesBelowMinimum() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(0.0);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are zero, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesAboveMinimum() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(10.0);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are 10, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesSameScore() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(5.0);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are the same, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesRandomScore() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(Math.random() * 10);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are random, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesRandomScore2() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(Math.random() * 10);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are random, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesRandomScore3() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(Math.random() * 10);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are random, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesRandomScore4() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(Math.random() * 10);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are random, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesRandomScore5() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(Math.random() * 10);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are random, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesRandomScore6() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(Math.random() * 10);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are random, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesRandomScore7() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(Math.random() * 10);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are random, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesRandomScore8() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(Math.random() * 10);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are random, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesRandomScore9() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(Math.random() * 10);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are random, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesRandomScore10() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(Math.random() * 10);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are random, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesRandomScore11() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(Math.random() * 10);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are random, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesRandomScore12() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(Math.random() * 10);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are random, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesRandomScore13() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(Math.random() * 10);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are random, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesRandomScore14() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(Math.random() * 10);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are random, the top 8 should be returned.");
    }

    @Test
    void testAllAthletesRandomScore15() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                athlete.addQualificationShot(Math.random() * 10);
            }
        }
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "Even if all scores are random, the top 8 should be returned.");
    }

    @Test
    void testLessThan10Athletes() throws ShotException {
        List<Athlete> fewAthletes = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            fewAthletes.add(new Athlete(
                    "ID" + i,
                    "FirstName" + i,
                    "LastName" + i,
                    "Country" + i,
                    Math.random() * 100,
                    i,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>()
            ));
        }
        QualifyingRound qualifyingRound = new QualifyingRound(fewAthletes);
        qualifyingRound.simulateShots();
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(5, top8Athletes.size(), "When fewer than 8 athletes, all should be returned");
    }

    @Test
    void testDuplicateAthletes() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        Athlete duplicateAthlete = new Athlete(
                "ID1",
                "FirstName1",
                "LastName1",
                "Country1",
                100.0,
                1,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        for (int i = 0; i < 60; i++) {
            duplicateAthlete.addQualificationShot(10.0);
        }

        for (int i = 0; i < 10; i++) {
            athletes.add(duplicateAthlete);
        }

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);

        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(8, top8Athletes.size(), "Should return 8 entries even with duplicate athletes");
        assertTrue(top8Athletes.stream().allMatch(a -> a.getAthleteID().equals("ID1")),
                "All top athletes should be the same duplicate athlete");
    }

    @Test
    void testExtremeScenarios() throws ShotException {
        List<Athlete> extremeAthletes = new ArrayList<>();

        Athlete perfectShooter = createAthleteWithFixedScores(10.0);

        Athlete worstShooter = createAthleteWithFixedScores(0.0);

        Athlete inconsistentShooter = new Athlete(
                "ID3",
                "FirstName3",
                "LastName3",
                "Country3",
                50.0,
                3,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        for (int i = 0; i < 30; i++) {
            inconsistentShooter.addQualificationShot(10.0);
            inconsistentShooter.addQualificationShot(0.0);
        }

        extremeAthletes.add(perfectShooter);
        extremeAthletes.add(worstShooter);
        extremeAthletes.add(inconsistentShooter);

        QualifyingRound qualifyingRound = new QualifyingRound(extremeAthletes);


        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(3, top8Athletes.size(), "Should return all athletes in extreme scenarios");
        assertEquals(perfectShooter.getAthleteID(), top8Athletes.getFirst().getAthleteID(), "Perfect shooter should be first");
    }

    private Athlete createAthleteWithFixedScores(double score) throws ShotException {
        Athlete athlete = new Athlete(
                "ID" + UUID.randomUUID().toString(),
                "FirstName",
                "LastName",
                "Country",
                Math.random() * 100,
                1,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        for (int i = 0; i < 60; i++) {
            athlete.addQualificationShot(score);
        }

        return athlete;
    }

    private Athlete createAthleteWithVariableScores() throws ShotException {
        Athlete athlete = new Athlete(
                "ID" + UUID.randomUUID().toString(),
                "FirstName",
                "LastName",
                "Country",
                Math.random() * 100,
                1,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        Random random = new Random();
        for (int i = 0; i < 60; i++) {
            athlete.addQualificationShot(random.nextDouble(0, 10));
        }

        return athlete;
    }

    @Test
    void testAthleteOrderingWithTieBreaker() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();

        Athlete athlete1 = createAthleteWithPreciseScores(9.8, 9.7, 9.9);
        Athlete athlete2 = createAthleteWithPreciseScores(9.9, 9.8, 9.7);

        athletes.add(athlete1);
        athletes.add(athlete2);

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(2, top8Athletes.size(), "Should return both athletes");
        assertTrue(top8Athletes.get(0).getQualificationScore() >= top8Athletes.get(1).getQualificationScore(),
                "Athletes should be sorted by total score");
    }

    @Test
    void testAthletesWithZeroAndNegativeScores() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();

        Athlete zeroScoreAthlete = new Athlete(
                "ID1",
                "ZeroScore",
                "Athlete",
                "Country1",
                0.0,
                1,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        for (int i = 0; i < 60; i++) {
            zeroScoreAthlete.addQualificationShot(0.0);
        }

        athletes.add(zeroScoreAthlete);

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(1, top8Athletes.size(), "Should return the zero score athlete");
        assertEquals(zeroScoreAthlete.getAthleteID(), top8Athletes.get(0).getAthleteID(),
                "Zero score athlete should be ranked correctly");
    }

    @Test
    void testLargeNumberOfAthletes() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            Athlete athlete = new Athlete(
                    "ID" + i,
                    "FirstName" + i,
                    "LastName" + i,
                    "Country" + i,
                    random.nextDouble() * 100,
                    i,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>()
            );

            for (int j = 0; j < 60; j++) {
                athlete.addQualificationShot(random.nextDouble() * 10);
            }

            athletes.add(athlete);
        }

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(8, top8Athletes.size(), "Should return top 8 athletes from large pool");

        for (int i = 0; i < top8Athletes.size() - 1; i++) {
            assertTrue(top8Athletes.get(i).getQualificationScore() >=
                            top8Athletes.get(i + 1).getQualificationScore(),
                    "Athletes should be sorted by qualification score");
        }
    }

    private Athlete createAthleteWithPreciseScores(double... scores) throws ShotException {
        Athlete athlete = new Athlete(
                "ID" + UUID.randomUUID().toString(),
                "FirstName",
                "LastName",
                "Country",
                Math.random() * 100,
                1,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        for (double score : scores) {
            for (int i = 0; i < 20; i++) {
                athlete.addQualificationShot(score);
            }
        }

        return athlete;
    }

    private Athlete createAthleteWithVariedScores(double... baseScores) throws ShotException {
        Athlete athlete = new Athlete(
                "ID" + UUID.randomUUID().toString(),
                "FirstName",
                "LastName",
                "Country",
                Math.random() * 100,
                1,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        Random random = new Random();
        for (double baseScore : baseScores) {
            for (int i = 0; i < 20; i++) {
                double variation = random.nextDouble(-0.5, 0.5);
                athlete.addQualificationShot(Math.max(0, Math.min(10, baseScore + variation)));
            }
        }

        return athlete;
    }

    @Test
    void testAthletesWithMixedScores() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        athletes.add(createAthleteWithFixedScores(0.0));
        athletes.add(createAthleteWithFixedScores(10.0));
        athletes.add(createAthleteWithFixedScores(5.0));
        athletes.add(createAthleteWithVariableScores());

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(4, top8Athletes.size(), "Should return all athletes");
    }

    @Test
    void testAthletesWithIncreasingScores() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            athletes.add(createAthleteWithFixedScores(i));
        }

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(8, top8Athletes.size(), "Should return top 8 athletes");
    }

    @Test
    void testAthletesWithDecreasingScores() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        for (int i = 10; i > 0; i--) {
            athletes.add(createAthleteWithFixedScores(i));
        }

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(8, top8Athletes.size(), "Should return top 8 athletes");
    }

    @Test
    void testAthletesWithRandomScores() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            athletes.add(createAthleteWithFixedScores(random.nextDouble() * 10));
        }

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(8, top8Athletes.size(), "Should return top 8 athletes");
    }

    @Test
    void testAthletesWithAlternatingScores() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            athletes.add(createAthleteWithFixedScores(i % 2 == 0 ? 10.0 : 0.0));
        }

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(8, top8Athletes.size(), "Should return top 8 athletes");
    }

    @Test
    void testAthletesWithHighVarianceScores() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            athletes.add(createAthleteWithFixedScores(i % 2 == 0 ? 10.0 : 5.0));
        }

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(8, top8Athletes.size(), "Should return top 8 athletes");
    }

    @Test
    void testAthletesWithLowVarianceScores() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            athletes.add(createAthleteWithFixedScores(5.0 + (i % 2 == 0 ? 0.1 : -0.1)));
        }

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(8, top8Athletes.size(), "Should return top 8 athletes");
    }

    @Test
    void testAthletesWithExtremeScores() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        athletes.add(createAthleteWithFixedScores(0.0));
        athletes.add(createAthleteWithFixedScores(10.0));

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(2, top8Athletes.size(), "Should return all athletes");
    }

    @Test
    void testAthletesWithMixedPerformance() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        athletes.add(createAthleteWithVariedScores(10.0, 0.0, 5.0));

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(1, top8Athletes.size(), "Should return the mixed performance athlete");
    }

    @Test
    void testAthletesWithConsistentPerformance() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        athletes.add(createAthleteWithVariedScores(9.0, 9.5, 8.5));

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(1, top8Athletes.size(), "Should return the consistent performance athlete");
    }

    @Test
    void testAthletesWithInconsistentPerformance() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        athletes.add(createAthleteWithVariedScores(10.0, 5.0, 7.0));

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(1, top8Athletes.size(), "Should return the inconsistent performance athlete");
    }

    @Test
    void testAthletesWithEqualScores() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            athletes.add(createAthleteWithFixedScores(7.5));
        }

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(8, top8Athletes.size(), "Should return top 8 athletes even with equal scores");
    }

    @Test
    void testAthletesWithCloseScores() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            athletes.add(createAthleteWithFixedScores(7.5 + (i * 0.1)));
        }

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(8, top8Athletes.size(), "Should return top 8 athletes with close scores");
    }

    @Test
    void testAthletesWithFarScores() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            athletes.add(createAthleteWithFixedScores(i * 1.0));
        }

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(8, top8Athletes.size(), "Should return top 8 athletes with far scores");
    }

    @Test
    void testAthletesWithNegativeScores() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            assertThrows(ShotException.class, () -> {
                athletes.add(createAthleteWithFixedScores(-1.0));
            }, "Should not allow negative scores");
        }

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(0, top8Athletes.size(), "Should return no athletes with negative scores");
    }

    @Test
    void testAthletesWithZeroScores() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            athletes.add(createAthleteWithFixedScores(0.0));
        }

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(8, top8Athletes.size(), "Should return top 8 athletes with zero scores");
    }

    @Test
    void testAthletesWithPerfectScores() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            athletes.add(createAthleteWithFixedScores(10.0));
        }

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(8, top8Athletes.size(), "Should return top 8 athletes with perfect scores");
    }

    @Test
    void testAthletesWithMixedQualityShots() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        athletes.add(createAthleteWithVariedScores(9.0, 9.5, 8.5));
        athletes.add(createAthleteWithVariedScores(10.0, 5.0, 7.0));

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(2, top8Athletes.size(), "Should return both athletes");
        assertTrue(top8Athletes.get(0).getQualificationScore() > top8Athletes.get(1).getQualificationScore(),
                "Athletes should be sorted by total score");
    }

    @Test
    void testAthletesWithExtremeScenarios() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        athletes.add(createAthleteWithFixedScores(10.0));
        athletes.add(createAthleteWithFixedScores(0.0));
        athletes.add(createAthleteWithVariedScores(10.0, 0.0, 5.0));

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(3, top8Athletes.size(), "Should return all athletes in extreme scenarios");
        assertEquals(10.0 * 60, top8Athletes.get(0).getQualificationScore(), "Perfect shooter should be first");
    }

    @Test
    void testAthletesWithLargeNumber() throws ShotException {
        List<Athlete> athletes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            athletes.add(createAthleteWithFixedScores(random.nextDouble() * 10));
        }

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        assertEquals(8, top8Athletes.size(), "Should return top 8 athletes from large pool");
        for (int i = 0; i < top8Athletes.size() - 1; i++) {
            assertTrue(top8Athletes.get(i).getQualificationScore() >= top8Athletes.get(i + 1).getQualificationScore(),
                    "Athletes should be sorted by qualification score");
        }
    }

    @Test
    public void testGetTop8Athletes() {
        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();
        assertEquals(8, top8Athletes.size(), "The top 8 athletes should be returned.");
    }

}
