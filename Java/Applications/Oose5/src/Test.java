class C1 {
	static int s = 1;

	void f1() {
		System.out.println("C1::f1");
	}

	void f2() {
		System.out.println("C1::f2");
	}
}

class C2 extends C1 {
	static int s = 2;

	void f1() {
		System.out.println("C2::f1");
	}

	void f3() {
		System.out.println("C2::f3");
	}
}

public class Test {
	static public void main(String[] args) {
		C1 a = new C2();
		C2 b = new C2();
		((C2) a).f1();
		((C1) b).f1();
		System.out.println(a.s);
		b.f1();
		b.f2();
		b.f3();
		// C1 c = new C1(); c.f3();
	    //C1 c = new C1(); ((C2) c).f1();
	}
}