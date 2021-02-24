package com.raul.libraryproject.util.filegenerator.generators;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.filegenerator.FileGenerator;

/**
 * <p>Class ObjectFileGenerator extends abstract class FileGenerator.
 * This class generates an Object file.
 * The generic type is assigned to Object type.
 * </p>
 * 
 * <p><b>Created at: </b>25/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class ObjectFileGenerator extends FileGenerator<Object> {

	/**
	 * This method requires a List interface type JSONObject.
	 * If there is more than one object a line separator
	 * is added to it.
	 * 
	 * The code is based on the article at:
	 * {@link <a href="https://stackoverflow.com/questions/8887197/reliably-convert-any-object-to-string-and-then-back-again">
	 * Stackoverflow - Reliably convert any object to String and then back again
	 * </a>}
	 * 
	 */
	@Override
	protected String generateContent(List<Object> data) throws IOException {
		/**
		 * Since String is an immutable object a StringBuilder is used
		 * to append each new data.
		 */
		StringBuilder fileBuilder = new StringBuilder();
		/*
		 * A For-each (enhanced-for) statement is used to traversal the
		 * List items.
		 * For each element a line separator is added.
		 */
		for (Object element : data)
			fileBuilder.append(serialize(element) + System.lineSeparator());
		// invokes toString method to convert a StringBuilder data to a String.
		return fileBuilder.toString();
	}
	
	/**
	 * Serializes any object and convert it to String.
	 * An object is convert to an array of byte and than
	 * it is converted to a String.
	 * 
	 * @param object is type of Object.
	 * @return String
	 * @throws IOException
	 */
	protected String serialize(Object object) throws IOException {

		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream so = new ObjectOutputStream(bo);
		// converts the object to an array of byte.
		so.writeObject(object);
		so.flush();
		return bo.toString();

	}
}
