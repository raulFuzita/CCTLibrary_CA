package com.raul.libraryproject.model.codecs.book.json;

import org.json.JSONObject;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class JSONBookEncoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Encoder class encodes from type Book to JSONObject.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param Book is to define the type object that is convert from
 * @param JSONObject is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class JSONBookEncoder implements Codec<Book, JSONObject> {

	/**
	 * @param book is a Book type
	 * @return JSONObject object
	 */
	@Override
	public JSONObject encode(Book book) {
		return toJSON(book);
	}

	/**
	 * @param books is a Book[]
	 * @return JSONObject[]
	 */
	@Override
	public JSONObject[] encode(Book[] books) {
		JSONObject[] jsonObj = new JSONObject[books.length];
		for (int i = 0; i < books.length; i++)
			jsonObj[i] = toJSON(books[i]);
		return jsonObj;
	}

	/**
	 * @param books is a {@code List<Book>}
	 * @return {@code List<JSONObject>}
	 */
	@Override
	public List<JSONObject> encode(List<Book> books) {
		List<JSONObject> jsonObjList = new LinkedList<>();
		for (Book book : books)
			jsonObjList.add(toJSON(book));
		return jsonObjList;
	}

	/**
	 * This method takes the Book properties and 
	 * creates JSON data out of them.
	 * 
	 * @param book is a Book type.
	 * @return JSONObject object.
	 */
	protected JSONObject toJSON(Book book) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", book.getId());
		jsonObj.put("title", book.getTitle());
		jsonObj.put("author", book.getAuthor());
		jsonObj.put("genre", book.getGenre());
		jsonObj.put("quantity", book.getQuantity());
		return jsonObj;
	}
	
}
