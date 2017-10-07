package aufgabe3;

/**
 * Oose Aufgabe 3
 * 
 * @version 25.06.2013
 * @author Florian Gläßer, Tobias Lenz, Lars Reimann
 */
class TNode<T> {

	/**
	 * Die Daten des Knotens.
	 */
	private T data;
	
	/**
	 * Eine Referenz auf den nächsten Knoten. Sollte dieser nicht existieren,
	 * d. h. der Knoten ist der letzte in der Liste, hat diese Variable den Wert
	 * null.
	 */
	private TNode<T> next;
	
	TNode(T data) {
		this.data = data;
	}

	TNode(T data, TNode<T> next) {
		this.data = data;
		this.next = next;
	}

	T getData() {
		return data;
	}

	TNode<T> getNext() {
		return next;
	}

	void setData(T nd) {
		data = nd;
	}

	void setNext(TNode<T> next) {
		this.next = next;
	}
}