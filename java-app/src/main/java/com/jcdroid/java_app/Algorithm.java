package com.jcdroid.java_app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Algorithm {

    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};

        // to sum
        int target = 13;
        int[] result = twoSum(numbers, target);
        System.out.println(Arrays.toString(result));

        // sequence query
        int key = 11;
        int index = sequenceSearch(numbers, key);
        System.out.println(index);

        // replace space
        StringBuffer str = new StringBuffer("We Are Happy");
        System.out.println(replaceSpace(str));

        // replace space
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        System.out.println(printListFromTailToHead(listNode1));

        System.out.println(calculateOperateNum(numbers));

        // replace space
        ListNode listNode11 = new ListNode(2);
        ListNode listNode12 = new ListNode(4);
        ListNode listNode13 = new ListNode(3);
        ListNode listNode21 = new ListNode(5);
        ListNode listNode22 = new ListNode(6);
        ListNode listNode23 = new ListNode(4);
        listNode11.next = listNode12;
        listNode12.next = listNode13;
        listNode21.next = listNode22;
        listNode22.next = listNode23;
        System.out.println(addTwoNumbers1(listNode11, listNode21));
    }

    /**
     * <strong>two-sum</strong>
     * <br/><br/>
     * <strong>题目大意：</strong>给定一个整数数组，找到2个数字，这样他们就可以添加到一个特定的目标号。功能twosum应该返回两个数字，他们总计达目标数，其中index1必须小于index2。请注意，你的答案返回（包括指数和指数）不为零的基础。你可以假设每个输入都有一个解决方案。
     * 输入数字numbers= { 2，7，11，15 }，目标= 9输出：index1 = 0，index2= 1
     * <br/><br/>
     * <strong>解题思路：</strong>可以申请额外空间来存储目标数减去从头遍历的数，记为key，如果hashMap中存在该key，就可以返回两个索引了。
     *
     * @param numbers 求和数组
     * @param target  目标数
     * @return 两位求和的数的索引数组
     * @see <a href="https://github.com/francistao/LearningNotes/blob/master/Part3/Algorithm/LeetCode/two-sum.md">#two-sum</a>
     */
    private static int[] twoSum(int[] numbers, int target) {
        int[] result = {};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.get(numbers[i]) != null) {
                result = new int[]{map.get(numbers[i]), i};
            } else {
                map.put(target - numbers[i], i);
            }
        }
        return result;
    }

    public static int[] twoSum1(int[] numbers, int target) {
        int[] result = {};
        int length = numbers.length;
        for (int i = 0; i < length; i++) {
            int remainder = target - numbers[i];
            for (int j = i + 1; j < length; j++) {
                if (numbers[j] == remainder) {
                    result = new int[]{i, j};
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 顺序查找
     *
     * @param numbers
     * @param key
     * @return 索引
     */
    private static int sequenceSearch(int[] numbers, int key) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查找，也叫折半查找
     * <br/>
     * 基本原理：每次查找都对半分，但要求数组是有序的
     *
     * @param arr
     * @param num
     * @return 索引
     */
    private static int binarySearch(int[] arr, int num) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
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

    /**
     * 题目描述：请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     *
     * @param str
     * @return
     */
    private static String replaceSpace(StringBuffer str) {
        return str.toString().replaceAll("\\s", "%20");
    }

    /**
     * 链表
     */
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 输入一个链表，从尾到头打印链表每个节点的值。
     *
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> arrayList = new ArrayList();
        ArrayList<Integer> result = new ArrayList();
        if (listNode == null) return arrayList;
        do {
            arrayList.add(listNode.val);
            listNode = listNode.next;
        } while (listNode != null);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            result.add(arrayList.get(size - 1 - i));
        }
        return result;
    }

    static ArrayList<Integer> arrayList = new ArrayList<>();

    public static ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHead1(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }

    /**
     * 一个数组中的数字，有大有小，如何用最少的+1或者-1的操作来消除数组中的数字差异过大的情况，返回你的+1和-1的总操作次数
     *
     * @param arr
     * @return
     */
    public static int calculateOperateNum(int[] arr) {
        int num = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int key = sum / arr.length;
        for (int i = 0; i < arr.length; i++) {
            num += Math.abs((arr[i] - key));
        }
        return num;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        int carry = 0;
        ListNode preNode = null;
        while (l1 != null || l2 != null) {
            int val1 = 0;
            if (l1 != null) {
                val1 = l1.val;
            }
            int val2 = 0;
            if (l2 != null) {
                val2 = l2.val;
            }
            int sum = val1 + val2 + carry;
            carry = sum >= 10 ? 1 : 0;
            int remainder = sum % 10;
            ListNode listNode = new ListNode(remainder);
            if (result == null) {
                result = listNode;
            }
            if (preNode != null) {
                preNode.next = listNode;
            }
            preNode = listNode;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            preNode.next = new ListNode(carry);
        }
        return result;
    }

    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        return partition(null, null, l1, l2, 0);
    }

    public static ListNode partition(ListNode result, ListNode preNode, ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
            if (carry == 1) {
                preNode.next = new ListNode(carry);
            }
            return result;
        }
        int val1, val2;
        if (l1 == null) {
            val1 = 0;
        } else {
            val1 = l1.val;
        }
        if (l2 == null) {
            val2 = 0;
        } else {
            val2 = l2.val;
        }
        int sum = val1 + val2 + carry;
        int remainder = sum % 10;
        carry = sum >= 10 ? 1 : 0;
        ListNode listNode = new ListNode(remainder);
        if (result == null) {
            result = listNode;
        }
        if (preNode != null) {
            preNode.next = listNode;
        }
        preNode = listNode;
        partition(result, preNode, l1 == null ? null : l1.next, l2 == null ? null : l2.next, carry);
        return result;
    }

    public static int reverse(int x) {
        char[] charArray = String.valueOf(x).toCharArray();
        char[] reverseArray = new char[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            if (x < 0) {
                if (i == 0) {
                    reverseArray[0] = charArray[0];
                } else {
                    reverseArray[i] = charArray[charArray.length - i];
                }
            } else {
                reverseArray[i] = charArray[charArray.length - 1 - i];
            }
        }
        int result = 0;
        try {
            result = Integer.parseInt(String.valueOf(reverseArray));
        } catch (NumberFormatException e) {
            // 溢出
        }
        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private List<Integer> result = new ArrayList<>();

    /**
     * 二叉树前序遍历，使用递归
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }
        result.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return result;
    }

    /**
     * 二叉树前序遍历，使用循环迭代
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        Stack<TreeNode> nodeStack = new Stack<>();
        while (root != null) {
            result.add(root.val);
            if (root.right != null) {
                nodeStack.push(root.right);
            }
            if (root.left != null) {
                root = root.left;
            } else {
                root = nodeStack.size() > 0 ? nodeStack.pop() : null;
            }
        }
        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }
        inorderTraversal(root.left);
        result.add(root.val);
        inorderTraversal(root.right);
        return result;
    }

    public List<Integer> inorderTraversal1(TreeNode root) {
        if (root == null) {
            return result;
        }
        inorderTraversal(root.left);
        result.add(root.val);
        inorderTraversal(root.right);
        return result;
    }
}
