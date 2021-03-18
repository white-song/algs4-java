package practice;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Skill {

    Map<String, List<Integer>> memo = new HashMap<>();

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

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


    // 计算表达式子(2+6* 3+5- (3*14/7+2)*5)+3
    // 数字
    // 括号
    // 运算符号
    public int calculate(String s) {
        int curr = 0, res = 0, num = 0, n = s.length();
        char op = '+';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                int k = i, ch = 0;
                for (; i < n; i++) {
                    if (s.charAt(i) == '(') ch++;
                    if (s.charAt(i) == ')') ch--;
                    if (ch == 0) break;
                }
                num = calculate(s.substring(k + 1, i));
            }

            if (i == n - 1 || c == '+' || c == '-' || c == '*' || c == '/') {
                switch (op) {
                    case '+': curr += num; break;
                    case '-': curr -= num; break;
                    case '*': curr *= num; break;
                    case '/': curr /= num; break;
                    default: break;
                }
                if (i == n - 1 || c == '+' || c == '-') {
                    res += curr;
                    curr = 0;
                }

                op = c;
                num = 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Skill().calculate("(2+6* 3+5- (3*14/7+2)*5)+3"));
    }
}
