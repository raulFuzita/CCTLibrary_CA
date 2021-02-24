package com.raul.libraryproject.model.codecs.junctionentity.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class XMLJunctionDecoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Decoder class decode from type XML Document to JunctionEntity.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param Document is to define the type object that is convert from
 * @param JunctionEntity is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class XMLJunctionDecoder implements Codec<Document, JunctionEntity> {
	// Sets the object root for a XML file.
	private String rootObject = "root";
	// Simple constructor
	public XMLJunctionDecoder() {}
	
	/**
	 * This constructor sets the root object name.
	 * If the argument doesn't match the requirements
	 * the value won't change and the value will
	 * remain the default one.
	 * @param rootObject is a String object.
	 */
	public XMLJunctionDecoder(String rootObject) {
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
	 * @return JunctionEntity
	 */
	@Override
	public JunctionEntity encode(Document document) {
		NodeList nodeList = document.getElementsByTagName(rootObject);
		if (nodeList.getLength() <= 0)
			return null;
		return XMLTo(nodeList.item(0));
	}

	/**
	 * @param documents is a Document[].
	 * @return JunctionEntity[]
	 */
	@Override
	public JunctionEntity[] encode(Document[] documents) {
		JunctionEntity[] books = new JunctionEntity[documents.length];
		for (int i = 0; i < documents.length; i++) {
			NodeList nodeList = documents[i].getElementsByTagName(rootObject);
			if (nodeList.getLength() <= 0)
				continue;
			books[i] = XMLTo(nodeList.item(0));
		}
		return books;
	}

	/**
	 * @param documents is a {@code List<Document>} object.
	 * @return {@code List<JunctionEntity>}
	 */
	@Override
	public List<JunctionEntity> encode(List<Document> documents) {
		List<JunctionEntity> junction = new LinkedList<>();
		for (Document document : documents) {
			NodeList nodeList = document.getElementsByTagName(rootObject);
			int nodeListSize = nodeList.getLength();
			if (nodeListSize <= 0)
				continue;
			for (int i = 0; i < nodeListSize; i++) {
				JunctionEntity entity = XMLTo(nodeList.item(i));
				junction.add(entity);
			}
		}
		return junction;
	}
	
	/**
	 * This method convert XML data to primitive or String object.
	 * Then the values are used to instantiate a JunctionEntity object.
	 * 
	 * @param node is a Node object
	 * @return JunctionEntity
	 */
	protected JunctionEntity XMLTo(Node node) {
		
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			
			String owner = element.getElementsByTagName("ownerId").item(0)
	                  .getTextContent();
			long ownerId = Long.parseLong(owner);
			
			String belong = element.getElementsByTagName("belongId").item(0)
	                  .getTextContent();
			long belongId = Long.parseLong(belong);

			
			return new JunctionEntity(ownerId, belongId);
		}
		return null;
	}

}
