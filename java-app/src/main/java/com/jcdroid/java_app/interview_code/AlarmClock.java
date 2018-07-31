package com.jcdroid.java_app.interview_code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 牛牛的闹钟
 * 牛牛总是睡过头，所以他定了很多闹钟，只有在闹钟响的时候他才会醒过来并且决定起不起床。从他起床算起他需要X分钟到达教室，上课时间为当天的A时B分，请问他最晚可以什么时间起床
 输入描述:
 每个输入包含一个测试用例。
 每个测试用例的第一行包含一个正整数，表示闹钟的数量N(N<=100)。
 接下来的N行每行包含两个整数，表示这个闹钟响起的时间为Hi(0<=A<24)时Mi(0<=BachelorIndex1<60)分。
 接下来的一行包含一个整数，表示从起床算起他需要X(0<=X<=100)分钟到达教室。
 接下来的一行包含两个整数，表示上课时间为A(0<=A<24)时B(0<=BachelorIndex1<60)分。
 数据保证至少有一个闹钟可以让牛牛及时到达教室。


 输出描述:
 输出两个整数表示牛牛最晚起床时间。

 输入例子1:
 3
 5 0
 6 0
 7 0
 59
 6 59

 输出例子1:
 6 0
 * Created by Jcdroid on 2018/7/6.
 */
public class AlarmClock {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int alarmClockNum = scanner.nextInt();
        List<AlarmClockTime> list = new ArrayList<>();
        for (int i = 0; i < alarmClockNum; i++) {
            int hi = scanner.nextInt();
            int mi = scanner.nextInt();
            AlarmClockTime time = new AlarmClockTime(hi, mi);
            list.add(time);
        }
        int needMi = scanner.nextInt();
        int deadLineTime_hi = scanner.nextInt();
        int deadLineTime_mi = scanner.nextInt();
        AlarmClockTime deadLineTime = new AlarmClockTime(deadLineTime_hi, deadLineTime_mi);
        List<Integer> results = calculate(list, needMi, deadLineTime);
        System.out.println(results.get(0) + " " + results.get(1));
        scanner.close();
    }

    public static class AlarmClockTime implements Comparable<AlarmClockTime> {
        int hi;
        int mi;
        public AlarmClockTime(int hi, int mi) {
            this.hi = hi;
            this.mi = mi;
        }

        public int getTotalMi() {
            return hi * 60 + mi;
        }

        @Override
        public int compareTo(AlarmClockTime alarmClockTime) {
            if (this.getTotalMi() >= alarmClockTime.getTotalMi()) {
                return 1;
            } else {
                return -1;
            }
        }

        @Override
        public int hashCode() {
            return hi + mi;
        }
    }

    public static List<Integer> calculate(List<AlarmClockTime> list, int needMi, AlarmClockTime deadLineTime) {
        long deadLineTimeMi = deadLineTime.getTotalMi();
        long lastTimeMi = deadLineTimeMi - needMi;

        Collections.sort(list);
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            AlarmClockTime time = list.get(i);
            long alarmClockTimeMi = time.getTotalMi();
            if (i > 0) {
                AlarmClockTime timePre = list.get(i - 1);
                long alarmClockTimePreMi = timePre.getTotalMi();
                if (alarmClockTimeMi > lastTimeMi && alarmClockTimePreMi <= lastTimeMi) {
                    results.add(timePre.hi);
                    results.add(timePre.mi);
                    break;
                }
            }
        }
        return results;
    }

}
