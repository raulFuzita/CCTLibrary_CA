package com.raul.libraryproject.util.fileloader.loaders;

import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.fileloader.FileLoader;

/**
 * <p>Class TextFileLoader extends abstract class FileLoader.
 * This class loads a plain file that only contains Strings.
 * The generic type is assigned by String type.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class TextFileLoader extends FileLoader<String> {

	/**
	 * Internally, a content type String is stored in a 
	 * List of Strings.
	 */
	@Override
	protected List<String> loadContent(String content) {
		List<String> list = new LinkedList<>();
		list.add(content);
		return list;
	}
	
}
