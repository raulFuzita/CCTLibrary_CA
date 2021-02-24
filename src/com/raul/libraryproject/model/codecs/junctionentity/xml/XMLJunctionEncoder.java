package com.raul.libraryproject.model.codecs.junctionentity.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.exceptionsupressor.UncheckedException;

/**
 * <p>class XMLJunctionEncoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Encoder class encodes from type JunctionEntity to XML Document.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param JunctionEntity is to define the type object that is convert from
 * @param Document is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class XMLJunctionEncoder implements Codec<JunctionEntity, Document> {
	// Sets the object root for a XML file.
	private String rootObject = "root";
	// Simple constructor
	public XMLJunctionEncoder() {}
	
	/**
	 * This constructor sets the root object name.
	 * If the argument doesn't match the requirements
	 * the value won't change and the value will
	 * remain the default one.
	 * @param rootObject is a String object.
	 */
	public XMLJunctionEncoder(String rootObject) {
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
	 * @param entity is a JunctionEntity type
	 * @return Document object
	 */
	@Override
	public Document encode(JunctionEntity entity) {
		return toXML(entity);
	}

	/**
	 * @param junction is a JunctionEntity[]
	 * @return Document[]
	 */
	@Override
	public Document[] encode(JunctionEntity[] junction) {
		Document[] documents = new Document[junction.length];
		for (int i = 0; i < junction.length; i++)
			documents[i] = toXML(junction[i]);
		return documents;
	}

	/**
	 * @param junction is a {@code List<JunctionEntity>}
	 * @return {@code List<Document>}
	 */
	@Override
	public List<Document> encode(List<JunctionEntity> junction) {
		List<Document> documents = new LinkedList<>();
		for (JunctionEntity entity : junction)
			documents.add(toXML(entity));
		return documents;
	}
	
	/**
	 * This method takes the JunctionEntity properties and 
	 * creates XML data out of them.
	 * 
	 * @param entity is a JunctionEntity type.
	 * @return Document object.
	 */
	protected Document toXML(JunctionEntity entity) {
		
		DocumentBuilderFactory dbFactory =
		         DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		Document document = null;
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			document = dBuilder.newDocument();
			
			Element rootElement = document.createElement(rootObject);
			document.appendChild(rootElement);
			
			String owner = Long.toString(entity.ownerId);
			Element ownerId = document.createElement("ownerId");
			ownerId.appendChild(document.createTextNode(owner));
			rootElement.appendChild(ownerId);
			
			String belong = Long.toString(entity.belongId);
			Element belongId = document.createElement("belongId");
			belongId.appendChild(document.createTextNode(belong));
			rootElement.appendChild(belongId);
			
		} catch (Exception e) {
			throw new UncheckedException(e);
		}
		
		return document;
	}

}
