package net.bplaced.programmierung.dataStructures;

public class DoublyLinkedList<K, V> implements DataStructure {
	private DoublyLinkedNode<K, V> head;
	private DoublyLinkedNode<K, V> tail;
	private int size;
	
	public DoublyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public DoublyLinkedNode<K, V> getFirst() {
		return head;
	}
	
	public DoublyLinkedNode<K, V> getLast() {
		return tail;
	}
	
	public void addFirst(K key, V value) {
		DoublyLinkedNode<K, V> node = new DoublyLinkedNode<K, V>(key, value, null, head);
		if (isEmpty()) {
			tail = node;
		}
		head = node;
		size++;
	}
	
	public DoublyLinkedNode<K, V> removeFirst() {
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
	
	public void addLast(K key, V value) {
		if (isEmpty()) {
			addFirst(key, value);
		} else {
			DoublyLinkedNode<K, V> node = new DoublyLinkedNode<K, V>(key, value, tail, null);
			tail.setNext(node);
			tail = node;
			size++;
		}
		
	}
	
	public DoublyLinkedNode<K, V> removeLast() {
		if (size <= 1) {
			return removeFirst();
		} else {
			DoublyLinkedNode<K, V> temp = tail;
			tail = tail.getPrevious();
			size--;
			return temp;
		}
	}
	
	/**
	 * @return the number of elements in this list.
	 */
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
