Methoden und Komponenten, die zur Verfügung stehen

protected JTextField tEingabe;
   Die Eingabezeile

protected JButton b1, b2, b3, b4;
	public void actionB1(){};
	public void actionB2(){};
	public void actionB3(){};
	public void actionB4(){};
	
protected JTextArea ta1, ta2, taInfo;
   ta1 ist das erste Textfeld, ta2 das zweite. Neue Texte sollten in 
   ta1 geschrieben werden, bearbeitete in ta2.
   taInfo kann für spezielle Informationen benutzt werden. 

public void zeigeMethoden(String klassenName) 
   zeigt die Methoden der Klasse in taInfo.
   
public void ladeInfo(String dateiName)
   zeigt den Inhalt einer Textdatei in taInfo
