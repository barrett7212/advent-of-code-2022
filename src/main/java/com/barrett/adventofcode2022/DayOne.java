package com.barrett.adventofcode2022;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DayOne {

    @Autowired
    ResourceLoader resourceLoader;

    public static void main(String[] args) {
        SpringApplication.run(DayOne.class, args);
    }

    @Bean
    public void dayOneSolution() {
        Resource resource = resourceLoader.getResource("classpath:day/one/input.txt");

        try {
            File file = resource.getFile();
            Scanner scanner = new Scanner(file);

            List<Integer> calorieTotalsByElf = new ArrayList<>();
            int curElfCalories = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Empty line delim between elf inventories
                if (line.equals("")) {
                    calorieTotalsByElf.add(curElfCalories);
                    curElfCalories = 0;
                } else {
                    curElfCalories += Integer.parseInt(line);
                }
            }
            // Sort all calorie counts, descending
            List<Integer> result = calorieTotalsByElf.stream().sorted((e1, e2) -> e2 - e1).limit(3).toList();
            System.out.println("D1P1 Solution: " + result.get(0));
            System.out.println("D1P2 Solution: " + result.stream().reduce(Integer::sum));
            scanner.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}