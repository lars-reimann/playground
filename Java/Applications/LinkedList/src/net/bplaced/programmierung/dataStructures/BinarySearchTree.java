package net.bplaced.programmierung.dataStructures;

public class BinarySearchTree<K extends Comparable<K>, V> implements DataStructure {

	private DoublyLinkedNode<K, V> root;
	private int size;
	
	public BinarySearchTree() {
		root = null;
		size = 0;
	}
	
	public DoublyLinkedNode<K, V> getFirst() {
		return root;
	}
	
	private void add(DoublyLinkedNode<K, V> root, K key, V value) {
		if (root.getKey().compareTo(key) == 1) {
			if (root.getPrevious() == null) {
				DoublyLinkedNode<K, V> node = new DoublyLinkedNode<K, V>(key, value, null, null);
				root.setPrevious(node);
			} else {
				add(root.getPrevious(), key, value);
			}
		} else if (root.getKey().compareTo(key) == 0) {
			root.setValue(value);
		} else {
			if (root.getNext() == null) {
				DoublyLinkedNode<K, V> node = new DoublyLinkedNode<K, V>(key, value, null, null);
				root.setNext(node);
			} else {
				add(root.getNext(), key, value);
			}
		}
	}
	
	public void add(K key, V value) {
		add(root, key, value);
		DoublyLinkedNode<K, V> node = new DoublyLinkedNode<K, V>(key, value, null, head);
		if (isEmpty()) {
			tail = node;
		}
		head = node;
		size++;
	}
	
	public DoublyLinkedNode<K, V> remove() {
		DoublyLinkedNode<K, V> temp = head;
		
		if (!isEmpty()) {
			head = head.getNext();
			size--;
			if (isEmpty()) {
				tail = null;
			}
		}
		
		return temp;
	}
	
	public int getSize() {
		return size;
	}
	
	/**
	 * This method returns true, if the head is null. Thus, it may behave
	 * differently than checking, if the size of the list is null, depending
	 * on when you do the check.
	 * 
	 * @return if this list is empty.
	 */
	public boolean isEmpty() {
		return head == null; 
	}
}
