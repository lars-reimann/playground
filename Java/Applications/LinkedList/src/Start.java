import net.bplaced.programmierung.dataStructures.*;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Stack<Integer, Integer> deque = new Stack<Integer, Integer>();
		deque.push(1, 2);
		deque.push(2, 3);
		deque.push(3, 5);
		deque.push(4, 7);
		deque.push(5, 11);
		deque.push(6, 13);
		deque.push(7, 17);
		System.out.println(deque)
		while (!deque.isEmpty()) {
			SinglyLinkedNode<Integer, Integer> node = deque.pop();
		    System.out.println(node.toString());
		}
	}

}
