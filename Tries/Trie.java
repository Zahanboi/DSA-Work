class Trie {
    public class Node{
        Node children[] = new Node[26];
        boolean endOfWord = false;

        Node(){
            for(int i = 0; i< 26; i++){
                children[i] = null;
            }
        }
    }

    Node root; // will exist for the Trie stored in memory when created an object or this class 
    public Trie() { // constructor of class trie not needed directly call via Node class
            root = new Node();
        }

    public void insert(String word) {
        Node curr = root;
        for(int i = 0; i< word.length(); i++){
            int index = word.charAt(i) - 'a';
            if(curr.children[index] == null){
                curr.children[index] = new Node();
            }

            curr = curr.children[index];
        }
        curr.endOfWord = true;
    }
    
    public boolean search(String word) {
        Node curr = root;
        for(int i = 0; i<word.length(); i++){
            int index = word.charAt(i) - 'a';
            if(curr.children[index] == null){
                return false;
            }
            curr = curr.children[index];
        }

        if(curr.endOfWord) return true;
        return false;
    }
    
    public boolean startsWith(String prefix) {
        Node curr = root;
        for(int i = 0; i<prefix.length(); i++){
            int index = prefix.charAt(i) - 'a';
            if(curr.children[index] == null){
                return false;
            }
            curr = curr.children[index];
        }

        return true;
    }

    public static void main(String[] args) {
        Trie obj = new Trie();
        obj.insert("apple");
        System.out.println(obj.search("apple")); // true
        System.out.println(obj.search("app")); // false
        System.out.println(obj.startsWith("app")); // true
        obj.insert("app");
        System.out.println(obj.search("app")); // true
    }
}