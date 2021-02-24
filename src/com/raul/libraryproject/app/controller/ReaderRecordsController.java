package com.raul.libraryproject.app.controller;

import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.model.facades.ReaderSearcher;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.model.reader.Reader;

/**
 * <p>
 * ReaderRecordsController can look a little intimidating but actually, it
 * only applies the Builder Design Pattern. This controller requires 
 * many parameter to show all reader's records
 * Any further detail is available in the methods documentation..
 * </p>
 * 
 * <p><b>Created at: </b>03/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class ReaderRecordsController {

	/*
	 * All these attributes should not be access.
	 * If attributes values have to be set it should happen 
	 * thrown the builder pattern.
	 */
	private DAO<JunctionEntity> borrowingDAO;
	private DAO<Book> bookDAO;
	private DAO<Reader> readerDAO;
	
	// Builder class
	public static class Builder {
		
		private DAO<JunctionEntity> borrowingDAO;
		private DAO<Book> bookDAO;
		private DAO<Reader> readerDAO;
		
		public Builder() {}
		
		/**
		 * This method sets a borrowing DAO
		 * @param borrowingDAO is a {@code DAO<JunctionEntity>}
		 * @return Builder
		 */
		public Builder borrowingDAO(DAO<JunctionEntity> borrowingDAO) {
			this.borrowingDAO = borrowingDAO;
			return this;
		}

		/**
		 * This method sets a book DAO.
		 * @param bookDAO is a {@code DAO<Book>}
		 * @return Builder
		 */
		public Builder bookDAO(DAO<Book> bookDAO) {
			this.bookDAO = bookDAO;
			return this;
		}

		/**
		 * This method sets a book DAO
		 * @param readerDAO is a {@code DAO<Reader>}
		 * @return Builder
		 */
		public Builder readerDAO(DAO<Reader> readerDAO) {
			this.readerDAO = readerDAO;
			return this;
		}

		/* When all parameters are set or the desired ones it should invokes 
		 * method build. */
		public ReaderRecordsController build() {
			return new ReaderRecordsController(this);
		}
	}
	
	// Makes impossible to instance the constructor without using the Builder
	private ReaderRecordsController(Builder builder) {
		borrowingDAO = builder.borrowingDAO;
		bookDAO = builder.bookDAO;
		readerDAO = builder.readerDAO;
	}
	
	/**
	 * This method prompts a message and requires inputs to decide
	 * which reader records will be displayed.
	 * 
	 */
	public void run() {
		
		System.out.println("\n*********** REDEAR'S RECORDS ************");
		System.out.println("* Search a reader to show his/hers      *");
		System.out.println("* books.                                *");
		System.out.println("*****************************************");
		
		ReaderRecords rr = new ReaderRecords(borrowingDAO, bookDAO);
		Reader reader = new ReaderSearcher(readerDAO).fetchReader();
		rr.showRecords(reader);
	}
}
