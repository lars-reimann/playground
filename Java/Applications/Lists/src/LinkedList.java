
public class LinkedList<E extends Object> {
	private Element<E> firstElement;
	private Element<E> lastElement;
	
	public void add(final E value) {
		final Element<E> newElement = new Element<E>(value);
		if (firstElement == null && lastElement == null) {
			firstElement = newElement;
			lastElement = newElement;
		} else {
			lastElement.setNext(newElement);
			lastElement = newElement;
		}
	}
	
	public Element<E> get(final int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		} else {
			Element<E> currentElement = firstElement;
			for (int i = 0; i < index; i++) {
				currentElement = currentElement.getNext();
				if (currentElement == null) {
					throw new IndexOutOfBoundsException();
				}
			}
			return currentElement;
		}
	}
	
	public void remove(final int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			firstElement = firstElement.getNext();
		} else {
			Element<E> currentElement = firstElement;
			for (int i = 0; i < index - 1; i++) {
				currentElement = currentElement.getNext();
				if (currentElement == null) {
					throw new IndexOutOfBoundsException();
				}
			}
			currentElement.setNext(currentElement.getNext().getNext());
		}
	}
	
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder("[");
		Element<E> currentElement = firstElement;
		builder.append(currentElement.getValue());
		while (true) {
			currentElement = currentElement.getNext();
			if (currentElement == null) {
				break;
			} else {
				builder.append(", " + currentElement.getValue());
			}
		}
		builder.append("]");
		return builder.toString();
	}
}
