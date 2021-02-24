package com.raul.libraryproject.app.controller;

import java.util.NoSuchElementException;

import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.model.facades.BookSearcher;
import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.keyboard.Keyboard;

/**
 * <p>
 * SearchBookController controls the way a book or books will be 
 * searched for and displayed.
 * </p>
 * 
 * <p><b>Created at: </b>03/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class SearchBookController {

	private DAO<Book> bookDAO;

	/**
	 * Constructor requires a DAO for book.
	 * @param bookDAO is a {@code DAO<Book>}
	 */
	public SearchBookController(DAO<Book> bookDAO) {
		this.bookDAO = bookDAO;
	}
	
	/**
	 * This method shows two options to search for a book.
	 * It is possible to search for a single result or many.
	 */
	public void run() {
		
		Book book = null;
		
		System.out.println("\n************** SEARCH BOOKS *************");
		System.out.println("*                                       *");
		System.out.println("* (1) Search a Book                     *");
		System.out.println("* (2) Search Books                      *");
		System.out.println("*                                       *");
		System.out.println("* Any invalid option to return to menu. *");
		System.out.println("*                                       *");
		System.out.println("*****************************************");
		
		String option = Keyboard.text("# Enter one of the options: ");
		
		BookSearcher bookSearcher = new BookSearcher(bookDAO);
		
		/*
		 * This code doesn't need any comments. It's pretty straightforward.
		 */
		try {
			switch (option) {
				case "1":
					 book = bookSearcher.fetchBook();
					 System.out.println(book);
				break;
				case "2":
					List<Book> books = bookSearcher.fetchBooks();
					for (Book b : books)
						System.out.println(b);
				break;
			}
		} catch (NoSuchElementException e) {
			System.out.println("\n[No book with such a reference]\n");
		}
	}
}
