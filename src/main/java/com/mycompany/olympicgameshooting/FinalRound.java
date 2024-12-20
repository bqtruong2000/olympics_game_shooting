package com.mycompany.olympicgameshooting;

import com.mycompany.olympicgameshooting.exceptions.athlete.ShotException;
import com.mycompany.olympicgameshooting.exceptions.finalround.FinalRoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chun
 */
public class FinalRound {
    private final List<Athlete> finalists;

    public FinalRound(List<Athlete> athletes) throws FinalRoundException {
        if (athletes == null || athletes.isEmpty()) {
            throw new FinalRoundException("Athlete list cannot be null or empty");
        }
        this.finalists = new ArrayList<>(athletes);
    }

    public void simulateFinalRound() throws ShotException {
        int roundNumber = 1;
        while (finalists.size() > 2) {
            simulateRound(roundNumber);
            eliminateLowestScorer();
            roundNumber++;
        }
        // Simulate the final round for the last two athletes
        simulateRound(roundNumber);
    }

    private void simulateRound(int roundNumber) throws ShotException {
        for (Athlete athlete : finalists) {
            double totalScore = shootAndCalculateScore(athlete, roundNumber);
        }
    }

    double shootAndCalculateScore(Athlete athlete, int roundNumber) throws ShotException {
        double totalScore = 0.0;
        int shots = (roundNumber == 1) ? 10 : 2;
        for (int i = 0; i < shots; i++) {
            double[] shot = Target.generateShot();
            double score = Target.calculateScore(shot[0], shot[1]);
            athlete.addShot(score);
            athlete.addFinalShot(score);
            totalScore += score;
        }
        return totalScore;
    }

    void eliminateLowestScorer() {
        Athlete lowestScorer = finalists.stream()
                .min(Comparator.comparing(Athlete::getTotalScore))
                .orElseThrow(() -> new IllegalStateException("No athletes to eliminate"));
        finalists.remove(lowestScorer);
    }

    public List<Athlete> getFinalists() {
        return finalists.stream()
                .sorted(Comparator.comparing(Athlete::getFinalScore).reversed())
                .collect(Collectors.toList());
    }

}
