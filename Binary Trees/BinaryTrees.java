import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTrees {
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

    public static class Info {//create as static otherwise error that instance of bt needed first to create a instance of info
        int diameter;
        int height;

        Info(int diameter, int height){
            this.diameter = diameter;
            this.height = height;
        }
    }

    public static class Pair {
        int count;
        Node root;

        Pair(int count, Node root){
            this.count = count;
            this.root = root;
        }
    }
    static class BinaryTree {
        static int idx = -1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx] == -1){
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
        
        void preOrder(Node root){
            if(root == null){
                System.out.print(-1 + " ");
                return;
            }
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }

        void inOrder(Node root){
            if(root == null){
                System.out.print(-1 + " ");
                return;
            }
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }

        void postOrder(Node root){
            if(root == null){
                System.out.print(-1 + " ");
                return;
            }
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }

        void levelOrder(Node root){
            Queue<Node> q = new LinkedList<>();
            Node currNode = root;
            q.add(currNode);
            q.add(null);//important line indicated level over |1,null,2,3,null.. so on each node queue se nikalte me will add there children 
            while (!q.isEmpty()) {
                currNode = q.remove();
                if (currNode == null) {
                    System.out.println();
                    if (q.isEmpty()) {//why do this step
                        break;
                    }else{
                        q.add(null);
                    }
                } else {
                    System.out.print(currNode.data);
                    if(currNode.left != null) q.add(currNode.left);
                    if(currNode.right != null) q.add(currNode.right);
                }
            }
        }

        int treeHeight(Node root){
            if(root == null){
                return 0;
            }
            int leftMax = treeHeight(root.left) + 1;
            int rightMax = treeHeight(root.right) + 1;

            return Math.max(leftMax, rightMax);//or just return with + 1 here
        }

        int countNodes(Node root){
            if (root==null) {
                return 0;
            }

            return (countNodes(root.left) + countNodes(root.right)) + 1;
        }

        int treeDiameter(Node root){// O(n2)
            //three ways either longest diameter can in left subtree or right subtree or be the one going through the root itself so,
            if (root == null) {
                return 0;
            }
            
            int leftDia = treeDiameter(root.left);
            int leftHeight = treeHeight(root.left);
            int rightDia = treeDiameter(root.right);
            int rightHeight = treeHeight(root.right);

            int selfDia = leftHeight + rightHeight + 1;
            return Math.max(selfDia, Math.max(leftDia, rightDia));
        }

        Info treeDiaOptimized(Node root){//O(n)
            //already calculate height while traversing for diameter
            if(root == null){ 
                return new Info(0, 0);
            }

            Info leftInfo = treeDiaOptimized(root.left);
            Info rightInfo = treeDiaOptimized(root.right);

            int selfDia = leftInfo.height + rightInfo.height + 1;

            int diameter = Math.max(Math.max(leftInfo.diameter, rightInfo.diameter), selfDia);
            int height = Math.max(leftInfo.height, rightInfo.height) + 1;

            return new Info(diameter, height);

        }

        boolean subtreeMatch(Node root , Node subroot){
            if(root == null && subroot == null){
                return true;
            }
            if(root == null || subroot == null){
                return false;
            }

            if (root.data == subroot.data) {
                // if(subtreeMatch(root.left, subroot.left) && subtreeMatch(root.right, subroot.right)){ //matching tree structure like this wont work because this never returns false so do
                //     return true;
                // }    
                if (isSame(root, subroot)) {
                    return true;
                }
            }

            return subtreeMatch(root.left, subroot) || subtreeMatch(root.right, subroot);
            
        }

        boolean isSame(Node a, Node b) {
            if (a == null && b == null) return true;
            if (a == null || b == null) return false;
            if (a.data != b.data) return false;

            return isSame(a.left, b.left) && isSame(a.right, b.right);
        }
        
        HashMap<Integer,Node> levelOrderModi(Node root, HashMap<Integer,Node> hm){
            Queue<Pair> q = new LinkedList<>();
            int currDiameter = 0;
            Node currNode = root;
            q.add(new Pair(currDiameter, root));
           
            while (!q.isEmpty()) {
                Pair pair = q.remove();
                currNode = pair.root;
                currDiameter = pair.count;
                
                    if(!hm.containsKey(currDiameter)) hm.put(currDiameter , currNode);
                    if(currNode.left != null){ 
                        q.add(new Pair((pair.count) - 1, currNode.left));
                    }
                    if(currNode.right != null) {
                        q.add(new Pair((pair.count) + 1, currNode.right));
                    }
                }
            

            return hm;
        }

        void topView(Node root , HashMap<Integer,Node> hm){//create a pair to store in queue to track counter

            HashMap<Integer,Node> newHm = levelOrderModi(root,hm);

            for (int key : newHm.keySet()) {
                Node node = newHm.get(key);//can also sort and print sort on basis on key
                System.out.print(node.data);
            }
        }

        void kLevel(Node root, int level, int counter){
            if (root == null) {
                return;
            }

            kLevel(root.left, level, counter + 1);//hrr baar counter new value passed and retained to the func
            kLevel(root.right, level, counter + 1);

            if (counter == level) {
                System.out.print(root.data);
                return;
            }
   
        }

        boolean search(Node root, Node root1, Node root2){
            if(root == null){
                return false;
            }
            if (root == root1 || root == root2) {
                return true;
            }
            if (search(root.left, root1, root2) || search(root.right, root1, root2)) {
                return true;    
            }else{
                return false;
            }

        }

        int lca(Node root, Node root1, Node root2){// O(n2) approach calling search each time can also use an list to track path of each and compare both lists
           if(search(root.left, root1, root2) && search(root.right, root1, root2)){
            return root.data;
           }else if(search(root.left, root1, root2)){
            int leftans = lca(root.left, root1, root2);
            return leftans;
           }else if(search(root.right, root1, root2)){
            int rightans = lca(root.right, root1, root2);
            return rightans;
           }else{
            return 0;
           }
        }//not work for both node in same subtree

        Node lcaOptimized(Node root, Node root1, Node root2) {// O(n) and SC - O(h) recursion stack
            if (root == null) return null;
            if (root == root1 || root == root2) return root;

            Node left = lcaOptimized(root.left, root1, root2);
            Node right = lcaOptimized(root.right, root1, root2);

            if (left != null && right != null) return root;
            return (left != null) ? left : right; //return left or right if nodes are in a single subtree not multiple
        }

        int minDist(Node root, Node root1, Node root2, int count){
            if (root == null) return 0;
            if (root == root1 || root == root2){
                return count;
            }
            
            int count1 = minDist(root.left, root1, root2, count+1);
            int count2 = minDist(root.right, root1, root2, count+1);

            if (count1 != 0 && count2 != 0) return (count1 - count) + (count2 - count);
            return (count1 != 0) ? count1 : count2;
        }

        int KthAnscestor(Node root, int n , int k){
            if(root == null) return -1;
            if(root.data == n) return 0;

            int leftDist = KthAnscestor(root.left, n, k);
            int rytDist = KthAnscestor(root.right, n, k);

            if(leftDist == -1 && rytDist == -1) return -1;
            int max = Math.max(leftDist, rytDist);
            if (max+1 == k) {
                System.out.println(root.data);
            }
            
            return max + 1;
        }

        int sumTree(Node root){
            if(root == null) return 0;

            int leftTreeSum = sumTree(root.left);
            int rightTreeSum = sumTree(root.right);
            int currData = root.data;
            root.data = leftTreeSum + rightTreeSum;

            return leftTreeSum + rightTreeSum + currData;
        }

    }

    public static void main(String[] args) {
        // int nodes[] = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        int nodes[] = {1,2,4,-1,6,-1,-1,-1,3,-1, 5, 7,-1,8,-1,-1,-1};
        int rootNodes[] = {3, 4, 1, -1, -1, 2, -1, -1, 5, -1, 6, -1, -1};
        int subRootNodes[] = {4, 1, -1, -1, 2, 1, -1 ,-1, -1};
        
        BinaryTree tree = new BinaryTree();
        BinaryTree.idx = -1; // Reset idx before building first tree
        Node root = tree.buildTree(rootNodes);
        BinaryTree.idx = -1; // Reset idx before building second tree
        Node subRoot = tree.buildTree(subRootNodes);//Static methods belong to the class, not to individual objects. so can directly call via class so thats why warning
        tree.preOrder(root);
        System.out.println("\n");
        tree.inOrder(root);
        System.out.println("\n");
        tree.postOrder(root);
        System.out.println("\n");
        tree.levelOrder(root);
        System.out.println("\n");
        System.out.println(tree.treeHeight(root));
        System.out.println("\n");
        System.out.println(tree.countNodes(root));
        System.out.println("\n");
        System.out.println(tree.treeDiameter(root));
        System.out.println("\n");
        System.out.println((tree.treeDiaOptimized(root)).height);
        System.out.println(tree.subtreeMatch(root, subRoot));
        HashMap<Integer,Node> hm = new HashMap<>();
        tree.topView(root, hm);
        System.out.println();
        tree.kLevel(root, 3, 1);
        System.out.println();
        System.out.println();
        System.out.print(tree.lcaOptimized(root, root.left, root.left.left).data);
        System.out.println();
        System.out.println(tree.minDist(root, root.left.left, root.left.right,0));
        tree.KthAnscestor(root, root.left.right.data, 2);
        System.out.println();
        System.out.println(tree.sumTree(root));
    }
}