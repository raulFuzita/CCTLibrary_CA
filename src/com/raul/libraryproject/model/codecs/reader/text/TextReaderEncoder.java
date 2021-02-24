package com.raul.libraryproject.model.codecs.reader.text;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class TextBookEncoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Encoder class encodes from type Reader to String.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param Reader is to define the type object that is convert from
 * @param String is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class TextReaderEncoder implements Codec<Reader, String> {

	/**
	 * @param reader is a Reader type
	 * @return String object
	 */
	@Override
	public String encode(Reader reader) {
		return toText(reader);
	}

	/**
	 * @param readers is a Reader[]
	 * @return String[]
	 */
	@Override
	public String[] encode(Reader[] readers) {
		String[] stringArray = new String[readers.length];
		for (int i = 0; i < stringArray.length; i++)
			stringArray[i] = toText(readers[i]);
		return stringArray;
	}

	/**
	 * @param books is a {@code List<Reader>}
	 * @return {@code List<String>}
	 */
	@Override
	public List<String> encode(List<Reader> readers) {
		List<String> stringList = new LinkedList<>();
		for (Reader reader : readers)
			stringList.add(toText(reader));
		return stringList;
	}
	
	/**
	 * This method takes the Reader properties and 
	 * creates String data out of them.
	 * 
	 * @param reader is a Reader type.
	 * @return String object.
	 */
	private String toText(Reader reader) {
		String data = reader.getId() + ";" + reader.getName() 
					+ ";" + reader.getSurname() 
					+ ";" + reader.getBirthdate().toString() 
					+ ";" + reader.getAddress();
		return data;
	}

}
