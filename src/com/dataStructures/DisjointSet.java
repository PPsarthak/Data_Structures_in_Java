package com.dataStructures;

import java.util.ArrayList;
import java.util.List;

public class DisjointSet {
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();
    DisjointSet(int n){
        //assumed 1-based indexing so:= i<=n; for 0 based make it <n
        for (int i = 0; i <= n; i++) {
            rank.add(0);
            size.add(1);
            parent.add(i);
        }
    }
    public int findUParent(int node){
        if(node == parent.get(node)){
            return node;
        }
        //recursive call to it parent + path compression
        int nodeParent = findUParent(parent.get(node));
        parent.set(node, nodeParent);
        return nodeParent;
    }

    public void unionByRank(int u, int v){
        int uParent = findUParent(u);
        int vParent = findUParent(v);

        if(uParent == vParent) return;

        //compare both components/nodes & add smaller component/node to higher rank component/node only
        if(rank.get(uParent) < rank.get(vParent)){
            //update the parent of smaller with larger
            parent.set(uParent, vParent);
        }
        else if(rank.get(uParent) > rank.get(vParent)){
            parent.set(vParent, uParent);
        }
        else{
            //if same, connect to either one:= your choice
            parent.set(vParent, uParent);
            //update the rank by 1
            int currRank = rank.get(uParent);
            rank.set(uParent, currRank+1);
        }
    }

    public void unionBySize(int u, int v){
        int uParent = findUParent(u);
        int vParent = findUParent(v);

        if(uParent == vParent) return;

        if(size.get(uParent) < size.get(vParent)){
            parent.set(uParent, vParent);
            size.set(vParent, size.get(uParent) + size.get(vParent));
        }
        else{
            parent.set(vParent, uParent);
            size.set(uParent, size.get(uParent) + size.get(vParent));
        }
    }
}

