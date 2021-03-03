package practice.stack;

import javafx.concurrent.Worker;
import sun.nio.ch.ThreadPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.*;

/**
 * 单调栈
 *
 * 是否会被后面的挡住
 */
public class MonoStack {
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            System.out.println(i % nums.length);
            while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();
            }
            res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return res;
    }

    public String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        int[] count = new int[256];
        boolean[] instack = new boolean[256];

        for (int i = 0; i < s.length(); i++) {
            count[i]++;
        }

        for (char c: s.toCharArray()) {
            count[c]--;

            if (instack[c]) {
                continue;
            }
            instack[c] = true;

            while(!stack.empty() && stack.peek() > c) {
                if (count[stack.peek()] == 0) {
                    break;
                }
                instack[stack.pop()] = false;
            }

            stack.push(c);
        }

        StringBuilder builder = new StringBuilder();
        while (!stack.empty()) {
            builder.append(stack.pop());
        }
        return builder.reverse().toString();
    }

    public static void main(String[] args) {
        MonoStack stack = new MonoStack();
        System.out.println(stack.removeDuplicateLetters("bcabc"));
    }
}
