class FloatWrapper {
    private float wrapped;
    
    public FloatWrapper(float wrapped) {
        this.wrapped = wrapped;
    }
    
    public float getWrapped() {
        return wrapped;
    }
    
    public void setWrapped(float wrapped) {
        this.wrapped = wrapped;
    }
}

public class Exercise3 {
    private static void nullify(FloatWrapper floatWrapper) {
        floatWrapper.setWrapped(0);
    }
    
    public static void main(String[] args) {
        FloatWrapper floatWrapper = new FloatWrapper(42);
        System.out.println("Before: " + floatWrapper.getWrapped());
        nullify(floatWrapper);
        System.out.println("After: " + floatWrapper.getWrapped());
    }
}


