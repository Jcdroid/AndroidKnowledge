package com.jcdroid.java_app.sort_algorithm;

import java.util.Arrays;

/**
 * Created by Jcdroid on 2018/7/9.
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {6, 3, 8, 2, 9, 1};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

}
