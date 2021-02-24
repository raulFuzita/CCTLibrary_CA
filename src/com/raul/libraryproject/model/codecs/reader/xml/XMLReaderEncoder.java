package com.raul.libraryproject.model.codecs.reader.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.exceptionsupressor.UncheckedException;

/**
 * <p>class XMLReaderEncoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Encoder class encodes from type Reader to XML Document.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param Reader is to define the type object that is convert from
 * @param Document is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class XMLReaderEncoder implements Codec<Reader, Document> {
	// Sets the object root for a XML file.
	private String rootObject = "root";
	// Simple constructor
	public XMLReaderEncoder() {}
	
	/**
	 * This constructor sets the root object name.
	 * If the argument doesn't match the requirements
	 * the value won't change and the value will
	 * remain the default one.
	 * @param rootObject is a String object.
	 */
	public XMLReaderEncoder(String rootObject) {
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
	 * @param reader is a Reader type
	 * @return Document object
	 */
	@Override
	public Document encode(Reader reader) {
		return toXML(reader);
	}

	/**
	 * @param readers is a Reader[]
	 * @return Document[]
	 */
	@Override
	public Document[] encode(Reader[] readers) {
		Document[] documents = new Document[readers.length];
		for (int i = 0; i < readers.length; i++)
			documents[i] = toXML(readers[i]);
		return documents;
	}

	/**
	 * @param readers is a {@code List<Reader>}
	 * @return {@code List<Document>}
	 */
	@Override
	public List<Document> encode(List<Reader> readers) {
		List<Document> documents = new LinkedList<>();
		for (Reader reader : readers)
			documents.add(toXML(reader));
		return documents;
	}
	
	/**
	 * This method takes the Reader properties and 
	 * creates XML data out of them.
	 * 
	 * @param reader is a Reader type.
	 * @return Document object.
	 */
	protected Document toXML(Reader reader) {
		
		DocumentBuilderFactory dbFactory =
		         DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		Document document = null;
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			document = dBuilder.newDocument();
			
			Element rootElement = document.createElement(rootObject);
			document.appendChild(rootElement);
			
			String BookId = Long.toString(reader.getId());
			Element id = document.createElement("id");
			id.appendChild(document.createTextNode(BookId));
			rootElement.appendChild(id);
			
			Element name = document.createElement("name");
			name.appendChild(document.createTextNode(reader.getName()));
			rootElement.appendChild(name);
			
			Element surname = document.createElement("surname");
			surname.appendChild(document.createTextNode(reader.getSurname()));
			rootElement.appendChild(surname);
			
			String readerBirthdate = reader.getBirthdate().toString();
			Element birthdate = document.createElement("birthdate");
			birthdate.appendChild(document.createTextNode(readerBirthdate));
			rootElement.appendChild(birthdate);
			
			Element address = document.createElement("address");
			address.appendChild(document.createTextNode(reader.getAddress()));
			rootElement.appendChild(address);
			
		} catch (Exception e) {
			throw new UncheckedException(e);
		}
		
		return document;
	}

}
