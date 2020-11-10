package com.lauren.threadSorting;

public class MergeThread extends Thread {
    private int[] ar;
    private int begin;
    private int end;


    public MergeThread(int[] ar, int begin, int end) {
        this.begin = begin;
        this.end = end;
        this.ar = ar;
    }

    @Override
    public void run() {
        Main.mergeSort(ar, begin, end);
    }
}
