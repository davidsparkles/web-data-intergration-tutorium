package Part1_XML;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Task5 {

	private static Document getDocument(String path) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder= factory.newDocumentBuilder();
		Document doc = builder.parse(path);
		return doc;
	}
	
	public static void main(String[] args) throws Exception {
		String path = "src/resources/mondial-3.0.xml";
		Document doc = getDocument(path);
		
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		XPathExpression expr = xpath.compile("/mondial/country[@name='Germany']");
		Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
		
		NamedNodeMap map = node.getAttributes();

		for (int i = 0; i < map.getLength(); i++) {
			System.out.println(map.item(i).getNodeName() + ":  " + map.item(i).getNodeValue());
		}
	}

}
