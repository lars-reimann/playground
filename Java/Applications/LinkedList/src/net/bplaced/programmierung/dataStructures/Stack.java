package net.bplaced.programmierung.dataStructures;

public class Stack<K, V> implements DataStructure {
	private SinglyLinkedList<K, V> list;
	
	public Stack() {
		list = new SinglyLinkedList<K, V>();
	}
	
	public SinglyLinkedNode<K, V> peek() {
		return list.getFirst();
	}
	
	public void push(K key, V value) {
		list.addFirst(key, value);
	}
	
	public SinglyLinkedNode<K, V> pop() {
		return list.removeFirst();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
}
