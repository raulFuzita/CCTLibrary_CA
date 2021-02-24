package com.raul.libraryproject.util.filegenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.processors.NullProcessor;
import com.raul.libraryproject.util.processors.Processor;

/**
 * <p>Class FileGenerator is an abstract class that implements Bridge design
 * pattern to generate a file. Its method generateFile will process the data
 * before generating a file. FileGenerator use a generic type to be able to 
 * generate any type of file of any type of data by using inheritance and
 * overriding method generateContent.
 * </p>
 * 
 * <p><b>Created at: </b>25/11/2020</p>
 * 
 * @param <T> is a Generic Type. It should be defined in the child class.
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 * 
 * @apiNote This class (FileGenerator) can't be instantiated. It must be
 * extended by a child class (concrete class).
 * Before using this class make sure to read the method documentation.
 *
 */
public abstract class FileGenerator<T> {
	
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
	 * It uses a FileOutputStream internally that requires a File instance
	 * to create a file. The instance of FileOutputStream will write in a file
	 * in format of an array of byte.
	 * generateFile method will call a processor before generating a file.
	 * If any processor is set a NullProcessor pattern is applied.
	 * NullProcessor doesn't change any byte. It only returns the array
	 * of byte.
	 * 
	 * @param data is a List interface type Generic.
	 * @param file is a File type to carry the file path.
	 * @throws Exception
	 * @throws IOException
	 */
	public final void
	generateFile(List<T> data, File file) throws Exception {
		
		/* generateContent is a hook method implemented by 
		 * its child class (concrete class).*/
		String content = generateContent(data);
		/* Since variable content is a String type it's possible to convert
		 * a String to array of bytes by invoking getBytes method.*/
		byte[] bytes = content.getBytes();
		/*
		 * Optional class checks if processor variable has an instance
		 * of Processor. If it doesn't and the variable is null
		 * Optional calls orElse to instantiates a NullProcessor class
		 * if the variable is null. Otherwise no changes are applied.
		 */
		Optional<Processor> optional = Optional.ofNullable(processor);
		// NullProcessor applies no changes to the bytes.
		bytes = (optional.orElse(new NullProcessor())).process(bytes);
		/*
		 * FileOutputStream inherits FileWriter. This instance will
		 * create a file at a given path.
		 */
		FileOutputStream fos = new FileOutputStream(file);
		// write method will store bytes in a file.
		fos.write(bytes);
		fos.close(); // Close instance.
	}
	
	/**
	 * This method is abstract in its parent. It must be overridden by
	 * the child class (concrete class). A List interface type String.
	 * The access modifier key word is protected. It works in its package.
	 * It might throw an exception. Keep that in mind and check out the 
	 * documentation of implementation method.
	 * 
	 * @param data is a List interface type Generic.
	 * @return String type formated according to the implementation
	 * logic.
	 * @throws Exception if anything goes wrong with generateContent method.
	 */
	protected abstract String generateContent(List<T> data) throws Exception;
}
