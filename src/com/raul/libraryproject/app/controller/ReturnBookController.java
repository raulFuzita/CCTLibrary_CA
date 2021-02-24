package com.raul.libraryproject.app.controller;

import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>
 * ReturnBookController controls how the business logic should work
 * to return a book to the library and at the same time manage 
 * the borrowing DAO list.
 * </p>
 * 
 * <p><b>Created at: </b>03/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class ReturnBookController {

	private DAO<JunctionEntity> borrowingsDAO;
	
	/**
	 * Constructor requires a DAO to operates the returning book.=
	 * 
	 * @param borrowingsDAO {@code DAO<JunctionEntity>}
	 */
	public ReturnBookController(DAO<JunctionEntity> borrowingsDAO) {
		this.borrowingsDAO = borrowingsDAO;
	}
	
	/**
	 * This method requires two parameter. A book and reader.
	 * If a record is found that matches the book and reader ID
	 * the book will be then returned and a nice message is
	 * displayed to show the reader information.
	 * 
	 * @param book is Book object
	 * @param reader is Reader object
	 * @return {@code true} if book and reader exist in the current 
	 * list of the library. Then it checks if the book and reader ID
	 * matches the belong and owner ID in the JunctionEntity object of
	 * the borrowing DAO.
	 */
	public boolean returnBook(Book book, Reader reader) {
	
		List<JunctionEntity> borrowings = this.borrowingsDAO.getAll();
		
		for (JunctionEntity entity : borrowings) {
			/*
			 * The comparison of elements were broke down into 
			 * separated method to improve readability, cleanliness,
			 * and usability according the best practice in the 
			 * book Clean Code by Robert C. Martin.
			 */
			if (isEqualOwner(entity, reader) && isEqualBelongs(entity, book)) {
				borrowingsDAO.remove(entity);
				/* Increase book quantity to perform the algorithm to
				 * return a book.*/
				book.increaseQuantity();
				System.out.println("\nThank you " + reader.getFullname() 
								+ " for returning the book");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method compares the owner ID of a JunctionEntity object
	 * against a reader ID.
	 * 
	 * @param entity is a JunctionEntity object
	 * @param reader is a Reader object
	 * @return {@code true} is both parameters have the same ID value.
	 */
	private boolean isEqualOwner(JunctionEntity entity, Reader reader) {
		return isEqual(entity.ownerId, reader.getId());
	}
	
	/**
	 * This method compares the belong ID of a JunctionEntity object
	 * against a book ID.
	 * 
	 * @param entity is a JunctionEntity object
	 * @param book is a Book object
	 * @return {@code true} is both parameters have the same ID value.
	 */
	private boolean isEqualBelongs(JunctionEntity entity, Book book) {
		return isEqual(entity.belongId, book.getId());
	}
	
	/**
	 * This method compares to long values to return rather they're equal
	 * or not.
	 * @param l1 is a long primitive type.
	 * @param l2 is a long primitive type.
	 * @return {@code true} if both parameters are equal. Otherwise false.
	 */
	private boolean isEqual(long l1, long l2) {
		return l1 == l2;
	}
}
