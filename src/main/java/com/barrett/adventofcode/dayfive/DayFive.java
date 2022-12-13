package com.barrett.adventofcode.dayfive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.util.*;

@SpringBootApplication
public class DayFive {

    @Autowired
    ResourceLoader resourceLoader;

    public static void main(String[] args) {
        SpringApplication.run(DayFive.class, args);
    }

    @Bean
    public void dayFiveSolution() {
        Resource resource = resourceLoader.getResource("classpath:day/5/input.txt");

        try {
            File file = resource.getFile();
            Scanner scanner = new Scanner(file);

            Map<Integer, Stack<Character>> stacks = new HashMap<>();

            // Process Stacks

            String line = scanner.nextLine();

            List<String> stackRows = new ArrayList<>();

            // Load the stackRows into a list
            while (scanner.hasNextLine() && line.startsWith("[")) {
                stackRows.add(line);
                line = scanner.nextLine();
            }

            // Convert the stackRows into a Stack for each Column
            for (int i = stackRows.size() - 1; i >= 0; i--) {
                String row = stackRows.get(i);
                int numStacks = (row.length() + 1) / 4;

                // For each row
                for (int j = 0; j < numStacks; j++) {

                    // Find the character in the brackets
                    char c = row.charAt(((j + 1) * 4) - 3);

                    // If it's a real character, add it to the appropriate stack
                    if (c >= 'A' && c <= 'Z') {
                        Stack<Character> stack = stacks.getOrDefault(j, new Stack<>());
                        stack.push(c);
                        stacks.put(j, stack);
                    }
                }
            }

            // Skip next two lines
            scanner.nextLine();

            // Execute moves
            while (scanner.hasNextLine()) {
                String move = scanner.nextLine();
                String[] moves = move.split(" ");

                // Subtract 1 since stacks are 1-indexed in input data
                int sourceStackIndex = Integer.parseInt(moves[3]) - 1;
                int destStackIndex = Integer.parseInt(moves[5]) - 1;

                int count = Integer.parseInt(moves[1]);

                for (int i = 0; i < count; i++) {
                    stacks.get(destStackIndex).push(stacks.get(sourceStackIndex).pop());
                }
            }

            // Get final message
            StringBuilder message = new StringBuilder();
            for (Stack<Character> stack : stacks.values()) {
                message.append(stack.peek());
            }

            System.out.println("D5P1 Solution: " + message);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}