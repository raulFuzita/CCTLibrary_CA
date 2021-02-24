package com.raul.libraryproject.app.controller;

import java.io.IOException;

import com.raul.libraryproject.model.dao.DAO;
import com.raul.libraryproject.model.dao.filestorage.FileManagerDAO;
import com.raul.libraryproject.model.dao.filestorage.factory.FactoryFileManager;
import com.raul.libraryproject.model.options.FileType;
import com.raul.libraryproject.model.options.PersistenceOption;
import com.raul.libraryproject.util.exceptionsupressor.UncheckedException;
import com.raul.libraryproject.util.keyboard.Keyboard;

/**
 * <p>
 * DAOInstanceController instances DAO classes.
 * </p>
 * 
 * <p><b>Created at: </b>02/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class DAOInstanceController {
	
	private FileType fileType;
	private PersistenceOption po;
	
	/**
	 * Constructor requires PersistenceOption to make decisions
	 * later on.
	 * @param po PersistenceOption
	 */
	public DAOInstanceController(PersistenceOption po) {
		
		if (po == PersistenceOption.FILESYSTEM)
			fileType = defineFileType();
		else
			fileType = FileType.NONE;
		this.po = po;
	}

	/**
	 * This method displays a menu with available file type support
	 * by the current version if the system.
	 * 
	 * @return FileType
	 */
	private FileType defineFileType() {
		
		System.out.println("*********** CHOOSE A FILE TYPE **********");
		System.out.println("*                                       *");
		System.out.println("* (1) JSON File                         *");
		System.out.println("* (2) XML File                          *");
		System.out.println("* (3) Text File                         *");
		System.out.println("* (4) Object File                       *");
		System.out.println("*                                       *");
		System.out.println("*****************************************");
		
		String option = Keyboard.text("# Enter an option: ");
		
		switch (option) {
			case "1":
				System.out.println("\nJSON File is selected...");
				return FileType.JSON;
	
			case "2":
				System.out.println("\nXML File is selected...");
				return FileType.XML;
				
			case "3":
				System.out.println("\nText File is selected...");
				return FileType.TEXT;
				
			case "4":
				System.out.println("\nObject File is selected...");
				return FileType.OBJECT;
				
			default:
				System.out.println("\nInvalid File Type.\n");
				Keyboard.text("# Press any key to contitnue: ");
			break;
		}
		
		return defineFileType();
	}
	
	/**
	 * This method return a DAOinstance. It is understood the source is 
	 * important and it should not go on. So an exception is thrown.
	 * 
	 * @param entityName is a String object
	 * @param source is a String object
	 * @return DAO instance. Otherwise a null.
	 * @throws UncheckedException
	 */
	@SuppressWarnings("rawtypes")
	public DAO getSafeInstanceDAO(String entityName, String source) {
		
		if (this.po == PersistenceOption.FILESYSTEM) {
			FileManagerDAO fmDAO = requiredFileDAO(entityName, source);
			fmDAO.loadFile();
			return fmDAO;
		}
		return null;
	}
	
	/**
	 * This method return a DAO instance. If an exception is thrown
	 * it'll create an empty DAO instance and return it.
	 * 
	 * @param entityName is a String object
	 * @param source is a String object
	 * @return DAO instance. Otherwise a null.
	 */
	@SuppressWarnings("rawtypes")
	public DAO getInstanceDAO(String entityName, String source) {
		
		if (this.po == PersistenceOption.FILESYSTEM) {
			FileManagerDAO fmDAO = requiredFileDAO(entityName, source);
			try {
				fmDAO.loadFile();
				return fmDAO;
			} catch (UncheckedException e) {
				if (e.getException() instanceof IOException)
					return fmDAO;
			}
		}
		return null;
	}
	
	
	/**
	 * This method return a DAOinstance.
	 * 
	 * @param entityName is a String object
	 * @param source is a String object
	 * @return DAO instance. Otherwise a null.
	 * @throws UncheckedException
	 */
	@SuppressWarnings("rawtypes")
	private FileManagerDAO requiredFileDAO(String entityName, String source) {
		return FactoryFileManager.make(entityName, fileType, source);
	}
	
	
}
