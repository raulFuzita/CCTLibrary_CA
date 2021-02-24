package com.raul.libraryproject.util.fileloader.loaders;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.fileloader.FileLoader;

/**
 * <p>Class ObjectFileLoader extends abstract class FileLoader.
 * This class loads an serialized object file 
 * that only contains serialized Object.
 * The generic type is assigned by Object type.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class ObjectFileLoader extends FileLoader<Object> {

	/**
	 * Uses internally a line separator to split the data into a String array.
	 * The String array is traversal and each data is stored in a List.
	 * 
	 * @return {@code List<Object>}
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	@Override
	protected List<Object> loadContent(String content) throws Exception {
		String[] linesOfObjects = content.split(System.lineSeparator());
		List<Object> list = new LinkedList<>();
		for (String objectLine : linesOfObjects) {
			// deserialize a serialized object
			list.add(deserialize(objectLine));
		}
		return list;
	}

	/**
	 * This method deserializes a serialized Object.
	 * 
	 * @return Object
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	protected Object 
	deserialize(String string) throws IOException, ClassNotFoundException {
		byte b[] = string.getBytes(); 
		ByteArrayInputStream bi = new ByteArrayInputStream(b);
	    ObjectInputStream si = new ObjectInputStream(bi);
		return si.readObject();
	}
}
