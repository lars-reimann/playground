package aufgabe2;

public class List {
	private Node head; // Kopf der Liste

	public List() {
		head = null; // null repräsentiert leere Liste
	}
	
	/**
	 * Fügt an das Ende der this-Liste die Liste a an. Verändert dabei die
	 * this-Liste.
	 */
	public void concatList(List a) {
		if (head == null) {
			head = a.head;
		} else {
			findTail().next = a.head;
		}
	}

	/**
	 * Erzeugt eine neue Liste durch Anfügen der Liste a an die this-Liste.
	 * Verändert dabei die this-Liste nicht.
	 */
	public List appendList(List a) {
		List res = new List();
		res.head = new Node(this.head);

		if (res.head == null) {
			res.head = a.head;
		} else {
			res.findTail().next = a.head;
		}
		return res;
	}

	/**
	 * Findet das letzte Element in der Liste und gibt dieses zurück.
	 * 
	 * @return das letzte Element in der Liste.
	 */
	private Node findTail() {
		Node current = head;
		if (current != null) {
			while (current.next != null) {
				current = current.next;
			}
		}
		return current;
	}

	/**
	 * Fügt das angegebene Objekt an das Ende dieser Liste hinzu.
	 * 
	 * @param obj
	 *            Das hinzuzufügende Objekt.
	 */
	public void add(Object obj) {
		if (head == null) {
			head = new Node(obj);
		} else {
			findTail().next = new Node(obj);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[ ");
		for (Node current = head; current != null; current = current.next) {
			builder.append(current.data);
			if (current.next != null) {
				builder.append(", ");
			}
		}
		builder.append(" ]");
		return builder.toString();
	}
}
