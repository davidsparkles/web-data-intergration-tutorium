package Part1_XML;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Task3 {

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
		XPathExpression expr = xpath.compile("/mondial/country[encompassed/@continent=/mondial/continent[@name='Europe']/@id]/@name");
		NodeList list = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		
		for (int i = 0; i < list.getLength(); i++) {
			System.out.println(list.item(i).getTextContent());
		}
	}

}
