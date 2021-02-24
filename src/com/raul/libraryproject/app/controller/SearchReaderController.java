package com.raul.libraryproject.app.controller;

import java.util.NoSuchElementException;

import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.model.facades.ReaderSearcher;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.keyboard.Keyboard;

/**
 * <p>
 * SearchReaderController controls the way a reader or readers will be 
 * searched for and displayed to.
 * </p>
 * 
 * <p><b>Created at: </b>03/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class SearchReaderController {

	private DAO<Reader> readerDAO;

	/**
	 * Constructor requires a DAO for book.
	 * @param bookDAO is a {@code DAO<Reader>}
	 */
	public SearchReaderController(DAO<Reader> readerDAO) {
		this.readerDAO = readerDAO;
	}
	
	/**
	 * This method shows two options to search for a book.
	 * It is possible to search for a single result or many.
	 */
	public void run() {
		
		Reader reader = null;
		
		System.out.println("\n************** SEARCH BOOKS *************");
		System.out.println("*                                       *");
		System.out.println("* (1) Search a Reader                   *");
		System.out.println("* (2) Search Readers                    *");
		System.out.println("*                                       *");
		System.out.println("* Any invalid option to return to menu. *");
		System.out.println("*                                       *");
		System.out.println("*****************************************");
		
		String option = Keyboard.text("# Enter one of the options: ");
		
		ReaderSearcher readerSearcher = new ReaderSearcher(readerDAO);
		
		/*
		 * This code doesn't need any comments. It's pretty straightforward.
		 */
		try {
			switch (option) {
				case "1":
					 reader = readerSearcher.fetchReader();
					 System.out.println(reader);
				break;
				case "2":
					List<Reader> readers = readerSearcher.fetchReaders();
					for (Reader r : readers)
						System.out.println(r);
				break;
			}
		} catch (NoSuchElementException e) {
			System.out.println("\n[No book with such a reference]\n");
		}
	}
}
