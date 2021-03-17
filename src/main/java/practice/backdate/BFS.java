package practice.backdate;

import java.util.*;

public class BFS {

    public String plusUp(String s, int n) {
        char[] chars = s.toCharArray();
        if (chars[n] == '9') {
            chars[n] = '0';
        } else {
            chars[n]++;
        }
        return new String(chars);
    }

    public String plusDown(String s, int n) {
        char[] chars = s.toCharArray();
        if (chars[n] == '0') {
            chars[n] = '9';
        } else {
            chars[n]--;
        }
        return new String(chars);
    }

    /**
     * 记录密码开锁的最少次数
     */
    public int openLock(String[] deadends, String target) {
        int stap = 0;
        Queue<String> queue = new LinkedList<>();
        //添加死亡数字
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        queue.offer("0000");
        visited.add("0000");
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (Objects.equals(curr, target)) {
                    return stap;
                }
                for (int j = 0; j < 4; j++) {
                    //向上转
                    String up = plusUp(curr, j);
                    if (!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    //向下转
                    String down = plusDown(curr, j);
                    if (!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            stap++;
        }
        return -1;
    }

    public static void main(String[] args) {
       PriorityQueue<Integer> res = new PriorityQueue<>(11, (a, b) -> b - a);
        System.out.println(new BFS().openLock(new String[]{}, "0001"));
    }
}
