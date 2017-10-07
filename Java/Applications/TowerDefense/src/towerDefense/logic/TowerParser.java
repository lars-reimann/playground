package towerDefense.logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Liest T&uuml;rme aus xml-Dateien ein.
 * 
 * @version 25.3.2012
 * @author Lars Reimann
 */
public class TowerParser extends AbstractParser {

    /**
     * Liest einen Turm aus der angegebenen Datei ein.
     * 
     * @param file
     *            Die auszulesende Datei
     * @return Den gelesenen Turm.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private Tower parseTower(File file) throws ParserConfigurationException,
            SAXException, IOException {
        Element root = createDocument(file).getDocumentElement();
        String name = parseName(root);
        int price = parseIntValue(root, "price");
        Weapon weapon = parseWeapon(root);
        List<Upgrade> upgrades = parseUpgrades(root);
        return new Tower(name, price, weapon, upgrades);
    }

    /**
     * Liest T&uuml;rme ein und f&uuml;gt sie nach Namen sortiert in eine Map
     * ein.
     * 
     * @return Eine Map mit den nach Namen sortierten eingelesenen T&uuml;rmen.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public Map<String, Tower> parseTowers()
            throws ParserConfigurationException, SAXException, IOException {
        Map<String, Tower> towers = new HashMap<String, Tower>();
        File[] xmlFiles = listXMLFiles("data" + File.separator + "towers");
        for (File file : xmlFiles) {
            Tower tower = parseTower(file);
            towers.put(tower.getName(), tower);
        }
        return towers;
    }

    /**
     * Liest ein einzelnes Upgrade ein.
     * 
     * @param upgradeTag
     *            Die Knoten an dem alle weiteren Daten h&auml;ngen.
     * @return Das gelesene Upgrade.
     */
    private Upgrade parseUpgrade(Element upgradeTag) {
        int price = parseIntValue(upgradeTag, "price");
        int newDamage = parseIntValue(upgradeTag, "newDamage");
        int newRange = parseIntValue(upgradeTag, "newRange");
        int newTimePerShoot = parseIntValue(upgradeTag, "newTimePerShoot");
        return new Upgrade(price, newDamage, newRange, newTimePerShoot);
    }

    /**
     * Liest die Liste der m&ouml;glichen Upgrades des Turmes ein.
     * 
     * @param root
     *            Das Wurzelelement des xml-Dokumentes.
     * @return Die eingelesene Liste der Upgrades.
     */
    private List<Upgrade> parseUpgrades(Element root) {
        List<Upgrade> upgrades = new ArrayList<Upgrade>();
        Element upgradesTag = (Element) root.getElementsByTagName("upgrades")
                .item(0);
        NodeList upgradeTags = upgradesTag.getElementsByTagName("upgrade");
        for (int i = 0; i < upgradeTags.getLength(); i++) {
            upgrades.add(parseUpgrade((Element) upgradeTags.item(i)));
        }
        return upgrades;
    }

    /**
     * Liest die Waffe des Turmes ein.
     * 
     * @param root
     *            Das Wurzelelement des Dokumentes.
     * @return Die eingelesene Waffe.
     */
    private Weapon parseWeapon(Element root) {
        Element weaponTag = (Element) root.getElementsByTagName("weapon").item(
                0);
        int damage = parseIntValue(weaponTag, "damage");
        int range = parseIntValue(weaponTag, "range");
        int timePerShoot = parseIntValue(weaponTag, "timePerShoot");
        return new Weapon(damage, range, timePerShoot);
    }
}
