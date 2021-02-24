package com.raul.libraryproject.app.controller;

import com.raul.libraryproject.model.options.PersistenceOption;
import com.raul.libraryproject.util.keyboard.Keyboard;

/**
 * <p>
 * PersistenceOptionController manages what persistence is support
 * by the current version of the system.
 * </p>
 * 
 * <p><b>Created at: </b>02/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class PersistenceOptionController {
	
	/**
	 * This method returns an Enum PersistenceOption object.
	 * If an existent option is chosen the method will call itself
	 * recursively. By pressing key button X it should end the system.
	 * @return PersistenceOption
	 */
	public PersistenceOption getOption(){
		
		System.out.println("\n******** CHOOSE A DATABASE SYSTEM *******");
		System.out.println("*                                       *");
		System.out.println("* (1) File System                       *");
		System.out.println("* (2) MySQL system                      *");
		System.out.println("*                                       *");
		System.out.println("* (x) Exit                              *");
		System.out.println("*                                       *");
		System.out.println("*****************************************");

		String option = Keyboard.text("# Enter an oprion: ");
		
		switch (option.toLowerCase()) {
			case "1":
				System.out.println("\nFile System is selected...");
				return PersistenceOption.FILESYSTEM;
			
			case "2":
				System.out.println("\nMySQL System is selected...");
				System.out.println("MySQL Database is not supported yet.\n");
				Keyboard.text("# Press any key to contitnue: ");
				// return PersistenceOption.MYSQL;
				return getOption();
			
			case "x":
				System.out.println("\nClosing System...");
				return PersistenceOption.EXIT;
	
			default:
				System.out.println("\nInvalid option, try again.\n");
				Keyboard.text("# Press any key to contitnue: ");
			break;
		}
		// Calls itself
		return getOption();
	}
}
