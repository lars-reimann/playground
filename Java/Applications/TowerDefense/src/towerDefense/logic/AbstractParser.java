package towerDefense.logic;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Ein abstrakter Parser, der einige von allen Parsern ben&ouml;tigte Methoden
 * bereitstellt. Diese Klasse kann somit als Basis f&uuml;r weitere Parser
 * dienen, sodass alle diese von dieser Klasse erben sollten.
 * 
 * @version 27.3.2012
 * @author Lars Reimann
 */
public abstract class AbstractParser {

    /**
     * Wandelt die angegebene Datei in ein Dokument um, welches im Anschluss
     * weiter interpretiert werden kann.
     * 
     * @param file
     *            Die umzuwandelnde Datei.
     * @return Die Baumdarstellung der angegebenen Datei.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    protected Document createDocument(File file)
            throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        Document document = factory.newDocumentBuilder().parse(file);
        document.normalize();
        return document;
    }

    /**
     * Listet alle xml-Dateien im angegebenen Verzeichnis.
     * 
     * @param path
     *            Der Pfad zu den einzulesenden Dateien.
     * 
     * @return Eine Liste der gefundenen xml-Dateien.
     */
    protected File[] listXMLFiles(String path) {
        File directory = new File(path);
        return directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        });
    }

    /**
     * Liest den im ersten Knoten mit dem angegebenen Namen hinterlegten
     * ganzzahligen Wert aus.
     * 
     * @param root
     *            Das Wurzelelement des Dokumentes.
     * @param tagName
     *            Der Name des auszulesenden Knotens.
     * @return Den gelesenen Wert.
     */
    protected int parseIntValue(Element root, String tagName) {
        NodeList tagNodes = root.getElementsByTagName(tagName);
        NodeList nodeValues = tagNodes.item(0).getChildNodes();
        String value = nodeValues.item(0).getNodeValue();
        return Integer.parseInt(value);
    }

    /**
     * Liest die im angegebenen Knoten hinterlegte Ganzzahl aus und gibt diese
     * zur&uuml;ck.
     * 
     * @param node
     *            Der auszulesende Knoten.
     * @return Den gelesenene Wert.
     */
    protected int parseIntValue(Node node) {
        NodeList nodeValues = node.getChildNodes();
        String value = nodeValues.item(0).getNodeValue();
        return Integer.parseInt(value);
    }

    /**
     * Liest den ersten gefundenen Knoten mit dem tagName "name" des Dokumentes
     * aus.
     * 
     * @param root
     *            Das Wurzelelement des Dokumentes.
     * @return Den ausgelesenen Namen.
     */
    protected String parseName(Element root) {
        NodeList tagNodes = root.getElementsByTagName("name");
        NodeList nodeValues = tagNodes.item(0).getChildNodes();
        String name = nodeValues.item(0).getNodeValue();
        return name;
    }
}
