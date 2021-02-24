package com.raul.libraryproject.model.codecs.book.json;

import org.json.JSONArray;
import org.json.JSONObject;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class JSONBookDecoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Decoder class decode from type JSONObject to Book.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param JSONObject is to define the type object that is convert from
 * @param Book is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class JSONBookDecoder implements Codec<JSONObject, Book> {
	// Sets the object root for a JSON file.
	private String rootObject = "object";
	// Simple constructor
	public JSONBookDecoder() {}
	
	/**
	 * This constructor sets the root object name.
	 * If the argument doesn't match the requirements
	 * the value won't change and the value will
	 * remain the default one.
	 * @param rootObject is a String object.
	 */
	public JSONBookDecoder(String rootObject) {
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
	 * @param jsonObj is a JSONObject object
	 * @return Book
	 */
	@Override
	public Book encode(JSONObject jsonObj) {
		JSONArray jsonArray = jsonObj.getJSONArray(rootObject);
		if (jsonArray.length() <= 0)
			return null;
		return JSONTo((JSONObject) jsonArray.get(0));
	}

	/**
	 * @param jsonObjs is a JSONObject[].
	 * @return Book[]
	 */
	@Override
	public Book[] encode(JSONObject[] jsonObjs) {
		Book[] books = new Book[jsonObjs.length];
		for (int i = 0; i < jsonObjs.length; i++) {
			JSONArray jsonArray = jsonObjs[i].getJSONArray(rootObject);
			if (jsonArray.length() <= 0)
				continue;
			books[i] = JSONTo((JSONObject) jsonArray.get(i));
		}
		return books;
	}

	/**
	 * @param jsonObjs is a {@code List<JSONObject>} object.
	 * @return {@code List<Book>}
	 */
	@Override
	public List<Book> encode(List<JSONObject> jsonObjs) {
		List<Book> books = new LinkedList<>();
		for (JSONObject jsonObj : jsonObjs) {
			JSONArray jsonArray = jsonObj.getJSONArray(rootObject);
			int jsonArraySize = jsonArray.length();
			for (int i = 0; i < jsonArraySize; i++) {
				Book book = JSONTo((JSONObject) jsonArray.get(i));
				books.add(book);
			}
		}
		return books;
	}
	
	/**
	 * This method convert JSON data to primitive or String object.
	 * Then the values are used to instantiate a Book object.
	 * 
	 * @param jsonObj is a JSONObject object
	 * @return Book
	 */
	protected Book JSONTo(JSONObject jsonObj) {
		
		long id = Long.parseLong(jsonObj.get("id").toString());
		String title = jsonObj.get("title").toString();
		String author = jsonObj.get("author").toString();
		String genre = jsonObj.get("genre").toString();
		int quantity = Integer.parseInt(jsonObj.get("quantity").toString());
		
		Book book = new Book.Builder(id)
				.setTitle(title)
				.setAuthor(author)
				.setGenre(genre)
				.setQuantity(quantity).build();
		
		return book;
	}

}
