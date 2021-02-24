package com.raul.libraryproject.util.filegenerator.generators;

import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.filegenerator.FileGenerator;

/**
 * <p>Class TextFileGenerator extends abstract class FileGenerator.
 * This class generates a plain file that only contains Strings.
 * The generic type is assigned by String type.
 * </p>
 * 
 * <p><b>Created at: </b>25/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class TextFileGenerator extends FileGenerator<String> {

	/**
	 * This method requires a List interface type String.
	 */
	@Override
	protected String generateContent(List<String> data) {
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
		for (String element : data) {
			fileBuilder.append(element + "\n");
		}
			
		// invokes toString method to convert a StringBuilder data to a String.
		return fileBuilder.toString();
	}
}
