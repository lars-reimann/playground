package net.bplaced.programmierung.dataStructures;

/**
 * Realizes an entry in a singly linked list. Each entry has a pointer to the
 * next element in the list. Furthermore it contains a key and a value. Their
 * datatypes can be specified by generics.
 * 
 * @version 20.02.2013
 * @author Lars Reimann
 * 
 * @param <K>
 *            The datatype of the key.
 * @param <V>
 *            The datatype of the value.
 */
public class SinglyLinkedNode<K, V> extends Node<K, V> {

	/**
	 * A pointer to the next element in the list.
	 */
	private SinglyLinkedNode<K, V> next;

	/**
	 * 
	 * @param key
	 *            The key of this node.
	 * @param value
	 *            The value that is associated with the key.
	 * @param next
	 *            A reference to the next element.
	 */
	public SinglyLinkedNode(K key, V value, SinglyLinkedNode<K, V> next) {
		super(key, value);
		this.next = next;
	}

	/**
	 * @return the reference to the next entry.
	 */
	public SinglyLinkedNode<K, V> getNext() {
		return next;
	}

	/**
	 * @param next
	 *            A reference to the new next element.
	 */
	public void setNext(SinglyLinkedNode<K, V> next) {
		this.next = next;
	}
}
