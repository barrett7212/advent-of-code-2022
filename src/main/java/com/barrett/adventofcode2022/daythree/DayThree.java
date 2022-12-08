package com.barrett.adventofcode2022.daythree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
public class DayThree {

    @Autowired
    ResourceLoader resourceLoader;

    public static void main(String[] args) {
        SpringApplication.run(DayThree.class, args);
    }

    @Bean
    public void dayThreeSolution() {
        Resource resource = resourceLoader.getResource("classpath:day/three/input.txt");

        int totalPriority = 0;

        try {
            File file = resource.getFile();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String compartment1 = line.substring(0, line.length() / 2);
                String compartment2 = line.substring(line.length() / 2);
                char both = findItemInBoth(compartment1, compartment2);
                totalPriority += calculatePriority(both);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("D3P1 Solution: " + totalPriority);
    }

    private char findItemInBoth(String compartment1, String compartment2) {
        Set<Character> chars = new HashSet<>();

        for (char c : compartment1.toCharArray()) {
            chars.add(c);
        }

        for (char c : compartment2.toCharArray()) {
            if (chars.contains(c)) {
                return c;
            }
        }
        return 'a';
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