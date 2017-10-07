package net.bplaced.programmierung.monopoly.graphics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import net.bplaced.programmierung.monopoly.logic.Player;

public class MonopolyDialogs {
    
    public static File requestFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(MonopolyFileFilter.INSTANCE);
        int option = fileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            throw new IllegalArgumentException("Keine Datei gewaehlt.");
        }      
    }
    
    private static class MonopolyFileFilter extends FileFilter {

        public static final MonopolyFileFilter INSTANCE = new MonopolyFileFilter();
        
        private MonopolyFileFilter() {
            if (INSTANCE != null) {
                throw new AssertionError("Der private Konstruktor ist von ausserhalb aufgerufen worden.");
            }
        }
        
        @Override
        public boolean accept(File arg0) {
            return arg0.getName().endsWith(".xml") || arg0.isDirectory();
        }

        @Override
        public String getDescription() {
            return "xml-Dateien";
        }
        
    }
    
    public static void requestFailed(String error) {
        JOptionPane.showMessageDialog(null, "Fehler: " + error + "Das Programm wird beendet.");
    }
    
    public static List<String> requestPlayerNames(JFrame frame) {
        List<String> playerNames = new ArrayList<String>();
        int nPlayers = Integer.parseInt(JOptionPane.showInputDialog(frame, "Wie viele Spieler sollen mitspielen?"));
        for (int i = 0; i < nPlayers; i++) { // TODO Sicherheit bei zu hoher Zahl (maximal 8)
            String name = JOptionPane.showInputDialog(frame, "Name des " + (i + 1) + ". Spielers:");
            playerNames.add(name);
        }
        return playerNames;
    }
}
