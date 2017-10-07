public class Exercise10 {
    
    private boolean target;
    
    public Exercise10() {
        target = false;
    }
    
    public void aim() {
        target = true;
    }
    
    @Override
    protected void finalize() {
        System.out.println("Finalize");
        if (!target) {
            System.out.println("Aim before you shoot!");

            // Uncaught exceptions inside finalizers are ignored!
            throw new IllegalStateException("Do not finalize the innocent!");
        }
    }
    
    /**
     * When tested with i in range [0, 265000[ the finalizer was executed only
     * once. For smaller values it is likely not called at all.
     */
    public static void main(String[] args) {
        Exercise10 ex;
        for (int i = 0; i < 265000; i++) {
            ex = new Exercise10();
        }
    }
}
