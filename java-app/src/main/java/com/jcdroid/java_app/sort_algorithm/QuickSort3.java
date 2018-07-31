package com.jcdroid.java_app.sort_algorithm;

import java.util.Arrays;

/**
 * Created by Jcdroid on 2018/7/25.
 */
public class QuickSort3 {

    public static void main(String[] args) {
        int[] arr = {11, 3, 8, 23, 9, 25, 81, 2, 19, 73, 6};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int arr[], int low, int high) {
        int l = low;
        int h = high;
        int key = arr[low];

        while (l < h) {
            while (l < h && arr[h] >= key) {
                h--;
            }
            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                l++;
            }

            while (l < h && arr[l] <= key) {
                l++;
            }
            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                h--;
            }
        }
        System.out.print("l=" + (l + 1) + ", h=" + (h + 1) + ", key=" + key + "\n");
        if (l > low) sort(arr, low, l - 1);
        if (h < high) sort(arr, h + 1, high);
    }

}
