import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTrees {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BST {
        
       public static Node buildTree(Node root, int value){
            if(root == null){
                return new Node(value);
            }

            if (value < root.data) {
                root.left = buildTree(root.left, value);
            } else if (value > root.data) {
                root.right = buildTree(root.right, value);
            }

            return root;
        }

       Node search(Node root, int value) {

            if (root == null) {
                System.out.println("Not Found");
                return null;
            }

            if (root.data == value) {
                System.out.println("Found: " + root.data);
                return root;
            }

            if (value < root.data) {
                return search(root.left, value);  
            } else {
                return search(root.right, value); 
            }
        }


        Node inOrderSuccessor(Node root) {
            Node curr = root.right;
            while (curr.left != null) {
                curr = curr.left;
            }
            return curr;
        }

        // void delete(Node root , int value){

        //     Node parent = null;
        //     Node curr = root;

        //     while (curr != null && curr.data != value) {
        //         parent = curr;
        //         if (value < curr.data) {
        //             curr = curr.left;
        //         } else {
        //             curr = curr.right;
        //         }
        //     }
            
            
        //     if (curr != null) {
        //         //case 1
        //         if(curr.left == null && curr.right == null){
        //             if (parent.data < curr.data) {
        //                 parent.right = null;
        //             } else {
        //                 parent.left = null;
        //             }
        //         }

        //         //case 2

        //         if (curr.left != null || curr.right != null) {
        //             if (parent.data < curr.data) {
        //                 if (curr.data != null ) {
        //                 parent.right = null;
        //                 } else {
        //                     parent.left = null;
        //                 }
        //             } else {
        //                 parent.left = null;
        //             }
        //         }


        //     }

        //     //case 2
        //     if (left != null) {
        //         if(left.left == null && left.right != null){
        //             root.left = left.right;
        //         }
            
        //         if(left.left != null && left.right == null){
        //             root.left = left.left;
        //         }
        //     }else{
        //         if(right.left == null && right.right != null){
        //             root.right = right.right;
        //         }
            
        //         if(right.left != null && right.right == null){
        //             root.right = right.left;
        //         }
        //     }

        //     //case-3
        //     Node next = inOrderSuccessor(root);
        //     if (left != null) {
        //         if(left.left != null && left.right != null){
        //             root.left = next;
        //         }
        //     }else{
        //         if(right.left != null && right.right != null){
        //             root.right = next;
        //         }
        //     }
        // }

    }

   public static void main(String[] args) {
        BST bst = new BST();
        Node root = null;
        
        int values[] = {3, 4, 1, 2, 5, 6}; // BST insert values only
        for (int v : values){
            root = bst.buildTree(root, v);
        }
        System.out.println(root.data);
        bst.search(root, 6);

    }
}

