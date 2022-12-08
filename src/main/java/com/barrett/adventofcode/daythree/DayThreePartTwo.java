package com.barrett.adventofcode.daythree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.util.*;

@SpringBootApplication
public class DayThreePartTwo {

    @Autowired
    ResourceLoader resourceLoader;

    public static void main(String[] args) {
        SpringApplication.run(DayThreePartTwo.class, args);
    }

    @Bean
    public void dayThreeSolutionPartTwo() {
        Resource resource = resourceLoader.getResource("classpath:day/three/input2.txt");

        int totalPriority = 0;

        try {
            File file = resource.getFile();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                // Assuming multiple of three for input
                char badge = findItemInEach(scanner.nextLine(), scanner.nextLine(), scanner.nextLine());
                totalPriority += calculatePriority(badge);
            }

            System.out.println("D3P2 Solution: " + totalPriority);
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private char findItemInEach(String... rucksacks) {

        int rucksackCount = 0;
        Set<Character> chars = new HashSet<>();

        for (String rucksack : rucksacks) {
            if (rucksackCount == 0) {
                for (int i = 0; i < rucksack.length(); i++) {
                    chars.add(rucksack.charAt(i));
                }
            } else {
                Set<Character> matchingChars = new HashSet<>();

                for (int j = 0; j < rucksack.length(); j++) {
                    char c = rucksack.charAt(j);
                    if (chars.contains(c)) {
                        matchingChars.add(c);
                    }
                }
                chars = matchingChars;
            }
            rucksackCount++;
        }
        return chars.stream().findFirst().orElse('a');
    }

    private int calculatePriority(char c) {
        if ('a' <= c && c <= 'z') {
            return c - 96;
        } else if ('A' <= c && c <= 'Z') {
            return c - 38;
        }
        return 0;
    }
}