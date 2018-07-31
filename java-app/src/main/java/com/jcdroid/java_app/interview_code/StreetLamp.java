package com.jcdroid.java_app.interview_code;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 小Q正在给一条长度为n的道路设计路灯安置方案。

 为了让问题更简单,小Q把道路视为n个方格,需要照亮的地方用'.'表示, 不需要照亮的障碍物格子用'X'表示。

 小Q现在要在道路上设置一些路灯, 对于安置在pos位置的路灯, 这盏路灯可以照亮pos - 1, pos, pos + 1这三个位置。

 小Q希望能安置尽量少的路灯照亮所有'.'区域, 希望你能帮他计算一下最少需要多少盏路灯。


 输入描述:
 输入的第一行包含一个正整数t(1 <= t <= 1000), 表示测试用例数
 接下来每两行一个测试数据, 第一行一个正整数n(1 <= n <= 1000),表示道路的长度。
 第二行一个字符串s表示道路的构造,只包含'.'和'X'。


 输出描述:
 对于每个测试用例, 输出一个正整数表示最少需要多少盏路灯。

 输入例子1:
 2
 3
 .X.
 11
 ...XX....XX

 输出例子1:
 1
 3
 * Created by Jcdroid on 2018/7/6.
 */
public class StreetLamp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // System.out.println("请输入测试用例数：");
        int testCaseNum = scanner.nextInt();
        List<Road> data = new ArrayList<>();
        for (int i = 0; i < testCaseNum; i++) {
            // System.out.println("请输入道路的长度：");
            int roadLength = scanner.nextInt();
            // System.out.println("请输入道路的构造,只包含'.'和'X'：");
            String roadData = scanner.next();
            Road road = new Road(roadLength, roadData);
            data.add(road);
        }
        List<Integer> results = calculate(data);
        for (Integer item : results) {
            System.out.println(item);
        }
        scanner.close();
    }

    public static class Road {
        int length;
        String data;

        public Road(int length, String data) {
            super();
            this.length = length;
            this.data = data;
        }
    }

    public static List<Integer> calculate(List<Road> roadList) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < roadList.size(); i++) {
            Road road = roadList.get(i);
            int length = road.length;
            String data = road.data;
            int index = 0;
            int num = 0;
            while (index <= (length - 1)) {
                char s = data.charAt(index);
                if (s == '.') {
                    index += 3;
                    num++;
                }
                if (s == 'X') {
                    index++;
                }
            }
            result.add(num);
        }
        return result;
    }

}
