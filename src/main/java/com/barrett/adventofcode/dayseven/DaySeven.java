package com.barrett.adventofcode.dayseven;

import com.sun.source.tree.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.util.*;

@SpringBootApplication
public class DaySeven {

    @Autowired
    ResourceLoader resourceLoader;

    public static void main(String[] args) {
        SpringApplication.run(DaySeven.class, args);
    }

    @Bean
    public void daySevenSolution() {
        Resource resource = resourceLoader.getResource("classpath:day/7/input.txt");

        try {
            File file = resource.getFile();
            Scanner scanner = new Scanner(file);

            TreeNode<String> root = new TreeNode<>("/");
            TreeNode<String> cwd = root;

            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if (line.startsWith("$")) {
                    String[] args = line.split(" ", 3);
                    String command = args[1];

                    if (command.equals("cd")){
                        String dir = args[2];
                        if(dir.equals("..")){
                            cwd = cwd.parent;
                        } else {
                            cwd = cwd.addChild(dir);
                        }

                    } else if (command.equals("ls")) {

                    }
                }
            }

            System.out.println("D7P1 Solution: ");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static class TreeNode<T> {
        private T value;
        private TreeNode<T> parent;
        private List<TreeNode<T>> children;

        public TreeNode(T value) {
            this.value = value;
            this.children = new LinkedList<TreeNode<T>>();
        }

        public TreeNode<T> addChild(T child) {
            TreeNode<T> childNode = new TreeNode<T>(child);
            childNode.parent = this;
            this.children.add(childNode);
            return childNode;
        }

    }
}