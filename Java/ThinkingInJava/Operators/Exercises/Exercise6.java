class Dog {
    private final String name;
    private final String says;
    
    public Dog(String name, String says) {
        this.name = name;
        this.says= says;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSays() {
        return says;
    }
}

public class Exercise6 {
	public static void main (String args[]) {
		Dog spot = new Dog("Spot", "Ruff!");
		Dog scruffy = new Dog("Scruffy", "Wurf!");
        Dog bello = spot;
        
        System.out.println("Spot == Scruffy: " + (spot == scruffy));
        System.out.println("Spot equals Scruffy: " + (spot.equals(scruffy)));
        System.out.println("Spot == Bello: " + (spot == bello));
        System.out.println("Spot equals Bello: " + (spot.equals(bello)));
        System.out.println("Scruffy == Bello: " + (scruffy == bello));
        System.out.println("Scruffy equals Bello: " + (scruffy.equals(bello)));
	}
}
