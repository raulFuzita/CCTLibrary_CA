package com.raul.libraryproject.model.codecs.book.object;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class ObjBookDecoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Decoder class decode from type Object to Book.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param Object is to define the type object that is convert from
 * @param Book is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class ObjBookDecoder implements Codec<Object, Book> {

	/**
	 * @param object is a Object object
	 * @return Book
	 */
	@Override
	public Book encode(Object object) {
		return (Book) object;
	}

	/**
	 * @param objects is a Object[].
	 * @return Book[]
	 */
	@Override
	public Book[] encode(Object[] objects) {
		return (Book[]) objects;
	}

	/**
	 * @param objects is a {@code List<Object>} object.
	 * @return {@code List<Book>}
	 */
	@Override
	public List<Book> encode(List<Object> objects) {
		List<Book> books = new LinkedList<>();
		for (Object object : objects)
			books.add((Book) object);
		return books;
	}

}
