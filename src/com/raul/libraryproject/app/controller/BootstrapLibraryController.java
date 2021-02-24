package com.raul.libraryproject.app.controller;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import com.raul.libraryproject.model.book.Book;
import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.model.facades.QueueManager;
import com.raul.libraryproject.model.junction.JunctionEntity;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.datastructure.Queue;
import com.raul.libraryproject.util.exceptionsupressor.UncheckedException;
import com.raul.libraryproject.util.keyboard.Keyboard;

/**
 * <p>
 * BootstrapLibraryController has the must important elements to 
 * make this system seem alive. All DAO is declared globally but their
 * visibility is private. It's not because is a class controller that
 * perform as a menu it should be neglected.
 * </p>
 * 
 * <p><b>Created at: </b>03/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class BootstrapLibraryController {

	// This map will track all waiting queues individually.
	private Map<Long, Queue<JunctionEntity>> queuesMap = new HashMap<>();
	
	private DAO<Book> bookDAO;
	private DAO<Reader> readerDAO;
	private DAO<JunctionEntity> borrowingDAO;
	private DAO<JunctionEntity> waitingDAO;
	private DAOInstanceController daoInstController;
	
	/**
	 * Simple controller that requires DAOInstanceController to know
	 * what persistence system was picked and based on this it'll
	 * instance DAO objects accordingly.
	 * @param daoInstController is a DAOInstanceController object.
	 */
	public BootstrapLibraryController(DAOInstanceController daoInstController) {
		this.daoInstController = daoInstController;
	}

	/**
	 * This method will handle all major exceptions to prevent the system keep
	 * running in an unconscious state.
	 * Main menu is displayed here in this method.
	 */
	@SuppressWarnings("unchecked")
	public void bootstrap() {
		
		/* Catch any Exception that should be considered to even shutdown 
		 * the system.*/
		try {
			
			/*
			 * It is expressively important to say that the first parameter
			 * of method getSafeInstanceDao and getInstanceDAO defines
			 * which FileGenerator, FileLoader, Codec encoder, Codec decoder,
			 * DAOs, MySQL Database and so on by passing a String with 
			 * that class target.
			 * The second parameter is the source file name or a table
			 * for a SQL connection.
			 * 
			 * getSafeInstanceDAO will throw an exception if there is no
			 * file source for Book and Reader. These two information
			 * are essential to start the system.
			 * 
			 * getInstanceDAO won't throw an exception if there no source file.
			 * It's ok if there is no borrowing records or readers waiting
			 * in a queue. If there is no source file for these two an empty
			 * instance is created.
			 */
			bookDAO = daoInstController.getSafeInstanceDAO("Book", "books");
			readerDAO = daoInstController.getSafeInstanceDAO("Reader", "readers");
			borrowingDAO = daoInstController.getInstanceDAO("Junction", "borrowing");
			waitingDAO = daoInstController.getInstanceDAO("Junction", "waiting");
			
			/*
			 * This step is important to create the queue system properly.
			 * A queue is not invented to be traversal but work as a FIFO
			 * First In First Out.
			 * Once the waiting list (queue) is loaded to the DAO object
			 * regardless where is from. The data from the DAO is traversal
			 * by using a Foreach (enhanced-for) to retrieve each data and
			 * enqueue it in a true Queue data structure. QueueManager as
			 * the name suggest is in charge of making decision how a
			 * new data or the existent one should be handle.
			 */
			if (waitingDAO.getAll().size() > 0) {
				QueueManager qm = new QueueManager(queuesMap);
				List<JunctionEntity> list = waitingDAO.getAll();
				for (JunctionEntity entity : list) {
					qm.enqueue(entity);
				}
			}
			
			String option = "";
			
			// Do-While statement should stop when the input is X.
			do {
				
				System.out.println("\n************* LIBRARY MENU **************");
				System.out.println("*                                       *");
				System.out.println("* (1) Show All Books                    *");
				System.out.println("* (2) Show All Readers                  *");
				System.out.println("* (3) Search a Book                     *");
				System.out.println("* (4) Search a Reader                   *");
				System.out.println("* (5) Reader's Records                  *");
				System.out.println("* (6) Borrow a Book                     *");
				System.out.println("* (7) Return a Book                     *");
				System.out.println("*                                       *");
				System.out.println("* (X) Exit Menu                         *");
				System.out.println("*                                       *");
				System.out.println("*****************************************");
				
				option = Keyboard.text("# Enter one of the options: ");
				
				/*
				 * The code below is pretty straightforward since it is
				 * a basic menu system instantiating controllers and
				 * calling methods.
				 */
				switch (option) {
					case "1": // Show All Books
						ShowBooksController shbc = 
										new ShowBooksController(bookDAO);
						shbc.run();
					break;
					
					case "2": // Show All Readers
						ShowReadersController shrc = 
										new ShowReadersController(readerDAO);
						shrc.run();
					break;
					
					case "3": // Search Books
						SearchBookController sbc = 
											new SearchBookController(bookDAO);
						sbc.run();
					break;
					
					case "4": // Search Readers
						SearchReaderController src = 
										new SearchReaderController(readerDAO);
						src.run();
					break;
					
					case "5": // Show Reader's Record
						// This controller uses Builder Pattern
						ReaderRecordsController rrc = 
									new ReaderRecordsController.Builder()
									.bookDAO(bookDAO)
									.readerDAO(readerDAO)
									.borrowingDAO(borrowingDAO)
									.build();
						rrc.run();
					break;
					
					case "6": // Borrow a book
						// This controller uses Builder Pattern
						BorrowManagerController bmc = 
									new BorrowManagerController.Builder()
									.queuesMap(queuesMap)
									.bookDAO(bookDAO)
									.readerDAO(readerDAO)
									.borrowingDAO(borrowingDAO)
									.waitingDAO(waitingDAO)
									.build();
						
						bmc.run();
					break;
					
					case "7": // Return Book
						ReturnManagerController rmc = 
									new ReturnManagerController.Builder()
									.queuesMap(queuesMap)
									.bookDAO(bookDAO)
									.readerDAO(readerDAO)
									.borrowingDAO(borrowingDAO)
									.waitingDAO(waitingDAO)
									.build();
						rmc.run();
					break;
					
					case "x":
					case "X":
						System.out.println("Exit option has been chosen");
					break;
	
					default:
						System.out.println("\n[Option Invalid]\n");
					break;
				}
				
				Keyboard.text("\n# Press any key to contitnue: ");
				
			} while(!option.equalsIgnoreCase("X"));
			
		} catch (UncheckedException e) {
			
			/*
			 * Below is how this instance should the following exceptions.
			 * All this exceptions are suppressed by the UncheckedException.
			 * Since FileGenerator and FileLoader classes implement
			 * Bridge Pattern is impossible to predict all exception since
			 * this implementation allow new implementation join the 
			 * system and it can grow overtime.
			 */
			
			// troubleshoot exception tracker
			//System.out.println(e.getException().getClass());
			
			if (e.getException() instanceof IOException)
				System.out.println("Persistence can't work without file resource");
			
			if (e.getException() instanceof NullPointerException)
				System.out.println("There is no entity");
			
			if (e.getException() instanceof EOFException)
				System.out.println("File is not readable. Wrong file format");
			
			if (e.getException() instanceof ClassCastException)
				System.out.println("Data can't be cast into an entity");
			
			if (e.getException() instanceof JSONException)
				System.out.println("FIle JSON is not readable. Wrong file format");
		}
		
	}
	
}
