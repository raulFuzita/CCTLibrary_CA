package com.raul.libraryproject.model.codecs.book.text;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class TextBookEncoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Encoder class encodes from type Book to String.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param Book is to define the type object that is convert from
 * @param String is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class TextBookEncoder implements Codec<Book, String> {

	/**
	 * @param book is a Book type
	 * @return String object
	 */
	@Override
	public String encode(Book entity) {
		return toText(entity);
	}

	/**
	 * @param books is a Book[]
	 * @return String[]
	 */
	@Override
	public String[] encode(Book[] books) {
		String[] stringArray = new String[books.length];
		for (int i = 0; i < stringArray.length; i++)
			stringArray[i] = toText(books[i]);
		return stringArray;
	}

	/**
	 * @param books is a {@code List<Book>}
	 * @return {@code List<String>}
	 */
	@Override
	public List<String> encode(List<Book> books) {
		List<String> stringList = new LinkedList<>();
		for (Book book : books)
			stringList.add(toText(book));
		return stringList;
	}
	
	/**
	 * This method takes the Book properties and 
	 * creates String data out of them.
	 * 
	 * @param book is a Book type.
	 * @return String object.
	 */
	private String toText(Book book) {
		String data = book.getId() + ";" + book.getTitle() + ";" 
					+ book.getAuthor() + ";" + book.getGenre() 
					+ ";" + book.getQuantity();
		return data;
	}

}
