package com.leetcode._1813_Sentence_Similarity_iii;

import com.leetcode.Leetcode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _1813_Sentence_Similarity_III {
    public static boolean areSentencesSimilar(String s1, String s2) {
        if(s2.length() > s1.length()){
            return areSentencesSimilar(s2, s1);
        }
        //str1 is always longer than str2
        String[] str1 = s1.split(" ");
        String[] str2 = s2.split(" ");

        // System.out.println(Arrays.toString(str1));
        // System.out.println(Arrays.toString(str2));

        int n = str1.length;
        int m = str2.length;

        //prefix max length can be str2.length and
        //suffix max length can also be str2.length
        int prefix = 0;
        int i = 0;
        int j = 0;
        while(i<n && j<m && str1[i].equals(str2[j])){
            prefix++;
            i++;
            j++;
        }

        // System.out.println("prefix " + prefix);

        int suffix = m-1;
        i = n-1;
        j = m-1;

        while(i>=0 && j>=0 && str1[i].equals(str2[j])){
            suffix--;
            i--;
            j--;
        }

        // System.out.println("suffix " + suffix);

        return prefix>suffix;
    }
    public static void main(String[] args) {
        String filePath = "C:\\Users\\pandi\\OneDrive\\Desktop\\Java\\Data_Structures_in_Java\\src\\com\\leetcode\\_1813_Sentence_Similarity_iii\\inputs.txt";
        try {
            // Create a Scanner object to read from the file
            File inputFile = new File(filePath);
            Scanner scanner = new Scanner(inputFile);

            // Read each line from the file and process it
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Process the input as if it's coming from the user

            }

            // Close the scanner
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + e.getMessage());
        }
    }
}
