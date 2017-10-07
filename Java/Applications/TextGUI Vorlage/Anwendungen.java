/**
 * Textverarbeitung
 * 
 * @author Alfred Hermes, Udo Strang
 * @version 20071212
 */
public class Anwendungen extends TextGui {
    
	public Anwendungen() {
		b1.setVisible(true);
		b1.setText("Kopiere Eingabe in Ausgabe");
		b2.setVisible(true);
		b2.setText("Kopiere Ausgabe in Eingabe");
		b3.setVisible(true);
		b3.setText("Loesche Eingabe");
		b4.setVisible(true);
		b4.setText("Loesche Ausgabe");
		zeigeMethoden("Anwendungen");
	}

	public void actionB1() { 
	    copyInput();
	}
	
	public void actionB2() {
	    copyOutput();
	}
	
	public void actionB3() { 
	    deleteInput();
	}
	
	public void actionB4() { 
	    deleteOutput();
	}
	
	public void deleteInput() {
	    taEingabe.setText("");  
	}
	
	public void deleteOutput() {
	    taAusgabe.setText("");  
	};
	    
	public void copyInput() {
		taAusgabe.setText(taEingabe.getText());
	}
	
	public void copyOutput() {
	    taEingabe.setText(taAusgabe.getText());
	}
	
	public void printASCII() {
	    StringBuilder builder = new StringBuilder();
	    for (int i = 32; i <= 127; i++) {
	        builder.append((char) i);
	    }
	    taAusgabe.setText(builder.toString());
	}
	
	public void printNumbers() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("\u00c4 = " + (int) 'Ä' + "\n");
	    builder.append("\u00e4 = " + (int) 'ä' + "\n");
	    builder.append("\u00d6 = " + (int) 'Ö' + "\n");
	    builder.append("\u00f6 = " + (int) 'ö' + "\n");
	    builder.append("\u00dc = " + (int) 'Ü' + "\n");
	    builder.append("\u00fc = " + (int) 'ü' + "\n");
	    builder.append("\u00df = " + (int) 'ß' + "\n");
	    taAusgabe.setText(builder.toString());
	}
	
	public void toLowerCase() {
	    taAusgabe.setText(taEingabe.getText().toLowerCase());
	}
	
	public void deleteDoubleLetters() {
	    String newString = taEingabe.getText();
	    for (int i = 0; i < newString.length() - 1; i++) {
	        if (newString.charAt(i) == newString.charAt(i + 1)) {
	            newString = newString.substring(0, i + 1) + newString.substring(i + 1, newString.length());
	        }
	   }
	   taAusgabe.setText(newString);
    }
}
