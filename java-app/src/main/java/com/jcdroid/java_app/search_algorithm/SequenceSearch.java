package com.jcdroid.java_app.search_algorithm;

/**
 * Created by Jcdroid on 2018/7/9.
 */
public class SequenceSearch {

    public static void main(String[] args) {
        int[] arr = {9, 11, 44, 66, 13, 2, 3, 31, 4, 5, 6, 43};
        int num = 9;
        int index = sequenceSearch2(arr, num);
        System.out.println(index);
    }

    /**
     * 普通顺序查找，又叫线性查找
     * @param arr
     * @param num
     * @return
     */
    public static int sequenceSearch(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 顺序查找优化，给数组设置"哨兵"元素，用于while循环的跳出，适应于大量数据的情况
     * @param arr
     * @param num
     * @return
     */
    public static int sequenceSearch2(int[] arr, int num) {
        int index = arr.length - 1;
        arr[0] = num;
        while (arr[index] != num) {
            index--;
        }
        return index;
    }

    /**
     * 同上，不过查询顺序是从小到大
     * @param arr
     * @param num
     * @return
     */
    public static int sequenceSearch3(int[] arr, int num) {
        int index = 0;
        arr[arr.length - 1] = num;
        while (arr[index] != num) {
            index++;
        }
        return index;
    }
}
