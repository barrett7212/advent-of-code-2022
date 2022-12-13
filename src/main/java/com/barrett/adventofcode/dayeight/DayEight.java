package com.barrett.adventofcode.dayeight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.util.*;

@SpringBootApplication
public class DayEight {

    @Autowired
    ResourceLoader resourceLoader;

    public static void main(String[] args) {
        SpringApplication.run(DayEight.class, args);
    }

    @Bean
    public void dayEightSolution() {
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

            int visibleCount = 0;

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (i == 0 || j == 0 || i == (width - 1) || j == (height - 1)) {
                        visibleCount++;
                    } else {
                        int treeHeight = trees.get(i).get(j);

                        // Check left
                        boolean leftVisible = true;

                        for (int k = j - 1; k >= 0; k--) {
                            if (trees.get(i).get(k) >= treeHeight) {
                                leftVisible = false;
                                break;
                            }
                        }

                        boolean rightVisible = true;
                        // Check right
                        for (int k = j + 1; k < width; k++) {
                            if (trees.get(i).get(k) >= treeHeight) {
                                rightVisible = false;
                                break;
                            }
                        }


                        boolean upVisible = true;
                        // Check up
                        for (int k = i - 1; k >= 0; k--) {
                            if (trees.get(k).get(j) >= treeHeight) {
                                upVisible = false;
                                break;
                            }
                        }


                        boolean downVisible = true;
                        // Check down
                        for (int k = i + 1; k < height; k++) {
                            if (trees.get(k).get(j) >= treeHeight) {
                                downVisible = false;
                                break;
                            }
                        }


                        if (leftVisible || rightVisible || upVisible || downVisible) {
                            visibleCount++;
                        }
                    }
                }
            }
            System.out.println("D8P1 Sol:" + visibleCount);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}