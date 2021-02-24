package com.raul.libraryproject.model.codecs.reader.json;

import org.json.JSONObject;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class JSONBookEncoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Encoder class encodes from type Reader to JSONObject.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param Reader is to define the type object that is convert from
 * @param JSONObject is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class JSONReaderEncoder implements Codec<Reader, JSONObject> {

	/**
	 * @param reader is a Reader type
	 * @return JSONObject object
	 */
	@Override
	public JSONObject encode(Reader reader) {
		return toJSON(reader);
	}

	/**
	 * @param readers is a Reader[]
	 * @return JSONObject[]
	 */
	@Override
	public JSONObject[] encode(Reader[] readers) {
		JSONObject[] jsonObj = new JSONObject[readers.length];
		for (int i = 0; i < readers.length; i++)
			jsonObj[i] = toJSON(readers[i]);
		return jsonObj;
	}

	/**
	 * @param readers is a {@code List<Reader>}
	 * @return {@code List<JSONObject>}
	 */
	@Override
	public List<JSONObject> encode(List<Reader> readers) {
		List<JSONObject> jsonObjList = new LinkedList<>();
		for (Reader reader : readers)
			jsonObjList.add(toJSON(reader));
		return jsonObjList;
	}

	/**
	 * This method takes the Reader properties and 
	 * creates JSON data out of them.
	 * 
	 * @param book is a Reader type.
	 * @return JSONObject object.
	 */
	private JSONObject toJSON(Reader book) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", book.getId());
		jsonObj.put("name", book.getName());
		jsonObj.put("surname", book.getSurname());
		jsonObj.put("birthdate", book.getBirthdate());
		jsonObj.put("address", book.getAddress());
		return jsonObj;
	}
}
