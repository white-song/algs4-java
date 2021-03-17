package practice;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Skill {

    Map<String, List<Integer>> memo = new HashMap<>();
    /**
     * 返回计算表达式的所有组合的结果
     */
    List<Integer> diffWaysToCompute(String input) {
        if (input == null || "".equals(input)) {
            return new ArrayList<>();
        }

        if (memo.containsKey(input)) {
            return memo.get(input);
        }

        List<Integer> res = new ArrayList<>();

        // 分治
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!(c == '+' || c == '-' || c == '*')) {
                continue;
            }
            List<Integer> left = diffWaysToCompute(input.substring(0, i));
            List<Integer> right = diffWaysToCompute(input.substring(i + 1));

            // 合并
            for (Integer l : left) {
                for (Integer r : right) {
                    if (c=='+') {
                        res.add(l + r);
                    }
                    if (c == '-') {
                        res. add(l - r);
                    }
                    if (c == '*') {
                        res.add(l * r);
                    }
                }
            }
        }
        // base
        if (res.isEmpty()) {
            res.add(Integer.parseInt(input));
        }

        memo.put(input, res);

        return res;
    }

    public int left(int[] arr, int target) {
        int l = 0, r = arr.length;
        while(l < r) {
            int m = l + (r - l) / 2;
            if (arr[m] < target) {
                l = m + 1;
            } else if (arr[m] > target) {
                r = m;
            } else {
                l = m + 1;
            }

        }
        return l - 1;
    }


    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode pre = null;
        ListNode tmp;
        while(curr != null) {
            tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        return pre;
    }

    public static void main(String[] args) {
        Integer a = new Integer(1);
        Integer b = new Integer(3);
        System.out.println(a.compareTo(b));
        new HashMap<>();
     }
}
