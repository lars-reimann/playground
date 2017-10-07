class Exercise2 {
    private static String onlyOne = "Only one";
    
    public static void main(String[] args) {
        Exercise2 ex1 = new Exercise2();
        Exercise2 ex2 = new Exercise2();
        System.out.println(ex1.onlyOne == ex2.onlyOne);
    }
}
