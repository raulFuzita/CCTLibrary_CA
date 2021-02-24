package com.raul.libraryproject.util.fileloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.processors.NullProcessor;
import com.raul.libraryproject.util.processors.Processor;

/**
 * <p>Class FileLoader is an abstract class that implements Bridge design
 * pattern to loads a file. Its method loadFile will process the data
 * before loading a file. FileLoader use a generic type to be able to 
 * load any type of file of any type of data by using inheritance and
 * overriding method loadContent.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @param <T> is a Generic Type. It should be defined in the child class.
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 * 
 * @apiNote This class (FileLoader) can't be instantiated. It must be
 * extended by a child class (concrete class).
 * Before using this class make sure to read the method documentation.
 *
 */
public abstract class FileLoader<T> {

	// Processor interface 
	private Processor processor;

	/**
	 * This method assign a processor that implements
	 * com.raul.libraryproject.util.processor.Processor.
	 * 
	 * @param processor is an interface Processor
	 */
	public void setProcessor(Processor processor) {
		this.processor = processor;
	}
	
	/**
	 * This method can't be override. It is meant to work as Template Method.
	 * It uses a FileInputStream internally that requires a File instance
	 * to create a file. The instance of FileInputStream will read from a file
	 * in format of an array of byte.
	 * loadFile method will call a processor before loading a file.
	 * If any processor is set a NullProcessor pattern is applied.
	 * NullProcessor doesn't change any byte. It only returns the array
	 * of byte.
	 * 
	 * @param file
	 * @return {@code List<T>}
	 * @throws Exception
	 * @throws IOException
	 */
	public final List<T> loadFile(File file) throws Exception{
		/*
		 * FileInputStream inherits FileReader. This instance will
		 * load a file at a given path.
		 */
		FileInputStream fis = new FileInputStream(file);
		// Reads all bytes and stores it an array of byte.
		byte[] bytes = fis.readAllBytes();
		fis.close(); // Close instance.
		/*
		 * Optional class checks if processor variable has an instance
		 * of Processor. If it doesn't and the variable is null
		 * Optional calls orElse to instantiates a NullProcessor class
		 * if the variable is null. Otherwise no changes are applied.
		 */
		Optional<Processor> optional = Optional.ofNullable(processor);
		// NullProcessor applies no changes to the bytes.
		bytes = (optional.orElse(new NullProcessor())).process(bytes);
		// Converts an array of byte to String.
		String content = new String(bytes);
		/* loadContent is a hook method implemented by 
		 * its child class (concrete class).*/
		return loadContent(content);
	}
	
	/**
	 * This method is abstract in its parent. It must be overridden by
	 * the child class (concrete class).
	 * The access modifier key word is protected. It works in its package.
	 * It might throw an exception. Keep that in mind and check out the 
	 * documentation of implementation method.
	 * 
	 * @param content is a String type
	 * @return {@code List<T>}
	 * @throws Exception
	 * @throws IOException
	 */
	protected abstract List<T> loadContent(String content) throws Exception;
}
