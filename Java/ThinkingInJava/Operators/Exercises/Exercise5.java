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

public class Exercise5 {
	public static void main (String args[]) {
		Dog spot = new Dog("Spot", "Ruff!");
		Dog scruffy = new Dog("Scruffy", "Wurf!");
        
        System.out.println(spot.getName() + ": " + spot.getSays());
        System.out.println(scruffy.getName() + ": " + scruffy.getSays());
	}
}

