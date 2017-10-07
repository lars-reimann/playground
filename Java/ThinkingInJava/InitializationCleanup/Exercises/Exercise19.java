public class Exercise19 {
    
    public static void print(String... strings) {
        for (String s : strings) {
            System.out.println(s);
        }
    }
    
    public static void main(String[] args) {
        print("Hallo", "Welt");
        print(new String[]{"Hallo", "Welt"});
    }
}
