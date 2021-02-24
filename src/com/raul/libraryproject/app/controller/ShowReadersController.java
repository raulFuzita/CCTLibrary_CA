package com.raul.libraryproject.app.controller;

import java.util.NoSuchElementException;

import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.model.facades.EntityDisplayer;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.model.reader.ReaderComparator;
import com.raul.libraryproject.util.keyboard.Keyboard;

/**
 * <p>
 * ShowReadersController controls the way a reader or readers will be 
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
public class ShowReadersController {

	private DAO<Reader> readerDAO;

	/**
	 * Constructor requires a DAO for book.
	 * @param bookDAO is a {@code DAO<Reader>}
	 */
	public ShowReadersController(DAO<Reader> readerDAO) {
		this.readerDAO = readerDAO;
	}
	
	/**
	 * This method shows four options to display readers 
	 * sorted by ID, name, surname, and birthday
	 */
	public void run() {
		
		EntityDisplayer entityDisplayer = new EntityDisplayer();
		
		System.out.println("\n************ SHOW ALL READERS ***********");
		System.out.println("*                                       *");
		System.out.println("* (1) Sort By ID                        *");
		System.out.println("* (2) Sort By Name                      *");
		System.out.println("* (3) Sort By Surnam                    *");
		System.out.println("* (4) Sort By Birthday                  *");
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
							 readerDAO, new ReaderComparator.ID());
				break;
				case "2":
					entityDisplayer.displayEntity(
							readerDAO, new ReaderComparator.Name());
				break;
				case "3":
					entityDisplayer.displayEntity(
							readerDAO, new ReaderComparator.Surname());
				break;
				case "4":
					entityDisplayer.displayEntity(
							readerDAO, new ReaderComparator.Birthday());
				break;
			}
		} catch (NoSuchElementException e) {
			System.out.println("\n[No book with such a reference]\n");
		}
	}
}
