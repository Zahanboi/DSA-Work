import java.util.*;

public class LL {
    class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    public Node head;
    public Node tail;
    public int size;

    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            size++;
            return;
        }
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            size++;
            return;
        }
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    public void print() {
        if (head == null) {
            System.out.println("LL is empty");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void addInMiddle(int data, int index) {
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }
        
        Node newNode = new Node(data);
        Node temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    public void removeFirst() {
        if (head == null) {
            System.out.println("LL is empty");
            return;
        }
        head = head.next;
        size--;
        if (size == 0) {
            tail = null;
        }
    }

    public void removeLast() {
        if (head == null) {
            System.out.println("LL is empty");
            return;
        }
        if (size == 1) {
            head = tail = null;
            size = 0;
            return;
        }
        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
        tail = temp;
        size--;
    }

    public Node reverseList(Node start) {
        Node prev = null;
        Node curr = start;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev; 
    }

    public boolean isPalindrome() { 
        if (head == null || head.next == null) {
            return true;
        }
        
        // Find mid
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse right half
        Node rightHead = reverseList(slow); 
        Node left = head;
        Node right = rightHead;
        boolean isPalin = true;

        // Compare
        while (right != null) {
            if (left.data != right.data) {
                isPalin = false;
                break;
            }
            left = left.next;
            right = right.next;
        }

        reverseList(rightHead);
        
        return isPalin;
    }

    public boolean isCycle() { 
        if (head == null) return false;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public void removeCycle() { 
        if (head == null) return;
        
        Node slow = head;
        Node fast = head;
        boolean cycle = false;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                cycle = true;
                break;
            }
        }
        
        if (cycle) { 
            slow = head;
            if(slow == fast) { // edge case if cycle connects exactly at head
                while(fast.next != slow) {
                    fast = fast.next;
                }
            } else {
                while (slow.next != fast.next) {
                    slow = slow.next;
                    fast = fast.next;
                }
            }
            fast.next = null;
        }
    }

    public Node mergeSortLL(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node mid = getMid(head);
        Node rightHead = mid.next;
        mid.next = null; // Sever list 
        
        Node newLeft = mergeSortLL(head);
        Node newRight = mergeSortLL(rightHead);
        
        return merge(newLeft, newRight);
    }

    public Node merge(Node left, Node right) {
        Node dummy = new Node(-1);
        Node temp = dummy;
        while (left != null && right != null) {
            if (left.data < right.data) {
                temp.next = left;
                left = left.next;
            } else {
                temp.next = right;
                right = right.next;
            }
            temp = temp.next;
        }
        while (left != null) {
            temp.next = left;
            left = left.next;
            temp = temp.next;
        }
        while (right != null) {
            temp.next = right;
            right = right.next;
            temp = temp.next;
        }
        return dummy.next;
    }

    public Node getMid(Node head) {
        if (head == null) return null;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void zigZagPrint() {
        if (head == null || head.next == null) {
            print();
            return;
        }

        Node mid = getMid(head);
        Node right = mid.next;
        mid.next = null;
        
        // Reverse right half and capture the new returned head
        right = reverseList(right); 
        Node left = head;
        
        // Merge zigzag style
        while (left != null && right != null) {
            Node leftNext = left.next;
            Node rightNext = right.next;
            
            left.next = right;
            if (leftNext == null) break;
            
            right.next = leftNext;
            
            left = leftNext;
            right = rightNext;
        }
        print();
    }

    public static void main(String[] args) {
        LL ll = new LL();
        ll.addFirst(2);
        ll.addFirst(1);
        ll.addLast(3);
        ll.addLast(4);
        ll.print(); //  1->2->3->4->null
        
        ll.addInMiddle(5, 2); 
        ll.print(); //  1->2->5->3->4->null
        
        System.out.println("Has Cycle: " + ll.isCycle()); //  false
        System.out.println("Is Palindrome: " + ll.isPalindrome()); //  false
        
        // Catch reversed head
        ll.head = ll.reverseList(ll.head);
        ll.print(); //  4->3->5->2->1->null
        
        // Catch sorted head
        ll.head = ll.mergeSortLL(ll.head);
        ll.print(); //  1->2->3->4->5->null
        
        ll.removeFirst();
        ll.print(); //  2->3->4->5->null
        
        ll.removeLast();
        ll.print(); //  2->3->4->null
        
        ll.zigZagPrint(); //  2->4->3->null

        LinkedList<Integer> llnew = new LinkedList<>();// can also define like this via java JCF , it is provided as a Class which implements list interface
    }
}