package com.raul.libraryproject.app;

import com.raul.libraryproject.app.controller.BootstrapLibraryController;
import com.raul.libraryproject.app.controller.DAOInstanceController;
import com.raul.libraryproject.app.controller.PersistenceOptionController;
import com.raul.libraryproject.app.controller.WelcomeMessage;
import com.raul.libraryproject.model.options.PersistenceOption;
import com.raul.libraryproject.util.keyboard.Keyboard;

/**
 * <p>
 * Library class is the one that has the main method.
 * There is no much in this class but it plays an important
 * role by managing what persistence system it'll use when
 * it's bootstraped.
 * </p>
 * 
 * <p><b>Created at: </b>01/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class Library {

	/*
	 * Main method instances Library constructor to prevent having to
	 * deal with static methods or static global variables.
	 */
	public static void main(String[] args) {
		new Library();
	}
	
	/**
	 * The controller job is to call initialize method and when
	 * the system is closed accordingly it'll perform a nice
	 * system shutdown.
	 * System.exit(0) is invoked to close an Java application
	 * properly.
	 */
	public Library() {
		initialize();
		System.out.println("System is closing...");
		System.out.println("Bye bye :D");
		System.exit(0);
	}
	
	/**
	 * This method will instantiate the main constructors and define
	 * the persistence system that'll be used.
	 */
	private void initialize() {
		
		// A warming welcome message.
		new WelcomeMessage();
		
		/* 
		 * Defines what persistence system will be used. For instance,
		 * File System or MySQL. It's important to highlight that
		 * the MySQL operation is not support yet. It is to mere 
		 * show that it could've be implemented by using the
		 * software architecture for this system. It's a prove that the 
		 * system is expandable and scalable.*/
		PersistenceOptionController poc = 
									new PersistenceOptionController();
		// Retrieve which option was picked.
		PersistenceOption po = poc.getOption();
		// Gives a little glimpse to user watch what is going on on each step
		Keyboard.text("# Press any key to contitnue: ");
		// If option exit is chosen it'll ending the system.
		if (po == PersistenceOption.EXIT)
			return;
		/* This controller requires PersistenceOption to make decision
		 * based on the choice to create each DAO according to the 
		 * chosen option.*/
		DAOInstanceController daoInstController = 
											new DAOInstanceController(po);
		// Gives a little glimpse to user watch what is going on on each step
		Keyboard.text("# Press any key to contitnue: ");
		/* The bootstrap controller requires the DAO controller to keep
		 * tracking the persistence option and call internally factory
		 * classes to make instances of wanted DAO according to the 
		 * persistence option.*/
		BootstrapLibraryController bootstrapLibrary = 
							new BootstrapLibraryController(daoInstController);
		// Fires the "user interface".
		bootstrapLibrary.bootstrap();
	}
}
