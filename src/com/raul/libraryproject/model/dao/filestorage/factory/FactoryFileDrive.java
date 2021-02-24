package com.raul.libraryproject.model.dao.filestorage.factory;

import java.io.File;

import com.raul.libraryproject.model.dao.filestorage.FileDrive;
import com.raul.libraryproject.model.options.FileType;
import com.raul.libraryproject.util.filegenerator.FileGenerator;
import com.raul.libraryproject.util.filegenerator.generators.JSONFileGenerator;
import com.raul.libraryproject.util.filegenerator.generators.ObjectFileGenerator;
import com.raul.libraryproject.util.filegenerator.generators.TextFileGenerator;
import com.raul.libraryproject.util.filegenerator.generators.XMLFileGenerator;
import com.raul.libraryproject.util.fileloader.FileLoader;
import com.raul.libraryproject.util.fileloader.loaders.JSONFileLoader;
import com.raul.libraryproject.util.fileloader.loaders.ObjectFileLoader;
import com.raul.libraryproject.util.fileloader.loaders.TextFileLoader;
import com.raul.libraryproject.util.fileloader.loaders.XMLFileLoader;

/**
 * <p>
 * This class name is self-explanatory. It implements a Factory Design Pattern.
 * FactoryFileDrive makes FileDrive instances.
 * For further information check out the methods documentation.
 * 
 * </p>
 * 
 * <p><b>Created at: </b>02/12/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public abstract class FactoryFileDrive {

	/**
	 * This method returns a FileDrive object.
	 * It's expected that the String path is only a path or 
	 * a file name without a file extension. The file extension is defined
	 * by the Enum FileType object.
	 * 
	 * Possibles accept values for method make:
	 * 
	 * <ul>
	 * 		<li>JSON</li>
	 * 		<li>XML</li>
	 * 		<li>OBJECT</li>
	 * 		<li>TEXT</li>
	 * </ul>
	 * 
	 * <p>
	 * If none of the options above matches it'll return a null.
	 * </p>
	 * 
	 * @param path is a String object.
	 * @param fileType is an Enum FileType object.
	 * @return FileDrive object.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> FileDrive<T> make(String path, FileType fileType) {
		
		FileDrive fileDrive = null;
		
		switch (fileType) {
		
			case JSON:
				fileDrive = 
					getFileDrive(new JSONFileGenerator(), new JSONFileLoader());
				fileDrive.file = new File(path+".json");
			break;
			
			case XML:
				fileDrive = 
					getFileDrive(new XMLFileGenerator(), new XMLFileLoader());
				fileDrive.file = new File(path+".xml");
			break;
			
			case OBJECT:
				fileDrive = 
					getFileDrive(new ObjectFileGenerator(), new ObjectFileLoader());
				fileDrive.file = new File(path+".obj");
			break;
			
			case TEXT:
				fileDrive = 
					getFileDrive(new TextFileGenerator(), new TextFileLoader());
				fileDrive.file = new File(path+".txt");
			break;
	
			default:
				System.out.println("Inexistent FileDrive");
			break;
		}
		
		return fileDrive;
	}
	
	/**
	 * By passing a FileGenerator and FileLoader object it'll
	 * instance a FileDrive and return it.
	 * 
	 * @param <T> is the method Generic assignment
	 * @param fileGenerator is a FileGenerator type Generic
	 * @param fileLoader is a FileLoader type Generic
	 * @return FileDrive object
	 */
	private static <T> FileDrive<T> 
	getFileDrive(FileGenerator<T> fileGenerator, FileLoader<T> fileLoader){
		return new FileDrive<T>(fileGenerator, fileLoader);
	}
}
