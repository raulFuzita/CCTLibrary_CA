package com.raul.libraryproject.util.filegenerator.generators;


import org.json.JSONArray;
import org.json.JSONObject;

import com.raul.libraryproject.util.datastructure.List;
import com.raul.libraryproject.util.filegenerator.FileGenerator;
// https://repo1.maven.org/maven2/org/json/json/20201115/
/**
 * <p>Class JSONFileGenerator extends abstract class FileGenerator.
 * This class generates a JSON file.
 * The generic type is assigned to JSONObject type from package
 * org.json.JSONObject.
 * 
 * JSONObject package is available at:
 * {@link <a href="https://mvnrepository.com/artifact/org.json/json">
 * MVN Repository
 * </a>}
 * </p>
 * 
 * <p><b>Created at: </b>25/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @version 1.0.0
 *
 */
public class JSONFileGenerator extends FileGenerator<JSONObject> {

	// This is used to define a JSON object root name
	private String rootObject = "object";
	
	/**
	 * Returns a String with the default JSON object root name.
	 * @return String
	 */
	public String getRootObject() {
		return rootObject;
	}

	/**
	 * This method allows to change the name of the JSON object root name.
	 * The characters allowed are: a-z A-Z 0-9 and underscore.
	 * If the input doesn't match the rule the JSON object root name
	 * will remain the default or last valid one.
	 * 
	 * @param rootObject is a String type.
	 * @return boolean if the change is applied. Otherwise false.
	 */
	public boolean setRootObject(String rootObject) {
		if (rootObject.matches("[a-zA-Z0-9_]+")) {
			this.rootObject = rootObject;
			return true;
		}
		return false;
	}



	/**
	 * This method requires a List interface type JSONObject.
	 * JSONObject belongs to the package:
	 * org.json.simple.JSONObject.
	 * 
	 * <p>
	 * JSONObject package is available at:
	 * {@link <a href="https://mvnrepository.com/artifact/org.json/json">
	 * MVN Repository
	 * </a>}
	 * </p>
	 * 
	 * 
	 * org.json Introduction is available at:
	 * <p>
	 * {@link <a href="https://www.baeldung.com/java-org-json">
	 * Introduction to JSON-Java
	 * </a>}
	 * <br>
	 * {@link <a href="https://stackoverflow.com/questions/40455305/parsing-json-in-java-using-org-json">
	 * Parsing JSON in Java Using org.json
	 * </a>}
	 * <br>
	 * {@link <a href="https://stleary.github.io/JSON-java/org/json/JSONArray.html">
	 * Class JSONArray
	 * </a>}
	 * </p>
	 * 
	 */
	@Override
	protected String generateContent(List<JSONObject> jsonObjects) {
		/**
		 * Since String is an immutable object a StringBuilder is used
		 * to append each new data.
		 */
		StringBuilder fileBuilder = new StringBuilder();
		// It'll create a root JSON object
		JSONObject jsonObject = new JSONObject();
		// For more than one JSON Object a JSON array is used
		JSONArray jsonArray = new JSONArray();
		
		/*
		 * A For-each (enhanced-for) statement is used to traversal the
		 * List items.
		 */
		for (JSONObject object : jsonObjects)
			jsonArray.put(object);
		/*
		 * After all objects from the List be stored in a JSON array then
		 * the array is stored in the root JSON object.
		 */
		jsonObject.put(rootObject, jsonArray);
		/*
		 * To show "pretty" a term to store or print a JSON data with
		 * indentation.
		 */
		fileBuilder.append(jsonObject.toString(4));
		// invokes toString method to convert a StringBuilder data to a String.
		return fileBuilder.toString();
	}

}
