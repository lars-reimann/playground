package net.bplaced.programmierung.dataStructures;

public class Deque<K, V> implements DataStructure {

	private DoublyLinkedList<K, V> list;
	
	public Deque() {
		list = new DoublyLinkedList<K, V>();
	}
	
	public void push(K key, V value) {
		list.addLast(key, value);
	}
	
	public DoublyLinkedNode<K, V> pop() {
		return list.removeLast();
	}
	
	public DoublyLinkedNode<K, V> get() {
		return list.removeFirst();
	}
	
	public void put(K key, V value) {
		list.addFirst(key, value);
	}
	
	public DoublyLinkedNode<K, V> first() {
		return list.getFirst();
	}
	
	public DoublyLinkedNode<K, V> last() {
		return list.getLast();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
}
