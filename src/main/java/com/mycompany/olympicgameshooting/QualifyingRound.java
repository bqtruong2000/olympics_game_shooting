package com.mycompany.olympicgameshooting;

import com.mycompany.olympicgameshooting.exceptions.athlete.ShotException;

import java.util.List;

/**
 * @author Chun
 */
public class QualifyingRound {
    private final List<Athlete> athletes;

    public QualifyingRound(List<Athlete> athletes) {
        this.athletes = sortAthletesByWorldRanking(athletes);
    }

    public void simulateShots() throws ShotException {
        for (Athlete athlete : athletes) {
            for (int i = 0; i < 60; i++) {
                double[] shot = Target.generateShot();
                double score = Target.calculateScore(shot[0], shot[1]);
                athlete.addShot(score);
                athlete.addQualificationShot(score);
            }
        }
    }

    public List<Athlete> getTop8Athletes() {
        return athletes.stream()
                .sorted((a1, a2) -> Double.compare(a2.getQualificationScore(), a1.getQualificationScore()))
                .limit(8)
                .toList();
    }

    private List<Athlete> sortAthletesByWorldRanking(List<Athlete> athletes) {
        return athletes.stream()
                .sorted((a1, a2) -> Integer.compare(a1.getWorldRanking(), a2.getWorldRanking()))
                .toList();
    }

}
