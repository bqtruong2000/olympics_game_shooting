/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.olympicgameshooting;

import com.mycompany.olympicgameshooting.exceptions.athlete.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Chun
 */
public class AthleteTest {

    private Athlete athlete;

    public AthleteTest() {
    }

    @BeforeEach
    void setUp() {
        athlete = new Athlete(
                "VN001",
                "Nguyen",
                "Van Anh",
                "Viet Nam",
                0.0,
                0,
                null,
                null,
                null
        );
    }

    @Test
    public void testConstructorInitializesFields() {
        assertEquals("VN001", athlete.getAthleteID());
        assertEquals("Nguyen", athlete.getFirstName());
        assertEquals("Van Anh", athlete.getLastName());
        assertEquals("Viet Nam", athlete.getCountry());
        assertEquals(0.0, athlete.getRankingScore());
        assertEquals(0, athlete.getWorldRanking());
        assertEquals(0, athlete.getShots().size());
        assertEquals(0, athlete.getQualificationShots().size());
        assertEquals(0, athlete.getFinalShots().size());
        assertEquals(0.0, athlete.getQualificationScore());
        assertEquals(0.0, athlete.getFinalScore());
        assertEquals(0.0, athlete.getTotalScore());
    }

    @Test
    public void testGetAthleteID() {
        assertEquals("VN001", athlete.getAthleteID());
    }

    @Test
    public void testSetAthleteID() throws AthleteIDException {
        athlete.setAthleteID("VN002");
        assertEquals("VN002", athlete.getAthleteID());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("Nguyen", athlete.getFirstName());
    }

    @Test
    public void testSetFirstName() throws NameException {
        athlete.setFirstName("Tran");
        assertEquals("Tran", athlete.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Van Anh", athlete.getLastName());
    }

    @Test
    public void testSetLastName() throws NameException {
        athlete.setLastName("Thi Bich");
        assertEquals("Thi Bich", athlete.getLastName());
    }

    @Test
    public void testGetCountry() {
        assertEquals("Viet Nam", athlete.getCountry());
    }

    @Test
    public void testSetCountry() throws CountryException {
        athlete.setCountry("Vietnam");
        assertEquals("Vietnam", athlete.getCountry());
    }

    @Test
    public void testGetRankingScore() {
        assertEquals(0.0, athlete.getRankingScore());
    }

    @Test
    public void testSetRankingScore() throws RankingScoreException {
        athlete.setRankingScore(100.0);
        assertEquals(100.0, athlete.getRankingScore());
    }

    @Test
    public void testGetWorldRanking() {
        assertEquals(0, athlete.getWorldRanking());
    }

    @Test
    public void testSetWorldRanking() throws WorldRankingException {
        athlete.setWorldRanking(1);
        assertEquals(1, athlete.getWorldRanking());
        assertThrows(WorldRankingException.class, () -> athlete.setWorldRanking(-1));
    }

    @Test
    public void testGetShots() throws ShotException {
        athlete.addShot(9.5);
        athlete.addShot(8.7);
        List<Double> shots = athlete.getShots();
        assertEquals(2, shots.size());
        assertEquals(9.5, shots.get(0));
        assertEquals(8.7, shots.get(1));
    }

    @Test
    public void testSetShots() {
        List<Double> newShots = List.of(9.5, 8.7, 10.0);
        athlete.setShots(newShots);
        List<Double> shots = athlete.getShots();
        assertEquals(3, shots.size());
        assertEquals(9.5, shots.get(0));
        assertEquals(8.7, shots.get(1));
        assertEquals(10.0, shots.get(2));
    }

    @Test
    public void testGetQualificationShots() throws ShotException {
        athlete.addQualificationShot(9.0);
        athlete.addQualificationShot(8.5);
        List<Double> qualificationShots = athlete.getQualificationShots();
        assertEquals(2, qualificationShots.size());
        assertEquals(9.0, qualificationShots.get(0));
        assertEquals(8.5, qualificationShots.get(1));

        List<Double> shots = athlete.getShots();
    }

    @Test
    public void testSetQualificationShots() {
        List<Double> newQualificationShots = List.of(9.0, 8.5, 10.0);
        athlete.setQualificationShots(newQualificationShots);
        List<Double> qualificationShots = athlete.getQualificationShots();
        assertEquals(3, qualificationShots.size());
        assertEquals(9.0, qualificationShots.get(0));
        assertEquals(8.5, qualificationShots.get(1));
        assertEquals(10.0, qualificationShots.get(2));
    }

    @Test
    public void testGetFinalShots() throws ShotException {
        athlete.addFinalShot(9.0);
        athlete.addFinalShot(8.5);
        List<Double> finalShots = athlete.getFinalShots();
        assertEquals(2, finalShots.size());
        assertEquals(9.0, finalShots.get(0));
        assertEquals(8.5, finalShots.get(1));
    }

    @Test
    public void testSetFinalShots() {
        List<Double> newFinalShots = List.of(9.0, 8.5, 10.0);
        athlete.setFinalShots(newFinalShots);
        List<Double> finalShots = athlete.getFinalShots();
        assertEquals(3, finalShots.size());
        assertEquals(9.0, finalShots.get(0));
        assertEquals(8.5, finalShots.get(1));
        assertEquals(10.0, finalShots.get(2));
    }

    @Test
    public void testGetQualificationScore() throws ShotException {
        athlete.addQualificationShot(9.0);
        athlete.addQualificationShot(8.5);
        double qualificationScore = athlete.getQualificationScore();
        assertEquals(17.5, qualificationScore);
    }

    @Test
    public void testGetFinalScore() throws ShotException {
        athlete.addFinalShot(9.0);
        athlete.addFinalShot(8.5);
        double finalScore = athlete.getFinalScore();
        assertEquals(17.5, finalScore);
    }

    @Test
    public void testGetTotalScore() throws ShotException {
        athlete.addShot(9.0);
        athlete.addShot(8.5);
        double totalScore = athlete.getTotalScore();
        assertEquals(17.5, totalScore);
    }

    @Test
    public void testGetFullName() {
        assertEquals("Nguyen Van Anh", athlete.getFullName());
    }

    @Test
    public void testToString() {
        assertEquals("Athlete: Nguyen Van Anh, Country: Viet Nam, Final Score: 0.00", athlete.toString());
    }

    @Test
    public void testAddShot() throws ShotException {
        athlete.addShot(9.5);
        athlete.addShot(8.7);
        List<Double> shots = athlete.getShots();
        assertEquals(2, shots.size());
        assertEquals(9.5, shots.get(0));
        assertEquals(8.7, shots.get(1));
    }

    @Test
    public void testAddQualificationShot() throws ShotException {
        athlete.addQualificationShot(9.0);
        athlete.addQualificationShot(8.5);
        List<Double> qualificationShots = athlete.getQualificationShots();
        assertEquals(2, qualificationShots.size());
        assertEquals(9.0, qualificationShots.get(0));
        assertEquals(8.5, qualificationShots.get(1));
    }

    @Test
    public void testAddFinalShot() throws ShotException {
        athlete.addFinalShot(9.0);
        athlete.addFinalShot(8.5);
        List<Double> finalShots = athlete.getFinalShots();
        assertEquals(2, finalShots.size());
        assertEquals(9.0, finalShots.get(0));
        assertEquals(8.5, finalShots.get(1));
    }

    @Test
    void testSetInvalidRankingScore() {
        assertThrows(RankingScoreException.class, () -> athlete.setRankingScore(-10.0));
    }

    @Test
    void testSetRankingScoreToZero() {
        assertThrows(RankingScoreException.class, () -> athlete.setRankingScore(0.0));
    }

    @Test
    void testSetInvalidWorldRanking() {
        assertThrows(WorldRankingException.class, () -> athlete.setWorldRanking(-5));
    }

    @Test
    void testSetWorldRankingToZero() {
        assertThrows(WorldRankingException.class, () -> athlete.setWorldRanking(0));
    }

    @Test
    void testEmptyQualificationShots() {
        assertEquals(0.0, athlete.getQualificationScore());
    }

    @Test
    void testEmptyFinalShots() {
        assertEquals(0.0, athlete.getFinalScore());
    }

    @Test
    void testInvalidShotValue() {
        assertThrows(ShotException.class, () -> athlete.addQualificationShot(-1.0));
    }

    @Test
    void testInvalidFinalShotValue() {
        assertThrows(ShotException.class, () -> athlete.addFinalShot(-1.0));
    }

    @Test
    void testAddShotWithZeroValue() throws ShotException {
        athlete.addShot(0.0);
        List<Double> shots = athlete.getShots();
        assertEquals(1, shots.size());
        assertEquals(0.0, shots.getFirst());
    }

    @Test
    void testAddQualificationShotWithZeroValue() throws ShotException {
        athlete.addQualificationShot(0.0);
        List<Double> qualificationShots = athlete.getQualificationShots();
        assertEquals(1, qualificationShots.size());
        assertEquals(0.0, qualificationShots.getFirst());
    }

    @Test
    void testAddFinalShotWithZeroValue() throws ShotException {
        athlete.addFinalShot(0.0);
        List<Double> finalShots = athlete.getFinalShots();
        assertEquals(1, finalShots.size());
        assertEquals(0.0, finalShots.getFirst());
    }

    @Test
    void testAddShotWithMaxValue() throws ShotException {
        athlete.addShot(Double.MAX_VALUE);
        List<Double> shots = athlete.getShots();
        assertEquals(1, shots.size());
        assertEquals(Double.MAX_VALUE, shots.getFirst());
    }

    @Test
    void testAddQualificationShotWithMaxValue() throws ShotException {
        athlete.addQualificationShot(Double.MAX_VALUE);
        List<Double> qualificationShots = athlete.getQualificationShots();
        assertEquals(1, qualificationShots.size());
        assertEquals(Double.MAX_VALUE, qualificationShots.getFirst());
    }

    @Test
    void testAddFinalShotWithMaxValue() throws ShotException {
        athlete.addFinalShot(Double.MAX_VALUE);
        List<Double> finalShots = athlete.getFinalShots();
        assertEquals(1, finalShots.size());
        assertEquals(Double.MAX_VALUE, finalShots.getFirst());
    }

    @Test
    void testSetNegativeRankingScore() {
        assertThrows(RankingScoreException.class, () -> athlete.setRankingScore(-1.0));
    }

    @Test
    void testSetNegativeWorldRanking() {
        assertThrows(WorldRankingException.class, () -> athlete.setWorldRanking(-1));
    }

    @Test
    void testAddMoreThan60QualificationShots() throws ShotException {
        for (int i = 0; i < 60; i++) {
            athlete.addQualificationShot(9.0);
        }
        assertThrows(ShotException.class, () -> athlete.addQualificationShot(9.0));
    }

    @Test
    void testGetFullNameWithMiddleName() throws NameException {
        athlete.setFirstName("Nguyen Van");
        athlete.setLastName("Anh");
        assertEquals("Nguyen Van Anh", athlete.getFullName());
    }

    @Test
    void testGetFullNameWithEmptyFirstName() {
        assertThrows(NameException.class, () -> athlete.setFirstName(""));
    }

    @Test
    void testGetFullNameWithEmptyLastName() {
        assertThrows(NameException.class, () -> athlete.setLastName(""));
    }

    @Test
    void testToStringWithEmptyFirstName() {
        assertThrows(NameException.class, () -> athlete.setFirstName(""));
    }

    @Test
    void testToStringWithEmptyLastName() {
        assertThrows(NameException.class, () -> athlete.setLastName(""));
    }

    @Test
    void testToStringWithUpdatedScores() throws ShotException {
        athlete.addQualificationShot(9.0);
        athlete.addFinalShot(8.5);
        assertEquals("Athlete: Nguyen Van Anh, Country: Viet Nam, Final Score: 8.50", athlete.toString());
    }

    @Test
    void testSetCountryToNeutralAthletes() throws CountryException {
        athlete.setCountry("Russia");
        assertEquals("Neutral Athletes", athlete.getCountry());
    }

    @Test
    void testSetCountryToNeutralAthletesBelarus() throws CountryException {
        athlete.setCountry("Belarus");
        assertEquals("Neutral Athletes", athlete.getCountry());
    }

    @Test
    void testSetCountryToNonNeutral() throws CountryException {
        athlete.setCountry("USA");
        assertEquals("USA", athlete.getCountry());
    }

    @Test
    void testSetInvalidFirstName() {
        assertThrows(NameException.class, () -> athlete.setFirstName(null));
    }

    @Test
    void testSetInvalidLastName() {
        assertThrows(NameException.class, () -> athlete.setLastName(null));
    }

    @Test
    void testSetFirstNameToWhitespace() {
        assertThrows(NameException.class, () -> athlete.setFirstName(" "));
    }

    @Test
    void testSetLastNameToWhitespace() {
        assertThrows(NameException.class, () -> athlete.setLastName(" "));
    }

    @Test
    void testSetInvalidCountry() {
        assertThrows(CountryException.class, () -> athlete.setCountry(null));
    }

    @Test
    void testSetCountryToEmptyString() {
        assertThrows(CountryException.class, () -> athlete.setCountry(""));
    }

    @Test
    void testSetCountryToWhitespace() {
        assertThrows(CountryException.class, () -> athlete.setCountry(" "));
    }

    @Test
    void testSetInvalidAthleteID() {
        assertThrows(AthleteIDException.class, () -> athlete.setAthleteID(null));
    }

    @Test
    void testSetInvalidAthleteIDShortLength() {
        assertThrows(AthleteIDException.class, () -> athlete.setAthleteID("VN"));
    }

    @Test
    void testSetInvalidAthleteIDLongLength() {
        assertThrows(AthleteIDException.class, () -> athlete.setAthleteID("VN0011"));
    }


    @Test
    void testAddShotWithNegativeValue() {
        assertThrows(ShotException.class, () -> athlete.addShot(-1.0));
    }

    @Test
    void testAddQualificationShotWithNegativeValue() {
        assertThrows(ShotException.class, () -> athlete.addQualificationShot(-1.0));
    }

    @Test
    void testAddFinalShotWithNegativeValue() {
        assertThrows(ShotException.class, () -> athlete.addFinalShot(-1.0));
    }

    @Test
    void testGetTotalScoreWithMultipleShots() throws ShotException {
        athlete.addShot(9.0);
        athlete.addShot(8.5);
        athlete.addShot(10.0);
        assertEquals(27.5, athlete.getTotalScore());
    }

    @Test
    void testGetQualificationScoreWithMultipleShots() throws ShotException {
        athlete.addQualificationShot(9.0);
        athlete.addQualificationShot(8.5);
        athlete.addQualificationShot(10.0);
        assertEquals(27.5, athlete.getQualificationScore());
    }

    @Test
    void testGetFinalScoreWithMultipleShots() throws ShotException {
        athlete.addFinalShot(9.0);
        athlete.addFinalShot(8.5);
        athlete.addFinalShot(10.0);
        assertEquals(27.5, athlete.getFinalScore());
    }

    @Test
    void testGetTotalScoreWithZeroShots() {
        assertEquals(0.0, athlete.getTotalScore());
    }

    @Test
    void testGetQualificationScoreWithZeroShots() {
        assertEquals(0.0, athlete.getQualificationScore());
    }

    @Test
    void testGetFinalScoreWithZeroShots() {
        assertEquals(0.0, athlete.getFinalScore());
    }

    @Test
    void testGetTotalScoreWithNegativeShots() {
        assertThrows(ShotException.class, () -> athlete.addShot(-1.0));
    }

    @Test
    void testGetQualificationScoreWithNegativeShots() {
        assertThrows(ShotException.class, () -> athlete.addQualificationShot(-1.0));
    }

    @Test
    void testGetFinalScoreWithNegativeShots() {
        assertThrows(ShotException.class, () -> athlete.addFinalShot(-1.0));
    }

    @Test
    void testGetTotalScoreWithMaxShots() throws ShotException {
        athlete.addShot(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, athlete.getTotalScore());
    }

    @Test
    void testGetQualificationScoreWithMaxShots() throws ShotException {
        athlete.addQualificationShot(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, athlete.getQualificationScore());
    }

    @Test
    void testGetFinalScoreWithMaxShots() throws ShotException {
        athlete.addFinalShot(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, athlete.getFinalScore());
    }

    @Test
    void testGetTotalScoreWithMinShots() throws ShotException {
        athlete.addShot(Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE, athlete.getTotalScore());
    }

    @Test
    void testGetQualificationScoreWithMinShots() throws ShotException {
        athlete.addQualificationShot(Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE, athlete.getQualificationScore());
    }

    @Test
    void testGetFinalScoreWithMinShots() throws ShotException {
        athlete.addFinalShot(Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE, athlete.getFinalScore());
    }

    @Test
    void testGetTotalScoreWithMultipleMaxShots() throws ShotException {
        athlete.addShot(Double.MAX_VALUE);
        athlete.addShot(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE * 2, athlete.getTotalScore());
    }

    @Test
    void testGetQualificationScoreWithMultipleMaxShots() throws ShotException {
        athlete.addQualificationShot(Double.MAX_VALUE);
        athlete.addQualificationShot(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE * 2, athlete.getQualificationScore());
    }

    @Test
    void testGetFinalScoreWithMultipleMaxShots() throws ShotException {
        athlete.addFinalShot(Double.MAX_VALUE);
        athlete.addFinalShot(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE * 2, athlete.getFinalScore());
    }

    @Test
    void testGetTotalScoreWithMultipleMinShots() throws ShotException {
        athlete.addShot(Double.MIN_VALUE);
        athlete.addShot(Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE * 2, athlete.getTotalScore());
    }

    @Test
    void testGetQualificationScoreWithMultipleMinShots() throws ShotException {
        athlete.addQualificationShot(Double.MIN_VALUE);
        athlete.addQualificationShot(Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE * 2, athlete.getQualificationScore());
    }

    @Test
    void testGetFinalScoreWithMultipleMinShots() throws ShotException {
        athlete.addFinalShot(Double.MIN_VALUE);
        athlete.addFinalShot(Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE * 2, athlete.getFinalScore());
    }

    @Test
    void testGetTotalScoreWithMultipleMixedShots() throws ShotException {
        athlete.addShot(Double.MAX_VALUE);
        athlete.addShot(Double.MIN_VALUE);
        assertEquals(Double.MAX_VALUE + Double.MIN_VALUE, athlete.getTotalScore());
    }

    @Test
    void testGetQualificationScoreWithMultipleMixedShots() throws ShotException {
        athlete.addQualificationShot(Double.MAX_VALUE);
        athlete.addQualificationShot(Double.MIN_VALUE);
        assertEquals(Double.MAX_VALUE + Double.MIN_VALUE, athlete.getQualificationScore());
    }

    @Test
    void testGetFinalScoreWithMultipleMixedShots() throws ShotException {
        athlete.addFinalShot(Double.MAX_VALUE);
        athlete.addFinalShot(Double.MIN_VALUE);
        assertEquals(Double.MAX_VALUE + Double.MIN_VALUE, athlete.getFinalScore());
    }

    @Test
    void testGetTotalScoreWithMultipleMixedShotsAndZero() throws ShotException {
        athlete.addShot(Double.MAX_VALUE);
        athlete.addShot(0.0);
        assertEquals(Double.MAX_VALUE, athlete.getTotalScore());
    }

    @Test
    void testGetQualificationScoreWithMultipleMixedShotsAndZero() throws ShotException {
        athlete.addQualificationShot(Double.MAX_VALUE);
        athlete.addQualificationShot(0.0);
        assertEquals(Double.MAX_VALUE, athlete.getQualificationScore());
    }

    @Test
    void testGetFinalScoreWithMultipleMixedShotsAndZero() throws ShotException {
        athlete.addFinalShot(Double.MAX_VALUE);
        athlete.addFinalShot(0.0);
        assertEquals(Double.MAX_VALUE, athlete.getFinalScore());
    }

    @Test
    void testGetTotalScoreWithMultipleMixedShotsAndNegative() throws ShotException {
        athlete.addShot(Double.MAX_VALUE);
        assertThrows(ShotException.class, () -> athlete.addShot(-1.0));
    }

    @Test
    void testGetQualificationScoreWithMultipleMixedShotsAndNegative() throws ShotException {
        athlete.addQualificationShot(Double.MAX_VALUE);
        assertThrows(ShotException.class, () -> athlete.addQualificationShot(-1.0));
    }

    @Test
    void testGetFinalScoreWithMultipleMixedShotsAndNegative() throws ShotException {
        athlete.addFinalShot(Double.MAX_VALUE);
        assertThrows(ShotException.class, () -> athlete.addFinalShot(-1.0));
    }

    @Test
    void testAddExcessiveQualificationShots() throws ShotException {
        Athlete athlete = new Athlete(
                "VN001",
                "Nguyen",
                "Van Anh",
                "Viet Nam",
                0.0,
                0,
                null,
                null,
                null
        );
        for (int i = 0; i < 60; i++) {
            athlete.addQualificationShot(5.0);
        }
        Exception exception = assertThrows(ShotException.class, () -> {
            athlete.addQualificationShot(5.0);
        });
        assertEquals("Cannot add more than 60 shots", exception.getMessage());
    }

    @Test
    void testGetQualificationScoreWithEdgeValues() throws ShotException {
        Athlete athlete = new Athlete(
                "VN001",
                "Nguyen",
                "Van Anh",
                "Viet Nam",
                0.0,
                0,
                null,
                null,
                null
        );
        athlete.addQualificationShot(10.0);
        athlete.addQualificationShot(0.0);
        athlete.addQualificationShot(5.5);
        double expected = 15.5;
        assertEquals(expected, athlete.getQualificationScore(), "Qualification score calculation mismatch.");
    }
}
