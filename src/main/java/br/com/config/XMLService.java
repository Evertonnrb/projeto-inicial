package br.com.config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author - Everton Ribeiro
 * @email - everton.nrb@gmail.com
 **/
public class XMLService {
    private Document doc;

    public XMLService(String fileName) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);
        try {
            configure(stream);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

    }

    private void configure(InputStream xml) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance();
        builder.setNamespaceAware(true);
        DocumentBuilder documentBuilder = builder.newDocumentBuilder();
        this.doc = documentBuilder.parse(xml);
    }

    public String fileValue(String nome) {
        Element element = doc.getDocumentElement();
        NodeList nodeList = element.getElementsByTagName("query");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) node;
                String attr = e.getAttribute("id");
                if (attr.equals(nome)) {
                    return e.getTextContent();
                }
            }
        }
        return null;
    }
}
