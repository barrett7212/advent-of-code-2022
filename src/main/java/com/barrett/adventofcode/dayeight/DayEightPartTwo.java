package com.barrett.adventofcode.dayeight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DayEightPartTwo {

    @Autowired
    ResourceLoader resourceLoader;

    public static void main(String[] args) {
        SpringApplication.run(DayEightPartTwo.class, args);
    }

    @Bean
    public void dayEightSolutionPartTwo() {
        Resource resource = resourceLoader.getResource("classpath:day/8/input.txt");

        try {
            File file = resource.getFile();
            Scanner scanner = new Scanner(file);

            List<List<Integer>> trees = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                List<Integer> row = new ArrayList<>();

                for (char c : line.toCharArray()) {
                    row.add((Integer.parseInt(String.valueOf(c))));
                }
                trees.add(row);
            }

            int width = trees.size();
            int height = trees.get(0).size();

            int maxScore = 0;

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (!(i == 0 || j == 0 || i == (width - 1) || j == (height - 1))) {
                        int treeHeight = trees.get(i).get(j);

                        // Check left
                        int leftScore = 0;

                        for (int k = j - 1; k >= 0; k--) {
                            leftScore++;
                            if (trees.get(i).get(k) >= treeHeight) {
                                break;
                            }
                        }

                        // Check right
                        int rightScore = 0;

                        for (int k = j + 1; k < width; k++) {
                            rightScore++;
                            if (trees.get(i).get(k) >= treeHeight) {
                                break;
                            }
                        }

                        // Check up
                        int upScore = 0;

                        for (int k = i - 1; k >= 0; k--) {
                            upScore++;
                            if (trees.get(k).get(j) >= treeHeight) {
                                break;
                            }
                        }


                        int downScore = 0;
                        // Check down
                        for (int k = i + 1; k < height; k++) {
                            downScore++;
                            if (trees.get(k).get(j) >= treeHeight) {
                                break;
                            }
                        }

                        int scenicScore = leftScore * rightScore * upScore * downScore;

                        if (scenicScore > maxScore) {
                            maxScore = scenicScore;
                        }
                    }
                }
            }
            System.out.println("D8P2 Sol:" + maxScore);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}