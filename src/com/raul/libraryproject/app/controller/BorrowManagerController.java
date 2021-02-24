package com.raul.libraryproject.app.controller;

import java.util.Map;
import java.util.NoSuchElementException;

import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.model.facades.BookSearcher;
import com.raul.libraryproject.model.facades.QueueManager;
import com.raul.libraryproject.model.facades.ReaderSearcher;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.util.datastructure.Queue;

/**
 * <p>
 * BorrowManagerController can look a little intimidating but actually, it
 * only applies the Builder Design Pattern. This controller requires 
 * many parameter to perform a book being borrowed to the library, the
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
public class BorrowManagerController {

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
		public BorrowManagerController build() {
			return new BorrowManagerController(this);
		}
		
	}
	
	// Makes impossible to instance the constructor without using the Builder
	private BorrowManagerController(Builder builder) {
		queuesMap			= builder.queuesMap;
		bookDAO				= builder.bookDAO;
		readerDAO			= builder.readerDAO;
		borrowingDAO		= builder.borrowingDAO;
		waitingDAO			= builder.waitingDAO;
	}
	
	/**
	 * This method prompts a message and requires inputs to decide
	 * which reader wants to borrow what book.
	 * 
	 * BookBorrowingController is instantiated to perform a book borrowing.
	 * 
	 */
	public void run() {
		
		System.out.println("\n************ BORROW A BOOK **************");
		System.out.println("* Search a reader to lend him/her a book*");
		System.out.println("* Then search a book to check its       *");
		System.out.println("* availability. If it's available the   *");
		System.out.println("* book will be lent by the reader.      *");
		System.out.println("*****************************************");
		
		QueueManager qm = new QueueManager(queuesMap);
		ReaderSearcher readerSearcher = new ReaderSearcher(readerDAO);
		BookSearcher bookSearcher = new BookSearcher(bookDAO);
		BookBorrowingController bbc = 
					new BookBorrowingController(borrowingDAO);
		
		Reader reader = null;
		Book book = null;
		
		try {
			reader = readerSearcher.fetchReader();
			book = bookSearcher.fetchBook();
			
			/*
			 * If books is available book and reader ID will be stored
			 * in the borrowing list. For further information check out the
			 * method borroBook documentation. Then the book ID is retrieve
			 * to update the book list (list in the book DAO)
			 */
			if (bbc.borrowBook(reader, book)) {
				long id = book.getId();
				this.bookDAO.update((int)id-1, book);
			} else {
				/*
				 * If book is not available then it should be stored in a
				 * JunctionEntity object and then passed into the QueueManager
				 * method enqueue to add it to the queue. It is also stored
				 * in the waiting DAO object for persistence purposes.
				 */
				System.out.println("\nBook is not available yet");
				System.out.println("Reader " + reader.getFullname() 
						+ " has been enqueued in the waiting list for the book\n");
				System.out.println(book.simpleInformation());
				
				JunctionEntity entity = 
						new JunctionEntity(book.getId(), reader.getId());
				qm.enqueue(entity);
				this.waitingDAO.add(entity);
			}
			
		} catch (NoSuchElementException e) {
			System.out.println("\n[No Such a Record]\n");
		}
	}
}
