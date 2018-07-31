package com.jcdroid.java_app.sort_algorithm;

import java.util.Arrays;

/**
 * Created by Jcdroid on 2018/7/9.
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {11, 3, 8, 23, 9, 25, 81, 2, 19, 73, 6};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int index = partition2(arr, lo, hi);
        quickSort(arr, lo, index - 1);
        quickSort(arr, index + 1, hi);
    }

    /**
     * 快速排序
     * @param arr
     * @param lo
     * @param hi
     * @return
     * @see <a href="https://www.cnblogs.com/coderising/p/5708801.html">快速排序（java实现）</a>
     */
    public static int partition1(int[] arr, int lo, int hi) {
        int key = arr[lo];
        while (lo < hi) {
            while (arr[hi] >= key && hi > lo) {
                hi--;
            }
            arr[lo] = arr[hi];
            while (arr[lo] <= key && hi > lo) {
                lo++;
            }
            arr[hi] = arr[lo];
        }
        arr[hi] = key;
        return hi;
    }

    /**
     * 快速排序的优化
     * @param arr
     * @param lo
     * @param hi
     * @return
     * @see <a href="https://www.cnblogs.com/coderising/p/5708801.html">快速排序（java实现）</a>
     */
    public static int partition2(int[] arr, int lo, int hi) {
        int mid = (lo + hi) / 2;
        if (arr[mid] > arr[hi]) {
            swap(arr[mid], arr[hi]);
        }
        if (arr[lo] > arr[hi]) {
            swap(arr[lo], arr[hi]);
        }
        if (arr[mid] > arr[lo]) {
            swap(arr[mid], arr[lo]);
        }
        int key = arr[lo];
        while (lo < hi) {
            while (arr[hi] >= key && hi > lo) {
                hi--;
            }
            arr[lo] = arr[hi];
            while (arr[lo] <= key && hi > lo) {
                lo++;
            }
            arr[hi] = arr[lo];
        }
        arr[hi] = key;
        return hi;
    }

    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

}
