package Part1_XML;
import javax.xml.parsers.DocumentBuilderFactory;

import java.util.HashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Task2 {

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
		XPathExpression expr = xpath.compile("/mondial/*");
		NodeList list = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		
		HashSet<String> uniqueNodes = new HashSet<String>();
		for (int i = 0; i < list.getLength(); i++) {
			String name = list.item(i).getNodeName();
			if (!uniqueNodes.contains(name)) {
				uniqueNodes.add(name);
				System.out.println(name);
			}
		}
	}

}
