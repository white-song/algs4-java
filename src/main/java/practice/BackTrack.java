package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackTrack {
    /**
     * 字符串全排列
     */
    public List<List<Integer>> fullArrangement(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();


        if (null == nums || 0 == nums.length) {
            return new ArrayList<>();
        }
        backTrackArr(nums, new ArrayList<>(), res);
        return res;
    }

    /**
     * N 皇后问题
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        //初始化棋盘
        List<char[]> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            board.add(chars);
        }
        backTrackBoard(board, 0, res);
        return res;
    }

    /**
     * 返回数组的子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> track = new ArrayList<>();
        backTrackSubset(track, 0, nums, res);
        return res;
    }

    /**
     * 返回数组的组合
     */
    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> track = new ArrayList<>();
        backTrackCombine(track, k, n, 1, res);
        return res;
    }


    /**
     * 解决数独问题
     */

    boolean solveSudoku(int[][] board, int r, int c) {
        int m = 9, n = 9;
        if (c == n) {
            // 穷举到最后一列的话就换到下一行重新开始。
            return solveSudoku(board, r + 1, 0);
        }
        if (r == m) {
            // 找到一个可行解，触发 base case
            return true;
        }
        // 就是对每个位置进行穷举
        for (int i = r; i < m; i++) {
            for (int j = c; j < n; j++) {
                if (board[i][j] != 0) {
                    // 如果有预设数字，不用我们穷举
                    return solveSudoku(board, i, j + 1);
                }

                for (int ch = 1; ch <= 9; ch++) {
                    // 如果遇到不合法的数字，就跳过
                    if (!isValidSudu(board, i, j, ch)) {
                        continue;
                    }

                    board[i][j] = ch;
                    // 如果找到一个可行解，立即结束
                    if (solveSudoku(board, i, j + 1)) {
                        return true;
                    }
                    board[i][j] = 0;
                }
                // 穷举完 1~9，依然没有找到可行解，此路不通
                return false;
            }
        }
        return false;
    }


    public boolean isValidSudu(int[][] board, int r, int c, int n) {
        for (int i = 0; i < 9 ; i++) {
            if (board[r][i] == n) {
                return false;
            }

            if (board[i][c] == n) {
                return false;
            }

        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[(r/3) * 3 + i][(c/3) * 3 + j] == n) {
                    return false;
                }
            }
        }

        return true;
    }

    public void backTrackCombine(List<Integer> track, int k, int n, int start, List<List<Integer>> res) {
        if (track.size() == k) {
            res.add(new ArrayList<>(track));
        }

        for (int i = start; i < n + 1; i++) {
            track.add(i);
            backTrackCombine(track, k, n, i + 1, res);
            track.remove(track.size() - 1);
        }
    }



    public void backTrackSubset(List<Integer> track, int start, int[] nums, List<List<Integer>> res) {
        res.add(new ArrayList<>(track));
        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backTrackSubset(track, i + 1, nums, res);
            track.remove(track.size() - 1);
        }
    }

    public void backTrackArr(int[] nums, List<Integer> track, List<List<Integer>> res) {
        if (nums.length == track.size()) {
            res.add(new ArrayList<>(track));
        }
        for (int num: nums) {
            if (track.contains(num)) {
                continue;
            }
            track.add(num);
            backTrackArr(nums, track, res);
            track.remove(track.size() - 1);
        }
     }




    public void backTrackBoard(List<char[]> board, int row, List<List<String>> res) {
        if (row == board.size()) {
            List<String> path = new ArrayList<>();
            for (char[] chars : board) {
                path.add(new String(chars));
            }
            res.add(path);
            return;
        }
        int n = board.get(row).length;
        for (int i = 0; i < n; i++) {
            if (isValid(board, row, i)) {
                board.get(row)[i] = 'Q';
                backTrackBoard(board, row + 1, res);
                board.get(row)[i] = '.';
            }
        }
    }

    public boolean isValid(List<char[]> board, int row, int col) {
        int n = board.get(row).length;
        // 竖行
        for (int i = 0; i < row; i++) {
            if (board.get(i)[col] == 'Q') {
                return false;
            }
        }
        //左上
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i)[col] == 'Q') {
                return false;
            }
        }
        //右上
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--,j++) {
            if (board.get(i)[col] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BackTrack backTrack = new BackTrack();
        int[][] board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            Arrays.fill(board[i], 0);
        }

        board[0][7] = 2;
        System.out.println(backTrack.solveSudoku(board, 0, 0));
        for (int[] b: board) {
            System.out.println(Arrays.toString(b));
        }
    }
}
