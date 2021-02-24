package com.raul.libraryproject.model.codecs.book.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.exceptionsupressor.UncheckedException;

/**
 * <p>class XMLBookEncoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Encoder class encodes from type Book to XML Document.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param Book is to define the type object that is convert from
 * @param Document is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class XMLBookEncoder implements Codec<Book, Document> {
	// Sets the object root for a XML file.
	private String rootObject = "root";
	// Simple constructor
	public XMLBookEncoder() {}
	
	/**
	 * This constructor sets the root object name.
	 * If the argument doesn't match the requirements
	 * the value won't change and the value will
	 * remain the default one.
	 * @param rootObject is a String object.
	 */
	public XMLBookEncoder(String rootObject) {
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
	 * @param book is a Book type
	 * @return Document object
	 */
	@Override
	public Document encode(Book book) {
		return toXML(book);
	}

	/**
	 * @param books is a Book[]
	 * @return Document[]
	 */
	@Override
	public Document[] encode(Book[] books) {
		Document[] documents = new Document[books.length];
		for (int i = 0; i < books.length; i++)
			documents[i] = toXML(books[i]);
		return documents;
	}

	/**
	 * @param books is a {@code List<Book>}
	 * @return {@code List<Document>}
	 */
	@Override
	public List<Document> encode(List<Book> books) {
		List<Document> documents = new LinkedList<>();
		for (Book book : books)
			documents.add(toXML(book));
		return documents;
	}
	
	/**
	 * This method takes the Book properties and 
	 * creates XML data out of them.
	 * 
	 * @param book is a Book type.
	 * @return Document object.
	 */
	protected Document toXML(Book book) {
		
		DocumentBuilderFactory dbFactory =
		         DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		Document document = null;
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			document = dBuilder.newDocument();
			
			Element rootElement = document.createElement(rootObject);
			document.appendChild(rootElement);
			
			String BookId = Long.toString(book.getId());
			Element id = document.createElement("id");
			id.appendChild(document.createTextNode(BookId));
			rootElement.appendChild(id);
			
			Element title = document.createElement("title");
			title.appendChild(document.createTextNode(book.getTitle()));
			rootElement.appendChild(title);
			
			Element author = document.createElement("author");
			author.appendChild(document.createTextNode(book.getAuthor()));
			rootElement.appendChild(author);
			
			Element genre = document.createElement("genre");
			genre.appendChild(document.createTextNode(book.getGenre()));
			rootElement.appendChild(genre);
			
			String bookQuantity = Integer.toString(book.getQuantity());
			Element quantity = document.createElement("quantity");
			quantity.appendChild(document.createTextNode(bookQuantity));
			rootElement.appendChild(quantity);
			
		} catch (Exception e) {
			throw new UncheckedException(e);
		}
		
		return document;
	}

}
