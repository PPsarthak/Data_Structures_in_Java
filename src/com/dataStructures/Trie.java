package com.dataStructures;

public class Trie {
    static class Node{
        Node[] letters;
        boolean end;

        Node(){
            letters = new Node[26];
            end = false;
        }
    }

    private Node root;

    Trie(){
        root = new Node();
    }

    void insert(String s){
        Node temp = root;
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if(temp.letters[idx] == null){
                temp.letters[idx] = new Node();
            }
            temp = temp.letters[idx];
        }
        temp.end = true;
    }

    boolean search(String s){
        Node temp = root;
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if(temp.letters[idx] == null){
                return false;
            }
            temp = temp.letters[idx];
        }
        return temp.end;
    }

    boolean startsWith(String s){
        Node temp = root;
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if(temp.letters[idx] == null){
                return false;
            }
            temp = temp.letters[idx];
        }
        return true;
    }
}
