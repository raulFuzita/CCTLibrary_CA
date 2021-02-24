package com.raul.libraryproject.model.dao.filestorage;

import java.io.File;

import com.raul.libraryproject.util.filegenerator.FileGenerator;
import com.raul.libraryproject.util.fileloader.FileLoader;

/**
 * <p>The purpose of this class, FileDrive, is to wrap FileGenerator and
 * FileLoader to be passed as one argument.
 * It assumes the same name and extension of the file will be used for
 * both drives, generator and loader.
 * 
 * This technique is according the Argument Object design.
 * 
 * "When a function seems to need more than two or three arguments, it is
 * likely that some of those arguments ought to be wrapped into a class of
 * their own."
 * Clean Code - A Handbook of Agile Software Craftsmanship, Robert C. Martin,
 * Function Arguments, page 43.
 * 
 * This class works as a DTO (Data Transfer Object) class.
 * According to Robert C. Martin, book Clean Code, page 100, published
 * by Prentice Hall publishing company a DTO class should public variables
 * and no functions that doesn't provide any benefit.
 * 
 * </p>
 * 
 * <p><b>Created at: </b>02/12/2020</p>
 * 
 * @param <T> is the type of the generators.
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class FileDrive<T> {

	public File file; // File reference for FileGanerator and FileLoader
	public FileGenerator<T> fileGenerator;
	public FileLoader<T> fileLoader;
	
	/**
	 * Wraps FileGenerator and FileLoader in one class.
	 * @param fileGenerator is a FileGenerator type Generic
	 * @param fileLoader is a FileLoader type Generic
	 */
	public 
	FileDrive(FileGenerator<T> fileGenerator, FileLoader<T> fileLoader) {
		this.fileGenerator = fileGenerator;
		this.fileLoader = fileLoader;
	}
	
}
