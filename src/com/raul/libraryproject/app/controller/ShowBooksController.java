package com.raul.libraryproject.app.controller;

import java.util.NoSuchElementException;

import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.model.book.BookComparator;
import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.model.facades.EntityDisplayer;
import com.raul.libraryproject.util.keyboard.Keyboard;

/**
 * <p>
 * ShowBooksController controls the way a book or books will be 
 * displayed.
 * </p>
 * 
 * <p><b>Created at: </b>05/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class ShowBooksController {

	private DAO<Book> bookDAO;

	/**
	 * Constructor requires a DAO for book.
	 * @param bookDAO is a {@code DAO<Book>}
	 */
	public ShowBooksController(DAO<Book> bookDAO) {
		this.bookDAO = bookDAO;
	}
	
	/**
	 * This method shows four options to display books 
	 * sorted by ID, title, author, and genre
	 */
	public void run() {
		
		EntityDisplayer entityDisplayer = new EntityDisplayer();
		
		System.out.println("\n************* SHOW ALL BOOKS ************");
		System.out.println("*                                       *");
		System.out.println("* (1) Sort By ID                        *");
		System.out.println("* (2) Sort By Title                     *");
		System.out.println("* (3) Sort By Author                    *");
		System.out.println("* (4) Sort By genre                     *");
		System.out.println("*                                       *");
		System.out.println("*****************************************");
		
		String option = Keyboard.text("# Enter one of the options: ");
		
		/*
		 * This code doesn't need any comments. It's pretty straightforward.
		 */
		try {
			switch (option) {
				case "1":
					 entityDisplayer.displayEntity(
								bookDAO, new BookComparator.ID());
				break;
				case "2":
					entityDisplayer.displayEntity(
							bookDAO, new BookComparator.Title());
				break;
				case "3":
					entityDisplayer.displayEntity(
							bookDAO, new BookComparator.Author());
				break;
				case "4":
					entityDisplayer.displayEntity(
							bookDAO, new BookComparator.Genre());
				break;
			}
		} catch (NoSuchElementException e) {
			System.out.println("\n[No book with such a reference]\n");
		}
	}
}
