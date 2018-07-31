package com.jcdroid.java_app.interview_code;

import java.util.Scanner;

/**
 * 光棍指数
 * 对于一个正整数，我们认为它的光棍指数是它二进制表示下1的个数。
 通常认为光棍指数越高，这个数就越孤单。那么问题来了，对于给定的[a,b]区间中。最孤单的数字是谁呢？
 如果光棍指数相同，最孤单的就是最小的那个数。

 输入描述:
 第一行一个整数 T (1≤T≤10^4)，表示问题数。
 接下来 T 行，每行两个整数 a,b (0≤a≤b≤2^31−1)。数据之间用一个空格分隔。


 输出描述:
 对于每个问题，输出一行 Case x: y，其中 x 是问题编号，从 1 开始，y 是答案

 输入例子1:
 2
 0 14
 100 1000

 输出例子1:
 Case 1: 7
 Case 2: 511
 * Created by Jcdroid on 2018/7/18.
 */
public class BachelorIndex {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] input = new int[n][2];
        for (int i = 0; i < n; i++) {
            input[i][0] = scanner.nextInt();
            input[i][1] = scanner.nextInt();
        }
        int[] result = bachelorIndex(input);
        for (int i = 0; i < result.length; i++) {
            System.out.println("Case " + (i + 1) + ": " + result[i]);
        }
        scanner.close();
    }

    public static int[] bachelorIndex(int[][] input) {
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            int[] item = input[i];
            int min = item[0];
            int max = item[1];
            int maxDigit = 1;
            while (max / 2 > 0) {
                max >>= 1;
                maxDigit++;
            }

            int maxDigitNum = (int) (Math.pow(2, maxDigit) - 1);
            if (max >= maxDigitNum) {
                result[i] = maxDigitNum;
            } else {
                int j = 0;
                while (maxDigitNum - Math.pow(2, j) < max && maxDigitNum - Math.pow(2, j) >= min) {
                    result[i] = (int) (maxDigitNum - Math.pow(2, j));
                    j++;
                }
            }
        }
        return result;
    }

    public static int[] bachelorIndex1(int[][] input) {
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            int[] item = input[i];
            int min = item[0];
            int max = item[1];
            int maxDigit = 1;
            while (max / 2 > 0) {
                max >>= 1;
                maxDigit++;
            }

            int minDigitNum = (int) (Math.pow(2, maxDigit - 1) - 1);
            if (min <= minDigitNum) {
                result[i] = minDigitNum;
            } else {
                int j = maxDigit - 2;
                while (minDigitNum + Math.pow(2, j) > max) {
                    if (min <= minDigitNum) {
                        result[i] = (int) (minDigitNum + Math.pow(2, j));
                    }
                    j++;
                }
            }
        }
        return result;
    }

    public static int[] bachelorIndex2(int[][] input) {
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            int[] item = input[i];
            int min = item[0];
            int max = item[1];
            int maxOneNum = 0;
            for(int j = max; j >= min; j--) {
                int temp = j;
                int oneNum = temp % 2;
                while (temp / 2 > 0) {
                    temp >>= 1;
                    if (temp % 2 == 1) {
                        oneNum++;
                    }
                }
                if (maxOneNum > oneNum) {
                    continue;
                } else {
                    result[i] = j;
                }
                maxOneNum = Math.max(maxOneNum, oneNum);
            }
        }
        return result;
    }

}
