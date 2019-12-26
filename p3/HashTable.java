/**
 * Filename:   HashTable.java
 * Project:    p3
 * Authors:    TODO: add your name(s) and lecture numbers here
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   TODO: add assignment due date and time
 * Version:    1.0
 * 
 * Credits:    TODO: name individuals and sources outside of course staff
 * 
 * Bugs:       TODO: add any known bugs, or unsolved problems here
 */


import java.util.NoSuchElementException;

// TODO: comment and complete your HashTableADT implementation
// DO NOT ADD PUBLIC MEMBERS TO YOUR CLASS
//
// TODO: describe the collision resolution scheme you have chosen
// identify your scheme as open addressing or bucket
// 
// if open addressing: describe probe sequence 
// if buckets: describe data structure for each bucket
//
// TODO: explain your hashing algorithm here 
// NOTE: you are not required to design your own algorithm for hashing,
//       you may use the hashCode provided by the <K key> object
//       
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	
	// TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation
		
	// TODO: comment and complete a default no-arg constructor
	public HashTable() {

	}
	
	// TODO: comment and complete a constructor that accepts initial capacity and load factor
	public HashTable(int initialCapacity, double loadFactor) {

	}

	// TODO: comment and complete this method
	@Override
	public void put(K key, V value) throws IllegalArgumentException {

	}
	
	// TODO: comment and complete this method
	@Override
	public V get(K key) throws IllegalArgumentException, NoSuchElementException {

		return null;
	}
	
	// TODO: comment and complete this method
	@Override
	public void remove(K key) throws IllegalArgumentException, NoSuchElementException {

	}
	
	// TODO: comment and complete this method
	@Override
	public int size() {
		return -1;
	}
		
}
