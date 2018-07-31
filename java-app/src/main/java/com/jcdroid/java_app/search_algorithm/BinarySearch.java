package com.jcdroid.java_app.search_algorithm;

/**
 * Created by Jcdroid on 2018/7/9.
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {0, 2, 3, 4, 5, 6, 11, 13, 31, 43, 44, 66};
        int num = 3;
        int index = binarySearch(arr, num);
        System.out.println(index);
    }

    /**
     * 二分查找，也叫折半查找
     * <br/>
     * 基本原理：每次查找都对半分，但要求数组是升序排序
     *
     * @param arr
     * @param num
     * @return 索引
     */
    private static int binarySearch(int[] arr, int num) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] > num) {
                hi = mid - 1;
            } else if (arr[mid] < num) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
