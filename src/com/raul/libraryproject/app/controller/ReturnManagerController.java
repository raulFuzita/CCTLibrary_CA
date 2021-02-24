package com.raul.libraryproject.app.controller;

import java.util.Map;
import java.util.NoSuchElementException;

import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.model.facades.BookSearcher;
import com.raul.libraryproject.model.facades.ReaderSearcher;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.util.datastructure.Queue;

/**
 * <p>
 * ReturnManagerController can look a little intimidating but actually, it
 * only applies the Builder Design Pattern. This controller requires 
 * many parameter to perform a book being returned to the library, the
 * update of a borrowing list, the check of a queue to determine if a book
 * is available to the reader in queue.
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
public class ReturnManagerController {

	/*
	 * All these attributes should not be access.
	 * If attributes values have to be set it should happen 
	 * thrown the builder pattern.
	 */
	private Map<Long, Queue<JunctionEntity>> queuesMap;
	private DAO<Book> bookDAO;
	private DAO<Reader> readerDAO;
	private DAO<JunctionEntity> borrowingDAO;
	private DAO<JunctionEntity> waitingDAO;
	
	// Builder class
	public static class Builder {
		
		private Map<Long, Queue<JunctionEntity>> queuesMap;
		private DAO<Book> bookDAO;
		private DAO<Reader> readerDAO;
		private DAO<JunctionEntity> borrowingDAO;
		private DAO<JunctionEntity> waitingDAO;
		
		public Builder() {}
		
		// Chainable methods
		/**
		 * This method sets queue Map
		 * @param queuesMap is a {@code Map<Long, Queue<JunctionEntity>>}
		 * @return Builder
		 */
		public Builder queuesMap(Map<Long, Queue<JunctionEntity>> queuesMap) {
			this.queuesMap = queuesMap;
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
		 * This method sets a waiting DAO
		 * @param waitingDAO {@code DAO<JunctionEntity>}
		 * @return Builder
		 */
		public Builder waitingDAO(DAO<JunctionEntity> waitingDAO) {
			this.waitingDAO = waitingDAO;
			return this;
		}

		/* When all parameters are set or the desired ones it should invokes 
		 * method build. */
		public ReturnManagerController build() {
			return new ReturnManagerController(this);
		}
		
	}
	
	// Makes impossible to instance the constructor without using the Builder
	private ReturnManagerController(Builder builder) {
		queuesMap			= builder.queuesMap;
		bookDAO				= builder.bookDAO;
		readerDAO			= builder.readerDAO;
		borrowingDAO		= builder.borrowingDAO;
		waitingDAO			= builder.waitingDAO;
	}
	
	/**
	 * This method prompts a message and requires inputs to decide
	 * which reader wants to return what book.
	 * 
	 * ReturnBookController is instantiated to perform a book returning.
	 * It also needs to instantiate BookBorrowingController since if a book
	 * is returned and if there is a reader in queue this book will
	 * be lent to a new reader that was waiting.
	 * 
	 */
	public void run() {
		
		System.out.println("\n************ BORROW A BOOK **************");
		System.out.println("* Search a reader to return his/hers    *");
		System.out.println("* book. Then search a book to check if  *");
		System.out.println("* it's the correct book.                *");
		System.out.println("*****************************************");
		
		ReaderSearcher readerSearcher = new ReaderSearcher(readerDAO);
		BookSearcher bookSearcher = new BookSearcher(bookDAO);
		BookBorrowingController bbc = new BookBorrowingController(borrowingDAO);
		ReturnBookController rbc = 
						new ReturnBookController(borrowingDAO);
		bbc = new BookBorrowingController(borrowingDAO);
		
		Reader reader = null;
		Book book = null;
		
		try {
			reader = readerSearcher.fetchReader();
			book = bookSearcher.fetchBook();
			
			/*
			 * returnBook requires a book and a reader. If the record is
			 * found the returning operation is executed. For further 
			 * information how this method works check out its documentation.
			 * 
			 * The book ID is kept to update the book DAO data.
			 */
			if (rbc.returnBook(book, reader)) {
				long id = book.getId();
				this.bookDAO.update((int)id-1, book);
				
				// Checks if there is someone waiting for the returned book.
				QueueManagerController qmc = 
							new QueueManagerController(queuesMap, readerDAO);
				reader = qmc.checkQueue(book);
				/*
				 * If a reader is found a message show that this reader
				 * is waiting to borrow the returned book
				 */
				if (reader != null) {
					System.out.println("\nReader " + reader.getFullname() 
					+ " is in the waiting list for the book: \n");
					System.out.println(book);
					/**
					 * The borrowing operation is perform, now to
					 * lend the book to the reader that was in queue.
					 */
					if (bbc.borrowBook(reader, book)) {
						this.bookDAO.update((int)id-1, book);
						this.waitingDAO.remove(qmc.getEntity());
					}
				} 
				/* For troubleshoot purpose
				 else {
					System.out.println("[Nobody in the queue]");
				}*/
				
			} else {
				System.out.println("[No book to return]");
			}
			
		} catch (NoSuchElementException e) {
			System.out.println("\n[No Such Record]\n");
		}
	}
}
