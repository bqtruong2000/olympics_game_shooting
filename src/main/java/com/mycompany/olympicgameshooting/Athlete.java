package com.mycompany.olympicgameshooting;

import com.mycompany.olympicgameshooting.exceptions.athlete.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chun
 */
public class Athlete {
    private String athleteID;
    private String firstName;
    private String lastName;
    private String country;
    private double rankingScore;
    private int worldRanking;
    private List<Double> shots;
    private List<Double> qualificationShots;
    private List<Double> finalShots;
    private double qualificationScore;
    private double finalScore;
    private double totalScore;

    public Athlete(String athleteID, String firstName, String lastName, String country, double rankingScore,
                   int worldRanking, List<Double> shots, List<Double> qualificationShots, List<Double> finalShots) {
        this.athleteID = athleteID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country.equalsIgnoreCase("Russia") || country.equalsIgnoreCase("Belarus")
                ? "Neutral Athletes" : country;
        this.rankingScore = rankingScore;
        this.worldRanking = worldRanking;
        this.shots = new ArrayList<>();
        this.qualificationShots = new ArrayList<>();
        this.finalShots = new ArrayList<>();
        this.qualificationScore = 0.0;
        this.finalScore = 0.0;
        this.totalScore = 0.0;
    }

    // getters
    public String getAthleteID() {
        return athleteID;
    }

    // setters
    public void setAthleteID(String athleteID) throws AthleteIDException {
        if (athleteID == null) {
            throw new AthleteIDException("Athlete ID cannot be null");
        }
        if (athleteID.isBlank()) {
            throw new AthleteIDException("Athlete ID cannot be blank");
        }
        if (athleteID.length() != 5) {
            throw new AthleteIDException("Athlete ID must be exactly 5 characters long");
        }
        this.athleteID = athleteID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws NameException {
        if (firstName == null || firstName.isBlank()) {
            throw new NameException("First name cannot be null or blank");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws NameException {
        if (lastName == null || lastName.isBlank()) {
            throw new NameException("Last name cannot be null or blank");
        }
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) throws CountryException {
        if (country == null || country.isBlank()) {
            throw new CountryException("Country cannot be null or blank");
        }
        this.country = country.equalsIgnoreCase("Russia") || country.equalsIgnoreCase("Belarus")
                ? "Neutral Athletes" : country;
    }

    public double getRankingScore() {
        return rankingScore;
    }

    public void setRankingScore(double rankingScore) throws RankingScoreException {
        if (rankingScore < 0) {
            throw new RankingScoreException("Ranking score cannot be negative");
        }
        if (rankingScore == 0) {
            throw new RankingScoreException("Ranking score cannot be zero");
        }
        this.rankingScore = rankingScore;
    }

    public int getWorldRanking() {
        return worldRanking;
    }

    public void setWorldRanking(int worldRanking) throws WorldRankingException {
        if (worldRanking < 0) {
            throw new WorldRankingException("World ranking cannot be negative");
        }
        if (worldRanking == 0) {
            throw new WorldRankingException("World ranking cannot be zero");
        }
        this.worldRanking = worldRanking;
    }

    public List<Double> getShots() {
        return shots;
    }

    public void setShots(List<Double> shots) {
        this.shots = shots;
    }

    public List<Double> getQualificationShots() {
        return qualificationShots;
    }

    public void setQualificationShots(List<Double> qualificationShots) {
        this.qualificationShots = qualificationShots;
    }

    public List<Double> getFinalShots() {
        return finalShots;
    }

    public void setFinalShots(List<Double> finalShots) {
        this.finalShots = finalShots;
    }

    public double getQualificationScore() {
        return qualificationShots.stream().mapToDouble(Double::doubleValue).sum();
    }

    public void setQualificationScore(double qualificationScore) {
        this.qualificationScore = qualificationScore;
    }

    public double getFinalScore() {
        return finalShots.stream().mapToDouble(Double::doubleValue).sum();
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

    public double getTotalScore() {
        return shots.stream().mapToDouble(Double::doubleValue).sum();
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return String.format("Athlete: %s, Country: %s, Final Score: %.2f", getFullName(), getCountry(), getFinalScore());
    }

    public void addShot(double score) throws ShotException {
        if (score < 0) {
            throw new ShotException("Shot value cannot be negative");
        }
        shots.add(score);
    }

    public void addQualificationShot(double score) throws ShotException {
        if (score < 0) {
            throw new ShotException("Shot value cannot be negative");
        }
        if (qualificationShots.size() >= 60) {
            throw new ShotException("Cannot add more than 60 shots");
        }
        qualificationShots.add(score);
    }

    public void addFinalShot(double score) throws ShotException {
        if (score < 0) {
            throw new ShotException("Shot value cannot be negative");
        }
        finalShots.add(score);
    }
}
