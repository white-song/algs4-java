package practice.pointer;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 滑动窗口的思想的应用
 *
 *
 */
public class SlidingWindow {

    /**
     * 最小窗口：包含t所有字母的最小子串
     */
    public String minWindow(String s, String t) {

        if (StringUtils.isEmpty(s) || StringUtils.isEmpty(t)) {
            return "";
        }
        Map<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0, right = 0, valid = 0, start = 0, len = Integer.MAX_VALUE;
        while(right < s.length()) {

            //窗口右移
            char x = s.charAt(right);
            right++;

            if (need.containsKey(x)) {
                int num = window.getOrDefault(x, 0) + 1;
                window.put(x, num);
                if (need.get(x) == num) {
                    valid++;
                }
            }

            while(valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // 窗口左移
                char d = s.charAt(left);
                left++;

                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }

        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    public List<Integer> findAnagrams(String s, String t) {
        List<Integer> res = new ArrayList<>();

        if (StringUtils.isEmpty(s) || StringUtils.isEmpty(t)) {
            return res;
        }
        Map<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0, right = 0, valid = 0;
        while(right < s.length()) {

            //窗口右移
            char x = s.charAt(right);
            right++;

            if (need.containsKey(x)) {
                int num = window.getOrDefault(x, 0) + 1;
                window.put(x, num);
                if (need.get(x) == num) {
                    valid++;
                }
            }

            while(right - left >= need.size()) {
                if (valid == need.size()) {
                    res.add(left);
                }

                // 窗口左移
                char d = s.charAt(left);
                left++;

                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }

        }
        return res;
    }

    public Integer lengthOfLongestSubstring(String s) {
        if (StringUtils.isEmpty(s)) {
            return 0;
        }
        Map<Character, Integer> window = new HashMap<>();
        int left = 0,  right = 0, res = 0;
        while(right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);

            while(window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                window.put(d, window.get(d) - 1);
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    public static void main(String[] args) {
        SlidingWindow window = new SlidingWindow();
        System.out.println(window.lengthOfLongestSubstring("abbcefbac"));
    }
}
