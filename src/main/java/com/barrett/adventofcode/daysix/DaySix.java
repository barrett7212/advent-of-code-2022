package com.barrett.adventofcode.daysix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.util.*;

@SpringBootApplication
public class DaySix {

    @Autowired
    ResourceLoader resourceLoader;

    public static void main(String[] args) {
        SpringApplication.run(DaySix.class, args);
    }

    @Bean
    public void daySixSolution() {
        Resource resource = resourceLoader.getResource("classpath:day/6/input.txt");

        try {
            File file = resource.getFile();
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("");

            int markerSize = 4; // Set markerSize to 14 for part2
            Queue<Character> marker = new LinkedList<>();

            int markerIndex = 0;

            while(scanner.hasNext()){
                char c  = scanner.next().charAt(0);

                marker.add(c);
                markerIndex++;
                if(marker.size() == markerSize){
                    if(marker.stream().distinct().count() == markerSize) {
                        break;
                    }
                    marker.remove();
                }
            }

            System.out.println("D6P1 Solution: " + markerIndex);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}