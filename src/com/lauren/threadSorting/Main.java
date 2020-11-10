package com.lauren.threadSorting;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        int[] ar = new int[20];
        for (int i = 0; i < ar.length; i++) {
            ar[i] = r.nextInt();
        }
        mergeSort(ar, 0, ar.length - 1);
        display(ar);

    }
    public static void mergeSort(int[] ar, int begin, int end)
    {
        if(begin != end)
        {
            int begin1 = begin;
            int end1 = begin + ((end - begin)/2);
            int begin2 = end1 + 1;
            int end2 = end;

            MergeThread thread1 = new MergeThread(ar, begin1, end1);
            MergeThread thread2 = new MergeThread(ar, begin2, end2);

            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            merge(ar, begin1, end1, begin2, end2);
        }
    }

    static void merge(int[] ar, int begin1, int end1, int begin2, int end2) {
        int[] temp = new int[end2 - begin1 + 1];
        int pos1 = begin1;
        int pos2 = begin2;
        for (int i = 0; i < temp.length; i++) {
            if (pos1 <= end1 && pos2 <= end2) {
                if (ar[pos1] < ar[pos2]) {
                    temp[i] = ar[pos1];
                    pos1++;
                } else {
                    temp[i] = ar[pos2];
                    pos2++;
                }
            } else {
                //either pos1 or pos2 is off the end of their list and the other guy is the
                //default winner
                if (pos1 > end1) {
                    temp[i] = ar[pos2];
                    pos2++;
                } else {
                    temp[i] = ar[pos1];
                    pos1++;
                }
            }
        } //end of for loop

        //copy temp back over ar from begin1 to end2
        int posInTemp = 0;
        for (int i = begin1; i <= end2; i++) {
            ar[i] = temp[posInTemp];
            posInTemp++;
        }
    }

    static void display(int[] ar) {
        for(int i = 0; i < ar.length; i++)
        {
            System.out.println(ar[i]);
        }
    }
}
