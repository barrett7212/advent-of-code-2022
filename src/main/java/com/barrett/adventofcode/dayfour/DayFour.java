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
public class DayFour {

    @Autowired
    ResourceLoader resourceLoader;

    public static void main(String[] args) {
        SpringApplication.run(DayFour.class, args);
    }

    @Bean
    public void dayFourSolution() {
        Resource resource = resourceLoader.getResource("classpath:day/4/input.txt");

        try {
            File file = resource.getFile();
            Scanner scanner = new Scanner(file);

            int fullyOverlappedPairs = 0;

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

                // First Elf Range is bigger(or equal), or if Second elf Range is bigger(or equal)
                if ((firstElfMin <= secondElfMin && firstElfMax >= secondElfMax) || (secondElfMin <= firstElfMin && secondElfMax >= firstElfMax)) {
                    fullyOverlappedPairs++;
                }
            }
            System.out.println("D4P1 Solution: " + fullyOverlappedPairs);
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}