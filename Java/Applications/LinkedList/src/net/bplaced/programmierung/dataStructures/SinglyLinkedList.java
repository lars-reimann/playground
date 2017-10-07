package net.bplaced.programmierung.dataStructures;

public class SinglyLinkedList<K, V> implements DataStructure {
	private SinglyLinkedNode<K, V> head;
	private SinglyLinkedNode<K, V> tail;
	private int size;
	
	/**
	 * 
	 */
	public SinglyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public SinglyLinkedNode<K, V> getFirst() {
		return head;
	}
	
	public SinglyLinkedNode<K, V> getLast() {
		return tail;
	}
	
	public V search(K key) {
		SinglyLinkedNode<K, V> temp = head;
		while (temp != null) {
			if (temp.getKey() == key) {
				return temp.getValue();
			}
			temp = temp.getNext();
		}
		return null;
	}
	
	public void addFirst(K key, V value) {
		SinglyLinkedNode<K, V> node = new SinglyLinkedNode<K, V>(key, value, head);
		if (isEmpty()) {
			tail = node;
		}
		head = node;
		size++;
	}
	
	public SinglyLinkedNode<K, V> removeFirst() {
		SinglyLinkedNode<K, V> temp = head;
		
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
			SinglyLinkedNode<K, V> node = new SinglyLinkedNode<K, V>(key, value, null);
			tail.setNext(node);
			tail = node;
			size++;
		}
		
	}
	
	public SinglyLinkedNode<K, V> removeLast() {
		if (size <= 1) {
			return removeFirst();
		} else {
			SinglyLinkedNode<K, V> temp = head;
			while (temp.getNext() != tail) {
				temp = temp.getNext();
			}
			temp.setNext(null);
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
