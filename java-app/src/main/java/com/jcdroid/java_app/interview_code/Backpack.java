package com.jcdroid.java_app.interview_code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 牛牛的背包问题
 * 牛牛准备参加学校组织的春游, 出发前牛牛准备往背包里装入一些零食, 牛牛的背包容量为w。
 牛牛家里一共有n袋零食, 第i袋零食体积为v[i]。
 牛牛想知道在总体积不超过背包容量的情况下,他一共有多少种零食放法(总体积为0也算一种放法)。

 输入描述:
 输入包括两行
 第一行为两个正整数n和w(1 <= n <= 30, 1 <= w <= 2 * 10^9),表示零食的数量和背包的容量。
 第二行n个正整数v[i](0 <= v[i] <= 10^9),表示每袋零食的体积。


 输出描述:
 输出一个正整数, 表示牛牛一共有多少种零食放法。

 输入例子1:
 3 10
 1 2 4

 输出例子1:
 8

 例子说明1:
 三种零食总体积小于10,于是每种零食有放入和不放入两种情况，一共有2*2*2 = 8种情况。
 * Created by Jcdroid on 2018/7/6.
 */
public class Backpack {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int foodNum = scanner.nextInt();
        int backPackVolume = scanner.nextInt();
        List<Integer> foodVolumeList = new ArrayList<>();
        for (int i = 0; i < foodNum; i++) {
            int foodVolume = scanner.nextInt();
            foodVolumeList.add(foodVolume);
        }
        int num = calculate(foodVolumeList, backPackVolume);
        System.out.println(num);
        scanner.close();
    }

    public static int calculate(List<Integer> list, int backPackVolume) {
        int num = 0;
        List<Integer> filterList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            final Integer item = list.get(i);
            if (item <= backPackVolume) {
                filterList.add(item);
            }
        }
        Collections.sort(filterList);
        List<List<Integer>> totalList = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, filterList.size()); i++) {
            List<Integer> itemList = new ArrayList<>();
//            itemList.add();
            totalList.add(itemList);
        }
        for (int i = 0; i < totalList.size(); i++) {
            int sum = 0;
            for (int j = 0; j < totalList.get(i).size(); j++) {
                sum += totalList.get(i).get(j);
            }
            if (sum <= backPackVolume) {
                num++;
            }
        }
        return num;
    }

}
