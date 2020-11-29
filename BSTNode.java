//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: p2_project
//
// Files: BALST.java, BALSTTest.java, BALSTADT.java, BSTNode.java
//
// Description: For this assignment, you are required to implement the BALST (Balanced Search Tree)
// class, you may choose to implement either AVL Tree or a Red-Black Tree
// implementation. Along with your BALST class, you will be writing a JUnit test
// class that shows that your implementations are complete and correct. Be sure to
// identify which balanced search tree you choose to implement in the class header
// and in a comment during submission.
//
// Course: CS400, Fall, 2019
//
// Author: Nathan Smith
// Email: nvsmith@wisc.edu
// Lecturer's Name: Professor Deppeler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

// Students may use and edit this class as they choose
// Students may add or remove or edit fields, methods, constructors for this class
// Students may inherit from an use this class in any way internally in other classes.
// Students are not required to use this class. 
// BUT, IF YOUR CODE USES THIS CLASS, BE SURE TO SUBMIT THIS CLASS
//
// RECOMMENDED: do not use public or private visibility modifiers
// and make this an inner class in your tree implementation.
//
// package level access means that all classes in the package can access directly.
// and accessing the node fields from classes other than your balanced search 
// is bad design as it creates many more chances for bugs to be introduced and not
// caught.
//
// Classes that use this type: BALST.java
class BSTNode<K,V> {
    
    K key;
    V value;
    BSTNode<K,V> left; 
    BSTNode<K,V> right;
    BSTNode<K,V>parent; //node that points to this node
    boolean color; //color of node
    boolean isNullLeaf; //if this is a null leaf created
    static final boolean RED = true;
    static final boolean BLACK = false;
    
    BSTNode(){
    	
    }

    /**
     * @param key
     * @param value
     * @param leftChild
     * @param rightChild
     */
    BSTNode(K key, V value) {
        this.key = key;
        this.value = value;
        left = null;
        right = null;
        color = RED;
    }
    
}
