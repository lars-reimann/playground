package net.bplaced.programmierung.monopoly.logic;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import net.bplaced.programmierung.monopoly.graphics.MonopolyDialogs;

import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) {
        try {
            File file = MonopolyDialogs.requestFile();
            new Parser().parseMonopoly(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}