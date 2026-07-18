import java.util.*;

public class Queue { // q2 is an interface in JCF so can define it by linkedlist or ArrayDeque class they have differences arraydeque is faster because it uses array implementation which stores on basis of addresses and ll creates new objs on connections

    static ArrayList<Integer> queue = new ArrayList<>();
    int front = -1;
    int rear = -1;

    public boolean isEmpty() {
        return rear == -1;
    }

    public void enqueue(int data) {
        queue.add(data);
        rear++;
        if (front == -1) {
            front = 0;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int data = queue.get(front);
        front++;
        if (front > rear) {
            front = -1;
            rear = -1;
        }
        return data;
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue.get(front);
    }

    //for circular queues update front and rear as front = (front + 1) % capacity and rear = (rear + 1) % capacity

    public void queueUsingStack() {
        java.util.Stack<Integer> stack1 = new java.util.Stack<>();
        java.util.Stack<Integer> stack2 = new java.util.Stack<>();

        // Enqueue operation
        for (int i = 1; i <= 5; i++) {
            stack1.push(i);
        }

        // Dequeue operation
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop() + " ");
        }
    }

    public int[] nonRepeatLetters(String str){
        
        int[] count = new int[26];
        for(int i=0;i<str.length();i++){
            count[str.charAt(i)-'a']++;
        }
        int[] result = new int[str.length()];
        for(int i=0;i<str.length();i++){
            result[i] = count[str.charAt(i)-'a'] == 1 ? str.charAt(i) : -1;
        }
        return result;
    }

    public void interleaveTwoHalves(String str){
        //imagine queue main is 12345 and q2 is 67890
        java.util.Queue<Character> q2 = new java.util.LinkedList<>();
        for(int i=0;i<str.length();i++){
            q2.add(str.charAt(i));
        }
        //idea is to pop 1 and add in back in queue then add 6 in back of queue then pop 2 and add in back then add 7 in back and so on
        while (!q2.isEmpty()) {
            char c1 = (char) dequeue();
            enqueue(c1);
            if (!q2.isEmpty()) {
                char c2 = q2.poll();
                enqueue(c2);
            }
        }

        while (!isEmpty()) {
            System.out.print((char) dequeue() + " ");
        }

    }

    public static void main(String[] args){
        Queue q = new Queue();
        q.enqueue('0');
        q.enqueue('1');
        q.enqueue('2');
        q.enqueue('3');
        q.enqueue('4');
        q.enqueue('5');
        System.out.println(q.dequeue());// 0
        System.out.println(q.peek());// 1
        // q.queueUsingStack(); // 1 2 3 4 5
        q.interleaveTwoHalves("67890"); // 1 6 2 7 3 8 4 9 5 0
        Deque<Integer> dq = new ArrayDeque<>();// double ended queue has add first add last removefirst remove last peekfirst peeklast methods
    }

}
