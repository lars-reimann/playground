package aufgabe2;

public class Start {
	public static void main(String[] args) {
		List list1 = new List();
		list1.add(3);
		list1.add(5);
		list1.add(7);
		System.out.println("List1: " + list1);
		
		List list2 = new List();
		list2.add(2);
		list2.add(4);
		list2.add(6);
		System.out.println("List2: " + list2);
		
		List append12 = list1.appendList(list2);
		System.out.println("Append 1 -> 2: " + append12);
				
		List append21 = list2.appendList(list1);
		System.out.println("Append 2 -> 1: " + append21);
		
		list1.concatList(list2);
		System.out.println("Concat 1 -> 2: " + list1);
	}
}
