/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.olympicgameshooting;

import com.mycompany.olympicgameshooting.exceptions.athlete.ShotException;
import com.mycompany.olympicgameshooting.exceptions.finalround.FinalRoundException;

import java.util.*;

/**
 * @author Chun
 */
public class OlympicGameShooting {

    private static final String[] FIRST_NAMES = {
            "Emma", "Liam", "Olivia", "Noah", "Sophia", "Ethan", "Isabella", "Mason",
            "Mia", "William", "Ava", "James", "Emily", "Benjamin", "Madison", "Jacob",
            "Abigail", "Michael", "Elizabeth", "Alexander", "Sofia", "Daniel", "Avery",
            "Aiden", "Ella", "Anthony", "Harper", "Joseph", "Scarlett", "Jackson"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia",
            "Rodriguez", "Wilson", "Martinez", "Anderson", "Taylor", "Thomas", "Hernandez",
            "Moore", "Martin", "Jackson", "Thompson", "White", "Lopez", "Lee", "Harris",
            "Clark", "Lewis", "Robinson", "Walker", "Perez", "Hall", "Young"
    };

    private static final String[] COUNTRIES = {
            "United States", "China", "Japan", "Germany", "United Kingdom", "France",
            "Italy", "Canada", "South Korea", "Australia", "Brazil", "Russia", "Netherlands",
            "Spain", "Kenya", "Jamaica", "New Zealand", "Ethiopia", "Cuba", "Ukraine"
    };

    private static final Random random = new Random();

    public static void main(String[] args) throws ShotException, FinalRoundException {
        List<Athlete> athletes = generateAthletes(54);

        System.out.println("All athletes:");

        for (Athlete athlete : athletes) {
            System.out.println(athlete);
        }

        System.out.println("\n====================================================================");

        QualifyingRound qualifyingRound = new QualifyingRound(athletes);

        qualifyingRound.simulateShots();

        List<Athlete> top8Athletes = qualifyingRound.getTop8Athletes();

        System.out.println("\nTop 8 athletes:");

        for (Athlete athlete : top8Athletes) {
            System.out.println(athlete);
        }

        System.out.println("\n====================================================================");

        System.out.println("\nFinal round:");

        FinalRound finalRound = new FinalRound(top8Athletes);

        finalRound.simulateFinalRound();

        List<Athlete> finalists = finalRound.getFinalists();

        finalists.forEach(System.out::println);

        System.out.println("\n====================================================================");

        System.out.println("The winner is: " + finalists.getFirst());
    }

    public static List<Athlete> generateAthletes(int numberOfAthletes) {
        List<Athlete> athletes = new ArrayList<>();
        Set<String> usedAthleteIDs = new HashSet<>();

        for (int i = 0; i < numberOfAthletes; i++) {
            String athleteID = generateUniqueAthleteID(usedAthleteIDs);
            String firstName = getRandomElement(FIRST_NAMES);
            String lastName = getRandomElement(LAST_NAMES);
            String country = getRandomElement(COUNTRIES);

            // More realistic scoring system
            double initialScore = generateRealisticScore();
            int ranking = generateRealisticRanking();

            List<Double> qualificationScores = generateScoreList(3); // 3 qualification scores
            List<Double> finalScores = generateScoreList(2); // 2 final scores
            List<Double> medalScores = generateScoreList(1); // 1 medal score

            Athlete athlete = new Athlete(
                    athleteID,
                    firstName,
                    lastName,
                    country,
                    initialScore,
                    ranking,
                    qualificationScores,
                    finalScores,
                    medalScores
            );
            athletes.add(athlete);
        }
        return athletes;
    }

    private static String generateUniqueAthleteID(Set<String> usedIDs) {
        String id;
        do {
            id = "A" + (random.nextInt(9000) + 1000); // 4-digit ID
        } while (usedIDs.contains(id));
        usedIDs.add(id);
        return id;
    }

    private static double generateRealisticScore() {
        // Generates a score between 6.5 and 9.5
        return 6.5 + (random.nextDouble() * 3.0);
    }

    private static int generateRealisticRanking() {
        // Generates a ranking between 1 and 200
        return random.nextInt(200) + 1;
    }

    private static List<Double> generateScoreList(int count) {
        List<Double> scores = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            // Generates scores between 7.0 and 9.8
            scores.add(7.0 + (random.nextDouble() * 2.8));
        }
        return scores;
    }

    private static <T> T getRandomElement(T[] array) {
        return array[random.nextInt(array.length)];
    }
}
