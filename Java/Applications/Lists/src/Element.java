public class Element<E extends Object> {
	
	private E value;
	private Element<E> next;
	
	public Element(final E value) {
		this.value = value;
	}
	
	public E getValue() {
		return value;
	}
	
	public Element<E> getNext() {
		return next;
	}
	
	public void setNext(final Element<E> next) {
		this.next = next;
	}
	
	public void setValue(final E value) {
		this.value = value;
	}
}
