package com.dataStructures;

public class SegmentTree {
    private int[] STree;
    /*
    private int maxsize;
    private int height;

    private  final int STARTINDEX = 0;
    private  final int ENDINDEX;
    private  final int ROOT = 0;

    public SegmentTree(int size)
    {
        height = (int)(Math.ceil(Math.log(size) /  Math.log(2)));
        maxsize = 2 * (int) Math.pow(2, height) - 1;
        tree = new int[maxsize];
        ENDINDEX = size - 1;
    }
     */

    /**
     * Returns the left child index
     * @param index of the parent
     * @return left child index
     */
    private int leftChild(int index){
        return (2*index)+1;
    }
    /**
     * Returns the right child index
     * @param index of the parent
     * @return right child index
     */
    private int rightChild(int index){
        return (2*index)+2;
    }

    /**
     * Returns the mid of the start and end
     * @param start index
     * @param end end
     * @return mid
     */
    private int getMid(int start, int end){
        return start + (end-start)/2;
    }
    private int constructSTreeUtil(int[] elements, int startIndex, int endIndex, int current){
        if(startIndex == endIndex){
            return STree[current] = elements[startIndex];
        }
        int mid = getMid(startIndex, endIndex);
        return STree[current] =
                constructSTreeUtil(elements, startIndex, mid, leftChild(current)) +
                constructSTreeUtil(elements, mid+1, endIndex, rightChild(current));
    }
    void constructSTree(int[] elements){
        constructSTreeUtil(elements, 0, 0, 0);
    }
    int getSumUtil(int startIdx, int endIdx, int start, int end, int current){
        if(start <= startIdx && end >= endIdx){
            //complete/exactly fits in the range
            return STree[current];
        }
        else if(endIdx < start || startIdx > end){
            //completely does not fit in the range
            return Integer.MIN_VALUE;
        }
        //partially fits in the range
        int mid = getMid(startIdx, endIdx);
        return getSumUtil(startIdx, mid, start, end, leftChild(current)) +
                getSumUtil(mid+1, endIdx, start, end, rightChild(current));
    }
    int getSum(int start, int end){
        if(start < 0 || end > STree.length) return -1;
        return getSumUtil(0,0,start, end, 0);
    }
    void updateSTreeUtil(int startIndex, int endIndex, int index, int newValue, int current)
    {
        if ( index < startIndex || index > endIndex){
            return;
        }
        STree[current] = STree[current] + newValue;
        if (startIndex != endIndex){
            int mid = getMid(startIndex, endIndex);
            updateSTreeUtil(startIndex, mid, index, newValue, leftChild(current));
            updateSTreeUtil(mid+1, endIndex, index, newValue, rightChild(current));
        }
    }
    void update(int newValue, int index, int[] elements)
    {
        int updateDiff = newValue - elements[index]  ;
        elements[index] = newValue;
        updateSTreeUtil(0, 0, index, updateDiff, 0);
    }
    public static void main(String[] args) {

    }
}
