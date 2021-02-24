package com.raul.libraryproject.app.controller;

import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.model.book.BookComparator;
import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.model.facades.EntitySearcher;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>
 * ReaderRecords controls the way a reader will be fetched and any information
 * related to he/she.
 * For further information check out the documentation.
 * </p>
 * 
 * <p><b>Created at: </b>03/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class ReaderRecords {

	private DAO<JunctionEntity> borrowingsDAO;
	private DAO<Book> bookDAO;
	
	/**
	 * Constructor requires {@code DAO<JunctionEntity>} to link it to
	 * the reader and his/hers records.
	 * {@code DAO<Book>} is used to link to the borrowing books and the
	 * reader's records.
	 * 
	 * @param borrowingsDAO is a {@code DAO<JunctionEntity> borrowingsDAO}
	 * @param bookDAO is a {@code DAO<Book>}
	 */
	public ReaderRecords
	(DAO<JunctionEntity> borrowingsDAO, DAO<Book> bookDAO) {
		this.borrowingsDAO = borrowingsDAO;
		this.bookDAO = bookDAO;
	}

	/**
	 * This method will search for a reader and displays all records related
	 * to the reader.
	 * 
	 * @param reader is a Reader
	 */
	public void showRecords(Reader reader) {
		
		Book book = null;
		long readerId = reader.getId();
		
		System.out.println("\nReader: " + reader.getFullname() + "'s books\n");
		
		List<JunctionEntity> borrowings = this.borrowingsDAO.getAll();
		
		/*
		 * Borrowing records have to be traversal. Each record and reference
		 * is in a JunctionEntity object. It'll be put against book list
		 * to link the reader and its book.
		 * 
		 */
		for (JunctionEntity entity : borrowings) {
			if (entity.ownerId == readerId) {
				long bookId = entity.belongId;
				book = new Book.Builder(bookId).build();
				// Calls Facade EntitySearcher to search for a reader
				book = EntitySearcher.searchEntity(
						this.bookDAO, book, new BookComparator.ID());
				// If result is different from null it is displayed.
				if (book != null)
					System.out.println(book);
			}
		}
		// Otherwise a message will warn that there is no records.
		if (book == null)
			System.out.println("[No book yet]");
	}
}
