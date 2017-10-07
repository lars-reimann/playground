package aufgabe2;

public class Node {
	Object data;
	Node next;

	public Node(Object a) {
		data = a;
		next = null;
	}

	public Node(Object a, Node n) {
		data = a;
		next = n;
	}
	
	public Node(Node node) {
		this.data = node.data;
		if (node.next == null) {
			this.next = null;
		} else {
			this.next = new Node(node.next);
		}
	}
	
	@Override
	public String toString() {
		return data.toString();
	}
}
