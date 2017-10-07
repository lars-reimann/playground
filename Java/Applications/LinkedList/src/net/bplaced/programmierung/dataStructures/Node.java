package net.bplaced.programmierung.dataStructures;

/**
 * The abstract realization of a node. It has a key and a value. It does not
 * contain any references to other nodes.
 * 
 * @version 20.02.2013
 * @author Lars Reimann
 * 
 * @param <K>
 *            The datatype of the key.
 * @param <V>
 *            The datatype of the value.
 */
public abstract class Node<K, V> {

	/**
	 * The key of this node.
	 */
	protected K key;

	/**
	 * The key of this node.
	 */
	protected V value;

	/**
	 * @param key
	 *            The key of this node.
	 * @param value
	 *            The value of this node.
	 */
	protected Node(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * @return the key of this node.
	 */
	public K getKey() {
		return key;
	}

	/**
	 * @return the value of this node.
	 */
	public V getValue() {
		return value;
	}

	/**
	 * @param key
	 *            The new key of this node.
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * @param value
	 *            The new value of this node.
	 */
	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
