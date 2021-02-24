package com.raul.libraryproject.model.codecs.book.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class XMLBookDecoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Decoder class decode from type XML Document to Book.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param Document is to define the type object that is convert from
 * @param Book is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class XMLBookDecoder implements Codec<Document, Book> {
	// Sets the object root for a XML file.
	private String rootObject = "root";
	// Simple constructor
	public XMLBookDecoder() {}
	
	/**
	 * This constructor sets the root object name.
	 * If the argument doesn't match the requirements
	 * the value won't change and the value will
	 * remain the default one.
	 * @param rootObject is a String object.
	 */
	public XMLBookDecoder(String rootObject) {
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
	 * @return Book
	 */
	@Override
	public Book encode(Document document) {
		NodeList nodeList = document.getElementsByTagName(rootObject);
		if (nodeList.getLength() <= 0)
			return null;
		return XMLTo(nodeList.item(0));
	}

	/**
	 * @param documents is a Document[].
	 * @return Book[]
	 */
	@Override
	public Book[] encode(Document[] documents) {
		Book[] books = new Book[documents.length];
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
	 * @return {@code List<Book>}
	 */
	@Override
	public List<Book> encode(List<Document> documents) {
		List<Book> books = new LinkedList<>();
		for (Document document : documents) {
			NodeList nodeList = document.getElementsByTagName(rootObject);
			int nodeListSize = nodeList.getLength();
			if (nodeListSize <= 0)
				continue;
			for (int i = 0; i < nodeListSize; i++) {
				Book book = XMLTo(nodeList.item(i));
				books.add(book);
			}
		}
		return books;
	}
	
	/**
	 * This method convert XML data to primitive or String object.
	 * Then the values are used to instantiate a Book object.
	 * 
	 * @param node is a Node object
	 * @return Book
	 */
	protected Book XMLTo(Node node) {
		
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			
			String bookId = element.getElementsByTagName("id").item(0)
	                  .getTextContent();
			long id = Long.parseLong(bookId);
			
			String title = element.getElementsByTagName("title").item(0)
	                  .getTextContent();
			
			String author = element.getElementsByTagName("author").item(0)
	                  .getTextContent();
			
			String genre = element.getElementsByTagName("genre").item(0)
	                  .getTextContent();
			
			String bookQuantity = element.getElementsByTagName("quantity")
					.item(0).getTextContent();
			int quantity = Integer.parseInt(bookQuantity);
			
			Book book = new Book.Builder(id)
					.setTitle(title)
					.setAuthor(author)
					.setGenre(genre)
					.setQuantity(quantity).build();
			
			return book;
		}
		return null;
	}

}
