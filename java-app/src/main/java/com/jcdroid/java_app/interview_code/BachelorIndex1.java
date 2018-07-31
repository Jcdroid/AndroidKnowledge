package com.jcdroid.java_app.interview_code;

import java.util.Scanner;

/**
 * Created by Jcdroid on 2018/7/18.
 */
public class BachelorIndex1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int i = 1; i <= t; i++) {
            int a = scan.nextInt(), b = scan.nextInt();
            int j = 1;
            while (true) {
                int temp = (int) Math.pow(2, j) - 1;
                if (temp >= a) break;
                else j++;
            }
            if ((int) Math.pow(2, j) - 1 <= b) {
                while (true) {
                    int temp = (int) Math.pow(2, j) - 1;
                    if (temp > b) break;
                    else j++;
                }
                int res = (int) Math.pow(2, j - 1) - 1;
                System.out.println("Case " + i + ": " + res);
            } else {
                int start = a, max = start;
                int m = 0;
                while (start != 0) {
                    if ((start & 1) == 0) {
                        if (a + (1 << m) <= b) a += 1 << m;
                    }
                    start = start >> 1;
                    m++;
                }
                System.out.println("Case " + i + ": " + a);
            }


        }

    }
}
