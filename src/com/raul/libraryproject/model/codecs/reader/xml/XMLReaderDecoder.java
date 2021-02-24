package com.raul.libraryproject.model.codecs.reader.xml;

import java.time.LocalDate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class XMLReaderDecoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Decoder class decode from type XML Document to Reader.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param Document is to define the type object that is convert from
 * @param Reader is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class XMLReaderDecoder implements Codec<Document, Reader> {
	// Sets the object root for a XML file.
	private String rootObject = "root";
	// Simple constructor
	public XMLReaderDecoder() {}
	
	/**
	 * This constructor sets the root object name.
	 * If the argument doesn't match the requirements
	 * the value won't change and the value will
	 * remain the default one.
	 * @param rootObject is a String object.
	 */
	public XMLReaderDecoder(String rootObject) {
		super();
		setRootObject(rootObject);
	}
	
	/**
	 * This method returns root object name.
	 * @return rootObject that is a String object.
	 */
	public String getRootObject() {
		return rootObject;
	}

	/**
	 * The argument parameter must match the following rules:
	 * Valid range: a-z A-Z 0-9 and underscore.
	 * If the argument doean't match the rule the root object name
	 * will remain the default one or the previous valid one.
	 * 
	 * @param rootObject is a String object.
	 * @return true if the change has been applied. Otherwise false.
	 */
	public boolean setRootObject(String rootObject) {
		if (rootObject.matches("[a-zA-Z0-9_]+")) {
			this.rootObject = rootObject;
			return true;
		}
		return false;
	}
	
	/**
	 * @param document is a Document object
	 * @return Reader
	 */
	@Override
	public Reader encode(Document document) {
		NodeList nodeList = document.getElementsByTagName(rootObject);
		if (nodeList.getLength() <= 0)
			return null;
		return XMLTo(nodeList.item(0));
	}

	/**
	 * @param documents is a Document[].
	 * @return Reader[]
	 */
	@Override
	public Reader[] encode(Document[] documents) {
		Reader[] readers = new Reader[documents.length];
		for (int i = 0; i < documents.length; i++) {
			NodeList nodeList = documents[i].getElementsByTagName(rootObject);
			if (nodeList.getLength() <= 0)
				continue;
			readers[i] = XMLTo(nodeList.item(0));
		}
		return readers;
	}

	/**
	 * @param documents is a {@code List<Document>} object.
	 * @return {@code List<Reader>}
	 */
	@Override
	public List<Reader> encode(List<Document> documents) {
		List<Reader> readers = new LinkedList<>();
		for (Document document : documents) {
			NodeList nodeList = document.getElementsByTagName(rootObject);
			int nodeListSize = nodeList.getLength();
			if (nodeListSize <= 0)
				continue;
			for (int i = 0; i < nodeListSize; i++) {
				Reader reader = XMLTo(nodeList.item(i));
				readers.add(reader);
			}
		}
		return readers;
	}
	
	/**
	 * This method convert XML data to primitive or String object.
	 * Then the values are used to instantiate a Reader object.
	 * 
	 * @param node is a Node object
	 * @return Reader
	 */
	protected Reader XMLTo(Node node) {
		
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			
			String bookId = element.getElementsByTagName("id").item(0)
	                  .getTextContent();
			long id = Long.parseLong(bookId);
			
			String name = element.getElementsByTagName("name").item(0)
	                  .getTextContent();
			
			String surname = element.getElementsByTagName("surname").item(0)
	                  .getTextContent();
			
			String readerBirthdate = element.getElementsByTagName("birthdate")
					.item(0)
					.getTextContent();
			LocalDate birthdate = LocalDate.parse(readerBirthdate);
			
			String address = element.getElementsByTagName("address").item(0)
	                  .getTextContent();
			
			Reader reader = new Reader.Builder(id)
					.setName(name)
					.setSurname(surname)
					.setBirthdate(birthdate)
					.setAddress(address).build();
			
			return reader;
		}
		return null;
	}

}
