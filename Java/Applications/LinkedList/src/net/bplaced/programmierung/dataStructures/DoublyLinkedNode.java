package net.bplaced.programmierung.dataStructures;

public class DoublyLinkedNode<K, V> extends Node<K, V> {
	private DoublyLinkedNode<K, V> previous;
	private DoublyLinkedNode<K, V> next;

	public DoublyLinkedNode(K key, V value,
			                    DoublyLinkedNode<K, V> previous,
			                    DoublyLinkedNode<K, V> next) {
		super(key, value);
		this.previous = previous;
		this.next = next;
	}

	public DoublyLinkedNode<K, V> getPrevious() {
		return previous;
	}

	public void setPrevious(DoublyLinkedNode<K, V> previous) {
		this.previous = previous;
	}
	
	public DoublyLinkedNode<K, V> getNext() {
		return next;
	}

	public void setNext(DoublyLinkedNode<K, V> next) {
		this.next = next;
	}
}
