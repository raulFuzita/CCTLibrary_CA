package com.raul.libraryproject.app.controller;

import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.model.reader.Reader;

/**
 * <p>
 * BookBorrowingController controls how it is done to 
 * borrow a book. Check out the methods docuemntation for
 * further infomration.
 * </p>
 * 
 * <p><b>Created at: </b>02/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class BookBorrowingController {

	private DAO<JunctionEntity> borrowingsDAO;
	
	/**
	 * Constructor requires a borrowing DAO to perform a book borrowing
	 * properly.
	 * @param borrowingsDAO {@code DAO<JunctionEntity>}
	 */
	public BookBorrowingController (DAO<JunctionEntity> borrowingsDAO) {
		this.borrowingsDAO = borrowingsDAO;
	}

	/**
	 * This method will check if a book is available. If it is an instance of
	 * JunctionEntity is created to store the book and reader Id. Then the
	 * record will be stored in the borrowing List.
	 * 
	 * A nice message is displayed to show who borrowed the book and which
	 * book was lent.
	 * 
	 * @param reader is a Reader object.
	 * @param book is a Book object
	 * @return {@code true} if a reader was able to borrow a book. 
	 * Otherwise false.
	 */
	public boolean borrowBook(Reader reader, Book book) {
		
		long readerId = reader.getId();
		long bookId = book.getId();
		
		if(book.isAvailable()) {
			this.borrowingsDAO.add(new JunctionEntity(readerId, bookId));
			book.decreaseQuantity();
			System.out.println(reader.getFullname() + " has borrowed " 
									+ book.simpleInformation());
			return true;
		}
		return false;
	}
}
