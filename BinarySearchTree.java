/************************************************
  * Compilation : javac BinaryTree.java
  * Execution   : java BinaryTree
  * Dependencies
  * 
  * The BinarySearchTree class contains implementation of a BinaryTree node and 
  * various algorithms for traversal and element finding in Binary Search Trees
  * 
  * @author Parikshit Sharma
  * @version 1.0 10/14/2014
  ***********************************************/

import java.util.Stack;
import java.util.NoSuchElementException;

public class BinarySearchTree <Key extends Comparable<Key>, Value> {
    
    private BinaryTreeNode root;        // Represents the root of the tree 
    int size = 0;                       // Holds the number of elements
    
    
    /* Binary Tree node implementation using generics and immutable types */
    private class BinaryTreeNode { 
        
        public final Key key;          // Contains the key for the node
        public final Value value;      // Contains the data/value for the node
        public BinaryTreeNode left;    // Points to the left child
        public BinaryTreeNode right;   // Points to the right child
        public BinaryTreeNode parent;  // Points to the parent of the node
        
        /* Constructor to initialize a node of the Binary Tree */
        public BinaryTreeNode(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }    // end of Binary Tree node class definition
    
    
    /**
     * Returns the root of the binary tree. 
     * The caller should check if it is null 
     * @return BinaryTreeNode representing the root
     */
    public BinaryTreeNode getRoot() {
        return this.root;
    }
    
    
    /**
     * Checks if the binary tree is empty and returns boolean true or false
     * @return boolean TRUE if size == 0, FALSE otherwise
     */
    public boolean isEmpty(){
        return this.size == 0 ;
    }
    
    /**
     * Inserts an elements into the binary tree
     * @param key : Used to maintain the binary search tree property
     * @param value : Value associated with a node
     */
    public void insert(Key key, Value value) {
        BinaryTreeNode position = root;          // Holds the current search position
        BinaryTreeNode parent = position.parent; // Holds the parent of the search position 
        
        /* At the end of the loop position will be null and parent will point 
        to the parent of position where the new node is to be inserted */
        while (position != null) {
            parent = position;
            if (key.compareTo(position.key) <= 0)
                position = position.left;
            else
                position = position.right;
        }
        BinaryTreeNode child = new BinaryTreeNode(key, value);
        child.parent = parent;        // Set the parent of the new node
        if (parent == null)           // This is the first element
            root = child;
        else if (key.compareTo(parent.key) <= 0)
            parent.left = child;
        else
            parent.right = child; 
        size ++;
    }
    
    
    /** 
     * Searches for a node in the BST and returns a pointer to it
     * @param key : the key to search in the tree
     * @return : BinaryTreeNode pointer to the element if found, null otherwise
     */
    public BinaryTreeNode search(Key key) {
        BinaryTreeNode position = root;    // start position for search
        while (position != null && position.key.compareTo(key) != 0) {
            if (key.compareTo(position.key) < 0)
                position = position.left;
            else
                position = position.right;
        }
        return position;
    }
    
    /**
     * Recursive implementation of the search algorithm.
     * This needs to be called with a pointer to the root
     */
    public BinaryTreeNode search_R(BinaryTreeNode root, Key key) {
        if (root == null || root.key.compareTo(key) == 0)
            return root;
        if (key.compareTo(root.key) < 0)
            return search_R(root.left, key);
        else
            return search_R(root.right, key);
    }
        
    
    /**
     * Returns the pointer to the maximum element in a tree rooted at root
     * @param root : The root of the BST whose maximum is to be searched
     * @return : BinaryTreeNode pointer to the maximum element
     * @throws NoSuchElementException if the tree is null
     */
    public BinaryTreeNode maximum(BinaryTreeNode root) throws Exception{
        if (root == null)
            throw new NoSuchElementException();
        while (root.right != null)
            root = root.right;
        return root;
    }
    
    /**
     * Returns the pointer to the minimum element in a tree rooted at root
     * @param root : The root of the BST whose minimum is to be searched
     * @return : BinaryTreeNode pointer to the minimum element
     * @throws NoSuchElementException if the tree is null
     */
    public BinaryTreeNode minimum(BinaryTreeNode root) throws Exception{
        if (root == null)
            throw new NoSuchElementException();
        while (root.left != null)
            root = root.left;
        return root;
    }
    
    /**
     *Finds the successor to an element in the BST
     * @param x : The BinaryTreeNode element whose successor is to be found
     * @return : BinaryTreeNode pointer to the successor
     * @throws : Exception if @param x is null or no successor exists
     */
    public BinaryTreeNode successor (BinaryTreeNode x) throws Exception {
        /* If x has a right child successor is the smallest right child
        else successor is the first ancestor whose left child 
        is an ancestor of x 
        */
        if (x == null)
            throw new NoSuchElementException();
        if (x.right != null)
            return minimum(x.right);
        BinaryTreeNode search = x.parent;   // searches for the ancestor 
        while (search != null && search.left != x) {
            x = search;
            search = search.parent;
        }
        if (search == null)
            throw new NoSuchElementException();
        return search;
    }
    
    /**
     * Finds the predecessor to an element in the BST
     * @param x : The BinaryTreeNode element whose predecessor is to be found
     * @return : BinaryTreeNode pointer to the predecessor
     * @throws : Exception if @param x is null or no predecessor exists
     */
    public BinaryTreeNode predecessor(BinaryTreeNode x) throws Exception {
        /* If x has a left child then the predecessor is the maximum
         * element in the left subtree of x else the predecessor if the first 
         * ancestor whose right child is also an ancestor of x
         */
        
        if (x == null)
            throw new NoSuchElementException();
        if (x.left != null)
            return maximum(x.left);
        BinaryTreeNode search = x.parent;  // Searches for the ancestor
        while (search !=null && x == search.left) {
            x = search;
            search = search.parent;
        }
        if (search == null)
            throw new NoSuchElementException();
        return search;
    }
    
    
    
    /**
     * Deletes a node from the binary tree. 
     * @param BinaryTreeNode node : a pointer to the node of Binary Tree found 
     * using search
     */
    public void delete(BinaryTreeNode node) {
        
    }
    
    
    
    
    /**
     * Recursive implementation for a pre-order traversal of a Binary Tree
     * @param root : Root of the binary tree to be traversed
     */
    public void preOrderTraversal_R(BinaryTreeNode root) {
        if (root != null) { 
            System.out.println(root.value);
            preOrderTraversal_R(root.left);
            preOrderTraversal_R(root.right);
        }
    }
    
    /**
     * Non recursive implementatio of a pre-order traversal of a Binary Tree
     * Implemenation makes use of Java.util.Stack to store nodes that need to
     * be visited
     * 
     * @param root : The root of the tree that needs to be traversed
     */
    
    public  void preOrderTraversal_NR(BinaryTreeNode root) {
        
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        stack.push(root);
        BinaryTreeNode curr;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            System.out.println(curr.value);
            if (curr.right != null)
                stack.push(curr.right);
            if (curr.left != null)
                stack.push(curr.left);
        }
    }
    
    
    
    public static void main(String[] args) throws Exception {
        int numNodes = 3;
        System.out.println("Creating balances binary tree with " + numNodes + " nodes");
        BinaryTree tree = new BinaryTree();
    }
    
 }