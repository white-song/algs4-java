package practice.backdate;

import java.util.ArrayList;
import java.util.List;

public class FullArrangement {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> solution(int[] nums) {
        if (null == nums || nums.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> track = new ArrayList<>();
        backtrack(nums, track);
        return res;
    }

    public void backtrack(int[] nums, List<Integer> track) {
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int num : nums) {
            if (track.contains(num)) {
                continue;
            }
            track.add(num);
            backtrack(nums, track);
            track.remove(track.size() - 1);
        }
    }


    public static void main(String[] args) {
        System.out.println(new FullArrangement().solution(new int[]{1, 2, 3}));
    }
}
