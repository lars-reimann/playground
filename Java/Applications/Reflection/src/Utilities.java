import java.lang.reflect.*;
import java.util.Vector;

public class Utilities {
    
    private static final Utilities INSTANCE = new Utilities();
    
    private Utilities() {
        super();
    }
    
    public Vector<String> getMethods() {
        final Vector<String> vector = new Vector<String>();
        for (Method m : Utilities.class.getDeclaredMethods()) {
            if (!"getMethods".equals(m.getName()) && !"getInstance".equals(m.getName())) {
                vector.add(m.getName());
            }
        }
        return vector;
    }
    
    public void copy() {
        MyFrame.getInstance().setOutput(MyFrame.getInstance().getInput());
    }
    
    public void swap() {
        final String temp = MyFrame.getInstance().getOutput();
        MyFrame.getInstance().setOutput(MyFrame.getInstance().getInput());
        MyFrame.getInstance().setInput(temp);
    }
    
    public void getASCII() {
        final String text = MyFrame.getInstance().getInput();
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            builder.append(text.charAt(i) + " = " + (int) text.charAt(i) + "\n");
        }
        MyFrame.getInstance().setOutput(builder.toString()); 
    }
    
    public void getHexadecimal() {
        try {
            final int number = Integer.parseInt(MyFrame.getInstance().getInput());
            MyFrame.getInstance().setOutput(Integer.toHexString(number));
        } catch (NumberFormatException exeption) {
            MyFrame.getInstance().setOutput("Keine Zahl!");
        }
    }
    
    public void getLetter() {
        try {
            MyFrame.getInstance().setOutput(String.valueOf((char) Integer.parseInt(MyFrame.getInstance().getInput())));
        } catch (NumberFormatException exeption) {
            MyFrame.getInstance().setOutput("Keine Zahl!");
        }
    }
    
    public void clear() {
        MyFrame.getInstance().setInput("");
        MyFrame.getInstance().setOutput("");
    }
    
    public static Utilities getInstance() {
        return INSTANCE;
    }
    
    public void hasDigits() {
        final String text = MyFrame.getInstance().getInput();
        boolean hasDigits = false;
        for (int i = 0; i < text.length(); i++) {
            if ((int) text.charAt(i) >= 48 && (int) text.charAt(i) <= 57) {
                hasDigits = true;
                break;
            }
        }
        if (hasDigits) {
            MyFrame.getInstance().setOutput("Enthaelt Ziffern!"); 
        } else {
            MyFrame.getInstance().setOutput("Enthaelt keine Ziffern!");
        }
    }
    
    public void replaceDigits() {
        final String text = MyFrame.getInstance().getInput();
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            switch ((int) text.charAt(i)) {
                case 48: 
                    builder.append("Null");
                    break;
                case 49: 
                    builder.append("Eins");
                    break;
                case 50:
                    builder.append("Zwei");
                    break;
                case 51:
                    builder.append("Drei");
                    break;
                case 52:
                    builder.append("Vier");
                    break;
                case 53:
                    builder.append("Fuenf");
                    break;
                case 54:
                    builder.append("Sechs");
                    break;
                case 55:
                    builder.append("Sieben");
                    break;
                case 56:
                    builder.append("Acht");
                    break;
                case 57:
                    builder.append("Neun");
                    break;
                default:
                    builder.append(text.charAt(i));
                    break;
            }
        }
        MyFrame.getInstance().setOutput(builder.toString());
    }
}
