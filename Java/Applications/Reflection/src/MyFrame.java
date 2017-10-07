import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;


public final class MyFrame extends JFrame {
    
    private final JTextPane input;
    private final JTextPane output;
    private static final MyFrame INSTANCE = new MyFrame();
    
    private MyFrame () {
        super();
        getContentPane().setLayout(null);
        
        final JList list = new JList(Utilities.getInstance().getMethods());
        list.addListSelectionListener(new SelectionListener());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        final JScrollPane scrollPane1 = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setBounds(370, 13, 100, 440);
        scrollPane1.setBorder(new LineBorder(Color.black));
        getContentPane().add(scrollPane1);
        
        input = new JTextPane();
        
        final JScrollPane scrollPane2 = new JScrollPane(input, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setBounds(13, 13, 320, 200);
        scrollPane2.setBorder(new LineBorder(Color.black));
        getContentPane().add(scrollPane2);
        
        output = new JTextPane();
        output.setEditable(false);
        
        final JScrollPane scrollPane3 = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane3.setBounds(13, 253, 320, 200);
        scrollPane3.setBorder(new LineBorder(Color.black));
        getContentPane().add(scrollPane3);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Reflection");
        setSize(500, 500);
        setVisible(true);
    }
    
    public void setOutput(final String text) {
        output.setText(text);
    }
    
    public void setInput(final String text) {
        input.setText(text);
    }
    
    public String getOutput() {
        return output.getText();
    }
    
    public String getInput() {
        return input.getText();
    }
    
    public static MyFrame getInstance() {
        return INSTANCE;
    }
}
