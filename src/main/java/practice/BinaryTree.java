package practice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode() {}
  TreeNode(int val) { this.val = val; }
  TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
  }
}


class Codec {

    // 把一棵二叉树序列化成字符串
    public String serialize(TreeNode root) {
        return null;
    }

    // 把字符串反序列化成二叉树
    public TreeNode deserialize(String data) {
        return null;
    }
}

public class BinaryTree {
    String SEP = ",";
    String NULL = "#";

    void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;
        root.right = left;

        TreeNode p = root;
        while(p.right != null) {
            p = p.right;
        }
        p.right = right;
    }


    public String serializePreorder(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializePreorder(root, sb);
        return sb.toString();
    }

    public String serializePostorder(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializePostorder(root, sb);
        return sb.toString();
    }

    public String serializeLevel(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            sb.append(NULL).append(SEP);
            return sb.toString();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append(NULL).append(SEP);
                continue;
            }
            sb.append(node.val).append(SEP);
            queue.offer(node.left);
            queue.offer(node.right);
        }

        return sb.toString();
    }

    public void serializePreorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }
        sb.append(root.val).append(SEP);
        serializePreorder(root.left, sb);
        serializePreorder(root.right, sb);
    }

    public void serializePostorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }
        serializePostorder(root.left, sb);
        serializePostorder(root.right, sb);
        sb.append(root.val).append(SEP);
    }

    public TreeNode deserializePreorder(String data) {
        return deserializePreorder(new LinkedList<>(Arrays.asList(data.split(SEP))));
    }

    public TreeNode deserializePostorder(String data) {
        return deserializePostorder(new LinkedList<>(Arrays.asList(data.split(SEP))));
    }

    public TreeNode deserializePreorder(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String node = nodes.removeFirst();
        if (node.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(node));
        root.left = deserializePreorder(nodes);
        root.right = deserializePreorder(nodes);
        return root;
    }

    public TreeNode deserializePostorder(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String node = nodes.removeLast();
        if (node.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(node));
        root.right = deserializePostorder(nodes);
        root.left = deserializePostorder(nodes);
        return root;
    }

    public TreeNode deserializeLevel(String data) {
        String[] nodes = data.split(SEP);
        if (data.length() == 0) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int i = 0;
        TreeNode root = new TreeNode(Integer.parseInt(nodes[i++]));
        queue.offer(root);
        String n;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            n = nodes[i++];
            if (!n.equals(NULL)) {
                node.left = new TreeNode(Integer.parseInt(n));
                queue.offer(node.left);
            }
            n = nodes[i++];
            if (!n.equals(NULL)) {
                node.right = new TreeNode(Integer.parseInt(n));
                queue.offer(node.right);
            }
        }
        return root;
    }

    TreeNode deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        // 从后往前取出元素
        String last = nodes.removeLast();
        if (last.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(last));
        // 限构造右子树，后构造左子树
        root.right = deserialize(nodes);
        root.left = deserialize(nodes);

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);

        BinaryTree tree = new BinaryTree();
        System.out.println(tree.serializePreorder(root));
        System.out.println(tree.serializePostorder(root));
        System.out.println(tree.serializeLevel(root));
        System.out.println(tree.serializePreorder(tree.deserializeLevel(tree.serializeLevel(root))));
    }
}

