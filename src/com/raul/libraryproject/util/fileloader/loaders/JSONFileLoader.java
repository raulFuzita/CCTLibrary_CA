package com.raul.libraryproject.util.fileloader.loaders;

import org.json.JSONObject;

import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.fileloader.FileLoader;

/**
 * <p>Class JSONFileLoader extends abstract class FileLoader.
 * This class loads a JSON file.
 * The generic type is assigned to JSONObject type from package
 * org.json.JSONObject.
 * 
 * JSONObject package is available at:
 * {@link <a href="https://mvnrepository.com/artifact/org.json/json">
 * MVN Repository
 * </a>}
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class JSONFileLoader extends FileLoader<JSONObject> {

	/**
	 * The whole content of a String is stored in a JSONObject object
	 * and then stored in a List of JSONObject.
	 * 
	 * <p>
	 * JSONObject package is available at:
	 * {@link <a href="https://mvnrepository.com/artifact/org.json/json">
	 * MVN Repository
	 * </a>}
	 * </p>
	 */
	@Override
	protected List<JSONObject> loadContent(String content) throws Exception {
		JSONObject jsonObject = new JSONObject(content);
		List<JSONObject> list = new LinkedList<>();
		list.add(jsonObject);
		return list;
	}

}
