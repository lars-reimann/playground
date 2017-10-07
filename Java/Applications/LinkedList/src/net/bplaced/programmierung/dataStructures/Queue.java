package net.bplaced.programmierung.dataStructures;

public class Queue<K, V> implements DataStructure {
	private SinglyLinkedList<K, V> list;
	
	public Queue() {
		list = new SinglyLinkedList<K, V>();
	}
	
	public void enter(K key, V value) {
		list.addLast(key, value);
	}
	
	public SinglyLinkedNode<K, V> leave() {
		return list.removeFirst();
	}
	
	public SinglyLinkedNode<K, V> front() {
		return list.getFirst();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	
}
