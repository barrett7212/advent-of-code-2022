package com.barrett.adventofcode.dayfour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.util.Scanner;

@SpringBootApplication
public class DayFourPartTwo {

    @Autowired
    ResourceLoader resourceLoader;

    public static void main(String[] args) {
        SpringApplication.run(DayFourPartTwo.class, args);
    }

    @Bean
    public void dayFourSolutionPartTwo() {
        Resource resource = resourceLoader.getResource("classpath:day/4/input.txt");

        try {
            File file = resource.getFile();
            Scanner scanner = new Scanner(file);

            int totalOverlappedPairs = 0;

            while (scanner.hasNextLine()) {
                String pair = scanner.nextLine();

                // Assume 2 elves
                String[] elves = pair.split(",", 2);

                // Find assignment range for the first elf
                String[] firstElf = elves[0].split("-", 2);
                int firstElfMin = Integer.parseInt(firstElf[0]);
                int firstElfMax = Integer.parseInt(firstElf[1]);

                // Find assignment range for the second elf
                String[] secondElf = elves[1].split("-", 2);
                int secondElfMin = Integer.parseInt(secondElf[0]);
                int secondElfMax = Integer.parseInt(secondElf[1]);


                if ((firstElfMin <= secondElfMin && firstElfMax >= secondElfMax) || // First Elf Range is bigger(or equal)
                        (secondElfMin <= firstElfMin && secondElfMax >= firstElfMax) || // Second elf Range is bigger(or equal)
                        (firstElfMin < secondElfMin && firstElfMax >= secondElfMin) || // First Elf Range is first
                        (firstElfMax > secondElfMax && firstElfMin <= secondElfMax)) { // Second Elf Range is second
                    totalOverlappedPairs++;
                }
            }
            System.out.println("D4P2 Solution: " + totalOverlappedPairs);
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}