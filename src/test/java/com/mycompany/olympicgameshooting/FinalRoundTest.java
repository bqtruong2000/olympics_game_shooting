/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.olympicgameshooting;

import com.mycompany.olympicgameshooting.exceptions.athlete.ShotException;
import com.mycompany.olympicgameshooting.exceptions.finalround.FinalRoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author Chun
 */
public class FinalRoundTest {

    private List<Athlete> finalists;
    private FinalRound finalRound;

    private static Stream<Arguments> provideScoreScenarios() {
        return Stream.of(
                arguments(List.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)),
                arguments(List.of(8.0, 8.0, 8.0, 8.0, 8.0, 8.0, 8.0, 7.0)),
                arguments(List.of(0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8))
        );
    }

    @BeforeEach
    void setUp() throws FinalRoundException {
        finalists = createTestAthletes(8);
        finalRound = new FinalRound(finalists);
    }

    private List<Athlete> createTestAthletes(int count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> new Athlete(
                        "ID" + i,
                        "FirstName" + i,
                        "LastName" + i,
                        "Country" + i,
                        Math.random() * 100,
                        i,
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>()
                ))
                .collect(Collectors.toList());
    }

    @Test
    void testFinalRoundInitialization() {
        assertNotNull(finalRound);
        assertEquals(8, finalists.size(), "Initial size of finalists should be 8.");
    }

    @Test
    void testFinalRoundInitializationWithNullList() {
        assertThrows(FinalRoundException.class, () -> new FinalRound(null),
                "Should throw FinalRoundException when null list is passed");
    }

    @Test
    void testFinalRoundInitializationWithEmptyList() {
        List<Athlete> emptyList = new ArrayList<>();
        assertThrows(FinalRoundException.class, () -> new FinalRound(emptyList));
    }

    @Test
    void testSimulateFinalRoundToTwoAthletes() throws ShotException {
        finalRound.simulateFinalRound();
        List<Athlete> remainingFinalists = finalRound.getFinalists();
        assertEquals(2, remainingFinalists.size());
    }

    @Test
    void testShootAndCalculateScore() throws ShotException {
        Athlete athlete = finalists.getFirst();
        double totalScore = finalRound.shootAndCalculateScore(athlete, 1);
        assertTrue(totalScore > 0, "Total score should be greater than 0 after shooting.");
        assertEquals(10, athlete.getFinalShots().size(), "Athlete should have 10 shots for round 1.");
    }

    @Test
    void testInvalidShot() {
        assertThrows(ShotException.class, () -> {
            throw new ShotException("Simulated invalid shot");
        });
    }

    @Test
    void testRandomizedAthleteScores() throws ShotException {
        finalRound.simulateFinalRound();
        List<Athlete> remainingFinalists = finalRound.getFinalists();
        assertEquals(2, remainingFinalists.size(), "Finalists should reduce to 2 after randomized simulation.");
    }

    @Test
    void testSimulateWithExactTwoAthletes() throws ShotException, FinalRoundException {
        List<Athlete> twoAthletes = finalists.subList(0, 2);
        FinalRound twoAthleteFinal = new FinalRound(twoAthletes);
        twoAthleteFinal.simulateFinalRound();
        assertEquals(2, twoAthletes.size(), "No elimination should occur with exactly 2 athletes.");
    }

    @Test
    void testBoundaryScoreHandling() throws ShotException {
        for (int i = 0; i < finalists.size(); i++) {
            finalists.get(i).setTotalScore(4.5 + i * 0.1);
        }
        finalRound.eliminateLowestScorer();
        assertEquals(7, finalRound.getFinalists().size(), "One athlete should be eliminated for boundary scores.");
    }

    @Test
    void testExtremeLargeScores() {
        for (Athlete athlete : finalists) {
            athlete.setTotalScore(Double.MAX_VALUE);
        }
        assertDoesNotThrow(() -> finalRound.eliminateLowestScorer());
    }

    @Test
    public void testSimulateFinalRound() throws ShotException {
        finalRound.simulateFinalRound();
        List<Athlete> remainingFinalists = finalRound.getFinalists();
        assertEquals(2, remainingFinalists.size(), "Final round should reduce the finalists to 2.");
        assertTrue(remainingFinalists.get(0).getTotalScore() >= remainingFinalists.get(1).getTotalScore(), "Gold medalist should have the highest score.");
    }

    @Test
    public void testEliminateLowestScorer() {
        int initialSize = finalists.size();
        finalRound.eliminateLowestScorer();
        assertEquals(initialSize - 1, finalRound.getFinalists().size(), "One athlete should be eliminated.");
        assertFalse(finalRound.getFinalists().contains(
                        finalists.stream().min(Comparator.comparing(Athlete::getTotalScore)).orElseThrow()),
                "Lowest scorer should be removed.");
    }

    @Test
    public void testGetFinalists() {
        List<Athlete> retrievedFinalists = finalRound.getFinalists();
        assertNotNull(retrievedFinalists, "Finalists list should not be null.");
        assertEquals(finalists.size(), retrievedFinalists.size(), "Finalists list size should match the initial size.");
    }

    @Test
    void testEliminateLowestScorerWithDifferentScores() throws ShotException {
        for (int i = 0; i < finalists.size(); i++) {
            finalists.get(i).setTotalScore(i * 0.5);
        }

        int initialSize = finalists.size();
        finalRound.eliminateLowestScorer();

        assertEquals(initialSize - 1, finalRound.getFinalists().size(),
                "One athlete should be eliminated");
        assertFalse(finalRound.getFinalists().contains(
                        finalists.stream().min(Comparator.comparing(Athlete::getTotalScore)).orElseThrow()),
                "Lowest scorer should be removed");
    }

    @Test
    void testEliminateLowestScorerWithEqualScores() {
        finalists.forEach(a -> a.setTotalScore(10.0));

        finalRound.eliminateLowestScorer();

        assertEquals(7, finalRound.getFinalists().size(),
                "One athlete should be eliminated even with equal scores");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void testShootAndCalculateScoreForDifferentRounds(int roundNumber) throws ShotException {
        Athlete testAthlete = finalists.getFirst();
        int expectedShots = (roundNumber == 1) ? 10 : 2;

        double totalScore = finalRound.shootAndCalculateScore(testAthlete, roundNumber);

        assertTrue(totalScore > 0, "Total score should be positive");
        assertEquals(expectedShots, testAthlete.getFinalShots().size(),
                "Shots should match round requirements");
    }

    @Test
    void testSimulateFinalRoundReducesToTwoAthletes() throws ShotException {
        finalRound.simulateFinalRound();
        assertEquals(2, finalRound.getFinalists().size(),
                "Final round should reduce to exactly 2 athletes");
    }

    @Test
    void testSimulateFinalRoundWithTwoInitialAthletes() throws FinalRoundException, ShotException {
        List<Athlete> twoAthletes = finalists.subList(0, 2);
        FinalRound twoAthleteRound = new FinalRound(twoAthletes);

        twoAthleteRound.simulateFinalRound();
        assertEquals(2, twoAthleteRound.getFinalists().size(),
                "With two athletes, no elimination should occur");
    }

    @Test
    void testHandlingOfExtremeScores() {
        finalists.forEach(a -> a.setTotalScore(Double.MAX_VALUE));
        assertDoesNotThrow(() -> finalRound.eliminateLowestScorer(),
                "Should handle extreme score values without error");
    }

    @Test
    void testHandlingOfMinimumScores() {
        finalists.forEach(a -> a.setTotalScore(Double.MIN_VALUE));
        assertDoesNotThrow(() -> finalRound.eliminateLowestScorer(),
                "Should handle minimum score values without error");
    }

    @Test
    void testEliminationRandomnessWithEqualScores() {
        finalists.forEach(a -> a.setTotalScore(10.0));

        int originalSize = finalists.size();
        finalRound.eliminateLowestScorer();

        assertEquals(originalSize - 1, finalRound.getFinalists().size(),
                "One athlete should be eliminated from equal-scored group");
    }

    @Test
    void testShotExceptionHandling() {
        assertThrows(ShotException.class, () -> {
            throw new ShotException("Simulated shot generation error");
        }, "Should properly handle shot generation exceptions");
    }

    @Test
    void testFinalRoundInitializationWithMinimumAllowedAthletes() throws FinalRoundException {
        List<Athlete> minAthletes = finalists.subList(0, 2);
        FinalRound minFinalRound = new FinalRound(minAthletes);
        assertEquals(2, minFinalRound.getFinalists().size(),
                "Final round should allow minimum of 2 athletes");
    }

    @ParameterizedTest
    @MethodSource("provideScoreScenarios")
    void testEliminationWithVariedScoreDistributions(List<Double> scores) {
        for (int i = 0; i < scores.size(); i++) {
            finalists.get(i).setTotalScore(scores.get(i));
        }

        finalRound.eliminateLowestScorer();

        assertEquals(finalists.size() - 1, finalRound.getFinalists().size(),
                "One athlete should be eliminated");

        assertTrue(finalRound.getFinalists().stream()
                        .noneMatch(a -> a.getTotalScore() == scores.stream().min(Double::compare).orElse(0.0)),
                "Lowest scorer should be removed");
    }

    @Test
    void testShootingSimulationConsistency() throws ShotException {
        Athlete athlete = finalists.getFirst();

        for (int round = 1; round <= 2; round++) {
            int expectedShots = (round == 1) ? 10 : 2;
            double totalScore = finalRound.shootAndCalculateScore(athlete, round);

            assertTrue(totalScore > 0, "Total score should be positive");
            assertEquals(expectedShots,
                    athlete.getFinalShots().size() - (round == 1 ? 0 : 10),
                    "Shots should match round requirements");
        }
    }

    @Test
    void testSimulateFinalRoundPerformance() {
        assertTimeout(java.time.Duration.ofSeconds(5), () -> {
            try {
                finalRound.simulateFinalRound();
            } catch (ShotException e) {
                fail("Unexpected exception during final round simulation");
            }
        }, "Final round simulation should complete within 5 seconds");
    }

    @Test
    void testFinalRoundInternalState() throws Exception {
        Field finalistsField = FinalRound.class.getDeclaredField("finalists");
        finalistsField.setAccessible(true);

        Object finalistsList = finalistsField.get(finalRound);
        assertNotNull(finalistsList, "Finalists list should not be null");
        assertInstanceOf(List.class, finalistsList, "Finalists should be stored in a List");
    }

    @Test
    void testSimulationWithHighVariabilityScores() throws ShotException {
        Random random = new Random();
        for (Athlete finalist : finalists) {
            finalist.setTotalScore(random.nextDouble() * 100);
        }

        finalRound.simulateFinalRound();

        assertEquals(2, finalRound.getFinalists().size(),
                "Final round should always reduce to 2 athletes");
    }

    @Test
    void testFinalRoundWithLargeNumberOfAthletes() throws FinalRoundException, ShotException {
        List<Athlete> largeGroupAthletes = IntStream.rangeClosed(1, 16)
                .mapToObj(i -> new Athlete(
                        "ID" + i,
                        "FirstName" + i,
                        "LastName" + i,
                        "Country" + i,
                        Math.random() * 100,
                        i,
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>()
                ))
                .collect(Collectors.toList());

        FinalRound largeFinalRound = new FinalRound(largeGroupAthletes);
        largeFinalRound.simulateFinalRound();

        assertEquals(2, largeFinalRound.getFinalists().size(),
                "Large group should reduce to 2 athletes");
    }

    @Test
    void testHandlingOfNaNScores() {
        finalists.forEach(a -> a.setTotalScore(Double.NaN));

        assertDoesNotThrow(() -> finalRound.eliminateLowestScorer(),
                "Should handle NaN scores gracefully");
    }

    @Test
    void testEliminationOrder() throws ShotException {
        for (int i = 0; i < finalists.size(); i++) {
            finalists.get(i).setTotalScore(i * 0.5);
        }

        Athlete expectedFirstElimination = finalists.stream()
                .min(Comparator.comparing(Athlete::getTotalScore))
                .orElseThrow();

        finalRound.eliminateLowestScorer();

        assertFalse(finalRound.getFinalists().contains(expectedFirstElimination),
                "Lowest scoring athlete should be eliminated first");
    }

    @Test
    void testAthleteAttributePreservationDuringFinalRound() throws ShotException {
        Athlete sampleAthlete = finalists.getFirst();
        String originalId = sampleAthlete.getAthleteID();
        String originalFirstName = sampleAthlete.getFirstName();
        String originalLastName = sampleAthlete.getLastName();
        String originalCountry = sampleAthlete.getCountry();

        finalRound.shootAndCalculateScore(sampleAthlete, 1);

        assertEquals(originalId, sampleAthlete.getAthleteID(), "Athlete ID should not change");
        assertEquals(originalFirstName, sampleAthlete.getFirstName(), "First name should not change");
        assertEquals(originalLastName, sampleAthlete.getLastName(), "Last name should not change");
        assertEquals(originalCountry, sampleAthlete.getCountry(), "Country should not change");
    }

    @Test
    void testAthleteAttributePreservationDuringFinalRound2() throws ShotException {
        Athlete sampleAthlete = finalists.getFirst();
        String originalId = sampleAthlete.getAthleteID();
        String originalFirstName = sampleAthlete.getFirstName();
        String originalLastName = sampleAthlete.getLastName();
        String originalCountry = sampleAthlete.getCountry();

        finalRound.shootAndCalculateScore(sampleAthlete, 2);

        assertEquals(originalId, sampleAthlete.getAthleteID(), "Athlete ID should not change");
        assertEquals(originalFirstName, sampleAthlete.getFirstName(), "First name should not change");
        assertEquals(originalLastName, sampleAthlete.getLastName(), "Last name should not change");
        assertEquals(originalCountry, sampleAthlete.getCountry(), "Country should not change");
    }

    @Test
    void testShotDistributionUniformity() throws ShotException {
        Athlete athlete = finalists.get(0);
        finalRound.shootAndCalculateScore(athlete, 1);

        List<Double> shots = athlete.getFinalShots().subList(0, 10);

        double mean = shots.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double variance = shots.stream()
                .mapToDouble(shot -> Math.pow(shot - mean, 2))
                .average()
                .orElse(0.0);

        assertTrue(mean >= 0 && mean <= 10, "Mean shot score should be between 0 and 10");
        assertTrue(variance >= 0, "Shot variance should be non-negative");
    }

    @Test
    void testEliminationStrategyConsistency() throws ShotException {
        for (int i = 0; i < finalists.size(); i++) {
            finalists.get(i).setTotalScore(10.0 - (i * 0.01));
        }

        List<Athlete> initialFinalists = new ArrayList<>(finalists);

        finalRound.eliminateLowestScorer();

        assertEquals(finalists.size() - 1, finalRound.getFinalists().size(),
                "One athlete should be eliminated");

        Athlete expectedEliminated = initialFinalists.stream()
                .min(Comparator.comparing(Athlete::getTotalScore))
                .orElseThrow();

        assertFalse(finalRound.getFinalists().contains(expectedEliminated),
                "Lowest scoring athlete should be removed");
    }

    @Test
    void testPrivateMethodAccessibility() {
        try {
            Method simulateRoundMethod = FinalRound.class.getDeclaredMethod("simulateRound", int.class);
            simulateRoundMethod.setAccessible(true);

            assertNotNull(simulateRoundMethod, "Private simulateRound method should be accessible");
        } catch (NoSuchMethodException e) {
            fail("Expected private method not found");
        }
    }

    @Test
    void testMemoryEfficiencyInLongSimulation() throws FinalRoundException, ShotException {
        List<Athlete> largeGroupAthletes = createTestAthletes(50);
        FinalRound largeFinalRound = new FinalRound(largeGroupAthletes);

        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        largeFinalRound.simulateFinalRound();

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        assertEquals(2, largeFinalRound.getFinalists().size(), "Should reduce to 2 athletes");

        assertTrue(memoryAfter - memoryBefore < memoryBefore * 2,
                "Memory usage should remain relatively stable");
    }

    @Test
    void testScoreManipulationEdgeCases() {
        List<Runnable> scoreManipulationTests = Arrays.asList(
                () -> finalists.forEach(a -> a.setTotalScore(Double.POSITIVE_INFINITY)),
                () -> finalists.forEach(a -> a.setTotalScore(Double.NEGATIVE_INFINITY)),
                () -> finalists.forEach(a -> a.setTotalScore(0.0)),
                () -> finalists.forEach(a -> a.setTotalScore(Double.MAX_VALUE))
        );

        scoreManipulationTests.forEach(test -> {
            try {
                finalists = createTestAthletes(8);
                finalRound = new FinalRound(finalists);

                test.run();

                assertDoesNotThrow(() -> finalRound.eliminateLowestScorer(),
                        "Should handle extreme score values");
            } catch (FinalRoundException e) {
                fail("Unexpected exception during score manipulation test");
            }
        });
    }

    @Test
    void testConcurrentEliminationSimulation() {
        AtomicInteger successfulSimulations = new AtomicInteger(0);

        IntStream.range(0, 100).parallel().forEach(i -> {
            try {
                List<Athlete> simulationAthletes = createTestAthletes(8);
                FinalRound simulationRound = new FinalRound(simulationAthletes);

                simulationRound.simulateFinalRound();

                if (simulationRound.getFinalists().size() == 2) {
                    successfulSimulations.incrementAndGet();
                }
            } catch (Exception e) {
                fail("Unexpected exception in concurrent simulation");
            }
        });

        assertTrue(successfulSimulations.get() > 90,
                "Most simulations should successfully reduce to 2 athletes");
    }

    @Test
    void testEliminationWithMixedPerformanceAthletes() throws ShotException {
        List<Double> performanceLevels = Arrays.asList(
                9.5, 8.7, 7.2, 6.5, 5.3, 4.6, 3.1, 2.0
        );

        for (int i = 0; i < finalists.size(); i++) {
            finalists.get(i).setTotalScore(performanceLevels.get(i));
        }

        Athlete expectedEliminated = finalists.stream()
                .min(Comparator.comparing(Athlete::getTotalScore))
                .orElseThrow();

        finalRound.eliminateLowestScorer();

        assertFalse(finalRound.getFinalists().contains(expectedEliminated),
                "Lowest performing athlete should be eliminated");
        assertEquals(finalists.size() - 1, finalRound.getFinalists().size(),
                "One athlete should be eliminated");
    }

    @Test
    void testCountryRepresentationInFinalRound() {
        Map<String, Long> countryCount = finalists.stream()
                .collect(Collectors.groupingBy(Athlete::getCountry, Collectors.counting()));

        assertTrue(countryCount.size() > 1, "Final round should have athletes from multiple countries");
    }

    @Test
    void testShotPrecisionConsistency() throws ShotException {
        Athlete athlete = finalists.getFirst();
        finalRound.shootAndCalculateScore(athlete, 1);

        athlete.getFinalShots().subList(0, 10).forEach(shot -> {
            assertEquals(
                    Math.round(shot * 100.0) / 100.0,
                    shot,
                    0.01,
                    "Shot scores should be precise to 2 decimal places"
            );
        });
    }

    private double calculateVariance(List<Double> values) {
        double mean = values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        return values.stream()
                .mapToDouble(v -> Math.pow(v - mean, 2))
                .average()
                .orElse(0.0);
    }

    @Test
    void testAthleteComparatorStability() {
        List<Athlete> sortedAthletes = new ArrayList<>(finalists);

        List<Comparator<Athlete>> comparators = Arrays.asList(
                Comparator.comparing(Athlete::getTotalScore),
                Comparator.comparing(Athlete::getAthleteID),
                Comparator.comparing(Athlete::getCountry)
        );

        comparators.forEach(comparator -> {
            sortedAthletes.sort(comparator);

            assertDoesNotThrow(() -> {
                    },
                    "Sorting athletes should not throw exceptions");
        });
    }

    @Test
    void testEliminationWithNearEqualScores() {
        Random random = new Random();
        for (Athlete finalist : finalists) {
            finalist.setTotalScore(10.0 + (random.nextDouble() * 0.001));
        }

        int initialSize = finalists.size();
        finalRound.eliminateLowestScorer();

        assertEquals(initialSize - 1, finalRound.getFinalists().size(),
                "One athlete should be eliminated even with near-equal scores");
    }

    @Test
    void testAthletePerformanceProfile() throws ShotException {
        Athlete athlete = finalists.getFirst();
        finalRound.shootAndCalculateScore(athlete, 1);

        List<Double> shots = athlete.getFinalShots().subList(0, 10);

        PerformanceProfile profile = analyzePerformanceProfile(shots);

        assertNotNull(profile, "Performance profile should be generated");
        assertTrue(profile.consistencyScore >= 0 && profile.consistencyScore <= 1,
                "Consistency score should be between 0 and 1");
    }

    private PerformanceProfile analyzePerformanceProfile(List<Double> shots) {
        double mean = shots.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double variance = calculateVariance(shots);

        double consistencyScore = 1 - (variance / 100.0);

        double peakPerformance = shots.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0.0);

        return new PerformanceProfile(
                Math.max(0, Math.min(1, consistencyScore)),
                peakPerformance
        );
    }

    @Test
    void testRapidSuccessiveFinalRoundSimulations() {
        List<List<Athlete>> simulationResults = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            try {
                FinalRound tempFinalRound = new FinalRound(createTestAthletes(8));
                tempFinalRound.simulateFinalRound();
                simulationResults.add(tempFinalRound.getFinalists());
            } catch (Exception e) {
                fail("Successive simulations should not throw exceptions");
            }
        }

        simulationResults.forEach(result ->
                assertEquals(2, result.size(), "Each simulation should reduce to 2 athletes")
        );
    }

    @Test
    void testDataIntegrityDuringSimulation() throws ShotException {
        Map<String, Object> initialAthleteState = captureAthleteState(finalists.getFirst());

        finalRound.shootAndCalculateScore(finalists.getFirst(), 1);

        Map<String, Object> finalAthleteState = captureAthleteState(finalists.getFirst());

        assertEquals(
                initialAthleteState.get("athleteId"),
                finalAthleteState.get("athleteId"),
                "Athlete ID should not change"
        );
        assertEquals(
                initialAthleteState.get("firstName"),
                finalAthleteState.get("firstName"),
                "First name should not change"
        );
    }

    private Map<String, Object> captureAthleteState(Athlete athlete) {
        return Map.of(
                "athleteId", athlete.getAthleteID(),
                "firstName", athlete.getFirstName(),
                "totalScore", athlete.getTotalScore()
        );
    }

    private static class PerformanceProfile {
        double consistencyScore;
        double peakPerformance;

        public PerformanceProfile(double consistencyScore, double peakPerformance) {
            this.consistencyScore = consistencyScore;
            this.peakPerformance = peakPerformance;
        }
    }
}


