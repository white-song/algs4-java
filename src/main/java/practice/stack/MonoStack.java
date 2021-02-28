package practice.stack;

import java.util.Arrays;
import java.util.Stack;

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

    public static void main(String[] args) {
        MonoStack stack = new MonoStack();
        System.out.println(Arrays.toString(stack.nextGreaterElements(new int[]{2, 1, 2, 4, 3})));
    }
}
