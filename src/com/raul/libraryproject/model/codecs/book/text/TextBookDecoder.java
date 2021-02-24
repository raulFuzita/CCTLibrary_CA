package com.raul.libraryproject.model.codecs.book.text;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class TextBookDecoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Decoder class decode from type String to Book.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param String is to define the type object that is convert from
 * @param Book is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class TextBookDecoder implements Codec<String, Book> {

	// Regular Expression that each valid data must match
	private String pattern = "[0-9]+;(.*?)+;(.*?)+;(.*?)+;[0-9]+";
	
	/**
	 * @param string is a String object
	 * @return Book
	 */
	@Override
	public Book encode(String string) {
		return textTo(string);
	}

	/**
	 * @param strings is a String[].
	 * @return Book[]
	 */
	@Override
	public Book[] encode(String[] strings) {
		Book[] books = new Book[strings.length];
		for (int i = 0; i < strings.length; i++)
			books[i] = textTo(strings[i]);
		return books;
	}

	/**
	 * @param strings is a {@code List<String>} object.
	 * @return {@code List<Book>}
	 */
	@Override
	public List<Book> encode(List<String> strings) {
		List<Book> books = new LinkedList<>();
		for (String string : strings) {
			String[] splitedString = string.split("\n");
			for (int i = 0; i < splitedString.length; i++) {
				Book book = textTo(splitedString[i]);
				if (book != null)
					books.add(book);
			}
		}
		return books;
	}
	
	/**
	 * This method convert JSON data to primitive or String object.
	 * Then the values are used to instantiate a Book object.
	 * 
	 * @param string is a String object
	 * @return Book
	 */
	private Book textTo(String string) {
		if (string.matches(pattern)) {
			String[] data = string.split(";");
			long id = Long.parseLong(data[0]);
			String title = data[1];
			String author = data[2];
			String genre = data[3];
			int quantity = Integer.parseInt(data[4]);
			
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
