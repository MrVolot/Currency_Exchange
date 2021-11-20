package com.example.madt1116;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlParser {
    public static String getRateFromECB(InputStream stream, String currencyCode) throws IOException {
        String result = "";
        try {
            DocumentBuilderFactory xmlDocFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlDocBuilder = xmlDocFactory.newDocumentBuilder();
            Document doc = xmlDocBuilder.parse(stream);

            Element element=doc.getDocumentElement();
            element.normalize();
            NodeList rateNodes = doc.getElementsByTagName(Constants.ITEM_NODE);
            for (int i = 0; i < rateNodes.getLength(); ++i) {
                Node node = rateNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    NodeList tmpNodeList = element2.getElementsByTagName(Constants.currency_str).item(0).getChildNodes();
                    Node tmpNode = tmpNodeList.item(0);
                    String tmpResult=tmpNode.getNodeValue();
                    if(tmpResult.equals(currencyCode)) {
                        NodeList nodeList = element2.getElementsByTagName(Constants.rate_str).item(0).getChildNodes();
                        Node node2 = nodeList.item(0);
                        result+=tmpResult+" - ";
                        result += node2.getNodeValue();
                        return result;
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return result;
    }
}
