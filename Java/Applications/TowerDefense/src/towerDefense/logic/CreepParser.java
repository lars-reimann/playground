package towerDefense.logic;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Liest Gegner aus xml-Dateien ein.
 * 
 * @version 23.3.2012
 * @author Lars Reimann
 */
public class CreepParser extends AbstractParser {

    /**
     * Liest einen Gegner aus der angegebenen Datei ein.
     * 
     * @param file
     *            Die auszulesende Datei.
     * @return Den gelesenen Gegner.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private Creep parseCreep(File file) throws ParserConfigurationException,
            SAXException, IOException {
        Element root = createDocument(file).getDocumentElement();
        String name = parseName(root);
        int hitpoints = parseIntValue(root, "hitpoints");
        int gain = parseIntValue(root, "gain");
        return new Creep(name, hitpoints, gain);
    }

    /**
     * Liest Gegner ein und f&uuml;gt sie nach Namen sortiert in eine Map ein.
     * 
     * @return Eine Map mit den nach Namen sortierten eingelesenen Gegnern.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public Map<String, Creep> parseCreeps()
            throws ParserConfigurationException, SAXException, IOException {
        Map<String, Creep> creeps = new HashMap<String, Creep>();
        File[] xmlFiles = listXMLFiles("data" + File.separator + "creeps");
        for (File file : xmlFiles) {
            Creep creep = parseCreep(file);
            creeps.put(creep.getName(), creep);
        }
        return creeps;
    }
}
