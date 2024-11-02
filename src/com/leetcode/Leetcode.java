package com.leetcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Leetcode {
    public List<String> read(){
        List<String> inputs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\pandi\\OneDrive\\Desktop\\Java\\Data_Structures_in_Java\\src\\com\\leetcode\\_1813_Sentence_Similarity_iii\\inputs.txt"))) {
            String line;
            while ((line = br.readLine()) != null && inputs.size() < 2) {
                inputs.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inputs;
    }
}
