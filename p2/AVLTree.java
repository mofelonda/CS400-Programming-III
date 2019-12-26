/**
 * Filename:   AVLTree.java
 * Project:    p2
 * Authors:    Debra Deppeler, TODO: add your name here
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * Lecture:    TODO: replace with your lecture number
 * 
 * Due Date:   Before 10pm on September 24, 2018
 * Version:    1.0
 * 
 * Credits:    TODO: name individuals and sources outside of course staff
 * 
 * Bugs:       no known bugs, but not complete either
 */

import java.lang.IllegalArgumentException;

/** TODO: add class header comments here
 * @param <K>
 */
public class AVLTree<K extends Comparable<K>> implements AVLTreeADT<K> {

	/** TODO: add class header comments here
	 * Represents a tree node.
	 * @param <K>
	 */
	class BSTNode<K> {
		/* fields */
		private K key;	// TODO: variable declaration comment
		private int height;	// TODO: variable declaration comment
		private BSTNode<K> left, right;	// TODO: variable declaration comment
		
		/**
		 * Constructor for a BST node.
		 * @param key
		 */
		BSTNode(K key) {
			// TODO: implement constructor
		}

		/* accessors */
		// TODO: add accessor methods for private fields here

		/* mutators */
		// TODO: add mutator methods for private fields here
	}
	
	/**
	 * TODO: add method header comments here
	 */
	@Override
	public boolean isEmpty() {
		// TODO: implement isEmpty()
		return false;
	}

	/**
	 * TODO: add method header comments here
	 */
	@Override
	public void insert(K key) throws DuplicateKeyException, IllegalArgumentException {
		// TODO: implement insert()
	}

	/**
	 * TODO: add method header comments here
	 */
	@Override
	public void delete(K key) throws IllegalArgumentException {
		// TODO: implement delete()
	}

	/**
	 * TODO: add method header comments here
	 */
	@Override
	public boolean search(K key) throws IllegalArgumentException {
		// TODO: implement search()
		return false;
	}

	/**
	 * TODO: add method header comments here
	 */
	@Override
	public String print() {
		// TODO: implement print()
		return "";
	}

	/**
	 * TODO: add method header comments here
	 */
	@Override
	public boolean checkForBalancedTree() {
		// TODO: implement checkForBalancedTree()
		return false;
	}

	/**
	 * TODO: add method header comments here
	 */
	@Override
	public boolean checkForBinarySearchTree() {
		// TODO: implement checkForBinarySearchTree()
		return false;
	}
}
