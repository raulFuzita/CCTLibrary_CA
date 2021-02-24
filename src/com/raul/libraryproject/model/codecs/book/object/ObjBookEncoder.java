package com.raul.libraryproject.model.codecs.book.object;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class ObjBookEncoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Encoder class encodes from type Book to Object.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param Book is to define the type object that is convert from
 * @param Object is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class ObjBookEncoder implements Codec<Book, Object> {

	/**
	 * @param book is a Book type
	 * @return Object object
	 */
	@Override
	public Object encode(Book book) {
		return book;
	}

	/**
	 * @param books is a Book[]
	 * @return Object[]
	 */
	@Override
	public Object[] encode(Book[] books) {
		return books;
	}

	/**
	 * @param books is a {@code List<Book>}
	 * @return {@code List<Object>}
	 */
	@Override
	public List<Object> encode(List<Book> books) {
		List<Object> objectList = new LinkedList<>();
		for (Book book : books)
			objectList.add(book);
		return objectList;
	}

}
