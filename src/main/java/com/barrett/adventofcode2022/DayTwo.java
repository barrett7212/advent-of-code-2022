package com.barrett.adventofcode2022;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Scanner;

@SpringBootApplication
public class DayTwo {

    @Autowired
    ResourceLoader resourceLoader;

    public static void main(String[] args) {
        SpringApplication.run(DayTwo.class, args);
    }

    @Bean
    public void dayTwoSolution() {
        Resource resource = resourceLoader.getResource("classpath:day/two/input.txt");

        int firstScore = 0;
        int secondScore = 0;

        try {
            File file = resource.getFile();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] choices = StringUtils.split(line, " ");

                firstScore += calculateFirstScore(choices[0], choices[1]);
                secondScore += calculateSecondScore(choices[0], choices[1]);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("D2P1 Solution: " + firstScore);
        System.out.println("D2P2 Solution: " + secondScore);
    }

    private int calculateFirstScore(String firstChoice, String secondChoice) {
        if (firstChoice.equals("A")) {
            switch (secondChoice) {
                case "X":
                    return 4; // Rock/Rock - 1 point for rock, 3 for draw
                case "Y":
                    return 8; // Rock/Paper - 2 for paper, 6 for win
                case "Z":
                    return 3; // Rock/Scissors - 3 for scissors, 0 for loss
            }
        } else if (firstChoice.equals("B")) {
            switch (secondChoice) {
                case "X":
                    return 1; // Paper/Rock - 1 for rock, 0 for loss
                case "Y":
                    return 5; // Paper/Paper - 2 for paper, 3 for draw
                case "Z":
                    return 9; // Paper/Scissors - 3 for scissors, 6 for win
            }
        } else if (firstChoice.equals("C")) {
            switch (secondChoice) {
                case "X":
                    return 7; // Scissors/Rock - 1 for rock, 6 for win
                case "Y":
                    return 2; // Scissors/Paper - 2 for paper, 0 for loss
                case "Z":
                    return 6; // Scissors/Scissors - 3 for scissors, 3 for draw
            }
        }
        return 0;
    }

    private int calculateSecondScore(String firstChoice, String secondChoice) {
        if (firstChoice.equals("A")) {
            switch (secondChoice) {
                case "X":
                    return 3; // Rock/Scissors - 3 for scissors, 0 for loss
                case "Y":
                    return 4; // Rock/Rock - 1 point for rock, 3 for draw
                case "Z":
                    return 8; // Rock/Paper - 2 for paper, 6 for win
            }
        } else if (firstChoice.equals("B")) {
            switch (secondChoice) {
                case "X":
                    return 1; // Paper/Rock - 1 for rock, 0 for loss
                case "Y":
                    return 5; // Paper/Paper - 2 for paper, 3 for draw
                case "Z":
                    return 9; // Paper/Scissors - 3 for scissors, 6 for win
            }
        } else if (firstChoice.equals("C")) {
            switch (secondChoice) {
                case "X":
                    return 2; // Scissors/Paper - 2 for paper, 0 for loss
                case "Y":
                    return 6; // Scissors/Scissors - 3 for scissors, 3 for draw
                case "Z":
                    return 7; // Scissors/Rock - 1 for rock, 6 for win
            }
        }
        return 0;
    }
}