package Part2_JSON;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;

public class Task6 {

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
		XPathExpression expr = xpath.compile("/mondial/country[encompassed/@continent=/mondial/continent[@name='Europe']/@id]");
		NodeList list = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		
		Gson gson = new Gson();
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("src/resources/mondial-3.0-europe-countries.json")));
		
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			NamedNodeMap map = node.getAttributes();
			Map<String, String> values = new HashMap<String, String>();
			for (int j = 0; j < map.getLength(); j++) {
				values.put(map.item(j).getNodeName(), map.item(j).getTextContent());
			}
			String jsonString = gson.toJson(values);
			writer.write(jsonString + "\n");
			System.out.println(gson.toJson(values));
		}
		writer.close();
	}

}
