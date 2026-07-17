import java.util.*;

class Stack {
    static ArrayList<Integer> stack = new ArrayList<>();
    public static boolean isEmpty() {
        return stack.size() == 0;
    }

    public static void push(int data) {
        stack.add(data);
    }

    public static int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return stack.remove(stack.size() - 1);
    }

    public static int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return stack.get(stack.size() - 1);
    }

    public static void print() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.print(stack.get(i) + " ");
        }
        System.out.println();
    }

    class Node { // can also implement stack using linked list just for show wont use it
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head;

    public void pushNode(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public int popNode() {
        if (head == null) {
            throw new RuntimeException("Stack is empty");
        }
        int data = head.data;
        head = head.next;
        return data;
    }

    public int peekNode() {
        if (head == null) {
            throw new RuntimeException("Stack is empty");
        }
        return head.data;
    }

    public void pushAtBottom(int data){
        if (stack.isEmpty()) {
            push(data);
            return;
        }

        int top = pop();
        pushAtBottom(data);
        push(top);
        
    }

    public void reverseString(String str) {
        for (int i = 0; i < str.length(); i++) {
            push(str.charAt(i));
        }
        StringBuilder reversed = new StringBuilder();
        while (!isEmpty()) {
            reversed.append((char) pop());
        }
        System.out.println(reversed.toString());
    }

    public void reverseStack() {
        if (isEmpty()) {
            return;
        }
        int top = pop();
        reverseStack();
        pushAtBottom(top);
    }

    public int[] nextGreaterElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        for (int i = n - 1; i >= 0; i--) {// starting from the end of the array because we want to find the next greater element for each element in the array
            while (!isEmpty() && peek() <= arr[i]) { // if curr element is greate than top of stack then pop it until we find the next greater element or stack becomes empty
                pop();
            }
            result[i] = isEmpty() ? -1 : peek();
            push(arr[i]);
        }
        return result;
    }

    public boolean validParenthesis(String str) {
        while(!isEmpty()) {
            pop();
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                push(ch);
            } else {
                if (isEmpty()) {
                    return false;
                }
                char top = (char) pop();
                if ((ch == ')' && top != '(') || (ch == '}' && top != '{') || (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return isEmpty();
    }

    public boolean isDuplicateParenthesis(String str) {
        while (!isEmpty()) {
            pop();
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch != ')') {
                push(ch);
            } else {
                count = 0;
                while (!isEmpty() && peek() != '(') {
                    pop();
                    count++;
                }
                if (!isEmpty()) {
                    pop(); // pop the opening parenthesis
                }
                if (count < 1) {
                    return true; // duplicate parenthesis found
                }
            }
        }
        return false;
    }

    public int maxRectangleArea(int[] heights) {
        while (!isEmpty()) {
            pop();
        }
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        
        for (int i = 0; i < n; i++) {
            while (!isEmpty() && heights[peek()] >= heights[i]) {
                pop();// pop it bcoz we not need it anymore as we are looking for the next smaller element to the left of the current element bcoz it will have the common height which will be smaller so we can count that in width
            }
            left[i] = isEmpty() ? -1 : peek();
            push(i);
        }
        
        while (!isEmpty()) {
            pop();
        }
        
        for (int i = n - 1; i >= 0; i--) {
            while (!isEmpty() && heights[peek()] >= heights[i]) {
                pop();
            }
            right[i] = isEmpty() ? n : peek();
            push(i);
        }
        
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            maxArea = Math.max(maxArea, heights[i] * (right[i] - left[i] - 1));
        }
        
        return maxArea;
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.print(); // 3 2 1
        System.out.println(stack.pop()); // 3
        System.out.println(stack.peek()); // 2
        stack.print(); // 2 1

        stack.pushAtBottom(0);
        stack.print(); // 2 1 0

        stack.reverseStack();
        stack.print(); // 0 1 2

        int[] arr = {4, 5, 2, 10, 8};
        int[] result = stack.nextGreaterElement(arr);
        System.out.println(Arrays.toString(result)); // [5, 10, 10, -1, -1]
        System.out.println(stack.validParenthesis("(){}[]")); // true
        System.out.println(stack.isDuplicateParenthesis("(((a+b)))")); // true duplicate
        System.out.println(stack.maxRectangleArea(new int[]{2, 1, 5, 6, 2, 3})); // 10
        
    }
}