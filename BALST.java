import java.util.ArrayList;
import java.util.List;
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
/**
 * 
 * Class to implement a BalanceSearchTree. Can be of type AVL or Red-Black.
 * Note which tree you implement here and as a comment when you submit.
 * 
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class BALST<K extends Comparable<K>, V> implements BALSTADT<K, V> {
	


	private BSTNode<K, V> root;  //root node of tree
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private int numKeys;  //number of keys in tree
	


	public BALST() {
		root =null;
	}

    /**
     * Returns the key that is in the root node of this BST.
     * If root is null, returns null.
     * @return key found at root node, or null
     */
	@Override
	public K getKeyAtRoot() {

		if(root==null)return null;
		return root.key;
	}

    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the left child.
     * If the left child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the left child of the found key
     * 
     * @throws IllegalNullKeyException if key argument is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
	@Override
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {

		if(key==null)throw new IllegalNullKeyException();
		if(get(root,key)==null||get(root,key).left==null)throw new KeyNotFoundException();
		return get(root,key).left.key;
	}

    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the right child.
     * If the right child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the right child of the found key
     * 
     * @throws IllegalNullKeyException if key is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
	@Override
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {

		if(key==null)throw new IllegalNullKeyException();
		
		if(get(root,key)==null||get(root,key).right==null)throw new KeyNotFoundException();
		return get(root,key).right.key;
	}
	
    /**
     * Returns the height of this BST.
     * H is defined as the number of levels in the tree.
     * 
     * If root is null, return 0
     * If root is a leaf, return 1
     * Else return 1 + max( height(root.left), height(root.right) )
     * 
     * Examples:
     * A BST with no keys, has a height of zero (0).
     * A BST with one key, has a height of one (1).
     * A BST with two keys, has a height of two (2).
     * A BST with three keys, can be balanced with a height of two(2)
     *                        or it may be linear with a height of three (3)
     * ... and so on for tree with other heights
     * 
     * @return the number of levels that contain keys in this BINARY SEARCH TREE
     */
	@Override
	public int getHeight() {
		if(root == null||root.isNullLeaf)return 0; //root is null
		if((root.left==null && root.right ==null)||(root.left.isNullLeaf && //root is leaf
				root.right.isNullLeaf))return 1;
		return getHeight(root);
	}
	
	/**
	 * private helper method for getHeight method
	 * recursively calls to get height of tree
	 * 
	 * @param node - tracer node
	 * @return height of tree
	 */
	private int getHeight(BSTNode<K,V> node) {
		if(node==null) return 0;
		return 1+Math.max(getHeight(node.right), getHeight(node.left));
	}

    /**
     * Returns the keys of the data structure in sorted order.
     * In the case of binary search trees, the visit order is: L V R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in-order
     */
	@Override
	public List<K> getInOrderTraversal() {
		List<K> inOrder = new ArrayList<K>();
		inOrderHelper(inOrder,root);
		return inOrder;
	}

	/**
	 * recursive private helper method to get list in order
	 * 
	 * @param inOrder - list to add to
	 * @param node - tracer node
	 */
	private void inOrderHelper(List<K>inOrder,BSTNode<K,V>node) {
		if(node !=null && !node.isNullLeaf) { //traverse if tracer node is not null
			inOrderHelper(inOrder,node.left); //left sub-tree
			inOrder.add(node.key); //visit
			inOrderHelper(inOrder,node.right); //right sub-tree
		}
		return;
		
	}
	
    /**
     * Returns the keys of the data structure in pre-order traversal order.
     * In the case of binary search trees, the order is: V L R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in pre-order
     */
	@Override
	public List<K> getPreOrderTraversal() {
		// TODO Auto-generated method stub
		List<K> preOrder = new ArrayList<K>();
		preOrderHelper(preOrder,root);
		return preOrder;
	}
	
	/**
	 * recursive helper method to get preorder traversal
	 * 
	 * @param preOrder - list to add to
	 * @param node - tracer node
	 */
	private void preOrderHelper(List<K>preOrder,BSTNode<K,V>node) {
		if(node !=null && !node.isNullLeaf) { //traverse if not null
			preOrder.add(node.key); //visit
			preOrderHelper(preOrder,node.left);//left
			preOrderHelper(preOrder,node.right);//right
		}
		return;
		
	}

    /**
     * Returns the keys of the data structure in post-order traversal order.
     * In the case of binary search trees, the order is: L R V 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in post-order
     */
	@Override
	public List<K> getPostOrderTraversal() {
		
		List<K> postOrder = new ArrayList<K>();
		postOrderHelper(postOrder,root);
		return postOrder;
	}
	
	/**
	 * recursive helper method to traverse tree in postorder
	 * 
	 * @param postOrder - list to add to
	 * @param node - tracer node used to traverse
	 */
	private void postOrderHelper(List<K>postOrder,BSTNode<K,V>node) {
		if(node !=null&& !node.isNullLeaf) { //if not null traverse
			postOrderHelper(postOrder,node.left); //left
			postOrderHelper(postOrder,node.right); //right
			postOrder.add(node.key); //visit
		}
		return;
		
	}
	
    /**
     * Returns the keys of the data structure in level-order traversal order.
     * 
     * The root is first in the list, then the keys found in the next level down,
     * and so on. 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in level-order
     */
	@Override
	public List<K> getLevelOrderTraversal() {
		List<K> levelOrder = new ArrayList<K>();
		int h = getHeight(); 
		int i;
		for(i=1;i<h;i++) //call recursive method at each level
			levelOrderHelper(root,i,levelOrder);
		
		return levelOrder;
	}
	
	/**
	 * recursive method to traverse in level order
	 * 
	 * @param node - tracer node used to traverse
	 * @param level - level of tree traversal is at
	 * @param list - list to add to
	 */
	private void levelOrderHelper(BSTNode<K,V> node,int level, List<K>list) {
		if(node != null && !node.isNullLeaf) {//if node isn't null then traverse
		if(level ==1)list.add(node.key); //add node to list since at level
		else if(level>1) { //if not at level then traverse
			levelOrderHelper(node.left,level-1,list);
			levelOrderHelper(node.right,level-1,list);
		}
		}
		return;
		
	}

    /** 
     * Add the key,value pair to the data structure and increase the number of keys.
     * If key is null, throw IllegalNullKeyException;
     * If key is already in data structure, throw DuplicateKeyException(); 
     * Do not increase the num of keys in the structure, if key,value pair is not added.
     */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		if(key == null) throw new IllegalNullKeyException();
		root = insert(null,root,key,value);

		numKeys++;
	}
	
	/**
	 * recursive helper method for inserting node into tree
	 * 
	 * @param parent - parent of this node
	 * @param node - tracer node
	 * @param key - key to insert
	 * @param value - value to insert
	 * @return - root node
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 */
	private BSTNode<K,V> insert(BSTNode<K,V> parent, BSTNode<K,V> node,K key,V value) throws DuplicateKeyException, IllegalNullKeyException {
		if( node  == null || node.isNullLeaf) {
            //if parent is not null means tree is not empty
            //so create a red leaf node
            if(parent != null) {
                return createRedNode(parent, key,value);
            } else { //otherwise create a black root node if tree is empty
                return createBlackNode(key,value);
            }
        }

        if(node.key.compareTo(key) == 0) {
            throw new DuplicateKeyException();
        }
        
        boolean isLeft;//holds which side we go on
        if(node.key.compareTo(key) > 0) {
            BSTNode<K,V> left = insert(node, node.left, key,value);
            
            //if left becomes root parent just return left
            if(left == node.parent) {
                return left;
            }
   
            
            node.left = left;
            isLeft = true;
        } else {
            BSTNode<K,V> right = insert(node, node.right, key,value);
            
            //if right becomes root parent means rotation
            //happened at lower level.
            if(right == node.parent) {
                return right;
            }

            node.right = right;

            isLeft = false;
        }

        if(isLeft) {
            //if we went to left side check to see Red-Red conflict
            if(node.color == RED && node.left.color == RED) {

                BSTNode<K,V> sibling = findSiblingNode(node);

                //if sibling is empty or BLACK
                if(sibling==null || sibling.color == BLACK) {
                    //check if root is left child of its parent
                    if(isLeftChild(node)) {
                        //this is left-left so rotate right
                        rightRotate(node, true);
                    } else {
                        //this creates left-right 
                        // So make root = root.parent because its left child before rotation
                        //is new root of this subtree.
                        rightRotate(node.left, false);
                        //after rotation root should be root's parent
                        node = node.parent;
                        //then do left rotate with change of color
                        leftRotate(node, true);
                    }

                } else {
                    //we have sibling color as RED. So change color of root
                    //and its sibling to Black. And then change color of their
                    //parent to red if their parent is not a root.
                    node.color = BLACK;
                    sibling.color = BLACK;
                    //if parent is root don't change to RED
                    if(node.parent.parent != null) {
                        node.parent.color = RED;
                    }
                }
            }
        } else {
            //this is same case as above just mirrored
            if(node.color == RED && node.right.color == RED) {
                BSTNode<K,V> sibling = findSiblingNode(node);
                if(sibling==null || sibling.color == BLACK) {
                    if(!isLeftChild(node)) {
                        leftRotate(node, true);
                    } else {
                        leftRotate(node.right, false);
                        node = node.parent;
                        rightRotate(node, true);
                    }
                } else {
                    node.color = BLACK;
                    sibling.color = BLACK;
                    if(node.parent.parent != null) {
                        node.parent.color = RED;
                    }
                }
            }
        }
        return node;

	}


    /** 
    * If key is found, remove the key,value pair from the data structure and decrease num keys.
    * If key is not found, do not decrease the number of keys in the data structure.
    * If key is null, throw IllegalNullKeyException
    * If key is not found, throw KeyNotFoundException().
    */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if(key ==null) throw new IllegalNullKeyException();
		
		//check to see if key is in structure, should throw exception if not
		get(key); 
		
		root = remove(root,key);
		//subtract number of keys
		numKeys--;
		return true;
	}
	
	/**
	 * recursive helper method for remove
	 * 
	 * @param node - tracer node
	 * @param key - key of node to remove
	 * @return
	 */
	private BSTNode<K,V> remove(BSTNode<K,V> node,K key){
		if(node == null||node.isNullLeaf)return null; //if node is not found return null
		
		//key is smaller so go left
		if(key.compareTo(node.key)<0)node.left = remove(node.left,key);
		//key is larger so go right
		else if(key.compareTo(node.key)>0)node.right = remove(node.right,key);
		//node is found
		else {
			if(node.right ==null||node.right.isNullLeaf)return node.left;//if no sibling to child then return
			else if(node.left ==null||node.right.isNullLeaf)return node.right;//mirrored
			
			BSTNode<K,V>temp = node;
			
			//find successor 
			node = inOrderSuccessor(temp.right);
			
			//delete node
			node.right = deleteMin(temp.right);
			//assign successors child
			node.left = temp.left;
		}
		return node;
	}
	
	private BSTNode<K,V> inOrderSuccessor(BSTNode<K,V>node){
		if(node.left ==null||node.left.isNullLeaf)return node;//node is found
		else return inOrderSuccessor(node.left);//recursive call to traverse left
	}
	
	private BSTNode<K,V>deleteMin(BSTNode<K,V>node){
		if(node.left==null||node.left.isNullLeaf)return node.right; //node found
		node.left = deleteMin(node.left);//traverse left
		return node;
	}

    /**
     *  Returns the value associated with the specified key
     *
     * Does not remove key or decrease number of keys
     * If key is null, throw IllegalNullKeyException 
     * If key is not found, throw KeyNotFoundException().
     */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if(key==null) throw new IllegalNullKeyException();
	
		if(get(root,key)==null) throw new KeyNotFoundException();
		
		return get(root,key).value;
	}
	
	private BSTNode<K,V> get(BSTNode<K, V> root,K key){
		if(root==null||root.isNullLeaf)return null; //if not found return null
					
		if (key.compareTo(root.key)<0) return get(root.left,key); //traverse left if smaller
		else if (key.compareTo(root.key)>0)return get(root.right,key); //mirrored
		else return root; //if found return

		
	}

    /** 
     * Returns true if the key is in the data structure
     * If key is null, throw IllegalNullKeyException 
     * Returns false if key is not null and is not present 
     */
	@Override
	public boolean contains(K key) throws IllegalNullKeyException {
		if(key==null)throw new IllegalNullKeyException();
		BSTNode<K,V> node = get(root,key);

		if(node==null||node.isNullLeaf)return false;//if not found return false
		
		return true; //true if found
	}

    /**
     *  Returns the number of key,value pairs in the data structure
     */
	@Override
	public int numKeys() {
		return numKeys;
	}

/**
 * Print the tree. 
 *
 * For our testing purposes: all keys that we insert in the tree
 * will have a string length of exactly 2 characters.
 * example: numbers 10-99, or strings aa - zz, or AA to ZZ
 *
 * This makes it easier for you to not worry about spacing issues.
 *
 * You can display in any of a variety of ways, but we should see
 * a tree that we can identify left and right children of each node
 *
 * For example: 
 
   |       |-------50
   |-------40
   |       |-------35
   30
   |-------20
   |       |-------10
   
   Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)
   
   Or, you can display a tree of this kind.
   
       30
       /\
      /  \
     20  40
     /   /\
    /   /  \
   10  35  50 

   Or, you can come up with your own orientation pattern, like this.

   10                 
           20
                   30
   35                
           40
   50                  

   The connecting lines are not required if we can interpret your tree.

 */
	@Override
	public void print() {
		printTree(root,0);

	}
	
	/**
	 * recursive private method to print tree
	 * 
	 * @param node - traversal
	 * @param space - space in between nodes
	 */
	private void printTree(BSTNode<K,V>node,int space) {
		if(node==null || node.isNullLeaf) { //done
			return;
		}
		
		printTree(node.right,space+5); //recursive call to print right side
		for(int i =0; i<space;i++) { //print spaces needed
			System.out.print(" ");
		}
		System.out.println(node.value + " ");//print value of node
		printTree(node.left,space+5);//recursive call down left
	}
	
	/**
	 * constructor for blacknode
	 * @param key - key of node
	 * @param value - value of node
	 * @return - return the node created
	 */
    private BSTNode<K,V> createBlackNode(K key,V value) {
        BSTNode<K,V> node = new BSTNode<K,V>(key,value);
        node.key = key;
        node.color = BLACK;
        
      //make children null
        node.left = createNullLeafNode(node); 
        node.right = createNullLeafNode(node);
        return node;
    }

    /**
     * constructor for null leaf
     * @param parent - parent of the leaf
     * @return - return the null leaf node
     */
    private BSTNode<K,V> createNullLeafNode(BSTNode<K,V> parent) {
        BSTNode<K,V> leaf = new BSTNode<K,V>();
        leaf.color = BLACK;
        //this leaf is null
        leaf.isNullLeaf = true;
        leaf.parent = parent;
        return leaf;
    }

    /**
     * constructor for rednode
     * 
     * @param parent - parent of node
     * @param key
     * @param value
     * @return - node created
     */
    private BSTNode<K,V> createRedNode(BSTNode<K,V> parent, K key, V value) {
        BSTNode<K,V> node = new BSTNode<K,V>();
        node.key = key;
        node.value = value;
        node.color = RED;
        node.parent = parent;
        
        //make null children
       node.left = createNullLeafNode(node);
        node.right = createNullLeafNode(node);
        return node;
    }

    /**
     * method to rotate tree right at node
     * 
     * @param root - node to rotate at
     * 
     * @param changeColor - notifier to change color of nodes
     */
    private void rightRotate(BSTNode<K,V> root, boolean changeColor) {
        BSTNode<K,V> parent = root.parent;
        root.parent = parent.parent; //directs new parent to root
        if(parent.parent != null) {//if parent isn't the root
            if(parent.parent.right == parent) {//if parent is a right subtree 
                parent.parent.right = root; //set root to this
            } else {
                parent.parent.left = root;
            }
        }
        
        BSTNode<K,V> right = root.right;
        root.right = parent;//switch nodes
        parent.parent = root;
        parent.left = right;
        if(right != null) { //if this was a left-right situation
            right.parent = parent;
        }
        if(changeColor) {
            root.color = BLACK;
            parent.color = RED;
        }
    }

    /**
     * method to rotate left at node
     * 
     * @param root
     * @param changeColor
     */
    private void leftRotate(BSTNode<K,V> root, boolean changeColor) { //comments mirror of rightRotate
       BSTNode<K,V> parent = root.parent;
        root.parent = parent.parent;
        if(parent.parent != null) {
            if(parent.parent.right == parent) {
                parent.parent.right = root;
            } else {
                parent.parent.left = root;
            }
        }
        BSTNode<K,V> left = root.left;
        root.left = parent;
        parent.parent = root;
        parent.right = left;
        if(left != null) {
            left.parent = parent;
        }
        if(changeColor) {
            root.color = BLACK;
            parent.color = RED;
        }
    }
    
    /**
     * method to find the sibling of this node
     * 
     * @param root - node to find sibling of
     * @return - sibling if there
     */
    private BSTNode<K,V> findSiblingNode(BSTNode<K,V>root){
    	
    	BSTNode<K,V>parent = root.parent;
    	//if this node is a left child and has a sibling return sibling
    	if(isLeftChild(root)&&(parent.right!=null)) return parent.right;
    	//if this node is a right child and has a sibling return sibling
    	else if(parent.left!=null)return  parent.left;
    	else return null;//no sibling
    }
    
    /**
     * method to find if this node is a left child
     * @param root
     * @return
     */
    private boolean isLeftChild(BSTNode<K,V> root) {
    	BSTNode<K,V> parent = root.parent;
    	if(parent.left ==root) {//if it's a left child return true
    		return true;
    	} else {
    		return false;
    	}
    }
	

	
}
