package aufgabe3;

/**
 * Oose Aufgabe 3
 * 
 * @version 25.06.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
public class TList<T> {

	/**
	 * Kopf der Liste
	 */
	private TNode<T> head;

	/**
	 * Länge der Liste.
	 */
	private int length = 0;

	/**
	 * Gibt die Daten an der angegebenen Stelle in der Liste zurück. Das erste
	 * Element hat den Index 0.
	 * 
	 * @param index
	 *            Der Index von dem die Daten geholt werden sollen.
	 * @return die Daten an der angegebenen Stelle.
	 * @throws IndexOutOfBoundsException
	 *             Diese Ausnahme wird geworfen, wenn das gewünschte Element
	 *             nicht existiert.
	 */
	public T get(int index) {
		if (index < 0 || index >= length) {
			throw new IndexOutOfBoundsException("Index " + index
					+ " ist außerhalb der Grenzen.");
		}
		TNode<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getData();
	}

	/**
	 * Löscht den Knoten an der angegebenen Stelle und gibt dessen Inhalt
	 * zurück. Das erste Element hat den Index 0.
	 * 
	 * @param index
	 *            Der Index des zu löschenden Knotens.
	 * @return die Daten an der angegebenen Stelle.
	 * @throws IndexOutOfBoundsException
	 *             Diese Ausnahme wird geworfen, wenn das gewünschte Element
	 *             nicht existiert.
	 */
	public T delete(int index) {
		if (index < 0 || index >= length) {
			throw new IndexOutOfBoundsException("Index " + index
					+ " ist außerhalb der Grenzen.");
		}
		return index == 0 ? deleteHead() : deleteNode(index);
	}

	/**
	 * Löscht den Kopf der Liste und gibt dessen Inhalt zurück.
	 * 
	 * @return die Daten, die im Kopf stehen.
	 */
	private T deleteHead() {
		TNode<T> res = head;
		head = head.getNext();
		length--;
		return res.getData();
	}

	/**
	 * Löscht einen Knoten, der nicht ganz vorne in der Liste steht. Index muss
	 * somit größer als 0 sein.
	 * 
	 * @param index
	 *            Der Index des zu löschenden Knotens.
	 * @return die Daten im gelöschten Knoten.
	 */
	private T deleteNode(int index) {
		assert index > 0 && index < length;
		TNode<T> current = head;
		for (int i = 0; i < index - 1; i++) {
			current = current.getNext();
		}
		TNode<T> res = current.getNext();
		current.setNext(res.getNext());
		length--;
		return res.getData();
	}

	/**
	 * Gibt die Länge der Liste zurück.
	 * 
	 * @return die Länge der Liste.
	 */
	public int length() {
		return length;
	}

	/**
	 * Fügt einen neuen Knoten in die Liste ein. Dieser steht an der angegebenen
	 * Stelle und hat die angegebenen Daten.
	 * 
	 * @param data
	 *            Die Daten des neuen Knotens.
	 * @param index
	 *            Der Index des einzufügenden Knotens.
	 * @throws IndexOutOfBoundsException
	 *             Diese Ausnahme wird geworfen, wenn der Index negativ ist oder
	 *             größer als die bisherige Länge. Eine solche Liste könnte
	 *             nicht durch korrektes Einfügen eines neuen Knotens entstehen.
	 */
	public void insert(T data, int index) {
		if (index < 0 || index > length) {
			throw new IndexOutOfBoundsException("Index " + index
					+ " ist außerhalb der Grenzen.");
		}
		if (index == 0) {
			insertHead(data);
		} else {
			insertNode(data, index);
		}
	}

	/**
	 * Fügt einen neuen Knoten in die Liste ein. Dieser ist an der angegebenen
	 * Stelle und besitzt die angegebenen Daten. Der Index ist dabei der index
	 * des Knotens in der Liste nach dem Einfügen. Ferner muss der Index größer
	 * als 0 sein.
	 * 
	 * @param data
	 *            Die Daten die im neuen Knoten stehen sollen.
	 * @param index
	 *            Der Index an dem der neue Knoten stehen soll.
	 */
	private void insertNode(T data, int index) {
		assert index > 0 && index < length;
		TNode<T> current = head;
		for (int i = 0; i < index - 1; i++) {
			current = current.getNext();
		}
		current.setNext(new TNode<T>(data, current.getNext()));
		length++;
	}

	/**
	 * Fügt die Daten in einem neuen Knoten als neuen Kopf der Liste ein.
	 * 
	 * @param data
	 *            Die Daten, die in diesem Knoten gehalten werden sollen.
	 */
	private void insertHead(T data) {
		head = new TNode<T>(data, head);
		length++;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[ ");
		for (TNode<T> current = head; current != null; current = current
				.getNext()) {
			builder.append(current.getData());
			if (current.getNext() != null) {
				builder.append(", ");
			}
		}
		builder.append(" ]");
		return builder.toString();
	}
}
