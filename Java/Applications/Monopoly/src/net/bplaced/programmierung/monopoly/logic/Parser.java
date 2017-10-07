package net.bplaced.programmierung.monopoly.logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// TODO ErrorHandler
public class Parser {

    private Document createDocument(File file)
            throws ParserConfigurationException, SAXException, IOException {
        if (!file.getName().endsWith(".xml")) {
            throw new IllegalArgumentException(
                    "Die Datei ist keine xml-Datei.");
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        Document document = factory.newDocumentBuilder().parse(file);
        if (!"monopoly".equals(document.getDoctype().getName())) {
            throw new IllegalArgumentException(
                    "Die Datei hat nicht den Doctype monopoly.");
        }
        document.normalize();
        return document;
    }

    private Field parseEventField(Node node) {
        String name = parseName(node);
        return new EventField(name);
    }

    private List<Event> parseEvents(Element root) {
        List<Event> events = new ArrayList<Event>();
        // TODO
        return events;
    }

    private List<Field> parseFields(Element root) {
        List<Field> fields = new ArrayList<Field>();
        Map<String, Group> groups = new HashMap<String, Group>();
        NodeList fieldsNodeList = root.getElementsByTagName("felder");
        NodeList values = fieldsNodeList.item(0).getChildNodes();
        int length = values.getLength();
        for (int i = 0; i < length; i++) {
            String nodeName = values.item(i).getNodeName();
            if ("los".equals(nodeName)) {
                fields.add(parseGoField(values.item(i)));
            } else if ("strasse".equals(nodeName)) {
                fields.add(parseStreetField(values.item(i), groups));
            } else if ("ereignis".equals(nodeName)) {
                fields.add(parseEventField(values.item(i)));
            } else if ("steuer".equals(nodeName)) {
                fields.add(parseTaxField(values.item(i)));
            } else if ("bahnhof".equals(nodeName)) {
                fields.add(parseStationField(values.item(i), groups));
            } else if ("gefaengnis".equals(nodeName)) {
                fields.add(parseJailField(values.item(i)));
            } else if ("werk".equals(nodeName)) {
                fields.add(parseSupplyField(values.item(i), groups));
            } else if ("parken".equals(nodeName)) {
                fields.add(parseParkingField(values.item(i)));
            } else if ("verhaftung".equals(nodeName)) {
                fields.add(parseImprisonmentField(values.item(i)));
            }
        }
        return fields;
    }

    private Field parseGoField(Node node) {
        String name = parseName(node);
        int gain = parseIntValue(node);
        return new GoField(name, gain);
    }

    private String parseGroup(Element root) {
        NodeList childNodes = root.getElementsByTagName("gruppe");
        Node child = childNodes.item(0);
        NodeList values = child.getChildNodes();
        String value = values.item(0).getNodeValue();
        return value;
    }

    private Field parseImprisonmentField(Node node) {
        String name = parseName(node);
        return new ImprisonmentField(name);
    }

    private int parseIntValue(Element root, String tag)
            throws IllegalArgumentException {
        NodeList startingCapitals = root.getElementsByTagName(tag); // TODO
        NodeList values = startingCapitals.item(0).getChildNodes();
        String value = values.item(0).getNodeValue();
        if (!value.matches("\\d+")) {
            throw new IllegalArgumentException(
                    "Die Datei stimmt nicht mit der DTD ueberein. "
                            + "Der Tag " + tag
                            + " muss genau eine Ganzzahl als "
                            + "Wert besitzen.");
        }
        return Integer.parseInt(value);
    }

    private int parseIntValue(Node node) {
        NodeList childNodes = node.getChildNodes();
        String value = childNodes.item(0).getNodeValue();
        if (!value.matches("\\d+")) {
            throw new IllegalArgumentException("Der Tag " + node.getNodeName()
                    + " muss genau eine " + "Ganzzahl als Wert besitzen.");
        }
        return Integer.parseInt(value);
    }

    private Field parseJailField(Node node) {
        String name = parseName(node);
        int fine = parseIntValue(node);
        return new JailField(name, fine);
    }

    public Monopoly parseMonopoly(File file)
            throws ParserConfigurationException, SAXException, IOException {
        Element root = createDocument(file).getDocumentElement();
        List<Event> events = parseEvents(root);
        List<Field> fields = parseFields(root);
        int startingCapital = parseIntValue(root, "startkapital");
        int stationRent = parseIntValue(root, "bahnhofMiete");
        int supplyMultiplier = parseIntValue(root, "werkMultiplikator");
        return new Monopoly(events, fields, startingCapital, stationRent,
                supplyMultiplier);
    }

    /**
     * Liest den Namen eines Feldes ein.
     * 
     * @param node
     *            Der Knoten, von dem der Name bestimmt werden soll.
     * @return Den Namen des Knotens.
     */
    private String parseName(Node node) {
        Node nameNode = node.getAttributes().getNamedItem("name");
        return nameNode.getNodeValue();
    }

    private Field parseParkingField(Node node) {
        String name = parseName(node);
        return new ParkingField(name);
    }

    private Rent parseRent(Node node) {
        int normal = parseIntValue((Element) node, "normal");
        int oneHouse = parseIntValue((Element) node, "einHaus");
        int twoHouses = parseIntValue((Element) node, "zweiHaeuser");
        int threeHouses = parseIntValue((Element) node, "dreiHaeuser");
        int fourHouses = parseIntValue((Element) node, "vierHaeuser");
        int hotel = parseIntValue((Element) node, "hotel");
        return new Rent(normal, oneHouse, twoHouses, threeHouses, fourHouses,
                hotel);
    }

    private Field parseStationField(Node node, Map<String, Group> groups) {
        String name = parseName(node);
        Group group;
        if (groups.containsKey("station")) {
            group = groups.get("station");
        } else {
            group = new Group("Station");
            groups.put("station", group);
        }
        int price = parseIntValue(node);
        return new StationField(name, group, price);
    }

    private Field parseStreetField(Node node, Map<String, Group> groups) {
        String name = parseName(node);
        String groupKey = parseGroup((Element) node);
        Group group;
        if (groups.containsKey(groupKey)) {
            group = groups.get(groupKey);
        } else {
            group = new Group(groupKey);
            groups.put(groupKey, group);
        }
        int price = parseIntValue((Element) node, "kosten");
        int housePrice = parseIntValue((Element) node, "hauskosten");
        Rent rent = parseRent(node);
        return new StreetField(name, group, price, housePrice, rent);
    }

    private Field parseSupplyField(Node node, Map<String, Group> groups) {
        String name = parseName(node);
        Group group;
        if (groups.containsKey("supply")) {
            group = groups.get("supply");
        } else {
            group = new Group("Werk");
            groups.put("supply", group);
        }
        int price = parseIntValue(node);
        return new SupplyField(name, group, price);
    }

    private Field parseTaxField(Node node) {
        String name = parseName(node);
        int amount = parseIntValue(node);
        return new TaxField(name, amount);
    }
}
