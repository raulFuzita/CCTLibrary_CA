package com.raul.libraryproject.util.filegenerator.generators;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.filegenerator.FileGenerator;

/**
 * <p>Class XMLFileGenerator extends abstract class FileGenerator.
 * This class generates a XML file.
 * The generic type is assigned to Document type from package
 * org.w3c.dom.Document.
 * 
 * </p>
 * 
 * <p><b>Created at: </b>25/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class XMLFileGenerator extends FileGenerator<Document> {

	// This is used to define a XML object root name
	private String rootName = "root";
	
	/**
	 * Returns a String with the default XML object root name.
	 * @return String
	 */
	public String getRootName() {
		return rootName;
	}

	/**
	 * This method allows to change the name of the XML object root name.
	 * The characters allowed are: a-z A-Z 0-9 and underscore.
	 * If the input doesn't match the rule the XML object root name
	 * will remain the default or last valid one.
	 * 
	 * @param rootObject is a String type.
	 * @return boolean if the change is applied. Otherwise false.
	 */
	public boolean setRootName(String rootName) {
		if (rootName.matches("[a-zA-Z0-9_]+")) {
			this.rootName = rootName;
			return true;
		}
		return false;
	}

	/**
	 * This method requires a List interface type JSONObject.
	 * Document belongs to the package:
	 * org.w3c.dom.Document.
	 * 
	 * <p>
	 * Packages:
	 * 
	 * <ul>
	 * 	<li>org.w3c.dom.Document</li>
	 * 	<li>javax.xml.transform.Transformer</li>
	 * 	<li>javax.xml.transform.TransformerFactory</li>
	 * 	<li>javax.xml.transform.dom.DOMSource</li>
	 * 	<li>javax.xml.transform.stream.StreamResult</li>
	 * </ul>
	 * 
	 * </p>
	 * 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws Exception
	 */
	@Override
	protected String generateContent(List<Document> documets) throws Exception {
		/**
		 * Since String is an immutable object a StringBuilder is used
		 * to append each new data.
		 */
		StringBuilder fileBuilder = new StringBuilder();
		
		DocumentBuilderFactory dbFactory =
		         DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document documentRoot = dBuilder.newDocument();
		
		// root element
		Element rootElement = documentRoot.createElement(rootName);
		documentRoot.appendChild(rootElement);
		
		for (Document document : documets) {
			mergeXMLDocuments(documentRoot, document);
		}
		
		String xmlContent = createXMLContent(documentRoot);
		fileBuilder.append(xmlContent);
		// invokes toString method to convert a StringBuilder data to a String.
		return fileBuilder.toString();
	}
	
	/**
	 * This method merges a Document JSON to another.
	 * The second parameter will be merged into the first one.
	 * 
	 * @param doc1 is Document type. The Document to merge to.
	 * @param doc2 is Document type. The Document to merge from.
	 */
	protected void mergeXMLDocuments(Document doc1, Document doc2) {
		
		/*
		 * Gets the name of first element of the XML document.
		 */
		String doc1FirstChild = doc1.getFirstChild().getNodeName();
        String doc2FirstChild = doc2.getFirstChild().getNodeName();
		
        // Sets a NodeLits for each document
		NodeList nodes1 = doc1.getElementsByTagName(doc1FirstChild); 
        NodeList nodes2 = doc2.getElementsByTagName(doc2FirstChild);
        
        /*
         * The For loop statement traversal the second NodeList
         * to import it to the first one.
         */
        for(int i=0; i < nodes2.getLength(); i++){  
       	 Node n = (Node) doc1.importNode(nodes2.item(i), true);  
       	 nodes1.item(i).appendChild(n);
        }
	}
	
	/**
	 * Creates an indented XML structure in a String variable.
	 * 
	 * <p>
	 * Packages:
	 * 
	 * <ul>
	 * 	<li>org.w3c.dom.Document</li>
	 * 	<li>javax.xml.transform.Transformer</li>
	 * 	<li>javax.xml.transform.TransformerFactory</li>
	 * 	<li>javax.xml.transform.dom.DOMSource</li>
	 * 	<li>javax.xml.transform.stream.StreamResult</li>
	 * </ul>
	 * The code is based on the articles at:
	 * <ul>
	 * 	<li>
	 * 		{@link <a href="https://www.tutorialspoint.com/java_xml/index.htm">
	 * 		Tutorialspoint - Java XML Tutorial
	 * 		</a>}
	 * 	</li>
	 * 	<li>
	 * 		{@link <a href="https://howtodoinjava.com/java/xml/xml-to-string-write-xml-file/">
	 * 		How to do in Java - Java XML to String – Write XML Object to File Example
	 * 		</a>}
	 * 	</li>
	 * </ul>
	 * </p>
	 * 
	 * @param xmlDocument is a Document type.
	 * @return String with a data parsed in XML
	 * @throws Exception
	 */
	protected String createXMLContent(Document xmlDocument) throws Exception {
		
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
	    	
			transformer = tf.newTransformer();
			// Indents the XML data
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        StringWriter writer = new StringWriter();
	        //transform document to string 
	        transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));
	        // Return a String with XML
	        return writer.getBuffer().toString();
	}

}
