import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title: p2_project
//
//Files: BALST.java, BALSTTest.java, BALSTADT.java, BSTNode.java
//
//Description: For this assignment, you are required to implement the BALST (Balanced Search Tree)
//class, you may choose to implement either AVL Tree or a Red-Black Tree
//implementation. Along with your BALST class, you will be writing a JUnit test
//class that shows that your implementations are complete and correct. Be sure to
//identify which balanced search tree you choose to implement in the class header
//and in a comment during submission.
//
//Course: CS400, Fall, 2019
//
//Author: Nathan Smith
//Email: nvsmith@wisc.edu
//Lecturer's Name: Professor Deppeler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully
//acknowledge and credit those sources of help here. Instructors and TAs do
//not need to be credited here, but tutors, friends, relatives, room mates
//strangers, etc do. If you received no outside help from either type of
//source, then please explicitly indicate NONE.
//
//Persons: NONE
//Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

// TODO: Add tests to test the tree is balanced or not

@SuppressWarnings("rawtypes")
public class BALSTTest {

    BALST<String,String> balst1;	
    BALST<Integer,String> balst2;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        balst1 = createInstance();
	balst2 = createInstance2();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
        balst1 = null;
	balst2 = null;
    }

    protected BALST<String, String> createInstance() {
        return new BALST<String,String>();
    }

    protected BALST<Integer,String> createInstance2() {
        return new BALST<Integer,String>();
    }

    /** 
     * Insert three values in sorted order and then check 
     * the root, left, and right keys to see if rebalancing 
     * occurred.
     */
    @Test
    void testBALST_001_insert_sorted_order_simple() {
        try {
            balst2.insert(10, "10");
            if (!balst2.getKeyAtRoot().equals(10)) 
                fail("rbt insert at root does not work");
            
            balst2.insert(20, "20");
            if (!balst2.getKeyOfRightChildOf(10).equals(20)) 
                fail("rbt insert to right child of root does not work");
            
            balst2.insert(30, "30");
            Integer k = balst2.getKeyAtRoot();
            if (!k.equals(20)) 
                fail("Tri-Node restructure does not work");
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
            Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));

            balst2.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception RBT 000: "+e.getMessage() );
        }
    }

    /** 
     * Insert three values in reverse sorted order and then check 
     * the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testBALST_002_insert_reversed_sorted_order_simple() {
        
        try {
            balst2.insert(30, "30");
            if (!balst2.getKeyAtRoot().equals(30)) 
                fail("RBT insert at root does not work");
            
            balst2.insert(20, "20");
            if (!balst2.getKeyOfLeftChildOf(30).equals(20)) 
                fail("RBT insert to right child of root does not work");
            
            balst2.insert(10, "10");
            Integer k = balst2.getKeyAtRoot();
            if (!k.equals(20)) 
                fail("Tri-Node restructure does not work");
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
            Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));

            balst2.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception RBT 000: "+e.getMessage() );
        }
        
    }

    /** 
     * Insert three values so that a right-left rotation is
     * needed to fix the balance.
     * 
     * Example: 10-30-20
     * 
     * Then check the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testBALST_003_insert_smallest_largest_middle_order_simple() {
        
        try {
            balst2.insert(10, "10");
            if (!balst2.getKeyAtRoot().equals(10)) 
                fail("rbt insert at root does not work");
            
            balst2.insert(30, "30");
            if (!balst2.getKeyOfRightChildOf(10).equals(30)) 
                fail("rbt insert to right child of root does not work");
            
            balst2.insert(20, "20");
            Integer k = balst2.getKeyAtRoot();
            if (!k.equals(20)) 
                fail("Tri-Node restructure does not work");
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
            Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));

            balst2.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception RBT 000: "+e.getMessage() );
        }
        
    }

    /** 
     * Insert three values so that a left-right rotation is
     * needed to fix the balance.
     * 
     * Example: 30-10-20
     * 
     * Then check the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testBALST_004_insert_largest_smallest_middle_order_simple() {
        
        try {
            balst2.insert(30, "30");
            if (!balst2.getKeyAtRoot().equals(30)) 
                fail("rbt insert at root does not work");
            
            balst2.insert(10, "10");
            if (!balst2.getKeyOfLeftChildOf(30).equals(10)) 
                fail("rbt insert to right child of root does not work");
            
            balst2.insert(20, "20");
            Integer k = balst2.getKeyAtRoot();
            if (!k.equals(20)) 
                fail("Tri-Node restructure does not work");
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
            Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));

            balst2.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception RBT 000: "+e.getMessage() );
        }
        
    }
// TODO: Add your own tests
    
    // Add tests to make sure that rebalancing occurs even if the 
    // tree is larger.   Does it maintain it's balance?
    // Does the height of the tree reflect it's actual height
    // Use the traversal orders to check.
    
    // Can you insert many and still "get" them back out?
    
    // Does delete work?  Does the tree maintain balance when a key is deleted?

    
    /** 
     * Checks boundary conditions for empty tree
     * 
     * checks correct exceptions are thrown, correct values, and empty lists
     */
    @Test
    void testBALST_005_various_empty_tree_operations() {
        
        try {
        
        	if (balst2.getKeyAtRoot()!=null) 
                fail("getKeyAtRoot should return null when empty");
        	if(balst2.getHeight()!=0)
        		fail("tree is empty height should be 0");
        	if(balst2.contains(1))
        		fail("calling contains on empty tree should return false");
        	if(balst2.numKeys()!=0)
        		fail("num keys should be 0");
        	
        	
        	Assertions.assertThrows(KeyNotFoundException.class,() ->{ balst2.remove(1);});
        	Assertions.assertThrows(KeyNotFoundException.class,() ->{ balst2.get(1);});
        	Assertions.assertThrows(KeyNotFoundException.class,() ->{ balst2.getKeyOfLeftChildOf(1);});
        	Assertions.assertThrows(KeyNotFoundException.class,() ->{ balst2.getKeyOfRightChildOf(1);});


        	Assert.assertEquals(balst2.getInOrderTraversal(), new ArrayList<>());
        	Assert.assertEquals(balst2.getLevelOrderTraversal(), new ArrayList<>());
        	Assert.assertEquals(balst2.getPostOrderTraversal(), new ArrayList<>());
        	Assert.assertEquals(balst2.getPreOrderTraversal(), new ArrayList<>());

            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception RBT 000: "+e.getMessage() );
        }
        
    }
    /**
     * insert in balanced order
     */
    @Test
    void testBALST_006_insert_a_few_balanced() {
        
        try {
        	balst2.insert(32, "32");
        	balst2.insert(16, "16");
        	balst2.insert(50, "50");
        	balst2.insert(8, "8");
        	balst2.insert(25, "25");
        	balst2.insert(40, "40");
        	balst2.insert(60, "60");

        	if(balst2.getKeyAtRoot()!=32)
        		fail("root should be 32");
        	if(balst2.getKeyOfLeftChildOf(32)!=16)
        		fail("left child of root should be 16");
        	if(balst2.getKeyOfRightChildOf(32)!=50)
        		fail("right child of root should be 50");
        	
        	
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception RBT 000: "+e.getMessage() );
        }
        
    }
    
    /**
     * insert in unbalanced order
     */
    @Test
    void testBALST_007_insert_a_few_unbalanced() {
        
        try {
        	balst2.insert(8, "8");
        	balst2.insert(9, "9");
        	balst2.insert(20, "20");
        	balst2.insert(32, "32");
        	balst2.insert(36, "36");
        	balst2.insert(40, "40");
        	balst2.insert(60, "60");
        	balst2.insert(65, "65");

        	if(balst2.getKeyAtRoot()!=32)
        		fail("root should be 32");
        	if(balst2.getKeyOfLeftChildOf(32)!=9)
        		fail("left child of root should be 9");
        	if(balst2.getKeyOfRightChildOf(32)!=40)
        		fail("right child of root should be 40");
        	
        	
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception RBT 000: "+e.getMessage() );
        }
        
    }
    
    /**
     * test functionality of remove method
     */
    @Test
    void testBALST_008_test_remove_functionality() {
        
        try {
        	balst2.insert(8, "8");
        	balst2.insert(9, "9");
        	balst2.insert(20, "20");
        	balst2.insert(32, "32");
        	balst2.insert(36, "36");
        	balst2.insert(40, "40");
        	balst2.insert(60, "60");
        	balst2.insert(65, "65");

        	balst2.remove(32);//remove root of tree
        	if(balst2.getKeyAtRoot()!=36)
        		fail("root should be 36 after removing 32");
        	
        	
        	balst2.remove(8);
        	balst2.remove(9);
        	balst2.remove(20);
        	balst2.remove(36);
        	balst2.remove(40);
        	balst2.remove(60);
        	balst2.remove(65);
        	balst2.print();
        	//remove all of nodes
        	if(balst2.getHeight()!=0)
        		fail("height should be 0 when removing all items height is"+
        				balst2.getHeight());
        	if(balst2.numKeys()!=0)
        		fail("should be 0 keys in tree");
        	
        	
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception RBT 000: "+e.getMessage() );
        }
        
    }
    
	

    
    
    
}
