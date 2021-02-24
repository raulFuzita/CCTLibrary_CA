package com.raul.libraryproject.model.codecs.reader.json;

import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class JSONReaderDecoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Decoder class decode from type JSONObject to Reader.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param JSONObject is to define the type object that is convert from
 * @param Reader is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class JSONReaderDecoder implements Codec<JSONObject, Reader> {
	// Sets the object root for a JSON file.
	private String rootObject = "object";
	// Simple constructor
	public JSONReaderDecoder() {}
	
	/**
	 * This constructor sets the root object name.
	 * If the argument doesn't match the requirements
	 * the value won't change and the value will
	 * remain the default one.
	 * @param rootObject is a String object.
	 */
	public JSONReaderDecoder(String rootObject) {
		super();
		setRootObject(rootObject);
	}

	/**
	 * This method returns root object name.
	 * @return rootObject that is a String object.
	 */
	public String getRootObject() {
		return rootObject;
	}
	
	/**
	 * The argument parameter must match the following rules:
	 * Valid range: a-z A-Z 0-9 and underscore.
	 * If the argument doean't match the rule the root object name
	 * will remain the default one or the previous valid one.
	 * 
	 * @param rootObject is a String object.
	 * @return true if the change has been applied. Otherwise false.
	 */
	public boolean setRootObject(String rootObject) {
		if (rootObject.matches("[a-zA-Z0-9_]+")) {
			this.rootObject = rootObject;
			return true;
		}
		return false;
	}

	/**
	 * @param jsonObj is a JSONObject object
	 * @return Reader
	 */
	@Override
	public Reader encode(JSONObject jsonObj) {
		JSONArray jsonArray = jsonObj.getJSONArray(rootObject);
		if (jsonArray.length() <= 0)
			return null;
		return JSONTo((JSONObject) jsonArray.get(0));
	}

	/**
	 * @param jsonObjs is a JSONObject[].
	 * @return Reader[]
	 */
	@Override
	public Reader[] encode(JSONObject[] jsonObjs) {
		Reader[] readers = new Reader[jsonObjs.length];
		for (int i = 0; i < jsonObjs.length; i++) {
			JSONArray jsonArray = jsonObjs[i].getJSONArray(rootObject);
			if (jsonArray.length() <= 0)
				continue;
			readers[i] = JSONTo((JSONObject) jsonArray.get(i));
		}
		return readers;
	}

	/**
	 * @param jsonObjs is a {@code List<JSONObject>} object.
	 * @return {@code List<Reader>}
	 */
	@Override
	public List<Reader> encode(List<JSONObject> jsonObjs) {
		List<Reader> readers = new LinkedList<>();
		for (JSONObject jsonObj : jsonObjs) {
			JSONArray jsonArray = jsonObj.getJSONArray(rootObject);
			int jsonArraySize = jsonArray.length();
			for (int i = 0; i < jsonArraySize; i++) {
				Reader reader = JSONTo((JSONObject) jsonArray.get(i));
				readers.add(reader);
			}
		}
		return readers;
	}
	
	/**
	 * This method convert JSON data to primitive or String object.
	 * Then the values are used to instantiate a Reader object.
	 * 
	 * @param jsonObj is a JSONObject object
	 * @return Reader
	 */
	protected Reader JSONTo(JSONObject jsonObj) {
		
		long id = Long.parseLong(jsonObj.get("id").toString());
		String name = jsonObj.get("name").toString();
		String surname = jsonObj.get("surname").toString();
		String date = jsonObj.get("birthdate").toString();
		LocalDate birthdate = LocalDate.parse(date);
		String address = jsonObj.get("address").toString();
		
		Reader reader = new Reader.Builder(id)
				.setName(name)
				.setSurname(surname)
				.setBirthdate(birthdate)
				.setAddress(address).build();
		
		return reader;
	}
}
