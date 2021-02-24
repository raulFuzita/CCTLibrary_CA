package com.raul.libraryproject.model.codecs.reader.text;

import java.time.LocalDate;

import com.raul.libraryproject.converter.Codec;
import com.raul.libraryproject.model.reader.Reader;
import com.raul.libraryproject.util.datastructure.LinkedList;
import com.raul.libraryproject.util.datastructure.List;

/**
 * <p>class TextBookDecoder that implements Codec interface from package:
 * com.raul.libraryproject.converter.
 * Decoder class decode from type String to Reader.
 * </p>
 * 
 * <p><b>Created at: </b>27/11/2020</p>
 * 
 * @author Raul Macedo Fuzita
 * 
 * @param String is to define the type object that is convert from
 * @param Reader is to define the return type object 
 * 
 * @version 1.0.0
 *
 */
public class TextReaderDecoder implements Codec<String, Reader> {

	// Regular Expression that each valid data must match
	private 
	String pattern = "[0-9]+;(.*?)+;(.*?)+;(\\d{4}-\\d{2}-\\d{2})+;(.*?)+";
	
	/**
	 * @param string is a String object
	 * @return Reader
	 */
	@Override
	public Reader encode(String string) {
		return textTo(string);
	}

	/**
	 * @param strings is a String[].
	 * @return Reader[]
	 */
	@Override
	public Reader[] encode(String[] strings) {
		Reader[] readers = new Reader[strings.length];
		for (int i = 0; i < strings.length; i++)
			readers[i] = textTo(strings[i]);
		return readers;
	}

	/**
	 * @param strings is a {@code List<String>} object.
	 * @return {@code List<Reader>}
	 */
	@Override
	public List<Reader> encode(List<String> strings) {
		List<Reader> readers = new LinkedList<>();
		for (String string : strings) {
			String[] splitedString = string.split("\n");
			for (int i = 0; i < splitedString.length; i++) {
				Reader reader = textTo(splitedString[i]);
				if (reader != null)
					readers.add(reader);
			}
		}
		return readers;
	}
	
	/**
	 * This method convert Text data to primitive or String object.
	 * Then the values are used to instantiate a Reader object.
	 * 
	 * @param string is a String object
	 * @return Reader
	 */
	private Reader textTo(String string) {
		
		if (string.matches(pattern)) {
			String[] data = string.split(";");
			long id = Long.parseLong(data[0]);
			String name = data[1];
			String surname = data[2];
			LocalDate birthdate = LocalDate.parse(data[3]);
			String address = data[4];
			
			Reader reader = new Reader.Builder(id)
					.setName(name)
					.setSurname(surname)
					.setBirthdate(birthdate)
					.setAddress(address).build();
			
			return reader;
		}
		return null;
	}

}
